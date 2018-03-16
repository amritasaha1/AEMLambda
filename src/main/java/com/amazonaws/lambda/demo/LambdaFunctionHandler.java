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
	public Object handleRequest(Map<String, Object> input, Context context) {
		int i = 0;
		Map<String, Object> currentIntent = (Map<String, Object>) input.get("currentIntent");
		String slotString = currentIntent.get("slots").toString();
		LexRequest lexRequest = (LexRequest) LexRequestFactory.createLexRequest(input);
		DialogAction dialogAction = new DialogAction("Close", "Fulfilled",
				new Message("PlainText", "Sorry I did not understand what you said. You have a nice day!"));
		ResponseCard responseCard;
		try {
			String responseToLexMsg = slotString;
			i = i + 1;
			//thanksIntent
			int equalIndex6 = responseToLexMsg.indexOf("thanks=") + 7;
			i = i + 1;
			String responseToLexMsg7 = responseToLexMsg.substring(equalIndex6);
			i = i + 1;
			int commaIndex5 = responseToLexMsg7.indexOf(",");
			String thanks = responseToLexMsg7.substring(0, commaIndex5);
			i = i + 1;
			if(!(thanks.equalsIgnoreCase("null"))) {
				Message message = new Message("PlainText", "You are welcome, am glad I was able to assist.");
				dialogAction = new DialogAction("Close", "Fulfilled", message);
				i = i + 1;
			}else {
			// filltypeintent
			int equalIndex5 = responseToLexMsg.indexOf("filltypeintent=") + 15;
			i = i + 1;
			String responseToLexMsg6 = responseToLexMsg.substring(equalIndex5);
			i = i + 1;
			int commaIndex4 = responseToLexMsg6.indexOf(",");
			String filltypeintent = responseToLexMsg6.substring(0, commaIndex4);
			i = i + 1;
			if (!(filltypeintent.equalsIgnoreCase("null"))) {
				String defaultLink = "http://54.195.246.137/services/JsonResponceServlet?fName=karthik&sName=bajjuri&email=abc@gm.co&phone=12345678&preference=loan";
				LambdaLogger logger = context.getLogger();
				//service call
				Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null");
				String formLinkMessage = "Here is the link to a partially filled application for you – "+defaultLink;
				dialogAction = new DialogAction("ElicitSlot", "FirstCreditIntent", slots, "thanks",new Message("PlainText", formLinkMessage));
				i = i + 1;
			} else {
				// usercardintent
				int equalIndex4 = responseToLexMsg.indexOf("usercardintent=") + 15;
				i = i + 1;
				String responseToLexMsg5 = responseToLexMsg.substring(equalIndex4);
				i = i + 1;
				int commaIndex3 = responseToLexMsg5.indexOf(",");
				String usercardintent = responseToLexMsg5.substring(0, commaIndex3);
				i = i + 1;
				if (!(usercardintent.equalsIgnoreCase("null"))) {
					LambdaLogger logger = context.getLogger();
					Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null");
					String fillIntentMessage = "If you would like to complete your application now, "
							+ "I can assist you with that or you can complete the application yourself online."
							+ " Please suggest.";
					dialogAction = new DialogAction("ElicitSlot", "FirstCreditIntent", slots, "filltypeintent",
							new Message("PlainText", fillIntentMessage));
					i = i + 1;
				} else {
					// featureIntent
					int equalIndex3 = responseToLexMsg.indexOf("featureintent=") + 14;
					i = i + 1;
					String responseToLexMsg4 = responseToLexMsg.substring(equalIndex3);
					i = i + 1;
					int endBrcktIndex2 = responseToLexMsg4.indexOf("}");
					String featureIntent = responseToLexMsg4.substring(0, endBrcktIndex2);
					i = i + 1;
					if (!(featureIntent.equalsIgnoreCase("null"))) {
						LambdaLogger logger = context.getLogger();
						String responseToLex5 = callAEMServicefor(lexRequest.getBotName(), logger);
						responseCard = processResponse(responseToLex5);
						Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null");
						// dialogAction = new DialogAction("ElicitIntent",new Message("PlainText","Here
						// are some good offers for you -"+responseToLex5 + "; What type of card would
						// you like to choose?"), responseCard);
						dialogAction = new DialogAction("ElicitSlot", "FirstCreditIntent", slots, "usercardintent",
								responseCard, new Message("PlainText", "Here are some good offers for you -"
										+ responseToLex5 + "; What type of card would you like to choose?"));
						i = i + 1;
					} else {
						// Score
						int equalIndex2 = responseToLexMsg.indexOf("userscore=") + 10;
						String responseToLexMsg3 = responseToLexMsg.substring(equalIndex2);
						int commaIndex2 = responseToLexMsg3.indexOf(",");
						String userscore = responseToLexMsg3.substring(0, commaIndex2);
						i = i + 1;
						if (!(userscore.equalsIgnoreCase("null"))) {
							Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null");
							dialogAction = new DialogAction("ElicitSlot", "FirstCreditIntent", slots, "featureintent",
									new Message("PlainText",
											"Thanks, you have a good credit score, keep it up. Are you looking for any specific features like Travel miles, hotel miles, cash rewards?"));
						} else {
							// Agrre to Answer
							int equalIndex = responseToLexMsg.indexOf("agreetoanswer=") + 14;
							String responseToLexMsg2 = responseToLexMsg.substring(equalIndex);
							int commaIndex = responseToLexMsg2.indexOf(",");
							String agreeToAnswer = responseToLexMsg2.substring(0, commaIndex);
							if (agreeToAnswer.equalsIgnoreCase("no")) {
								dialogAction = new DialogAction("Close", "Fulfilled",
										new Message("PlainText", "Okay. You have a nice day!"));
							} else {
								responseCard = processScoreResponsecard();
								Slots slots = new Slots("null", "null", "null", "null", "null", "null", "null");
								dialogAction = new DialogAction("ElicitSlot", "FirstCreditIntent", slots, "userscore",
										responseCard);
							}
						}
					}
				}
			}
		}
		return new LexResponse(dialogAction);
		} catch (Exception e) {
			dialogAction = new DialogAction("Close", "Fulfilled",
					new Message("PlainText", "Sorry , I did not understand. Please try again."));
			return new LexResponse(dialogAction);
		}

	}

	private ResponseCard processScoreResponsecard() {
		// String scoreString = "<500 | 500 to 600 | 600 to 700 | 700 to 800 | 800+";
		Button buttonArray[] = new Button[5];
		Button button = null;
		button = new Button("500 or less", "500");
		buttonArray[0] = button;
		button = new Button("500 - 800", "500 - 800");
		buttonArray[1] = button;
		button = new Button("800 or more", "800");
		buttonArray[2] = button;
		Attachment attachmentArray[] = new Attachment[1];
		attachmentArray[0] = new Attachment(buttonArray, "What is your approximate credit score?", "Select the Score");
		ResponseCard responseCard = new ResponseCard(attachmentArray, 1, "application/vnd.amazonaws.card.generic");
		return responseCard;
	}

	private ResponseCard processResponse(String responseFrmAEM) {

		String[] cards = responseFrmAEM.split(",");
		List<String> cardList = Arrays.asList(cards);
		System.out.println(cardList);
		System.out.println(cards);
		Button buttonArray[] = new Button[3];
		Button button = null;
		for (int i = 0; i < cards.length; i++) {
			System.out.println("card from AEMS Array --- ");
			System.out.println(cards[i]);
			button = new Button(cards[i], cards[i]);
			buttonArray[i] = button;
		}
		System.out.println("buttonArray --- ");
		System.out.println(buttonArray);

		Attachment attachmentArray[] = new Attachment[1];
		attachmentArray[0] = new Attachment(buttonArray, "What type of card would you like to choose?",
				"Select the Card");
		ResponseCard responseCard = new ResponseCard(attachmentArray, 1, "application/vnd.amazonaws.card.generic");

		return responseCard;

	}

	private String callAEMServicefor(String userInput, LambdaLogger logger) {
		System.out.println("You are here in service called method.....");
		String serviceresponse = "This is mock service response....";
		BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("summituser", "abcd");
		ResponseEntity<String> jsonresult = restTemplate
				.getForEntity("http://54.195.246.137/bin/trainingServlet?query=cards", String.class);
		JSONObject jsonAEMResponse = new JSONObject(jsonresult);
		// logger.log("Full response details:: " + jsonAEMResponse.toString());
		String responseBody = (String) jsonAEMResponse.get("body");
		JSONObject responseJSON = new JSONObject(responseBody);
		serviceresponse = responseJSON.toString();
		JSONArray cardArray = (JSONArray) responseJSON.get("creditcards");
		StringBuffer creditCards = new StringBuffer();
		for (int i = 0; i < cardArray.length(); i++) {
			String s = (String) cardArray.getJSONObject(i).get("product_name");
			creditCards.append(s).append(",");
		}
		String responseToLex = creditCards.toString();
		logger.log(responseToLex);
		return responseToLex;
	}

}
