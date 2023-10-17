package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionChestOpened extends Action {

	static ActionChestOpened  provider_action = new ActionChestOpened();

	private final Entity killer;
	private final Entity chest;
	private Map<Integer, Integer> resources ;

	public final int type = Action.CHEST_OPENED;

	public ActionChestOpened(Entity killer, Entity chest, Map<Integer, Integer> resources) {
		this.killer = killer;
		this.chest = chest;
		this.resources = resources;
		fields=new String[]{"type","killer","chest"};
	}

	@Override
	public JSONArray getJSON() {

		JSONArray retour =   new JSONArray();
		retour.add(type);
		retour.add(killer.getFId());
		retour.add(chest.getFId());

		var res = new JSONObject();
		for (var r : resources.entrySet()) {
			res.put(String.valueOf(r.getKey()), r.getValue());
		}
		retour.add(res);
		return retour;
	}
	@Override
	public JSONArray getFieldsStrJson() {
		JSONArray retour =  super.getFieldsStrJson();
		retour.add("resources");
		return retour;
	}
	public ActionChestOpened() {
		this(new Leek() , new Leek(), new  HashMap<Integer, Integer>());
	}

	@Override
	public ArrayList<Action> getDefaultActions() {

		return new ArrayList<>(Arrays.asList(new ActionChestOpened()));
	}

public static Action getProvider(){
	return provider_action;
}


}
