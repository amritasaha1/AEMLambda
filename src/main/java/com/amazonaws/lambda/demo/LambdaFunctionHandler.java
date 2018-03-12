package com.amazonaws.lambda.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Map<String, Object>, Object> {

    @Override
    public Object handleRequest(Map<String,Object> input, Context context) {
       LexRequest lexRequest = (LexRequest) LexRequestFactory.createLexRequest(input);
       //service call
       LambdaLogger logger = context.getLogger();
       String responseToLex = callAEMServicefor(lexRequest.getBotName(), logger);
       ResponseCard responseCard = processResponse(responseToLex);
          
       Message message = new Message("PlainText","What type of card would you like to choose?");
       Slots slots = new Slots("null","null","null","null");
       //DialogAction dialogAction = new DialogAction("ElicitSlot",message,"FirstCreditIntent",responseCard, slots,"agreetoanswer");
       responseToLex = "Great! Here are some good options for you -"+responseToLex;
       DialogAction dialogAction = new DialogAction("Close","Fulfilled",new Message("PlainText",responseToLex));
       return new LexResponse(dialogAction);
    }
    
    private ResponseCard processResponse(String responseFrmAEM) {
    	       
        String[] cards = responseFrmAEM.split(",");
        List<String> cardList = Arrays.asList(cards);
        System.out.println(cardList);
        System.out.println(cards);
        Button buttonArray[] = new Button[3];
        Button button = null;
        for(int i = 0; i< cards.length; i++){
        	System.out.println("card from AEMS Array --- ");
        	System.out.println(cards[i]);
        	button = new Button(cards[i],cards[i]);
        	buttonArray[i] = button;
       	}
        System.out.println("buttonArray --- ");
        System.out.println(buttonArray);
        
        Attachment attachmentArray[] = new Attachment[1];
        attachmentArray[0] = new Attachment(buttonArray,"What type of card would you like to choose?", "Select the Card");
    	ResponseCard responseCard = new ResponseCard(attachmentArray,1,"application/vnd.amazonaws.card.generic");
  	
    	return responseCard;
    	
    }
    
    
    private String callAEMServicefor(String userInput, LambdaLogger logger) {
        System.out.println("You are here in service called method.....");
        String serviceresponse = "This is mock service response....";
        BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("summituser", "abcd");
        ResponseEntity<String> jsonresult = restTemplate.getForEntity("http://54.195.246.137/bin/trainingServlet?query=cards", String.class);
        JSONObject jsonAEMResponse = new JSONObject(jsonresult);
        //logger.log("Full response details:: " + jsonAEMResponse.toString());
        String responseBody = (String)jsonAEMResponse.get("body");                    
        JSONObject responseJSON = new JSONObject(responseBody);
        serviceresponse = responseJSON.toString();
        JSONArray cardArray = (JSONArray)responseJSON.get("creditcards");
        StringBuffer creditCards = new StringBuffer();
        for(int i=0;i<cardArray.length();i++) {
                        String s = (String)cardArray.getJSONObject(i).get("product_name");
                        creditCards.append(s).append(",");
        }
        String responseToLex = creditCards.toString();
        logger.log(responseToLex);
        return responseToLex;
}

    
    

}
