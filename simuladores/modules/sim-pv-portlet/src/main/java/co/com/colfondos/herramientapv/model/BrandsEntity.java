package co.com.colfondos.herramientapv.model;

import java.time.LocalDate;

public class BrandsEntity {

    private int id;

    private LocalDate dataDate;

    private double colfondos;

    private double proteccion;

    private double porvenir;

    private double oldMutual;

    public BrandsEntity() {
    }

    public BrandsEntity(double colfondos, double proteccion, double porvenir, double oldMutual) {
        this.colfondos = colfondos;
        this.proteccion = proteccion;
        this.porvenir = porvenir;
        this.oldMutual = oldMutual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataDate() {
        return dataDate;
    }

    public void setDataDate(LocalDate dataDate) {
        this.dataDate = dataDate;
    }

    public double getColfondos() {
        return colfondos;
    }

    public void setColfondos(double colfondos) {
        this.colfondos = colfondos;
    }

    public double getProteccion() {
        return proteccion;
    }

    public void setProteccion(double proteccion) {
        this.proteccion = proteccion;
    }

    public double getPorvenir() {
        return porvenir;
    }

    public void setPorvenir(double porvenir) {
        this.porvenir = porvenir;
    }

    public double getOldMutual() {
        return oldMutual;
    }

    public void setOldMutual(double oldMutual) {
        this.oldMutual = oldMutual;
    }

    public String getFormatColf() {
        return formatValue(this.colfondos*100);
    }

    public String getFormatProt() {
        return formatValue(this.proteccion*100);
    }

    public String getFormatPorv() {
        return formatValue(this.porvenir*100);
    }

    public String getFormatOld() {
        return formatValue(this.oldMutual*100);
    }

    private String formatValue(double value) {
        return String.format("%.3f%%", value);
    }

    @Override
    public String toString() {
        return String.format(
                    "colf: %.3f%%\n" +
                    "prot: %.3f%%\n" +
                    "porv: %.3f%%\n" +
                    "old: %.3f%%",
                colfondos*100, proteccion*100, porvenir*100, oldMutual*100);
    }
}
