package model;

import java.io.Serializable;

public class IncomeOutcome implements Serializable{
	private double income;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getOutcome() {
        return outcome;
    }

    public void setOutcome(double outcome) {
        this.outcome = outcome;
    }

    private double outcome;

	public IncomeOutcome(double income, double outcome){
        this.income = income;
        this.outcome = outcome;
    }

}
