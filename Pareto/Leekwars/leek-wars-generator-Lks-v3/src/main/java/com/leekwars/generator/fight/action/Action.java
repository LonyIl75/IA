package com.leekwars.generator.fight.action;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public abstract class  Action {

	// Actions
	public final static int START_FIGHT = 0;
	// public final static int USE_WEAPON_OLD = 1;
	// public final static int USE_CHIP = 2;
	// public final static int SET_WEAPON = 3;
	public final static int END_FIGHT = 4;
	public final static int PLAYER_DEAD = 5;
	public final static int NEW_TURN = 6;
	public final static int LEEK_TURN = 7;
	public final static int END_TURN = 8;
	public final static int SUMMON = 9;
	public final static int MOVE_TO = 10;
	public static final int KILL = 11;
	public final static int USE_CHIP = 12;
	public final static int SET_WEAPON = 13;
	public final static int STACK_EFFECT = 14;
	public final static int CHEST_OPENED = 15;
	public final static int USE_WEAPON = 16;

	// Buffs
	public final static int LOST_PT = 100;
	public final static int LOST_LIFE = 101;
	public final static int LOST_PM = 102;
	public final static int HEAL = 103;
	public static final int VITALITY = 104;
	public static final int RESURRECT = 105;
	public static final int LOSE_STRENGTH = 106;
	public final static int NOVA_DAMAGE = 107;
	public final static int DAMAGE_RETURN = 108;
	public final static int LIFE_DAMAGE = 109;
	public final static int POISON_DAMAGE = 110;
	public final static int AFTEREFFECT = 111;
	public static final int NOVA_VITALITY = 112;

	// "fun" actions
	// public final static int SAY_OLD = 200;
	public final static int LAMA = 201;
	// public final static int SHOW_CELL_OLD = 202;
	public final static int SAY = 203;
	public final static int SHOW_CELL = 205;

	// Effects
	public final static int ADD_WEAPON_EFFECT = 301;
	public final static int ADD_CHIP_EFFECT = 302;
	public final static int REMOVE_EFFECT = 303;
	public final static int UPDATE_EFFECT = 304;
	public final static int ADD_STACKED_EFFECT = 305;
	public final static int REDUCE_EFFECTS = 306; // Action juste pour afficher "Les effets de X sont réduits de Y%"
	public final static int REMOVE_POISONS = 307; // Action juste pour afficher "Les poisons de X sont neutralisés"
	public final static int REMOVE_SHACKLES = 308; // Action juste pour afficher "Les entraves de X sont retirées"

	// Other
	public final static int ERROR = 1000;
	public final static int MAP = 1001;
	public final static int AI_ERROR = 1002;

	public abstract ArrayList<Action> getDefaultActions();
	

	public final String name_fields = "name_fields";
	public final String value_fields = "value_fields";

	public final  static String [] headers = {"Times", "Buffs" , "Funs" , "Effects" , "Others"};// NE PAS METTRE EN STATIQUE !!!
	public final  static int interval_size = 100;

	public String[] fields  ;
	

	public JSONArray getJSON() {
		JSONArray retour = new JSONArray();

		Field field = null;


		for (String str_field : fields){
			
			try {
				field = this.getClass().getDeclaredField(str_field);
			 
				field.setAccessible(true);
				retour.add(field.get(this));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retour;
	}

	public ArrayList<JSONArray> getDefaultJSONs() {
		ArrayList<JSONArray> retour = new ArrayList<JSONArray>();
		getDefaultActions().forEach(action -> retour.add(action.getJSON()));
		return retour;
		

}

public JSONArray getFieldsStrJson() {
	JSONArray retour = new JSONArray();
	Field field = null;


	
	for (String str_field : fields){
		
		try {

			field = this.getClass().getDeclaredField(str_field);
		 
			field.setAccessible(true);
			retour.add(field.getName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	return retour;
}


public JSONObject getDfJson(){
	JSONObject retour = new JSONObject();

	JSONObject arr_value_fields ;
	JSONArray arr_str = getFieldsStrJson();
	ArrayList<JSONArray> arr_value  = getDefaultJSONs() ;
	JSONObject _retour ;


	for (int i = 0; i < arr_value.size(); i++) {
		_retour = new JSONObject();
		for (int k = 0; k < arr_str.size(); k++) {
			_retour.put((String)arr_str.get(k) , arr_value.get(i).get(k));
		}
		_retour.put(name_fields, arr_str);
		retour.put(String.valueOf(arr_value.get(i).get(0)), _retour);
	}

	return retour;
}

static Class<?>[] arr_class_actions = {
	ActionAddEffect.class,
	ActionAIError.class,
	ActionChestOpened.class,
	ActionDamage.class,
	ActionEndTurn.class,
	ActionEntityDie.class,
	ActionEntityTurn.class,
	ActionHeal.class,
	ActionInvocation.class,
	ActionKill.class,
	ActionLama.class,
	ActionLoseMP.class,
	ActionLoseTP.class,
	ActionMove.class,
	ActionNewTurn.class,
	ActionNovaVitality.class,
	ActionReduceEffects.class,
	ActionRemoveEffect.class,
	ActionRemovePoisons.class,
	ActionRemoveShackles.class,
	ActionResurrect.class,
	ActionSay.class,
	ActionSetWeapon.class,
	ActionShowCell.class,
	ActionStackEffect.class,
	ActionStartFight.class,
	ActionUpdateEffect.class,
	ActionUseChip.class,
	ActionUseWeapon.class,
	ActionVitality.class};

	public static JSONObject actionsToJson(){
		JSONObject json_obj =  new  JSONObject();

		for (Class<?> action : arr_class_actions) {
			try {
			json_obj.putAll(((Action)action.getDeclaredConstructor().newInstance()).getDfJson());
			//System.out.println(action.getSimpleName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return json_obj;
	}






}
