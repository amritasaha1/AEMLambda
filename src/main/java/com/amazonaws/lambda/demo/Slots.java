package com.amazonaws.lambda.demo;

public class Slots {
	
	private String intendedcard;
	private String mointent;
	private String endStmt;
	     
	public Slots(String intendedcard, String mointent, String endStmt) {
		this.intendedcard = intendedcard;
		this.mointent = mointent;
		this.endStmt = endStmt;
	}

	public String getIntendedcard() {
		return intendedcard;
	}

	public void setIntendedcard(String intendedcard) {
		this.intendedcard = intendedcard;
	}

	public String getMointent() {
		return mointent;
	}

	public void setMointent(String mointent) {
		this.mointent = mointent;
	}

	public String getEndStmt() {
		return endStmt;
	}

	public void setEndStmt(String endStmt) {
		this.endStmt = endStmt;
	}
	
	
	
}
