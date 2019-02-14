package co.com.colfondos.herramientapo.model;

import java.util.Map;

/**
 * Class that store all the required parameters for
 * simulator to work and perform calculations
 */
public class PensionParameters {

    private double monthValue;
    private double regulatoryPension;
    private Map<String, Map<String, Double>> rentRisk;
    private Map<String, Map<String, Double>> rentMod;
    private Map<String, Map<String, Double>> rentCons;
    private String projectionType;
    private int pensionAge;

    public PensionParameters() {
        rentRisk = null;
        rentMod = null;
        rentCons = null;
    }

    public Map<String, Map<String, Double>> getRentRisk() {
        return rentRisk;
    }

    public void setRentRisk(Map<String, Map<String, Double>> rentRisk) {
        this.rentRisk = rentRisk;
    }

    public Map<String, Map<String, Double>> getRentMod() {
        return rentMod;
    }

    public void setRentMod(Map<String, Map<String, Double>> rentMod) {
        this.rentMod = rentMod;
    }

    public Map<String, Map<String, Double>> getRentCons() {
        return rentCons;
    }

    public void setRentCons(Map<String, Map<String, Double>> rentCons) {
        this.rentCons = rentCons;
    }

    public double getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(double monthValue) {
        this.monthValue = monthValue;
    }

    public double getRegulatoryPension() {
        return regulatoryPension;
    }

    public void setRegulatoryPension(double regulatoryPension) {
        this.regulatoryPension = regulatoryPension;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public int getPensionAge() {
        return pensionAge;
    }

    public void setPensionAge(int pensionAge) {
        this.pensionAge = pensionAge;
    }

}
