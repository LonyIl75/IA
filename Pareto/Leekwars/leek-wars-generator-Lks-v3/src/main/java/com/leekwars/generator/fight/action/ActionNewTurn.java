package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.Order;

public class ActionNewTurn extends Action {

	static ActionNewTurn provider_action = new ActionNewTurn();

	private final int count;

	public final int type = Action.NEW_TURN;

	public ActionNewTurn(int count) {
		this.count = count;
		fields=new String[]{"type","count"};
	}

		
	public static int  notSetCount() {
		Order order = new Order();
		return order.getTurn() ;
	}


	public ActionNewTurn() {
		this(notSetCount());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionNewTurn()));
	}

public static Action getProvider(){
	return provider_action;
}



}
