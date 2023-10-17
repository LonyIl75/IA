package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ActionSay extends Action {

	static ActionSay provider_action = new ActionSay();

	private final String message;
	public final int type = Action.SAY;

	public ActionSay(String message) {
		this.message = message;
		fields=new String[]{"type","message"};
	}

	@Override
	public JSONArray getJSON() {
		JSONArray retour = new JSONArray();
		retour.add(Action.SAY);
		retour.add(message.replaceAll("\t", "    "));
		return retour;
	}

	public static String notSetMessage() {
		return "";
	}

	public ActionSay() {
		
		this(notSetMessage());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionSay()));
	}

public static Action getProvider(){
	return provider_action;
}





}
