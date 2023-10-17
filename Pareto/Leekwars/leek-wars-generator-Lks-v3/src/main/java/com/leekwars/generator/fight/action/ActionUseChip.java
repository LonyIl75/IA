package com.leekwars.generator.fight.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.maps.Cell;

public class ActionUseChip extends Action {

	static ActionUseChip provider_action = new ActionUseChip();

	private final int cell;
	private final int chip;
	private final int success;

	public final int type = Action.USE_CHIP;


	public ActionUseChip(Cell cell, Chip chip, int success) {
		this.cell = cell.getId();
		this.chip = chip.getTemplate();
		this.success = success;

		fields=new String[]{"type","chip","cell","success"};
	}







	public   ActionUseChip() {
		this(new Cell(),Chip.df_Chip(),Attack.df_result());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new  ActionUseChip()));
	}

public static Action getProvider(){
	return provider_action;
}



}
