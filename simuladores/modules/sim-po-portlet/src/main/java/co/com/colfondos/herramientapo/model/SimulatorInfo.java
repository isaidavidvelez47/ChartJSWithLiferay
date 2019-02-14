package co.com.colfondos.herramientapo.model;

import java.time.LocalDate;

/**
 * Class with relevant info about simulator
 * Dates and SOAP service info
 */
public class SimulatorInfo {

    private LocalDate beginDate;
    private LocalDate endDate;

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
