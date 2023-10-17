package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;

public class ActionVitality extends Action {


	static ActionVitality provider_action = new ActionVitality();


	private final int target;
	private final int life;

	public final int type = Action.VITALITY;

	public ActionVitality(Entity target, int life) {
		this.target = target.getFId();
		this.life = life;

		fields=new String[]{"type","target","life"};
	}



	public static int notSetLife()
	{
		return 0 ;
	}


	public    ActionVitality() {
		this(new Leek(),notSetLife());
	}


public static Action getProvider(){
	return provider_action;
}

	


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionVitality()));
	}






}
