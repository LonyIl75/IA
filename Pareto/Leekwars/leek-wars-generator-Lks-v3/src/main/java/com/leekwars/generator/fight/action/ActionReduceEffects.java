package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionReduceEffects extends Action {

	static ActionReduceEffects provider_action = new ActionReduceEffects();

	private final int id;
	private final int value;

	public final int type = Action.REDUCE_EFFECTS;

	public ActionReduceEffects(Entity target, int value) {
		this.id = target.getFId();
		this.value = value;

		fields=new String[]{"type","id","value"};
	}


				
	public static int  notSetValueEffect() {
		return 0 ;
	}


	public ActionReduceEffects() {
		this(new Leek(),notSetValueEffect());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionReduceEffects()));
	}

public static Action getProvider(){
	return provider_action;
}



}