package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;
import com.leekwars.generator.maps.Cell;

public class ActionInvocation extends Action {

	static ActionInvocation provider_action = new ActionInvocation();


	private final int target;
	private final int cell;
	private final int owner;
	private final int result;
	public final int type = Action.SUMMON;

	public ActionInvocation(Entity target, int result) {
		Entity _summoner = target.getSummoner();
		this.owner = (_summoner != null ? _summoner.getFId() : new Leek().getFId());
		this.target = target.getFId();
		Cell _cell = target.getCell();
		this.cell = (_cell != null ? _cell.getId() : new Cell().getId());
		this.result = result;

		fields=new String[]{"type","owner","target","cell","result"};
	}



	
	public static int df_resultChip() {
		return Attack.USE_FAILED;
	}
	public ActionInvocation() {
		this(new Leek(),  df_resultChip());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new  ActionInvocation()));
	}
	
public static Action getProvider(){
	return provider_action;
}



}
