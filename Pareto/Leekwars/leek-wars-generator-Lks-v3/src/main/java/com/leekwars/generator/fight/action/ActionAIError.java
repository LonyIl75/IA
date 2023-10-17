package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;

public class ActionAIError extends Action {

	static ActionAIError provider_action = new ActionAIError();

	private final int id;
	public final int type= Action.AI_ERROR;

	public ActionAIError() {
		this(null);
	}

	public ActionAIError(Entity leek) {
		if (leek == null)
			this.id = -1;
		else
			this.id = leek.getFId();

		fields=new String[]{"type","id"};
	}

	

	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionAIError()));
	}

public static Action getProvider(){
	return provider_action;
}

}
