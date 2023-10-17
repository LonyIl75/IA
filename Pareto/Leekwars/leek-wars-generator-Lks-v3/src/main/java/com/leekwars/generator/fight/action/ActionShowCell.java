package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.Util;
import com.leekwars.generator.maps.Cell;

public class ActionShowCell extends Action {

	static ActionShowCell provider_action = new ActionShowCell();

	private final int mCell;
	private final int mColor;

	public final int type = Action.SHOW_CELL;

	public ActionShowCell(int cell, int color) {
		mCell = cell;
		mColor = color;
		fields=new String[]{"type","mCell","mColor"};
	}

	@Override
	public JSONArray getJSON() {
		JSONArray retour = new JSONArray();
		retour.add(type);
		retour.add(mCell);
		retour.add(Util.getHexaColor(mColor));

		return retour;
	}

	public static int df_Color() {
		return 0xffffff;
	}



	public ActionShowCell() {
		
		this(Cell.df_id_Cell()  , df_Color());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionShowCell()));
	}

public static Action getProvider(){
	return provider_action;
}




}
