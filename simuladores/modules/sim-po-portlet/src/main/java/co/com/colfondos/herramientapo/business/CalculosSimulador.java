package co.com.colfondos.herramientapo.business;

import co.com.colfondos.herramientapo.constants.BrandsKeys;
import co.com.colfondos.herramientapo.constants.PortfolioKeys;
import co.com.colfondos.herramientapo.model.PensionFund;
import co.com.colfondos.herramientapo.model.PensionParameters;
import co.com.colfondos.herramientapo.model.Person;
import co.com.colfondos.herramientapo.model.SimulatorInfo;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static co.com.colfondos.herramientapo.constants.BrandsKeys.*;
import static co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys.DATES;
import static co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys.GLOBAL_PACKAGE_LOGGER;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;

public class CalculosSimulador {

    private static Log LOGGER = LogFactoryUtil.getLog(GLOBAL_PACKAGE_LOGGER);

    /**
     * This method calculates the values of a required simulation depending on input parameters
     * @param pensionParameters object of type {@link PensionParameters}
     * @param person object of type {@link Person}
     * @param pensionFund object of type {@link PensionFund}
     * @param simulatorInfo object of type {@link SimulatorInfo}
     * @return a {@link Map} with results
     */
    public static Map<String, List<Object>> simulationValues(PensionParameters pensionParameters, Person person,
                                                                    PensionFund pensionFund, SimulatorInfo simulatorInfo) {

        List<Object> riskColfList = new ArrayList<>();
        List<Object> riskPorvList = new ArrayList<>();
        List<Object> riskProtList = new ArrayList<>();
        List<Object> riskOldList = new ArrayList<>();
        List<Object> portfolioList = new ArrayList<>();
        List<Object> datesList = new ArrayList<>();
        List<Object> decisionMonthsList = new ArrayList<>();

        BrandsKeys.PORTFOLIO = pensionFund.getName() + " " + pensionFund.getPortfolio();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/yyyy");

        String projectionType = pensionParameters.getProjectionType();

        Map<String, Double> rentRiskMap = null;
        Map<String, Double> rentConsMap = null;
        Map<String, Double> rentModMap  = null;

        try {
            rentRiskMap = pensionParameters
                    .getRentRisk()
                    .get(projectionType);
            rentConsMap = pensionParameters
                    .getRentCons()
                    .get(projectionType);
            rentModMap = pensionParameters
                    .getRentMod()
                    .get(projectionType);
        } catch (NullPointerException e) {
            LOGGER.error("Error: Null maps on rent", e);
        }

        if (rentRiskMap == null || rentConsMap == null || rentModMap == null) {
            return null;
        }

        /* Dates variables */
        final LocalDate beginDate = simulatorInfo.getBeginDate();
        LocalDate beginDateTmp = beginDate;
        LocalDate presentDate = beginDate;                                              // Current date
        LocalDate tmpDate = beginDate.minus(1, MONTHS);                 // 1 month before begin date
        LocalDate historicalDate = tmpDate.minus(5, ChronoUnit.YEARS);  // 5 years before tmp date

        /* Days variables */
        long monthsToPension = MONTHS.between(beginDate, simulatorInfo.getEndDate());


        long elapsedDays;

        /* Ages variables */
        final int personAge = person.getAge();
        final int pensionAge = pensionParameters.getPensionAge();
        final int edgeAge = pensionAge - 5;
        int currentAge;

        final int decisionMonths = pensionFund.getDecisionMonths();

        /* Formulas values */
        double exponentiationValue;
        double percent = 0;
        double monthValue = pensionParameters.getMonthValue();
        double colfondosValue;

        //LOGGER.debug("Months to pension: " + monthsToPension);
        //LOGGER.debug("Aporte: " + ((monthsToPension-1)*monthValue + person.getBalance()));
        Object contributions = (monthsToPension-1)*monthValue + person.getBalance();

        /* Rent percentages Mayor Riesgo */
        double riskRentColf = rentRiskMap.get(BrandsKeys.COLFONDOS);
        double riskRentPorv = rentRiskMap.get(BrandsKeys.PORVENIR);
        double riskRentProt = rentRiskMap.get(BrandsKeys.PROTECCION);
        double riskRentOld = rentRiskMap.get(BrandsKeys.OLD_MUTUAL);

        /* Rent percentages Conservador */
        double consRentColf = rentConsMap.get(BrandsKeys.COLFONDOS);
        double consRentPorv = rentConsMap.get(BrandsKeys.PORVENIR);
        double consRentProt = rentConsMap.get(BrandsKeys.PROTECCION);
        double consRentOld = rentConsMap.get(BrandsKeys.OLD_MUTUAL);

        /* Rent percentages Moderado */
        double moderateRent = rentModMap.get(pensionFund.getName());
        final int OPT_PORTFOLIO = BrandsKeys.getBrandNumber(pensionFund.getName());

        /* Temporal variables to use in loop calculations */
        double []tmpRiskProjection = new double[4];
        double []tmpConsProjection = new double[4];
        double tmpModProjection;
        double []tmpRiskConv = new double[4];
        double tmpModConv;
        double []balance = {person.getBalance(), person.getBalance(), person.getBalance(), person.getBalance(), person.getBalance()};

        /* Calculates projection percentage for every month until pension date */
        for (int i = 0; i < monthsToPension; i++) {
            /* Auxiliary values */
            elapsedDays = ChronoUnit.DAYS.between(historicalDate, tmpDate );
            exponentiationValue = ((double) elapsedDays / 365) * (1 / (double)elapsedDays) * ChronoUnit.DAYS.between(tmpDate, beginDateTmp);

            /* ********  ************************************************
             ***************** Calculate Projection percentages *********
             ************************************************************/
            /* Projection values for Mayor Riesgo */
            tmpRiskProjection[N_COLFONDOS] = Math.pow(1 + riskRentColf, exponentiationValue) - 1;
            tmpRiskProjection[N_PORVENIR] = Math.pow(1 + riskRentPorv, exponentiationValue) - 1;
            tmpRiskProjection[N_PROTECCION] = Math.pow(1 + riskRentProt, exponentiationValue) - 1;
            tmpRiskProjection[N_OLD_MUTUAL] = Math.pow(1 + riskRentOld, exponentiationValue) - 1;

            /* Projection values for Conservative */
            tmpConsProjection[N_COLFONDOS] = Math.pow(1 + consRentColf, exponentiationValue) - 1;
            tmpConsProjection[N_PORVENIR] = Math.pow(1 + consRentPorv, exponentiationValue) - 1;
            tmpConsProjection[N_PROTECCION] = Math.pow(1 + consRentProt, exponentiationValue) - 1;
            tmpConsProjection[N_OLD_MUTUAL] = Math.pow(1 + consRentOld, exponentiationValue) - 1;

            /* ********  ************************************************
             ***************** Calculate Convergence percentages ********
             ************************************************************/
            currentAge = (int) YEARS.between(beginDate, presentDate) + personAge;
            if (currentAge >= edgeAge) {
                percent = (double)(currentAge - edgeAge + 1) / 5;
            }

            /* Calculate Mayor Riesgo convergence */
            tmpRiskConv[N_COLFONDOS] = tmpRiskProjection[N_COLFONDOS]*(1 - percent) + tmpConsProjection[N_COLFONDOS] * percent;
            tmpRiskConv[N_PORVENIR] = tmpRiskProjection[N_PORVENIR]*(1 - percent) + tmpConsProjection[N_PORVENIR] * percent;
            tmpRiskConv[N_PROTECCION] = tmpRiskProjection[N_PROTECCION]*(1 - percent) + tmpConsProjection[N_PROTECCION] * percent;
            tmpRiskConv[N_OLD_MUTUAL] = tmpRiskProjection[N_OLD_MUTUAL]*(1 - percent) + tmpConsProjection[N_OLD_MUTUAL] * percent;

            /* Calculate Moderado convergence if that portfolio was selected */
            if (pensionFund.getPortfolio().equalsIgnoreCase(PortfolioKeys.MODERATE)) {
                /* Projection values for Moderado */
                tmpModProjection = Math.pow(1 + moderateRent, exponentiationValue) - 1 ;

                /* Calculate Moderado convergence */
                tmpModConv = tmpModProjection * (1 - percent) + tmpConsProjection[OPT_PORTFOLIO] * percent;

            } else {
                tmpModConv = tmpConsProjection[OPT_PORTFOLIO];
            }

            /* ********  ************************************************
             ***************** Calculate Results of simulation ********
             ************************************************************/
            /* First value is not calculated */
            if (i > 0) {
                balance[N_COLFONDOS] = balance[N_COLFONDOS] * (1 + tmpRiskConv[N_COLFONDOS]) + monthValue;
                balance[N_PORVENIR] = balance[N_PORVENIR] * (1 + tmpRiskConv[N_PORVENIR]) + monthValue;
                balance[N_PROTECCION] = balance[N_PROTECCION] * (1 + tmpRiskConv[N_PROTECCION]) + monthValue;
                balance[N_OLD_MUTUAL] = balance[N_OLD_MUTUAL] * (1 + tmpRiskConv[N_OLD_MUTUAL]) + monthValue;
                balance[N_PORTFOLIO] = balance[N_PORTFOLIO] * (1 + tmpModConv) + monthValue;
            }

            riskColfList.add(balance[N_COLFONDOS]);
            riskPorvList.add(balance[N_PORVENIR]);
            riskProtList.add(balance[N_PROTECCION]);
            riskOldList.add(balance[N_OLD_MUTUAL]);

            portfolioList.add(balance[N_PORTFOLIO]);

            datesList.add(formatter2.format(presentDate));

            /* Updates loop variables */
            beginDateTmp = beginDateTmp.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            presentDate = presentDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            tmpDate = tmpDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            historicalDate = historicalDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

            //System.out.println("p_age: " + currentAge + "\tpercent: " + percent);
            //System.out.println("i:"+ (i+1) + "colfondos conv:" + tmpRiskConv[N_COLFONDOS]);
            //System.out.println("i:"+ (i+1) + " colfondos proj:" + tmpRiskProjection[N_COLFONDOS]);
            //System.out.println("i:"+ (i+1) + " balance:" + balance[N_COLFONDOS]);
            /*
            System.out.println("colf\tporv\tprot\told_mutual");
            System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\n",
                    balance[N_COLFONDOS],
                    balance[N_PORVENIR],
                    balance[N_PROTECCION],
                    balance[N_OLD_MUTUAL]
            );
            */

        }
        Map<String, List<Object>> tmpRiskMap = new HashMap<>();

        /* Obtain Colfondos value to substract */
        colfondosValue = (double) riskColfList.get(decisionMonths);

        /* Calculate AFP values depending on colfondos value */
        decisionMonthsList.add(colfondosValue - (double)riskPorvList.get(decisionMonths));
        decisionMonthsList.add(colfondosValue - (double)riskProtList.get(decisionMonths));
        decisionMonthsList.add(colfondosValue - (double)riskOldList.get(decisionMonths));
        decisionMonthsList.add(colfondosValue - (double)portfolioList.get(decisionMonths));

        tmpRiskMap.put(BrandsKeys.COLFONDOS, riskColfList);
        tmpRiskMap.put(BrandsKeys.PORVENIR, riskPorvList);
        tmpRiskMap.put(BrandsKeys.PROTECCION, riskProtList);
        tmpRiskMap.put(BrandsKeys.OLD_MUTUAL, riskOldList);
        tmpRiskMap.put(BrandsKeys.PORTFOLIO, portfolioList);
        tmpRiskMap.put(DATES, datesList);
        tmpRiskMap.put("data_months", decisionMonthsList);
        tmpRiskMap.put("contributions", Collections.singletonList(contributions));

        return tmpRiskMap;
    }
}
