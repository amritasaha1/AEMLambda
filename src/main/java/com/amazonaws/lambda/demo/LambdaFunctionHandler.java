package com.amazonaws.lambda.demo;

import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Map<String, Object>, Object> {

    @Override
    public Object handleRequest(Map<String,Object> input, Context context) {
       LexRequest lexRequest = (LexRequest) LexRequestFactory.createLexRequest(input);
       //service call
       Message message = new Message("PlainText","We will get back soon. We noted your credit score");
       DialogAction dialogAction = new DialogAction("Close","Fulfilled",message);
       return new LexResponse(dialogAction);
    }

}
