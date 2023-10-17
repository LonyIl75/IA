package com.leekwars.generator.fight.julienStats.Stats;

import com.leekwars.generator.fight.julienStats.Stats.LazyStats.LazyBaseCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.LazyStats.LazyCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.LazyStats.LazyDot;

public class Provider {

    
    static StatCaracteristique provider_statCara  = new StatCaracteristique();
    static StatDot provider_statDot  = new StatDot();


    static LazyBaseCaracteristique lazy_baseCara = new LazyBaseCaracteristique(provider_statCara);
    static LazyCaracteristique lazy_statCara = new LazyCaracteristique(provider_statCara);
    static LazyDot lazy_statDot = new LazyDot(provider_statDot);

    
    
}
