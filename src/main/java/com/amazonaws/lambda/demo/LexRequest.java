package com.amazonaws.lambda.demo;

public class LexRequest {
	
    private String botName;
    private String intentName;
    private String requestedFeature;
    private String statedCreditScore;
    
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

    

}
