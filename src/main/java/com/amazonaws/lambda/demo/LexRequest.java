package com.amazonaws.lambda.demo;

public class LexRequest {
	
    private String botName;
    private String intentName;
    private String requestedFeature;
    private String statedCreditScore;
	private String creditcard;
	private String agreetoanswer;
    
	public String getBotName() {
		return botName;
	}
	public void setBotName(String botName) {
		this.botName = botName;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public String getRequestedFeature() {
		return requestedFeature;
	}
	public void setRequestedFeature(String requestedFeature) {
		this.requestedFeature = requestedFeature;
	}
	public String getStatedCreditScore() {
		return statedCreditScore;
	}
	public void setStatedCreditScore(String statedCreditScore) {
		this.statedCreditScore = statedCreditScore;
	}
	public String getCreditcard() {
		return creditcard;
	}
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
	public String getAgreetoanswer() {
		return agreetoanswer;
	}
	public void setAgreetoanswer(String agreetoanswer) {
		this.agreetoanswer = agreetoanswer;
	}
	
}
