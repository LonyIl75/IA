package com.leekwars.generator.fight.action;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.fight.entity.Entity;

public class ActionAddEffect extends Action {

	static ActionAddEffect provider_action = new ActionAddEffect();

	public final int type;
	private final int itemID;
	private final int id;
	private final int caster;
	private final int target;
	private final int effectID;
	private final int value;
	private final int turns;
	private final int modifiers;


	public ActionAddEffect(){
		this(0);
	}
	public static int createEffect(Actions logs, int type, int itemID, Entity caster, Entity target, int value, int turns, boolean stacked, int modifiers) {
		return createEffect(logs, type,itemID, caster, target, getNotSetEffectID() , value, turns, stacked, modifiers);
	}
	public static int createEffect(Actions logs, int type, int itemID, Entity caster, Entity target, int effectID, int value, int turns, boolean stacked, int modifiers) {

		int r = logs.getEffectId();
		ActionAddEffect effect = new ActionAddEffect(type, itemID, r, caster.getFId(), target.getFId(), effectID, value, turns, stacked, modifiers);
		logs.log(effect);
		return r;
	}

	public static int notSet() {
		return -1;
	}
	public static boolean df_stacked() {
		return false;
	}
	
	public ActionAddEffect(int type ){
	

		this(type, ActionAddEffect.notSet(), ActionAddEffect.notSet(),ActionAddEffect.notSet(),ActionAddEffect.notSet(), ActionAddEffect.notSet(), ActionAddEffect.notSet(),ActionAddEffect.notSet(),ActionAddEffect.df_stacked(),ActionAddEffect.notSet());
	}


	public ActionAddEffect(int type, int itemID, int id, int caster, int target, int effectID, int value, int turns, boolean stacked, int modifiers) {

		if (type == Attack.TYPE_CHIP) {
			if (stacked) {
				this.type = Action.ADD_STACKED_EFFECT;
			} else {
				this.type = Action.ADD_CHIP_EFFECT;
			}
		} else if (type == Attack.TYPE_WEAPON) {
			if (stacked) {
				this.type = Action.ADD_STACKED_EFFECT;
			} else {
				this.type = Action.ADD_WEAPON_EFFECT;
				//System.out.println("WeaponID : " + itemID);
				
			}
		} else {
			this.type = type;
		}
		this.itemID = itemID;
		this.id = id;
		this.caster = caster;
		this.target = target;
		this.effectID = effectID;
		this.value = value;
		this.turns = turns;
		this.modifiers = modifiers;

		if(effectID !=getNotSetEffectID() ){
			fields=new String[]{"type","itemID","id","caster","target","effectID","value","turns","modifiers"};
		}else{
			fields=new String[]{"type","itemID","id","caster","target","value","turns","modifiers"};
		}

	}

	public static final int  getNotSetEffectID(){
		return -1;
	}

	@Override 
	public ArrayList<Action> getDefaultActions() {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (Integer id_typeItem : Attack.type_item ){
			actions.add(new ActionAddEffect(id_typeItem));
		}
		return actions ;
	}

public static Action getProvider(){
	return provider_action;
}



}
