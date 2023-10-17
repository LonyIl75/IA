package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.effect.EffectDefault;

public class ActionUpdateEffect extends Action {

	static ActionUpdateEffect provider_action = new ActionUpdateEffect();
	
	private final int id;
	private final int value;
	public final int type = Action.UPDATE_EFFECT;

	public ActionUpdateEffect(int id, int value) {
		this.id = id;
		this.value = value;
		fields=new String[]{"type","id","value"};
	}


	public static int  notSetLogID() {
		EffectDefault effect = new EffectDefault();
		return effect.getLogID() ;
	}

	public static int  notSetValue() {
		EffectDefault effect = new EffectDefault();
		return effect.getValue() ;
	}

	public  ActionUpdateEffect() {
		
		this(notSetLogID(),notSetValue());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionUpdateEffect()));
	}

public static Action getProvider(){
	return provider_action;
}



}