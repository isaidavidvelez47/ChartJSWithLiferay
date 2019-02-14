package co.com.colfondos.herramientapo.business;

import co.com.colfondos.herramientapo.constants.BrandsKeys;
import co.com.colfondos.herramientapo.constants.ProjectionKeys;
import co.com.colfondos.herramientapo.delegate.CacheDelegate;
import co.com.colfondos.herramientapo.model.PensionFund;
import co.com.colfondos.herramientapo.model.PensionParameters;
import co.com.colfondos.herramientapo.model.Person;
import co.com.colfondos.herramientapo.model.SimulatorInfo;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.Edades;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.RentaPortafolioVO;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.SalarioMinimo;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys.DATES;
import static co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys.GLOBAL_PACKAGE_LOGGER;

/**
 * This class implements <code>IPensionParametersDAO</code> interface
 * defining all methods required to make the simulator work
 *
 * @author      Pragma S.A. - Guillermo Grajales
 * @version     1.0
 * @see         IPensionParametersDAO
 */
public class PensionParametersDAO implements IPensionParametersDAO {

    private static final Log LOGGER = LogFactoryUtil.getLog(GLOBAL_PACKAGE_LOGGER);

    private int MALE_PENSION_AGE;
    private int FEMALE_PENSION_AGE;
    private double MIN_SALARY;

    private Configuration properties;

    private PensionParameters pensionParameters = new PensionParameters();
    private SimulatorInfo simulatorInfo = new SimulatorInfo();

    /**
     * Constructor
     * Used in this case to init some attributes
     */
    public PensionParametersDAO(Configuration properties) {
        this.properties = properties;

    }

    public int getMALE_PENSION_AGE() {
        return MALE_PENSION_AGE;
    }

    public int getFEMALE_PENSION_AGE() {
        return FEMALE_PENSION_AGE;
    }

    public double getMIN_SALARY() {
        return MIN_SALARY;
    }

    /**
     * Method to establish simulator parameters based on input params
     * @param person object of type {@link Person} with relevant values
     * @param projectionType object of type {@link String}
     */
    @Override
    public void establishParameters(Person person, String projectionType) {
        int yearsToPension;
        // Establish pension age
        if (person.getGender().equalsIgnoreCase("Hombre")) {
            pensionParameters.setPensionAge(MALE_PENSION_AGE);
        } else {
            pensionParameters.setPensionAge(FEMALE_PENSION_AGE);
        }

        // Establish regulatory percentage for pension
        if (person.getSalary() > 4 * MIN_SALARY) {
            pensionParameters.setRegulatoryPension(0.115);  // Regulatory pension 11.5%
        } else {
            pensionParameters.setRegulatoryPension(0.13);   // Regulatory pension 13%
        }

        // Establish month value
        pensionParameters.setMonthValue(person.getSalary() * pensionParameters.getRegulatoryPension());

        // Establish projection type
        pensionParameters.setProjectionType(projectionType.toLowerCase());

        // Establish begin and end date
        yearsToPension = pensionParameters.getPensionAge() - person.getAge();
        simulatorInfo.setBeginDate(LocalDate.now());  // Example date
        simulatorInfo.setEndDate(LocalDate.now().plusYears(yearsToPension).with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * Method to determine whether rentability values have been set or not
     * @return a boolean indicating the result
     */
    @Override
    public boolean hasRentabilityValues() {
        return  (pensionParameters.getRentRisk() != null &&
                pensionParameters.getRentCons() != null &&
                pensionParameters.getRentMod() != null);
    }

    /**
     * This method read pension age (male and female) and min salary from SOAP Service
     * @param defaultMaleAge: type {@link Integer}
     * @param defaultFemaleAge: type {@link Integer}
     * @param defaultMinSalary: type {@link Double}
     *                        All parameters are used in case no values from Service or properties are read
     * @return boolean
     *          true: if parameters are successfully read
     *          false: otherwise
     */
    @Override
    public boolean readPensionParameters(int defaultMaleAge, int defaultFemaleAge, double defaultMinSalary) {
        boolean noPropertiesValues = true;

        List<SalarioMinimo> minSalaryList = CacheDelegate.getInstance().getSalarioMinimo();
        List<Edades> edadesList = CacheDelegate.getInstance().getEdadesPension();

        boolean isSalarySet = false;

        try {
            /* Storing pensiong age */
            if (edadesList == null) {
                LOGGER.warn("No se pudo obtener las edades de pension desde el servicio.\n Usando valores por defecto");
                MALE_PENSION_AGE = Integer.parseInt(properties.get("pension-age.male"));
                FEMALE_PENSION_AGE = Integer.parseInt(properties.get("pension-age.female"));

                noPropertiesValues = false;
            } else {
                Edades agesPension = edadesList.get(0);

                MALE_PENSION_AGE = agesPension.getEdadJubilacionHombre();
                FEMALE_PENSION_AGE = agesPension.getEdadJubilacionMujer();
            }

            /* Storing min salary */
            if (minSalaryList == null) {
                LOGGER.warn("No se pudo obtener el salario m\u00ednimo desde el servicio.\nUsando valor por defecto");
                MIN_SALARY = Double.parseDouble(properties.get("min-salary"));

                noPropertiesValues = false;
            } else {
                double tmpSalary = 0;

                for(SalarioMinimo minSalary: minSalaryList) {
                    if ( minSalary.getAhno().equals(String.valueOf(LocalDate.now().getYear())) ) {
                        tmpSalary = minSalary.getValor();
                        isSalarySet = true;
                        break;
                    }
                }

                if (isSalarySet) {
                    LOGGER.info("Salario minimo leido correctamente desde el WS");
                    MIN_SALARY = tmpSalary;
                }
                else {
                    LOGGER.warn("No se encontró un salario mínimo para el año correspondiente.\nUsando valor por defecto");
                    MIN_SALARY = Double.parseDouble(properties.get("min-salary"));

                    noPropertiesValues = false;
                }
            }
        } catch (Exception e) {
            /* If exception occurs default values are loaded */
            MALE_PENSION_AGE = defaultMaleAge;
            FEMALE_PENSION_AGE = defaultFemaleAge;
            MIN_SALARY = defaultMinSalary;
            return false;
        }

        return noPropertiesValues;
    }

    /**
     * Method that obtains rentability values from SOAP service
     * implemented in colfondos MULE Bus
     */
    @Override
    public void setRentabilityValues(List<RentaPortafolioVO> rentaPortafolioVOList) {
        /* Store data coming from service */
        if (rentaPortafolioVOList != null) {
            LOGGER.info("Datos provenientes del servicio leidos");

            Map<String, Map<String, Double>> rentRiskValues = new HashMap<>();
            Map<String, Map<String, Double>> rentConsValues = new HashMap<>();
            Map<String, Map<String, Double>> rentModValues = new HashMap<>();
            Map<String, Double> tmpMap;

            RentaPortafolioVO riskRent;
            RentaPortafolioVO consRent;
            RentaPortafolioVO modRent;

            for(int i = 0, j = 0; i < rentaPortafolioVOList.size(); i+= 3, j++) {
                riskRent = rentaPortafolioVOList.get(i);
                consRent = rentaPortafolioVOList.get(i+1);
                modRent = rentaPortafolioVOList.get(i+2);

                /* Rent values Mayor Riesgo */
                tmpMap = new HashMap<>();

                tmpMap.put(BrandsKeys.COLFONDOS, riskRent.getColfondosRentaPortafolio());
                tmpMap.put(BrandsKeys.PORVENIR, riskRent.getPorvenirRentaPortafolio());
                tmpMap.put(BrandsKeys.PROTECCION, riskRent.getProteccionRentaPortafolio());
                tmpMap.put(BrandsKeys.OLD_MUTUAL, riskRent.getOldMutualRentaPortafolio());

                rentRiskValues.put( ProjectionKeys.getProjectionName(j), tmpMap );

                /* Rent values Conservador */
                tmpMap = new HashMap<>();

                tmpMap.put(BrandsKeys.COLFONDOS, consRent.getColfondosRentaPortafolio());
                tmpMap.put(BrandsKeys.PORVENIR, consRent.getPorvenirRentaPortafolio());
                tmpMap.put(BrandsKeys.PROTECCION, consRent.getProteccionRentaPortafolio());
                tmpMap.put(BrandsKeys.OLD_MUTUAL, consRent.getOldMutualRentaPortafolio());

                rentConsValues.put( ProjectionKeys.getProjectionName(j), tmpMap);

                /* Rent values Moderado */
                tmpMap = new HashMap<>();

                tmpMap.put(BrandsKeys.COLFONDOS, modRent.getColfondosRentaPortafolio());
                tmpMap.put(BrandsKeys.PORVENIR, modRent.getPorvenirRentaPortafolio());
                tmpMap.put(BrandsKeys.PROTECCION, modRent.getProteccionRentaPortafolio());
                tmpMap.put(BrandsKeys.OLD_MUTUAL, modRent.getOldMutualRentaPortafolio());

                rentModValues.put( ProjectionKeys.getProjectionName(j), tmpMap);
            }

            pensionParameters.setRentRisk(rentRiskValues);
            pensionParameters.setRentCons(rentConsValues);
            pensionParameters.setRentMod(rentModValues);
        }
    }

    /**
     * Method that calculates values to create graphs
     * @param pensionFund object of type {@link PensionFund} with AFP info
     * @param person object of type {@link Person} containing user info
     * @return a {@link Map} with results for every AFP (Colfondos - Porvenir - Proteccio -
     *                                                      Old Mutual - Old Portfolio)
     */
    @Override
    public Map<String, List<Object>> calculateResultValues(PensionFund pensionFund, Person person) {
        Map<String, List<Object>> dataMap;
        Map<String, List<Object>> tmpMap = new HashMap<>();

        List<Object> riskColfList;
        List<Object> riskPorvList;
        List<Object> riskProtList;
        List<Object> riskOldList;
        List<Object> portfolioList;
        List<Object> datesList;

        List<Object> newRiskColfList = new ArrayList<>();
        List<Object> newRiskPorvList = new ArrayList<>();
        List<Object> newRiskProtList = new ArrayList<>();
        List<Object> newRiskOldList = new ArrayList<>();
        List<Object> newPortfolioList = new ArrayList<>();
        List<Object> newDatesList = new ArrayList<>();

        dataMap = CalculosSimulador.simulationValues(pensionParameters, person, pensionFund, simulatorInfo);
        if (dataMap == null) {
            LOGGER.error("Error al realizar los calculos");
            return null;
        }

        riskColfList = dataMap.get(BrandsKeys.COLFONDOS);
        riskPorvList = dataMap.get(BrandsKeys.PORVENIR);
        riskProtList = dataMap.get(BrandsKeys.PROTECCION);
        riskOldList = dataMap.get(BrandsKeys.OLD_MUTUAL);
        portfolioList = dataMap.get(BrandsKeys.PORTFOLIO);
        datesList = dataMap.get(DATES);

        boolean savedLastValue = false;

        for (int i = 0; i < datesList.size(); i+= 12) {
            newRiskColfList.add(riskColfList.get(i));
            newRiskPorvList.add(riskPorvList.get(i));
            newRiskProtList.add(riskProtList.get(i));
            newRiskOldList.add(riskOldList.get(i));
            newPortfolioList.add(portfolioList.get(i));
            newDatesList.add(datesList.get(i));

            if (i == datesList.size() - 1 ) {
                savedLastValue = true;
            }
        }

        if (!savedLastValue) {
            int size = datesList.size()-1;
            newRiskColfList.add(riskColfList.get(size));
            newRiskPorvList.add(riskPorvList.get(size));
            newRiskProtList.add(riskProtList.get(size));
            newRiskOldList.add(riskOldList.get(size));
            newPortfolioList.add(portfolioList.get(size));
            newDatesList.add(datesList.get(size));
        }

        tmpMap.put(BrandsKeys.COLFONDOS, newRiskColfList);
        tmpMap.put(BrandsKeys.PORVENIR, newRiskPorvList);
        tmpMap.put(BrandsKeys.PROTECCION, newRiskProtList);
        tmpMap.put(BrandsKeys.OLD_MUTUAL, newRiskOldList);
        tmpMap.put(BrandsKeys.PORTFOLIO, newPortfolioList);
        tmpMap.put(DATES, newDatesList);
        tmpMap.put("data_months", dataMap.get("data_months"));
        tmpMap.put("contributions", dataMap.get("contributions"));

        return tmpMap;
    }
}

