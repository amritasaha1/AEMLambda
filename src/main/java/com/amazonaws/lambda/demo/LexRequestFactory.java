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
       //lexRequest.setIntentName(currentIntent.toString());
        lexRequest.setRequestedFeature((String) input.get("feature"));
        lexRequest.setStatedCreditScore((String)input.get("score"));
        return lexRequest;
    }
}
