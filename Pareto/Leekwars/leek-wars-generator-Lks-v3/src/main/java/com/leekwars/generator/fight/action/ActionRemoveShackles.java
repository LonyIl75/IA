package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionRemoveShackles extends Action {


	static ActionRemoveShackles provider_action = new ActionRemoveShackles();


	private final int id;
	public final int type = Action.REMOVE_SHACKLES;

	public ActionRemoveShackles(Entity target) {
		this.id = target.getFId();

		fields=new String[]{"type","id"};
	}




	
	public ActionRemoveShackles() {
		
		this(new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionRemoveShackles()));
	}

public static Action getProvider(){
	return provider_action;
}


}