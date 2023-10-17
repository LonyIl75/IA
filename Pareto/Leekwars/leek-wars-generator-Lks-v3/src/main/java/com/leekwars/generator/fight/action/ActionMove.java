package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;
import com.leekwars.generator.maps.Cell;

public class ActionMove extends Action {

	static ActionMove provider_action = new ActionMove();

	private final int leek;
	private final int[] path;
	private final int end;

	public final int type = Action.MOVE_TO;

	public ActionMove(Entity leek, List<Cell> path) {
		this.leek = leek.getFId();
		this.path = new int[path.size()];
		for (int i = 0; i < path.size(); i++) {
			this.path[i] = path.get(i).getId();
		}
		end = path.get(path.size() - 1).getId();

		fields = new String[] { "type", "leek", "end", "path" };
	}


	
	public static List<Cell> notSetPath() {
		return new ArrayList<Cell>(Arrays.asList(new Cell()));
	}


	public ActionMove() {
		this(new Leek(),notSetPath());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionMove()));
	}

public static Action getProvider(){
	return provider_action;
}




}
