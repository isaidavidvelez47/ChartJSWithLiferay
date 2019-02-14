package com.colfondos.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.colfondos.business.funcionario.wsclient.FuncionarioWSClient;
import com.colfondos.constants.GeneralConstants;
import com.colfondos.model.GraficoRentabilidadRs;
import com.colfondos.model.OpcionInversionRs;
import com.colfondos.model.Percent;
import com.colfondos.model.Period;
import com.colfondos.model.Profitability;
import com.colfondos.model.Projection;
import com.colfondos.model.RentabilidadFondo;

import co.com.colfondos.business.funcionario.RentaPortafolioVO;
import co.com.colfondos.business.funcionario.RentabilidadCE;
import co.com.colfondos.business.funcionario.ResponseConsultarDatosGraficaRentabilidadCE;
import co.com.colfondos.business.funcionario.ResponseConsultarRentaPortafolio;

/**
 * Clase con la lógica de negocio del simulador
 * 
 * @author silvio.cantillo
 *
 */
public class CalculosSimulador {

	/**
	 * Metodo para obtener las rentabildiades por fondo en un periodo determinado
	 * 
	 * @param fechaCorte
	 * @param proyeccion
	 * @return
	 * @throws Exception
	 */
	public List<RentabilidadFondo> getRentabilidadPeriodo(String fechaCorte, String proyeccion) throws Exception {
		List<RentabilidadFondo> fondos = new ArrayList<>();
		FuncionarioWSClient funcionarioWSClient = new FuncionarioWSClient();
		ResponseConsultarRentaPortafolio responseConsultarRentaPortafolio = funcionarioWSClient
				.getRentaPortafolio(proyeccion, convertirFechaCorte(fechaCorte));
		if (responseConsultarRentaPortafolio.getCodMensajeResp().equals(GeneralConstants.WS_PARAM_CODIGO_EXITOSO)) {
			fondos = generarRentabilidades(responseConsultarRentaPortafolio.getRentaPortafolio());
		} else {
			throw new Exception("El servicio no responde");
		}
		return fondos;
	}

	/**
	 * Método para obtener la rentabilidad acumulada
	 * 
	 * @param rentabilidades
	 * @return
	 */
	private List<RentabilidadFondo> generarRentabilidades(List<RentaPortafolioVO> rentabilidades) {
		List<RentabilidadFondo> fondos = new ArrayList<>();
		boolean isVersus = Boolean.FALSE;
		List<RentaPortafolioVO> rentabilidadAcumulada;
		if (rentabilidades.size() == 1) {
			rentabilidadAcumulada = rentabilidades;
			isVersus = Boolean.TRUE;
		} else {
			rentabilidadAcumulada = rentabilidades.stream().filter(
					rentabilidad -> rentabilidad.getTipoRentabilidad().equals(GeneralConstants.RENTABILIDAD_ACUMULADA))
					.collect(Collectors.toList());
		}

		if (!rentabilidadAcumulada.isEmpty()) {
			RentaPortafolioVO rentaPortafolioVO = rentabilidadAcumulada.get(0);
			RentabilidadFondo rentabilidadFondo = new RentabilidadFondo();
			if (isVersus) {
				rentabilidadFondo.setFondo(
						GeneralConstants.FONDO_COLFONDOS + " " + GeneralConstants.FONDO_COLFONDOS_LARGO_PLAZO);
				rentabilidadFondo.setRentabilidad(rentaPortafolioVO.getColfondosLargoPlazoAcumulada());
				fondos.add(rentabilidadFondo);
				rentabilidadFondo = new RentabilidadFondo();
				rentabilidadFondo.setFondo(
						GeneralConstants.FONDO_COLFONDOS + " " + GeneralConstants.FONDO_COLFONDOS_CORTO_PLAZO);
				rentabilidadFondo.setRentabilidad(rentaPortafolioVO.getColfondosCortoPlazoAcumulada());
				fondos.add(rentabilidadFondo);
			} else {

				rentabilidadFondo.setFondo(GeneralConstants.FONDO_COLFONDOS);
				rentabilidadFondo.setRentabilidad(rentaPortafolioVO.getColfondosRentaPortafolio());
				fondos.add(rentabilidadFondo);

				rentabilidadFondo = new RentabilidadFondo();
				rentabilidadFondo.setFondo(GeneralConstants.FONDO_PORVENIR);
				rentabilidadFondo.setRentabilidad(rentaPortafolioVO.getPorvenirRentaPortafolio());
				fondos.add(rentabilidadFondo);

				rentabilidadFondo = new RentabilidadFondo();
				rentabilidadFondo.setFondo(GeneralConstants.FONDO_PROTECCION);
				rentabilidadFondo.setRentabilidad(rentaPortafolioVO.getProteccionRentaPortafolio());
				fondos.add(rentabilidadFondo);

				rentabilidadFondo = new RentabilidadFondo();
				rentabilidadFondo.setFondo(GeneralConstants.FONDO_OLD_MUTUAL);
				rentabilidadFondo.setRentabilidad(rentaPortafolioVO.getOldMutualRentaPortafolio());
				fondos.add(rentabilidadFondo);
			}

		}
		return fondos;
	}

	/**
	 * Método para obtener los datos de los gráficos de la evoluvión de rentabilidad
	 * 
	 * @param proyeccion
	 * @return
	 * @throws Exception
	 */
	public GraficoRentabilidadRs getGraficoRentabilidad(String proyeccion) throws Exception {
		GraficoRentabilidadRs graficoRentabilidadRs = new GraficoRentabilidadRs();
		graficoRentabilidadRs.setLabels(generarLabels(proyeccion));
		graficoRentabilidadRs.setProfitability(consultarRentabilidad(proyeccion));
		return graficoRentabilidadRs;
	}

	/**
	 * 
	 * 
	 * @param proyeccion
	 * @return
	 * @throws Exception
	 */
	private List<Profitability> consultarRentabilidad(String proyeccion) throws Exception {
		List<Profitability> rentabildades = new ArrayList<>();
		FuncionarioWSClient funcionarioWSClient = new FuncionarioWSClient();
		ResponseConsultarDatosGraficaRentabilidadCE responseConsultarDatosGraficaRentabilidadCE = funcionarioWSClient
				.getGraficoRentabilidad(proyeccion);
		if (responseConsultarDatosGraficaRentabilidadCE.getCodMensajeResp()
				.equals(GeneralConstants.WS_PARAM_CODIGO_EXITOSO)) {
			int meses = proyeccion.equals(GeneralConstants.CORTO_PLAZO_3M) ? GeneralConstants.PROP_KEY_CORTO_PLAZO
					: GeneralConstants.PROP_KEY_LARGO_PLAZO;
			rentabildades = responseConsultarDatosGraficaRentabilidadCE.getRentaPortafolio().stream()
					.sorted(Comparator.comparing(RentabilidadCE::getFechaFinal)).map(rentabilidad -> {
						Profitability profitability = new Profitability();
						profitability.setFinalDate(convertirFecha(rentabilidad.getFechaFinal()));
						profitability.setInitialDate(calcularFechaInicio(rentabilidad.getFechaFinal(), meses));
						profitability.setPercent(generarPorcentaje(rentabilidad));
						return profitability;
					}).collect(Collectors.toList());

		} else {
			throw new Exception("El servicio no responde");
		}
		return rentabildades;
	}

	/**
	 * Convertir fecha del servico de yyyyMMdd a dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 */
	private String convertirFecha(String fecha) {
		LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.BASIC_ISO_DATE);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(GeneralConstants.FORMATO_FECHA);
		return date.format(formatters);
	}

	/**
	 * Calcular la fecha de inicio apartir de la fecha final
	 * 
	 * @param fecha
	 * @param meses
	 * @return
	 */
	private String calcularFechaInicio(String fecha, int meses) {
		LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.BASIC_ISO_DATE);
		date = date.minus(meses, ChronoUnit.MONTHS);
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(GeneralConstants.FORMATO_FECHA);
		return date.format(formatters);
	}

	/**
	 * Convertir fecha de corte
	 * 
	 * @param fecha
	 * @return
	 */
	private String convertirFechaCorte(String fecha) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(GeneralConstants.FORMATO_FECHA);
		LocalDate fechaCorte = LocalDate.parse(fecha, formatters);
		DateTimeFormatter formattersResult = DateTimeFormatter.ofPattern(GeneralConstants.FORMATO_FECHA_ID);
		return fechaCorte.format(formattersResult);
	}

	/**
	 * Setear los datos de la grafica
	 * 
	 * @param rentabilidadCE
	 * @return
	 */
	private Percent generarPorcentaje(RentabilidadCE rentabilidadCE) {
		Percent percent = new Percent();
		percent.setColfondos(rentabilidadCE.getColfondosRentaEA());
		percent.setColfondosCortoPlazo(rentabilidadCE.getColfondosCortoPlazo());
		percent.setColfondosLargoPlazo(rentabilidadCE.getColfondosLargoPlazo());
		percent.setOldmutual(rentabilidadCE.getOldMutualRentaEA());
		percent.setPorvenir(rentabilidadCE.getPorvenirRentaEA());
		percent.setProteccion(rentabilidadCE.getProteccionRentaEA());
		return percent;
	}

	/**
	 * Método para generar los labels de la gráfica
	 * 
	 * @return
	 */
	private List<String> generarLabels(String proyeccion) {
		List<String> labels = new ArrayList<>();
		if (proyeccion.equals(GeneralConstants.CORTO_LARGO_PLAZO_24M)) {
			labels.add(GeneralConstants.FONDO_COLFONDOS_CORTO_PLAZO);
			labels.add(GeneralConstants.FONDO_COLFONDOS_LARGO_PLAZO);
		} else {
			labels.add(GeneralConstants.FONDO_COLFONDOS);
			labels.add(GeneralConstants.FONDO_OLD_MUTUAL);
			labels.add(GeneralConstants.FONDO_PORVENIR);
			labels.add(GeneralConstants.FONDO_PROTECCION);
		}
		return labels;
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<OpcionInversionRs> getOpcionesInversion() throws FileNotFoundException, IOException {
		List<OpcionInversionRs> opcionesInversionRs = new ArrayList<>();
		Projection projection = new Projection();
		projection.setIdProjection(GeneralConstants.CORTO_PLAZO_3M);
		projection.setNameProjection(GeneralConstants.CORTO_PLAZO);
		opcionesInversionRs.add(getCortoLargoPlazo(projection, GeneralConstants.PROP_KEY_CORTO_PLAZO));

		projection = new Projection();
		projection.setIdProjection(GeneralConstants.LARGO_PLAZO_24M);
		projection.setNameProjection(GeneralConstants.LARGO_PLAZO);

		opcionesInversionRs.add(getCortoLargoPlazo(projection, GeneralConstants.PROP_KEY_LARGO_PLAZO));

		projection = new Projection();
		projection.setIdProjection(GeneralConstants.CORTO_LARGO_PLAZO_24M);
		projection.setNameProjection(GeneralConstants.LARGO_VS_CORTO);

		opcionesInversionRs.add(getCortoLargoPlazo(projection, GeneralConstants.PROP_KEY_LARGO_PLAZO));
		return opcionesInversionRs;
	}

	/**
	 * @param opcion
	 * @param meses
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private OpcionInversionRs getCortoLargoPlazo(Projection opcion, int meses)
			throws FileNotFoundException, IOException {
		OpcionInversionRs opcionInversionRs = new OpcionInversionRs();
		opcionInversionRs.setOptions(opcion);
		opcionInversionRs.setTittle(generarTitulos(opcion.getNameProjection()));
		opcionInversionRs.setPeriods(generarPeriodos(meses));
		return opcionInversionRs;
	}

	/**
	 * Método para genearar los periodos
	 * 
	 * @param meses meses del plazo
	 * @return retornar los peridos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private List<Period> generarPeriodos(int meses) throws FileNotFoundException, IOException {
		List<Period> periodos = new ArrayList<>();
		Period periodo;
		LocalDate afterMonth = LocalDate.now();
		afterMonth = afterMonth.withDayOfMonth(afterMonth.lengthOfMonth());
		LocalDate initialMoth = afterMonth.minus(3, ChronoUnit.MONTHS);
		initialMoth = initialMoth.withDayOfMonth(initialMoth.lengthOfMonth());

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(GeneralConstants.FORMATO_FECHA);
		int limiteMeses = GeneralConstants.PROP_KEY_LIMITE;
		int numberMonth = ((12 * limiteMeses) - meses) + afterMonth.getMonthValue();
		for (int i = numberMonth; i >= 1; i--) {
			periodo = new Period();
			periodo.setInitialDate(initialMoth.format(formatters));
			periodo.setFinalDate(afterMonth.format(formatters));
			afterMonth = afterMonth.minus(1, ChronoUnit.MONTHS);
			afterMonth = afterMonth.withDayOfMonth(afterMonth.lengthOfMonth());
			initialMoth = afterMonth.minus(meses, ChronoUnit.MONTHS);
			initialMoth = initialMoth.withDayOfMonth(initialMoth.lengthOfMonth());
			periodos.add(periodo);
		}
		return periodos;
	}

	/**
	 * Método para generar los tirulos por opción de inversión
	 * 
	 * @param opcion nombre de la opción de inversión
	 * @return listado de titulos seteados a la opción de inversión
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private List<String> generarTitulos(String opcion) throws FileNotFoundException, IOException {
		List<String> titulos = new ArrayList<>();
		titulos.add(GeneralConstants.PROP_KEY_TITULO_TABLA + " - " + opcion);
		titulos.add(GeneralConstants.PROP_KEY_TITULO_GRAFICO_SALDOS + " - " + opcion);
		if (opcion.equals(GeneralConstants.LARGO_VS_CORTO)) {
			titulos.add(GeneralConstants.PROP_KEY_TITULO_GRAFICO_RENTABILIDAD_CORTO_LARGO_PLAZO + " - " + opcion);
		} else {
			titulos.add(GeneralConstants.PROP_KEY_TITULO_GRAFICO_RENTABILIDAD + " - " + opcion);
		}

		return titulos;
	}

}
