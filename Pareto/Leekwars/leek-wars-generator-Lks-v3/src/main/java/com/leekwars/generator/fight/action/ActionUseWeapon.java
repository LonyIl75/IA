package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.maps.Cell;

public class ActionUseWeapon extends Action {

	static ActionUseWeapon provider_action = new ActionUseWeapon();

	private final int cell;
	private final int success;

	public final int type = Action.USE_WEAPON;

	public ActionUseWeapon(Cell cell, int success) {
		this.cell = cell.getId();
		this.success = success;

		fields=new String[]{"type","cell","success"};
	}




	public   ActionUseWeapon() {
		this(new Cell(),Attack.df_result());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new  ActionUseWeapon()));
	}

public static Action getProvider(){
	return provider_action;
}




}
