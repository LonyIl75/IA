package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionLoseMP extends Action {

	static ActionLoseMP provider_action = new ActionLoseMP();

	private final int target;
	private final int mp;
	public final int type = Action.LOST_PM;

	public ActionLoseMP(Entity target, int pm) {

		this.target = target.getFId();
		this.mp = pm;

		fields=new String[]{"type","target","mp"};
	}



	public static int notSetMP() {
		return 0;
	}


	public ActionLoseMP() {
		this(new Leek(),notSetMP());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new   ActionLoseMP()));
	}

public static Action getProvider(){
	return provider_action;
}

	

}
