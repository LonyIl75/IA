package com.leekwars.generator.fight.julienStats;

import java.util.ArrayList;

import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;

public class myEntityAI<G extends Enum<G>>  {


    public void updateStat(G enum_stat){
        System.out.println("updateStat");
        
    }

    public double getStat_base (enumCaracteristique cara){
        return 0;
    }

    public double getStat_atm (enumCaracteristique cara){
        return 0;
    }
    public double getStat_atm (enumDot cara){
        return 0;
        
    }


}
