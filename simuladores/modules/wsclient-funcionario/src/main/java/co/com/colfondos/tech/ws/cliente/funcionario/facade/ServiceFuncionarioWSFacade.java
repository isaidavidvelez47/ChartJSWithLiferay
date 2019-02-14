package co.com.colfondos.tech.ws.cliente.funcionario.facade;

import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * This Class extends <code>BaseWSFacade</code> class
 * <p>
 * Defines methods to obtain specific data from WS
 * given a request
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.0.0
 */
public class ServiceFuncionarioWSFacade extends BaseWSFacade {

    private static Log LOGGER = LogFactoryUtil.getLog(ServiceFuncionarioWSFacade.class);

    /**
     * Field IServiceFuncionario
     */
    protected IServiceFuncionario serviceFuncionario;
    /**
     * Field objFactory.
     */
    protected ObjectFactory objFactory = new ObjectFactory();

    /**
     * @return the objFactory
     */
    public ObjectFactory getObjFactory() {
        return objFactory;
    }

    /**
     * Constructor for {@link ServiceFuncionarioWSFacade}
     * with to params
     * @param canal {@link String}
     * @param qName {@link QName}
     */
    public ServiceFuncionarioWSFacade(String canal, QName qName) {
        setCanal(canal);
        Service service = createPortTypeService(qName);
        serviceFuncionario = service.getPort(IServiceFuncionario.class);
        setTimeouts();
    }

    /**
     * Constructor for {@link ServiceFuncionarioWSFacade}
     * with three params
     * @param canal {@link String}
     * @param url {@link URL}
     * @param qName {@link QName}
     */
    public ServiceFuncionarioWSFacade(String canal, URL url, QName qName) {
        setCanal(canal);
        Service service = createPortTypeService(url, qName);
        serviceFuncionario = service.getPort(IServiceFuncionario.class);
        setTimeouts();
    }

    private void setTimeouts() {
        Map<String, Object> requestContext = ((BindingProvider)serviceFuncionario).getRequestContext();
        requestContext.put("javax.xml.ws.client.receiveTimeout", "1000");
        requestContext.put("javax.xml.ws.client.connectionTimeout", "1000");
    }

    /**
     * Method to request Rentabilities to Web Service of Colfondos
     * @param request of type {@link ConsultarRentaPortafolioRequest} containing the request parameters
     * @return a List of type {@link RentaPortafolioVO}
     * @throws Exception
     */
    public List<RentaPortafolioVO> consultarRentaValues(ConsultarRentaPortafolioRequest request)
            throws Exception {
        LOGGER.info("Trying to read rentability values");
        return serviceFuncionario.consultarRentaPortafolio(request).getRentaPortafolio();
    }

    /**
     * Method to request minimum salary to Web Service of Colfondos
     * @param request of type {@link ConsultarSalarioMinimoRequest} containing the request parameters
     * @return a object of type {@link co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.SalariosMinimos.Salarios}
     * @throws Exception
     */
    public SalariosMinimos.Salarios consultarSalarioMinimo(ConsultarSalarioMinimoRequest request)
            throws Exception {
        LOGGER.info("Trying to read min salary");
        return serviceFuncionario.consultarSalarioMinimo(request).getSalarios();
    }

    /**
     * Method to request pension ages to Web Service of Colfondos
     * @param request of type {@link ConsultarEdadPensionSimpleRequest} containing the request parameters
     * @return a List of type {@link Edades}
     * @throws Exception
     */
    public List<Edades> consultarEdadesPension(ConsultarEdadPensionSimpleRequest request)
            throws Exception {
        LOGGER.info("Trying to read pension ages");
        return serviceFuncionario.consultarEdadPension(request).getEdades();
    }

    /**
     * Method to request cut date to Web Service of Colfondos
     * @param request of type {@link ConsultarParametrosHerramientasFVSimpleRequest} containing the request parameters
     * @return a List of type {@link FechasDatosPortafolios}
     * @throws Exception
     */
    public List<FechasDatosPortafolios> consultarFechaCorte(ConsultarParametrosHerramientasFVSimpleRequest request)
            throws Exception {
        LOGGER.info("Trying to read cut date");
        return serviceFuncionario.consultarParametrosHerramientasFV(request).getFechas();
    }
}
