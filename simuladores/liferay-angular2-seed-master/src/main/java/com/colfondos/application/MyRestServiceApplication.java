/**
 * 
 */
package com.colfondos.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;

import com.colfondos.business.CalculosSimulador;
import com.colfondos.model.GraficoRentabilidadRs;
import com.colfondos.model.OpcionInversionRs;
import com.colfondos.model.RentabilidadFondo;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@ApplicationPath("/cesantias")
@Component(immediate = true, service = Application.class)
public class MyRestServiceApplication extends Application {

	private static Log LOGGER = LogFactoryUtil.getLog(MyRestServiceApplication.class);
	private CalculosSimulador calculosSimulador;

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/opciones-inversion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OpcionInversionRs> getOpcionesInversion() {
		List<OpcionInversionRs> opciones = new ArrayList<>();

		try {
			opciones = getCalculosSimulador().getOpcionesInversion();
		} catch (Exception e) { // TODO: handle exception
			LOGGER.error(e);
		}

		return opciones;

	}

	@GET
	@Path("/graficos/rentabilidades")
	@Produces(MediaType.APPLICATION_JSON)
	public GraficoRentabilidadRs getRendimientos(@QueryParam("proyeccion") String proyeccion) {
		GraficoRentabilidadRs rentabildiades = new GraficoRentabilidadRs();

		try {
			rentabildiades = getCalculosSimulador().getGraficoRentabilidad(proyeccion);
		} catch (Exception e) { // TODO: handle exception
			LOGGER.error(e);
		}

		return rentabildiades;

	}

	@GET
	@Path("/rentabilidades")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RentabilidadFondo> getRendimientosFondosPeriodo(@QueryParam("proyeccion") String proyeccion,
			@QueryParam("periodo") String fechaCorte) {
		List<RentabilidadFondo> rentabilidades = new ArrayList<>();
		try {
			rentabilidades = getCalculosSimulador().getRentabilidadPeriodo(fechaCorte, proyeccion);
		} catch (Exception e) { // TODO: handle exception
			LOGGER.error(e);
		}

		return rentabilidades;

	}

	private CalculosSimulador getCalculosSimulador() {
		return calculosSimulador == null ? new CalculosSimulador() : calculosSimulador;
	}

}
