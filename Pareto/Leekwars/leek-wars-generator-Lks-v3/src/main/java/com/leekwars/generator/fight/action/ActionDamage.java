package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionDamage extends Action {

	static ActionDamage provider_action = new ActionDamage();

	private final DamageType type;
	private final int target;
	private final int pv;
	private final int erosion;
	private int dmg_type;

	public static int notSet() {
		return -1;
	}
	public static DamageType df_dmgType() {
		return DamageType.NOTSET;
	}
	public ActionDamage() {
		this(ActionDamage.df_dmgType(), new Leek(), ActionDamage.notSet(),ActionDamage.notSet());
	}
	public ActionDamage(DamageType type, Entity target, int pv, int erosion) {
		this.type = type;
		this.target = target.getFId();
		this.pv = pv;
		this.erosion = erosion;
		fields=new String[]{"dmg_type","target","pv","erosion"};
		dmg_type=type.value;
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		ArrayList<Action> actions = new ArrayList<>();
		for ( DamageType type : DamageType.values() ) {
			if (type != DamageType.NOTSET) {
				actions.add(new ActionDamage(type, new Leek(), 0, 0));
			}
		}
		return actions;
	}

public static Action getProvider(){
	return provider_action;
}

}
