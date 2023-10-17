package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionRemovePoisons extends Action {

	static ActionRemovePoisons provider_action = new ActionRemovePoisons();

	private final int id;
	public final int type = Action.REMOVE_POISONS;

	public ActionRemovePoisons(Entity target) {
		this.id = target.getFId();

		fields=new String[]{"type","id"};
	}



	public ActionRemovePoisons() {
		
		this(new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionRemoveEffect()));
	}

public static Action getProvider(){
	return provider_action;
}


}
