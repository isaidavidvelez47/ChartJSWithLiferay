package co.com.colfondos.herramientapo.model;

/**
 * Class that represents AFP object including its
 * name, portfolio and months to take a decision
 */
public class PensionFund {

    private String name;
    private String portfolio;
    private int decisionMonths;

    public int getDecisionMonths() {
        return decisionMonths;
    }

    public void setDecisionMonths(int decisionMonths) {
        this.decisionMonths = decisionMonths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }
}
