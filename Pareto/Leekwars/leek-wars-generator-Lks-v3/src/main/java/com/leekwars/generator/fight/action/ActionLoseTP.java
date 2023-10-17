package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionLoseTP extends Action {

	static ActionLoseTP provider_action = new ActionLoseTP();

	private final int target;
	private final int tp;
	public final int type = Action.LOST_PT;

	public ActionLoseTP(Entity target, int pt) {

		this.target = target.getFId();
		this.tp = pt;

		fields=new String[]{"type","target","tp"};
	}


	public static int notSetTP() {
		return 0;
	}


	public ActionLoseTP() {
		this(new Leek(),notSetTP());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new   ActionLoseMP()));
	}

public static Action getProvider(){
	return provider_action;
}



}
