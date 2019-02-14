package co.com.colfondos.tech.ws.cliente.funcionario.cache.delegate;

import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.*;
import co.com.colfondos.tech.ws.cliente.funcionario.facade.ServiceFuncionarioWSFacade;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class is used as a delegate to access to
 * Web Services provided by Colfondos.
 * <p>
 * This class provides the methods to obtain
 * the different data coming from WS.
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.1.1
 */
public class FuncionarioDelegate {

    private static final String PROP_FILE_NAME = "content/config";

    private ServiceFuncionarioWSFacade serviceFuncionarioWSFacade;

    public FuncionarioDelegate() {
        init();
    }

    /**
     * This method initialises the field serviceFuncionarioWSFacade
     * with all parameters to make a connection to Web Service
     */
    private void init() {
        String WSDL_URI = "http://172.18.160.196:63110/Funcionario?wsdl";
        String QNAME_URI = "http://funcionario.business.colfondos.com.co/";

        Configuration properties = this.getProperties();

        if (properties != null) {
            WSDL_URI = properties.get("soap-service.wsdl-endpoint");
            QNAME_URI = properties.get("soap-service.qname");
        }

        try {
            URL urlFuncionario = new URL(WSDL_URI);
            final QName COLFONDOS_QNAME = new QName(QNAME_URI, "IServiceFuncionarioService");
            this.serviceFuncionarioWSFacade = new ServiceFuncionarioWSFacade("INTERNET",
                    urlFuncionario, COLFONDOS_QNAME);

        } catch (MalformedURLException e ) {
            e.printStackTrace();
        }
    }

    /**
     * This method obtains Rentability Values for PO Simulator
     * @param product: {@link String} indicating PO or PV
     * @return a {@link List<RentaPortafolioVO>}
     */
    public List<RentaPortafolioVO> getRentaValues(String product)
            throws Exception {

        ConsultarRentaPortafolioRequest rentaPortafolioRequest = new ConsultarRentaPortafolioRequest();
        ConsultarRentaPortafolioControlRequest requestParameters = new ConsultarRentaPortafolioControlRequest();
        requestParameters.setProducto(product);
        requestParameters.setCanal("INTERNET");
        requestParameters.setIdentificadorDeTransaccion(LocalDate.now().toString());

        rentaPortafolioRequest.setControl(requestParameters);

        return serviceFuncionarioWSFacade.consultarRentaValues(rentaPortafolioRequest);
    }

    /**
     * This method obtains rentabilities, volatilities and draw down for PV Simulator
     * @param product: {@link String} indicating PV
     * @param cutDate: {@link LocalDate} indicating cut date selected by user
     * @param salary: {@link Double} user's salary entered in simulator
     * @return a {@link List<RentaPortafolioVO>}
     */
    public List<RentaPortafolioVO> getRentaValues(String product, LocalDate cutDate, Double salary)
            throws Exception {

        ConsultarRentaPortafolioRequest rentaPortafolioRequest = new ConsultarRentaPortafolioRequest();
        ConsultarRentaPortafolioControlRequest requestParameters = new ConsultarRentaPortafolioControlRequest();

        String monthValue;

        if (cutDate.getMonthValue() < 10) {
            monthValue = "0" + String.valueOf(cutDate.getMonthValue());
        } else {
            monthValue = String.valueOf(cutDate.getMonthValue());
        }
        //cutDate.mi
        requestParameters.setProducto(product);
        requestParameters.setCanal("INTERNET");
        requestParameters.setFechaCorte(
                String.valueOf(cutDate.getYear()) +
                monthValue +
                String.valueOf(cutDate.getDayOfMonth())
        );
        requestParameters.setSalario(salary);
        requestParameters.setIdentificadorDeTransaccion(LocalDate.now().toString());

        rentaPortafolioRequest.setControl(requestParameters);

        return serviceFuncionarioWSFacade.consultarRentaValues(rentaPortafolioRequest);
    }

    /**
     * This method obtains the min salary for PO Simulator
     * @param date: {@link LocalDate} indicating current date
     * @return a {@link List<SalarioMinimo>}
     */
    public List<SalarioMinimo> getSalarioMinimo(LocalDate date)
            throws Exception {

        /* Salario mínimo Request Parameters */
        ConsultarSalarioMinimoRequest salaryRequest = new ConsultarSalarioMinimoRequest();
        ConsultarSalarioMinimoControlRequest salaryReqParams = new ConsultarSalarioMinimoControlRequest();
        salaryReqParams.setCanal("Internet");
        salaryReqParams.setIdentificadorDeTransaccion(LocalDate.now().toString());
        salaryReqParams.setFecSig(date.getYear()+"0101");

        salaryRequest.setControl(salaryReqParams);

        return serviceFuncionarioWSFacade.consultarSalarioMinimo(salaryRequest).getSalario();
    }

    /**
     * This method obtains the pension ages for PO Simulator
     * @return a {@link List<Edades>}
     */
    public List<Edades> getEdadesPension() throws Exception {

        /* Edad Request Parameters */
        ConsultarEdadPensionSimpleRequest edadPensionRequest = new ConsultarEdadPensionSimpleRequest();
        ConsultarEdadPensionControlRequest edadPensionParams = new ConsultarEdadPensionControlRequest();
        edadPensionParams.setCanal("CANALWEB");
        edadPensionParams.setIdentificadorDeTransaccion(LocalDate.now().toString());

        edadPensionRequest.setControl(edadPensionParams);

        return serviceFuncionarioWSFacade.consultarEdadesPension(edadPensionRequest);
    }

    /**
     * This method obtains cut date for PV Simulator
     * @return a {@link LocalDate}
     * @throws Exception
     */
    public LocalDate getFechaCorte() throws Exception {
        FechasDatosPortafolios response;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");     // Formatter to parse date coming from WS

        /* Cut date Request parameters */
        ConsultarParametrosHerramientasFVSimpleRequest paramsRequest = new ConsultarParametrosHerramientasFVSimpleRequest();
        ConsultarParametrosHerramientasFVControlRequest paramsRequestCtrl = new ConsultarParametrosHerramientasFVControlRequest();

        paramsRequestCtrl.setCanal("WEB");
        paramsRequestCtrl.setIdentificadorDeTransaccion(LocalDate.now().toString());

        paramsRequest.setControl(paramsRequestCtrl);

        response = serviceFuncionarioWSFacade.consultarFechaCorte(paramsRequest).get(0);

        String dateString = response.getUltimaFecha();

        return LocalDate.parse(dateString, formatter);
    }

    /**
     * This method loads config.properties
     * @return a {@link Configuration} object with all properties loaded
     * or null otherwhise
     */
    private Configuration getProperties() {
        Configuration properties;

        try {
            properties = ConfigurationFactoryUtil.getConfiguration(FuncionarioDelegate.class.getClassLoader(), PROP_FILE_NAME);
        } catch (Exception e) {
            return null;
        }

        return properties;
    }

}
