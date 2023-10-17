package com.leekwars.generator.fight.julienStats;
import java.util.ArrayList;

import com.leekwars.generator.fight.julienStats.Stats.LazyStats;
import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;

import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;

public class BoostEnum  {

    ArrayList<LazyStats<enumCaracteristique , StatCaracteristique>> boosts ; 
    int[] nbTurn ;

    public BoostEnum() {
        boosts = new ArrayList<LazyStats<enumCaracteristique , StatCaracteristique>>();
        nbTurn = new int[enumCaracteristique.values().length];
    }

    public void addBoost(LazyStats<enumCaracteristique , StatCaracteristique> boost , int nbTurn){
        boosts.add(boost);
        this.nbTurn[boosts.size()] = nbTurn;
    }

    
}
