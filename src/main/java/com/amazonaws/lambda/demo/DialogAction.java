package com.amazonaws.lambda.demo;

public class DialogAction {
    private String type;
    private String fulfillmentState;
    private String intentName;
    private ResponseCard responseCard;
    private Slots slots;
    private Message message;
    private String elicitIntent;
    private String slotToElicit;

    public class Type {
        public static final String Close = "Close";
    }

    public class FulfillmentState {
        public static final String Fulfilled = "Fulfilled";
        public static final String ElicitSlot = "ElicitSlot";
        public static final String ElicitIntent = "ElicitIntent";
        public static final String Failed = "Failed";
    }
    
    public DialogAction(String type,Message message) {

        this.type = type;
        this.message = message;
    }
    
    public DialogAction(String type,String fulfillmentState,Message message) {

        this.type = type;
        this.fulfillmentState = fulfillmentState;
        this.message = message;
    }

    public DialogAction(String type,Message message,String intentName,ResponseCard responseCard ,Slots slots, String slotToElicit) {

        this.type = type;
        this.fulfillmentState = fulfillmentState;
        this.intentName = intentName;
        this.responseCard = responseCard;
        this.slots = slots;
        this.message = message;
        this.slotToElicit = slotToElicit;
    }
    
    public ResponseCard getResponseCard() {
		return responseCard;
	}

	public void setResponseCard(ResponseCard responseCard) {
		this.responseCard = responseCard;
	}

	public DialogAction() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFulfillmentState() {
        return fulfillmentState;
    }

    public void setFulfillmentState(String fulfillmentState) {
        this.fulfillmentState = fulfillmentState;
    }

    public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	
	public Slots getSlots() {
		return slots;
	}

	public void setSlots(Slots slots) {
		this.slots = slots;
	}

	public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

	public String getSlotToElicit() {
		return slotToElicit;
	}

	public void setSlotToElicit(String slotToElicit) {
		this.slotToElicit = slotToElicit;
	}

	public String getElicitIntent() {
		return elicitIntent;
	}

	public void setElicitIntent(String elicitIntent) {
		this.elicitIntent = elicitIntent;
	}
	
	
    
}

