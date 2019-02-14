package co.com.colfondos.herramientapv.model;

import java.time.LocalDate;

public class RBrutaEntity {

    private int id;

    private LocalDate date;

    private double colfTrad;

    private double colfDinam;

    private double colfAccion;

    private double colfRfi;

    private double colfRfv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getColfTrad() {
        return colfTrad;
    }

    public void setColfTrad(double colfTrad) {
        this.colfTrad = colfTrad;
    }

    public double getColfDinam() {
        return colfDinam;
    }

    public void setColfDinam(double colfDinam) {
        this.colfDinam = colfDinam;
    }

    public double getColfAccion() {
        return colfAccion;
    }

    public void setColfAccion(double colfAccion) {
        this.colfAccion = colfAccion;
    }

    public double getColfRfi() {
        return colfRfi;
    }

    public void setColfRfi(double colfRfi) {
        this.colfRfi = colfRfi;
    }

    public double getColfRfv() {
        return colfRfv;
    }

    public void setColfRfv(double colfRfv) {
        this.colfRfv = colfRfv;
    }
}
