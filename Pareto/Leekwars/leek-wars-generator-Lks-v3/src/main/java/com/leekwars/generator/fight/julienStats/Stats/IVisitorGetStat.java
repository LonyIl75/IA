package com.leekwars.generator.fight.julienStats.Stats;

import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.NegCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumEffect;

import leekscript.runner.AI;

interface IVisitorGetStat {
        double when(ICases c);
    
        
        interface ICases {
            
            double is(  enumCaracteristique _cara );
    
            double is( enumDot _dot );

            double is( enumEffect _effect );

            double is( NegCaracteristique  _negCara );

        }
    
    
}
