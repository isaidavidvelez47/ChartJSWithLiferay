package co.com.colfondos.herramientapo.portlet;

import co.com.colfondos.herramientapo.business.PensionParametersDAO;
import co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys;
import co.com.colfondos.herramientapo.delegate.CacheDelegate;
import co.com.colfondos.herramientapo.model.PensionFund;
import co.com.colfondos.herramientapo.model.Person;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.RentaPortafolioVO;
import com.google.gson.Gson;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys.*;

/**
 * Portlet of HerramientaPO based on {@link MVCPortlet}
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.0
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=root//ColfondosFuncionarios//PO",
            "com.liferay.portlet.instanceable=false",
            "com.liferay.portlet.header-portlet-css=/css/main.css",
            "com.liferay.portlet.header-portlet-css=/css/responsive.css",
            "com.liferay.portlet.header-portlet-javascript=/js/index.js",
            "com.liferay.portlet.header-portlet-javascript=/js/formulario.js",
            "com.liferay.portlet.css-class-wrapper=sim-po",
            "javax.portlet.display-name=Colfondos-HerramientaPO Portlet",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.jsp",
            "javax.portlet.init-param.add-process-action-success-action=false",
            "javax.portlet.name=" + HerramientaPOPortletKeys.HerramientaPO,
            "javax.portlet.portlet-mode=view",
            "javax.portlet.mime-type=text/html",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HerramientaPOPortlet extends MVCPortlet {

    private static Log LOGGER = LogFactoryUtil.getLog(GLOBAL_PACKAGE_LOGGER);

    private static final String PROP_FILE_NAME = "content/config";

    private Configuration properties;

    private static int MALE_AGE = 62;
    private static int FEMALE_AGE = 57;
    private static double MIN_SALARY = 781.242;

    private PensionParametersDAO pensionParametersDAO;

    private Map<String, List<Object>> simulationResults;

    @Override
    public void init() throws PortletException {
        LOGGER.info("Clearing cache");
        CacheDelegate.getInstance().clearAllCache();

        LOGGER.info("Trying to read config properties");
        //properties = ;
        properties = getProperties();

        if (properties == null) {
            LOGGER.warn("Config properties not read");
            LOGGER.warn("Using program default values");
        } else {
            LOGGER.info("Properties loaded correctly");

            MALE_AGE = Integer.parseInt(properties.get("pension-age.male"));
            FEMALE_AGE = Integer.parseInt(properties.get("pension-age.female"));
        }

        super.init();
    }

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        renderRequest.setAttribute("male_age", MALE_AGE);
        renderRequest.setAttribute("female_age", FEMALE_AGE);
        super.render(renderRequest, renderResponse);
    }

    @ProcessAction(name = "saveData")
    public void saveDataAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException {

        Person person = new Person();
        PensionFund pensionFund = new PensionFund();

        LOGGER.debug("Guardando datos de formulario");

        String projectionType = "";
        Locale esCOLocale = new Locale.Builder()
                .setLanguage("es")
                .setRegion("CO")
                .build();

        NumberFormat numberFormat = NumberFormat.getInstance(esCOLocale);
        try {
            /* Format es_CO numbers coming from form */
            double salary = numberFormat.parse(request.getParameter("person_salary")).doubleValue();
            double balance = numberFormat.parse(request.getParameter("person_balance")).doubleValue();

            /* Set values to person object */
            person.setAge(Integer.parseInt(request.getParameter("person_age")));
            person.setGender(request.getParameter("person_gender"));
            person.setSalary(salary);
            person.setBalance(balance);

            pensionFund.setDecisionMonths( Integer.parseInt(request.getParameter("decision_months")));
            pensionFund.setName(request.getParameter("afp_name"));
            pensionFund.setPortfolio(request.getParameter("fund_type"));

            projectionType = request.getParameter("projection_type");

        } catch (ParseException e) {
            LOGGER.error("Error en parametros de entrada", e);
        }

        LOGGER.info("Datos de formulario correctos");
        LOGGER.info("...Valores estadisticos...");
        LOGGER.info("Genero usuario: " + person.getGender());
        LOGGER.info("Edad usuario: " + person.getAge());
        LOGGER.info("AFP actual: " + pensionFund.getName());
        LOGGER.info("AFP portafolio: " + pensionFund.getPortfolio());

        pensionParametersDAO.establishParameters(person, projectionType);

        /* Uses cache to get rentabilities */
        LOGGER.info("Getting rentabilities from cache");
        List<RentaPortafolioVO> rentsList = CacheDelegate.getInstance().getRentabilidadesPO();
        pensionParametersDAO.setRentabilityValues(rentsList);

        LOGGER.info("Calculando valores de simulacion");
        simulationResults = pensionParametersDAO.calculateResultValues(pensionFund, person);
        if (simulationResults == null) {    /* Error calculating values */
            // Show some error?
            LOGGER.error("Error al realizar los calculos");
        } else {                            /* Calculus successful */
            // Sets Url to navigate to graphs page
            LOGGER.info("Calculos terminados");

            /* Set URL parameters */
            request.setAttribute("redirect-page", "yes");
            response.setRenderParameter("mvcPath", "/graphs.jsp");

            /* Set simulator attributes */
            request.setAttribute(GENDER_ATTRIBUTE, person.getGender());
            request.setAttribute(AGE_ATTRIBUTE, String.valueOf(person.getAge()));
            request.setAttribute(SALARY_ATTRIBUTE, numberFormat.format(person.getSalary()));
            request.setAttribute(BALANCE_ATTRIBUTE, numberFormat.format(person.getBalance()));
            request.setAttribute(PROJECTION_ATTRIBUTE, projectionType);
            request.setAttribute(MONTHS_ATTRIBUTE, String.valueOf(pensionFund.getDecisionMonths()));
        }

    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws IOException, PortletException {
        String resourceId = resourceRequest.getResourceID();

        if (resourceId.equals("callAjax")) {

            Gson gson = new Gson();

            // Writing the result in resourceResponse writer.
            PrintWriter writer = resourceResponse.getWriter();
            writer.println(gson.toJson(simulationResults));
        } else if (resourceId.equals("initParams")) {
            LOGGER.info("Loading init params");

            pensionParametersDAO = new PensionParametersDAO(properties);

            LOGGER.info("Trying to get init params from WS");
            if (pensionParametersDAO.readPensionParameters(MALE_AGE, FEMALE_AGE, MIN_SALARY)) {
                LOGGER.info("Parametros leidos correctamente");

            } else {
                LOGGER.warn("Algunos parametros no se obtuvieron desde el WS");
            }

            MALE_AGE = pensionParametersDAO.getMALE_PENSION_AGE();
            FEMALE_AGE = pensionParametersDAO.getFEMALE_PENSION_AGE();
            MIN_SALARY = pensionParametersDAO.getMIN_SALARY();

            LOGGER.info("Male pension age: " + MALE_AGE);
            LOGGER.info("Female pension age: " + FEMALE_AGE);
            LOGGER.info("Current min salary: " + MIN_SALARY);

            Map<String, Integer> pensionAges = new HashMap<>();

            pensionAges.put("male_age", MALE_AGE);
            pensionAges.put("female_age", FEMALE_AGE);

            Gson gson = new Gson();

            // Writing the result in resourceResponse writer.
            PrintWriter writer = resourceResponse.getWriter();
            writer.println(gson.toJson(pensionAges));
        }
    }

    /**
     * This method loads portlet.properties
     * @return a {@link Configuration} object with all properties loaded
     * or null otherwhise
     */
    private Configuration getProperties() {
        Configuration properties;

        try {
            properties = ConfigurationFactoryUtil.getConfiguration(HerramientaPOPortlet.class.getClassLoader(), PROP_FILE_NAME);
        } catch (Exception e) {
            LOGGER.error("Error reading config properties", e);
            return null;
        }

        return properties;
    }
}