package com.colfondos.business;

import java.util.List;

import com.colfondos.model.OpcionInversionRs;
import com.colfondos.model.RentabilidadFondo;

public class Prueba {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CalculosSimulador c = new CalculosSimulador();
		c.getGraficoRentabilidad("CORTO_PLAZO_3M");
		List<RentabilidadFondo> per = c.getRentabilidadPeriodo("31/01/2018", "CORTO_LARGO_PLAZO_24M");
		System.out.println(per);
		List<OpcionInversionRs> ss = c.getOpcionesInversion();
		ss.stream().forEach((p) -> {
			System.out.println(p.getOptions().getNameProjection());
			p.getPeriods().forEach((q) -> {
				System.out.println(q.getFinalDate());
			});
		});
	}

}
