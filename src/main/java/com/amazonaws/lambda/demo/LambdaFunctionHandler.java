package com.amazonaws.lambda.demo;

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
       String aemOutput = callAEMServicefor(lexRequest.getBotName(), logger);
          
       Message message = new Message("PlainText",aemOutput);
       DialogAction dialogAction = new DialogAction("Close","Fulfilled",message);
       return new LexResponse(dialogAction);
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
        logger.log("Available Credit Cards:: " +  creditCards.toString());
        return serviceresponse;
}

    
    

}
