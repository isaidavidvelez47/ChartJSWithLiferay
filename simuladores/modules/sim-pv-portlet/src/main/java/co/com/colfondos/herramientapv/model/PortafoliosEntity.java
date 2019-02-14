package co.com.colfondos.herramientapv.model;

import java.util.Arrays;
import java.util.List;

public class PortafoliosEntity {

    private BrandsEntity tradicional;

    private BrandsEntity dinamico;

    private BrandsEntity accionCol;

    private BrandsEntity rfi;

    private BrandsEntity rvi;

    public BrandsEntity getTradicional() {
        return tradicional;
    }

    public void setTradicional(BrandsEntity tradicional) {
        this.tradicional = tradicional;
    }

    public BrandsEntity getDinamico() {
        return dinamico;
    }

    public void setDinamico(BrandsEntity dinamico) {
        this.dinamico = dinamico;
    }

    public BrandsEntity getAccionCol() {
        return accionCol;
    }

    public void setAccionCol(BrandsEntity accionCol) {
        this.accionCol = accionCol;
    }

    public BrandsEntity getRfi() {
        return rfi;
    }

    public void setRfi(BrandsEntity rfi) {
        this.rfi = rfi;
    }

    public BrandsEntity getRvi() {
        return rvi;
    }

    public void setRvi(BrandsEntity rvi) {
        this.rvi = rvi;
    }

    public List<BrandsEntity> getAll() {
        return  Arrays.asList(tradicional, dinamico, accionCol, rfi, rvi);
    }
}
