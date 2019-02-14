package com.colfondos.business.funcionario.wsclient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.colfondos.constants.GeneralConstants;

import co.com.colfondos.business.funcionario.ConsultarDatosGraficaRentabilidadCEControlRequest;
import co.com.colfondos.business.funcionario.ConsultarDatosGraficaRentabilidadCERequest;
import co.com.colfondos.business.funcionario.ConsultarRentaPortafolioControlRequest;
import co.com.colfondos.business.funcionario.ConsultarRentaPortafolioRequest;
import co.com.colfondos.business.funcionario.IServiceFuncionario;
import co.com.colfondos.business.funcionario.IServiceFuncionarioService;
import co.com.colfondos.business.funcionario.InternalException;
import co.com.colfondos.business.funcionario.ResponseConsultarDatosGraficaRentabilidadCE;
import co.com.colfondos.business.funcionario.ResponseConsultarRentaPortafolio;

public class FuncionarioWSClient {

	private IServiceFuncionarioService iServiceFuncionarioService;

	/**
	 * Método que consume el servicio de ConsultarRentaPortafolio
	 * 
	 * @param proyeccion la proyección que es la opción de inversión la cual puede
	 *                   ser CORTO_PLAZO_3M, LARGO_PLAZO_24M o CORTO_LARGO_PLAZO_24M
	 * @param fechaCorte fecha de corte donde se consulta el periodo
	 * @return
	 * @throws InternalException
	 */
	public ResponseConsultarRentaPortafolio getRentaPortafolio(String proyeccion, String fechaCorte)
			throws InternalException {
		IServiceFuncionario iServiceFuncionario = getServiceFuncionario();
		ConsultarRentaPortafolioRequest consultarRentaPortafolioRequest = new ConsultarRentaPortafolioRequest();

		ConsultarRentaPortafolioControlRequest control = new ConsultarRentaPortafolioControlRequest();
		control.setCanal(GeneralConstants.WS_PARAM_CANAL_PORTAFOLIO);
		control.setIdentificadorDeTransaccion(generarIdTransaccion());
		control.setProducto(GeneralConstants.WS_PARAM_PRODUCTO);
		control.setFechaCorte(fechaCorte);
		control.setProyeccion(proyeccion);
		consultarRentaPortafolioRequest.setControl(control);

		return iServiceFuncionario.consultarRentaPortafolio(consultarRentaPortafolioRequest);
	}

	/**
	 * Metodo que consume el servicio para las graficas de Rentabilidad
	 * 
	 * @param proyeccion la proyección que es la opción de inversión la cual puede
	 *                   ser CORTO_PLAZO_3M, LARGO_PLAZO_24M o CORTO_LARGO_PLAZO_24M
	 * @return
	 * @throws InternalException
	 */
	public ResponseConsultarDatosGraficaRentabilidadCE getGraficoRentabilidad(String proyeccion)
			throws InternalException {
		IServiceFuncionario iServiceFuncionario = getServiceFuncionario();
		ConsultarDatosGraficaRentabilidadCERequest consultarDatosGraficaRentabilidadCERequest = new ConsultarDatosGraficaRentabilidadCERequest();

		ConsultarDatosGraficaRentabilidadCEControlRequest control = new ConsultarDatosGraficaRentabilidadCEControlRequest();
		control.setCanal(GeneralConstants.WS_PARAM_CANAL);
		control.setIdentificadorDeTransaccion(generarIdTransaccion());
		control.setProyeccion(proyeccion);
		consultarDatosGraficaRentabilidadCERequest.setControl(control);

		return iServiceFuncionario.consultarDatosGraficaRentabilidadCE(consultarDatosGraficaRentabilidadCERequest);
	}

	/**
	 * Método para generar el id de transacción con la fecha
	 * 
	 * @return
	 */
	private String generarIdTransaccion() {
		LocalDate afterMonth = LocalDate.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(GeneralConstants.FORMATO_FECHA_ID);
		return afterMonth.format(formatters);
	}

	/**
	 * Get Service Port
	 * 
	 * @return
	 */
	private IServiceFuncionario getServiceFuncionario() {

		if (iServiceFuncionarioService == null) {
			iServiceFuncionarioService = new IServiceFuncionarioService();
		}
		return iServiceFuncionarioService.getIServiceFuncionarioPort();
	}

}
