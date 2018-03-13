package com.amazonaws.lambda.demo;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.util.TextUtils.isEmpty;

public class LexRequestFactory {
    public static LexRequest createLexRequest(Map<String, Object> input) {
        Map<String,Object> botMap = (Map<String,Object>) input.get("bot");
        String botName = (String) botMap.get("name");
        LexRequest lexRequest = new LexRequest();
        lexRequest.setBotName(botName);
        Map<String,Object> currentIntent = (Map<String,Object>) input.get("currentIntent");
        System.out.println(botMap.keySet().toString());
        System.out.println(currentIntent.keySet().toString());
        for (String key : currentIntent.keySet()) {
            System.out.println(key + " -- " + currentIntent.get(key));
        }
        //Map<String,Object> slotDetails = (Map<String,Object>) input.get("slotDetails");
        //System.out.println(slotDetails.keySet().toString());
       //lexRequest.setIntentName(currentIntent.toString());
        lexRequest.setCredit((String) input.get("credit"));
        lexRequest.setType((String)input.get("type"));
        lexRequest.setFeature((String) input.get("feature"));
        lexRequest.setScore((String)input.get("score"));
        lexRequest.setScore((String)input.get("usercardintent"));
        lexRequest.setScore((String)input.get("filltypeintent"));
        lexRequest.setScore((String)input.get("thanks"));
        return lexRequest;
    }
}
