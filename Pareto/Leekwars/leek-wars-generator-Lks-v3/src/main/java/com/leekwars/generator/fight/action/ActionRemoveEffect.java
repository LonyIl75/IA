package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.effect.EffectDefault;

public class ActionRemoveEffect extends Action {

	static ActionRemoveEffect provider_action = new ActionRemoveEffect();

	private final int id;
	public final int type = Action.REMOVE_EFFECT;

	public ActionRemoveEffect(int id) {
		this.id = id;
		fields=new String[]{"type","id"};
	}




	public static int  notSetLogID() {
		EffectDefault effect = new EffectDefault();
		return effect.getLogID() ;
	}


	public ActionRemoveEffect() {
		
		this(notSetLogID());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionRemoveEffect()));
	}

public static Action getProvider(){
	return provider_action;
}



}
