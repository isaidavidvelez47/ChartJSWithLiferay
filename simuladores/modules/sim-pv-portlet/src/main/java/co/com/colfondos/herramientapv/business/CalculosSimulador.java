package co.com.colfondos.herramientapv.business;

import co.com.colfondos.herramientapv.model.BrandsEntity;
import co.com.colfondos.herramientapv.model.PortafoliosEntity;

import co.com.colfondos.tech.ws.cliente.funcionario.cache.delegate.FuncionarioDelegate;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.RentaPortafolioVO;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.time.LocalDate;
import java.util.*;

import static co.com.colfondos.herramientapv.constants.HerramientaPVPortletKeys.*;
import static co.com.colfondos.herramientapv.constants.PortfolioKeys.*;


public class CalculosSimulador {

    private static Log LOGGER = LogFactoryUtil.getLog(GLOBAL_PACKAGE_LOGGER);


    public Map<String, List<BrandsEntity>> getNormalValues(double salary, LocalDate cutDate, int portfolio) {
        Map<String, List<BrandsEntity>> results = new HashMap<>();
        List<PortafoliosEntity> rNNetaValues;
        List<BrandsEntity> subRNeta = new ArrayList<>();
        List<BrandsEntity> rNetaCustomList = new ArrayList<>();
        List<BrandsEntity> finalValuesList = new ArrayList<>();

        List<RentaPortafolioVO> wsDataList;

        // Uses delegate to obtain data from WS
        FuncionarioDelegate wsDelegate = new FuncionarioDelegate();

        // Obtain PV Rentabilities
        LOGGER.info("Reading PV Data...");
        try {
            wsDataList = wsDelegate.getRentaValues("PV", cutDate, salary);
        } catch (Exception e) {
            LOGGER.error("Error reading rentabilities");
            return null;
        }

        LOGGER.info("PV Data read correctly");

        /*
        /* Retrieves rentabilities from WS
        */
        for (int i = INIT_RENT; i < INIT_RENT + 35; i+= 5) {
            BrandsEntity tmpBrand = new BrandsEntity();

            tmpBrand.setColfondos(wsDataList.get(i + portfolio).getColfondosRentaPortafolio());
            tmpBrand.setProteccion(wsDataList.get(i + portfolio).getProteccionRentaPortafolio());
            tmpBrand.setPorvenir(wsDataList.get(i + portfolio).getPorvenirRentaPortafolio());
            tmpBrand.setOldMutual(wsDataList.get(i + portfolio).getOldMutualRentaPortafolio());

            subRNeta.add(tmpBrand);
        }

        for (int i = 0; i < subRNeta.size(); i++) {
            BrandsEntity tmpBrandPerc = subRNeta.get(i);
            BrandsEntity tmpValues = calculateRNCustom(tmpBrandPerc, i);

            rNetaCustomList.add(tmpValues);

            /* Final value salary */
            tmpValues = new BrandsEntity();
            tmpValues.setColfondos(formatDouble(salary + salary*tmpBrandPerc.getColfondos(), 0));
            tmpValues.setProteccion(formatDouble(salary + salary*tmpBrandPerc.getProteccion(), 0));
            tmpValues.setPorvenir(formatDouble(salary + salary*tmpBrandPerc.getPorvenir(), 0));
            tmpValues.setOldMutual(formatDouble(salary + salary*tmpBrandPerc.getOldMutual(), 0));

            finalValuesList.add(tmpValues);
        }

        /*
        * Get volatilities from retrieved data
        */
        List<BrandsEntity> volatilityList = getVolatilityValues(portfolio, wsDataList);

        /*
         * Get max draw down from retrieved data
         */
        List<BrandsEntity> drawDownList = getMaxDrawDowmValues(portfolio, wsDataList);

        //LOGGER.debug("Volatilities: " + volatilityList);

        results.put(RENTABILITIES_KEY, rNetaCustomList);
        results.put(FINAL_SALARY_KEY, finalValuesList);
        results.put(VOLATILITY_KEY, volatilityList);
        results.put(DRAW_DOWM_KEY, drawDownList);

        return results;
    }

    public Map<Integer, List<BrandsEntity>> getMixValues(double salary, LocalDate cutDate) {
        Map<Integer, List<BrandsEntity>> resultsMap = new HashMap<>();

        List<RentaPortafolioVO> wsDataList;
        BrandsEntity[][] tmpPortfolios = new BrandsEntity[5][MONTHS_VALUE.length - 2];

        // Uses delegate to obtain data from WS
        FuncionarioDelegate wsDelegate = new FuncionarioDelegate();

        // Obtain PV Rentabilities
        LOGGER.info("Reading PV Data...");
        try {
            wsDataList = wsDelegate.getRentaValues("PV", cutDate, salary);
        } catch (Exception e) {
            return null;
        }

        for (int i = 0, j = 0, lim = INIT_RENT + 10; lim < INIT_RENT + 35; lim++) {
            // i -> portfolios counter - limit 5
            // j -> months counter - limit 5

            // Tmp Obj RentPortalioVO
            RentaPortafolioVO tmpRenta = wsDataList.get(lim);

            // Sets Brands Entities
            tmpPortfolios[i][j] = new BrandsEntity(
                    tmpRenta.getColfondosRentaPortafolio(),
                    tmpRenta.getProteccionRentaPortafolio(),
                    tmpRenta.getPorvenirRentaPortafolio(),
                    tmpRenta.getOldMutualRentaPortafolio()
            );

            i++;

            // Every round of portfolios increase j and reset i
            if (i == 5) {
                i = 0;
                j++;
            }
        }

        resultsMap.put(TRADICIONAL, Arrays.asList(tmpPortfolios[TRADICIONAL]));
        resultsMap.put(DINAMICO, Arrays.asList(tmpPortfolios[DINAMICO]));
        resultsMap.put(ACCION_COL, Arrays.asList(tmpPortfolios[ACCION_COL]));
        resultsMap.put(RFI, Arrays.asList(tmpPortfolios[4]));   // Service delivers data with RFI as index 4
        resultsMap.put(RVI, Arrays.asList(tmpPortfolios[3]));   // and RVI as index 3

        return resultsMap;
    }

    private BrandsEntity calculateRNCustom(BrandsEntity subRNeta, int index) {
        /* Net Rentabilities */
        BrandsEntity tmpValues = new BrandsEntity();

        /* Calculate new net rentability values and format to percent */
        tmpValues.setColfondos(formatDouble(Math.pow(1 + subRNeta.getColfondos(), 12.0/MONTHS_VALUE[index]) - 1,true));
        tmpValues.setProteccion(formatDouble(Math.pow(1 + subRNeta.getProteccion(), 12.0/MONTHS_VALUE[index]) - 1,true));
        tmpValues.setPorvenir(formatDouble(Math.pow(1 + subRNeta.getPorvenir(), 12.0/MONTHS_VALUE[index]) - 1,true));
        tmpValues.setOldMutual(formatDouble(Math.pow(1 + subRNeta.getOldMutual(), 12.0/MONTHS_VALUE[index]) - 1,true));

        return tmpValues;
    }

    public List<BrandsEntity> getVolatilityValues(int portfolio, List<RentaPortafolioVO> wsData) {
        List<BrandsEntity> resultsList = new ArrayList<>();

        for (int i = INIT_VOLAT + 10; i < INIT_DROP_DOWN; i+=5) {       //Start counting to get data starting at month 12
            BrandsEntity tmpBrand = new BrandsEntity();

            tmpBrand.setColfondos(formatDouble(wsData.get(i+portfolio).getColfondosRentaPortafolio() * Math.sqrt(12), true));
            tmpBrand.setProteccion(formatDouble(wsData.get(i+portfolio).getProteccionRentaPortafolio() * Math.sqrt(12), true));
            tmpBrand.setPorvenir(formatDouble(wsData.get(i+portfolio).getPorvenirRentaPortafolio() * Math.sqrt(12), true));
            tmpBrand.setOldMutual(formatDouble(wsData.get(i+portfolio).getOldMutualRentaPortafolio() * Math.sqrt(12), true));

            /* Populate list */
            resultsList.add(tmpBrand);
        }

        return resultsList;
    }

    public List<BrandsEntity> getMaxDrawDowmValues(int portfolio, List<RentaPortafolioVO> wsData) {
        List<BrandsEntity> resultsList = new ArrayList<>();

        for (int i = INIT_DROP_DOWN + 10; i < INIT_RENT; i+=5) {       //Start counting to get data starting at month 12
            BrandsEntity tmpBrand = new BrandsEntity();

            tmpBrand.setColfondos(formatDouble(wsData.get(i+portfolio).getColfondosRentaPortafolio(), true));
            tmpBrand.setProteccion(formatDouble(wsData.get(i+portfolio).getProteccionRentaPortafolio(), true));
            tmpBrand.setPorvenir(formatDouble(wsData.get(i+portfolio).getPorvenirRentaPortafolio(), true));
            tmpBrand.setOldMutual(formatDouble(wsData.get(i+portfolio).getOldMutualRentaPortafolio(), true));

            /* Populate list */
            resultsList.add(tmpBrand);
        }

        return resultsList;
    }

    private static double formatDouble(double value, boolean mult) {
        return Double.parseDouble(String.format(Locale.US,"%.2f",value*100));
    }

    private static double formatDouble(double value) {
        return Double.parseDouble(String.format(Locale.US,"%.2f",value));
    }

    private static double formatDouble(double value, int decimals) {
        return Double.parseDouble(String.format(Locale.US,"%."+decimals+"f",value));
    }
}
