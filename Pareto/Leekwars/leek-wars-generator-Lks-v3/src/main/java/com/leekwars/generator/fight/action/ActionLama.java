package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;

public class ActionLama extends Action {

	static ActionLama provider_action = new ActionLama();

	public final int type = Action.LAMA;
	public ActionLama() {
		fields=new String[]{"type"};

	}



	public static int notSet() {
		return -1;
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new   ActionLama()));
	}

public static Action getProvider(){
	return provider_action;
}


	
}
