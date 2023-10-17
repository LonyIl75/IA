package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionEndTurn extends Action {

	static ActionEndTurn provider_action = new ActionEndTurn();

	private final int target;
	private final int pt;
	private final int pm;
	public final int type = Action.END_TURN;



	public ActionEndTurn(Entity target) {

		this.target = target.getFId();
		this.pt = target.getTP();
		this.pm = target.getMP();
		fields=new String[]{"type","target","pt","pm"};
		
	}



	public static int notSet() {
		return -1;
	}
	public ActionEndTurn() {
		this(new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new  ActionEndTurn()));
	}


public static Action getProvider(){
	return provider_action;
}

}
