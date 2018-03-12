package com.amazonaws.lambda.demo;

public class Slots {
	
	//private String intendedcard;
	private String agreetoanswer;
	private String userscore;
	private String creditcard;
	private String featureintent;
	     
	public Slots(String agreetoanswer, String userscore,String creditcard, String featureintent) {
		//this.intendedcard = intendedcard;
		this.agreetoanswer = agreetoanswer;
		this.userscore = userscore;
		this.creditcard = creditcard;
		this.featureintent = featureintent;
	}


	public String getAgreetoanswer() {
		return agreetoanswer;
	}

	public void setAgreetoanswer(String agreetoanswer) {
		this.agreetoanswer = agreetoanswer;
	}

	public String getUserscore() {
		return userscore;
	}

	public void setUserscore(String userscore) {
		this.userscore = userscore;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public String getFeatureintent() {
		return featureintent;
	}

	public void setFeatureintent(String featureintent) {
		this.featureintent = featureintent;
	}

   
	
	
	
}
