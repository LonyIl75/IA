package com.leekwars.generator.fight.julienStats.ComputeDimension;

import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.julienStats.Stats._AffectStat;
import com.leekwars.generator.fight.julienStats.enCours.Stats;

public class AffectStat{


    public _AffectStat affect ;
    public Stats stat ; 
    public _AffectStat affectedBy ;

    public AffectStat(Stats stat) {
        this.stat = stat;
        affect = new _AffectStat(this.stat);
        affectedBy = new _AffectStat(this.stat);
        
    }
    public AffectStat(){
        this.stat = new Stats();
        affect = new _AffectStat(this.stat );
        affectedBy = new _AffectStat(this.stat );
        
    }

    public Stats getStat(){
        return stat;
    }

    public _AffectStat getAffect() {
        return affect;
    }

    public _AffectStat getAffectedBy() {
        return affectedBy;
    }

    public String toString(){
        return "Affect : "+this.affect.toString()+"\nAffectedBy : "+this.affectedBy.toString();
    }
    public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("affect", affect.toJson());
		json.put("affectedBy", affectedBy.toJson());
        return json ; 
    }


}