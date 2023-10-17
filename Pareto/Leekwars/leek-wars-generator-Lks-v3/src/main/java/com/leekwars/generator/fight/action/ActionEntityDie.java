package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionEntityDie extends Action {

	static ActionEntityDie provider_action = new ActionEntityDie();

	private final int id;
	private final int killer;
	public final int type = Action.PLAYER_DEAD;

	public ActionEntityDie(Entity leek, Entity killer) {
		this.id = leek.getFId();
		this.killer = killer != null ? killer.getFId() : -1;
		if( this.killer != -1) {
			fields=new String[]{"type","id","killer"};
		}
		else {
			fields=new String[]{"type","id"};
		}
	}


	public static int notSet() {
		return -1;
	}
	public ActionEntityDie() {
		this(new Leek(),new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new  ActionEntityDie()));
	}

public static Action getProvider(){
	return provider_action;
}

}
