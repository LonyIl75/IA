package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.weapons.Weapon;


public class ActionSetWeapon extends Action {

	static ActionSetWeapon provider_action = new ActionSetWeapon();

	public int leek;
	public int weapon;
	public final int type = Action.SET_WEAPON;

	public ActionSetWeapon(Weapon weapon) {
		this.weapon = weapon.getTemplate();

		fields=new String[]{"type","weapon"};
	}



	public ActionSetWeapon() {
		
		this(Weapon.df_Weapon());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionSetWeapon()));
	}

public static Action getProvider(){
	return provider_action;
}



}
