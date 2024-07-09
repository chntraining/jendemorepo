package com.wipro.model;

import java.io.Serializable;

public class Salary implements Serializable {

	private int basic;
	private double hra;
	private double pf;
	private double gross;
	private double net;

	/*
	 * protected String salaryType; private double hourlyRate; private int
	 * hoursWorked; private double dailySalary;
	 */
	public Salary() {
		super();

	}

	public Salary(int basic, double hra, double pf, double gross, double net) {
		super();
		this.basic = basic;
		this.hra = hra;
		this.pf = pf;
		this.gross = gross;
		this.net = net;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public double getHra() {
		return hra;
	}

	public void setHra() {
		this.hra = this.getBasic() * 0.1;
	}

	public double getPf() {
		return pf;
	}

	public void setPf() {
		this.pf = this.getBasic() * 0.05;
	}

	public double getGross() {
		return gross;
	}

	public void setGross() {
		this.gross = this.getBasic() + this.getHra() + this.getPf();
	}

	public double getNet() {
		return net;
	}

	public void setNet() {
		this.net = this.getGross() - this.getPf();
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public void setPf(double pf) {
		this.pf = pf;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public void setNet(double net) {
		this.net = net;
	}

	@Override
	public String toString() {
		return "Salary [basic=" + basic + ", hra=" + hra + ", pf=" + pf + ", gross=" + gross + ", net=" + net + "]";
	}

}
