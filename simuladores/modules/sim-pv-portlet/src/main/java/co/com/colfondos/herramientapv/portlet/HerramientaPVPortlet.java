package co.com.colfondos.herramientapv.portlet;

import co.com.colfondos.herramientapv.business.CalculosSimulador;
import co.com.colfondos.herramientapv.constants.HerramientaPVPortletKeys;
import co.com.colfondos.herramientapv.delegate.CacheDelegate;
import co.com.colfondos.herramientapv.model.BrandsEntity;
import com.google.gson.Gson;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static co.com.colfondos.herramientapv.constants.HerramientaPVPortletKeys.GLOBAL_PACKAGE_LOGGER;


/**
 * @author guillermo.grajales
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=root//ColfondosFuncionarios//PV",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.header-portlet-css=/css/responsive.css",
		"com.liferay.portlet.header-portlet-javascript=/js/index.js",
		"com.liferay.portlet.header-portlet-javascript=/js/formulario.js",
        "com.liferay.portlet.header-portlet-javascript=/js/graphs.js",
		"com.liferay.portlet.header-portlet-javascript=/js/tables_responsive.js",
		"com.liferay.portlet.css-class-wrapper=sim-pv",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.ajaxable=true",
		"javax.portlet.display-name=Colfondos-HerramientaPV Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/index.jsp",
		"javax.portlet.portlet-mode=view",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.name=" + HerramientaPVPortletKeys.HERRAMIENTA_PV,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HerramientaPVPortlet extends MVCPortlet {

	private static Log LOGGER = LogFactoryUtil.getLog(GLOBAL_PACKAGE_LOGGER);

	private CalculosSimulador calculosSimulador;

	@Override
	public void init() throws PortletException {
		LOGGER.info("Clearing cache");
		CacheDelegate.getInstance().clearAllCache();

		calculosSimulador = new CalculosSimulador();

		super.init();
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.render(renderRequest, renderResponse);
	}

	/**
	 * Method to process all resources requests
	 * @param resourceRequest
	 * @param resourceResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		String id = resourceRequest.getResourceID();

		double salary;
		String cutDate;
		try {
			if (id.equals("submitNormal")) {
				salary = ParamUtil.getDouble(resourceRequest,"salary");
				cutDate = ParamUtil.getString(resourceRequest, "cutDate");
				int portfolio = ParamUtil.getInteger(resourceRequest, "afp");

				LOGGER.info("Salary: " + salary);
				LOGGER.info("Date: " + cutDate);
				LocalDate tmpDate = LocalDate.parse(cutDate);
				LOGGER.info("Date formatted: " + String.valueOf(tmpDate.getYear()) +
						String.valueOf(tmpDate.getMonthValue()) +
						String.valueOf(tmpDate.getDayOfMonth()));
				LOGGER.info("Portfolio: " + portfolio);

				Map<String,List<BrandsEntity>> resultsNormal = calculosSimulador.getNormalValues(salary, LocalDate.parse(cutDate), portfolio);

				Gson gson = new Gson();

				// Writing the result in resourceResponse writer.
				PrintWriter writer = resourceResponse.getWriter();

				if (resultsNormal != null) {

					writer.println(gson.toJson(resultsNormal));
				} else {
					LOGGER.error("Data was not loaded from WS");
					writer.println(gson.toJson("Error"));
				}
			} else if (id.equals("submitMix")) {
				LOGGER.info("Serving resource Mix");
				salary = ParamUtil.getDouble(resourceRequest,"salary");
				cutDate = ParamUtil.getString(resourceRequest, "cutDate");

				Map<Integer, List<BrandsEntity>> resultsMix = calculosSimulador.getMixValues(salary, LocalDate.parse(cutDate));

				Gson gson = new Gson();

				// Writing the result in resourceResponse writer.
				PrintWriter writer = resourceResponse.getWriter();

				if (resultsMix != null) {
					writer.println(gson.toJson(resultsMix));
				} else {
					LOGGER.error("Data was not loaded from WS");
					writer.println(gson.toJson("Error"));
				}

			} else if (id.equals("cutDate")) {
				LOGGER.info("Getting cut date...");
				LocalDate cutDateServer = CacheDelegate.getInstance().getCutDate();

				if (cutDateServer == null) {
					LOGGER.warn("Error loading cut date from server");
					return;
				}

				LOGGER.info("Date loaded correctly");

				Gson gson = new Gson();
				resourceResponse.getWriter().println(gson.toJson(cutDateServer.toString()));
			}

		} catch (Exception e) {
			LOGGER.error("Exception Serving Resource: " + e);
			Gson gson = new Gson();
			resourceResponse.getWriter().println(gson.toJson("Error"));
		}

	}
}