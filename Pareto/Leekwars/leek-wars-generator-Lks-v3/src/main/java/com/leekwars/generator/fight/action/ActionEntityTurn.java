package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionEntityTurn extends Action {

	static ActionEntityTurn provider_action = new ActionEntityTurn();

	private final int id;
	public final int type = Action.LEEK_TURN;

	public ActionEntityTurn(Entity leek) {
		if (leek == null)
			this.id = -1;
		else {
			this.id = leek.getFId();
		}
		fields=new String[]{"type","id"};
	}



	public static int notSet() {
		return -1;
	}
	public ActionEntityTurn() {
		this(new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new   ActionEntityTurn()));
	}

public static Action getProvider(){
	return provider_action;
}


}
