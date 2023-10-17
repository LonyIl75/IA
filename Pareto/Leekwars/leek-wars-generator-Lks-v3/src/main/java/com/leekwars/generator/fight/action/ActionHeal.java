package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionHeal extends Action {

	static ActionHeal provider_action = new ActionHeal();

	private final int target;
	private final int life;
	public final int type = Action.HEAL;

	public ActionHeal(Entity target, int life) {
		this.target = target.getFId();
		this.life = life;

		fields=new String[]{"type","target","life"};
	}



	public static int notSetLife() {
		return 0;
	}
	public ActionHeal() {
		this(new Leek(), notSetLife());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new  ActionHeal()));
	}


public static Action getProvider(){
	return provider_action;
}

}
