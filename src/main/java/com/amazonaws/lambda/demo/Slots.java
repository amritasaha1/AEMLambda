package com.amazonaws.lambda.demo;

public class Slots {
	
	private String usercardintent;
    private String score;
    private String type;
	private String credit;
	private String feature;
	private String thanks;
	private String filltypeintent;
	
	
	public Slots(String usercardintent, String score, String type, String credit, String feature, String thanks, String filltypeintent) {
		this.usercardintent = usercardintent;
		this.score = score;
		this.type = type;
		this.credit = credit;
		this.feature = feature;
		this.thanks = thanks;
		this.filltypeintent = filltypeintent;
	}
	
	
	public String getThanks() {
		return thanks;
	}

	public void setThanks(String thanks) {
		this.thanks = thanks;
	}

	public String getFilltypeintent() {
		return filltypeintent;
	}

	public void setFilltypeintent(String filltypeintent) {
		this.filltypeintent = filltypeintent;
	}



	public String getUsercardintent() {
		return usercardintent;
	}

	public void setUsercardintent(String usercardintent) {
		this.usercardintent = usercardintent;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
	
}
