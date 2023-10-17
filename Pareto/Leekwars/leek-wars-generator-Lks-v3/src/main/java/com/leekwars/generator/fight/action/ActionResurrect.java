package com.leekwars.generator.fight.action;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.leek.Leek;
import com.leekwars.generator.maps.Cell;

public class ActionResurrect extends Action {

	static ActionResurrect provider_action = new ActionResurrect();


	private final int target;
	private final int cell;
	private final int owner;
	private final int life;
	private final int max_life;
	public final int type = Action.RESURRECT;

	public ActionResurrect(Entity owner, Entity target) {
		this.owner = owner.getFId();
		this.target = target.getFId();
		Cell targetCell = target.getCell();
		this.cell = (targetCell == null ? new Cell().getId() : targetCell.getId() );
		this.life = target.getLife();
		this.max_life = target.getTotalLife();

		fields=new String[]{"type","owner","target","cell","life","max_life"};
	}



	public ActionResurrect() {
		
		this(new Leek(),new Leek());
	}


	@Override
	public ArrayList<Action> getDefaultActions() {
		return new ArrayList<>(Arrays.asList(new ActionResurrect()));
	}

public static Action getProvider(){
	return provider_action;
}






}
