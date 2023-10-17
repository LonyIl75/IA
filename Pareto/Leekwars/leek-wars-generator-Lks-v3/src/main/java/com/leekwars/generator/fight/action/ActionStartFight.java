package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ActionStartFight extends Action {

	static ActionStartFight provider_action = new ActionStartFight();

	int team1, team2;
	public final int type = Action.START_FIGHT;

	public ActionStartFight(int team1, int team2) {
		this.team1 = team1;
		this.team2 = team2;
		fields=new String[]{"type"};
	}


	public static int  df_team_id() {
		return 0;
	}

	public  ActionStartFight() {
		
		this(df_team_id(),df_team_id()+1);
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionStartFight()));
	}

public static Action getProvider(){
	return provider_action;
}






}
