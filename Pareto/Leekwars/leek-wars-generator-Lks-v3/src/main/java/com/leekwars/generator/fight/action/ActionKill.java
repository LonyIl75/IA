package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionKill extends Action {

	static ActionKill provider_action = new ActionKill();

	private final int caster;
	private final int target;
	public final int type = Action.KILL;

	public ActionKill(Entity caster, Entity target) {
		this.caster = target.getFId();
		this.target = target.getFId();

		fields=new String[]{"type","caster","target"};
	}


	
	public static int notSet() {
		return -1;
	}
	public ActionKill() {
		this(new Leek(),new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new   ActionKill()));
	}

public static Action getProvider(){
	return provider_action;
}



}
