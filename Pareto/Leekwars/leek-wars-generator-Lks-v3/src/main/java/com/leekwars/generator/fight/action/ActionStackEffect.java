package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.effect.EffectDefault;

public class ActionStackEffect extends Action {

	static ActionStackEffect provider_action = new ActionStackEffect();

	private final int id;
	private final int value;
	public final int type = Action.STACK_EFFECT;

	public ActionStackEffect(int id, int value) {
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

	public ActionStackEffect() {
		
		this(notSetLogID(),notSetValue());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionStackEffect()));
	}

public static Action getProvider(){
	return provider_action;
}






}
