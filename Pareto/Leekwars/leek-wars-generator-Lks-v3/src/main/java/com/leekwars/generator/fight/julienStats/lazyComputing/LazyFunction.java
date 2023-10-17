package com.leekwars.generator.fight.julienStats.lazyComputing;

import java.util.ArrayList;

import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum;

public abstract class  LazyFunction <G extends Enum<G> ,T extends IStatsEnum<G>> {
    public abstract Object exec(Object  obj  ,G index  );
    public abstract void update(Object obj , G index  );
    T provider ;
    public LazyFunction(T provider){
        this.provider = provider;
    }

    public   ArrayList<LazyResult<G,T> >  init_lazyResults(EntityAI entity ){
        ArrayList<LazyResult<G,T> >  lazyResults  = new ArrayList<LazyResult<G,T>>();

        for ( G c : provider.values() )
        {
            lazyResults.add(new LazyResult<G,T>( this , entity , c));
        }
        
        return lazyResults;
    }

}