package test;

import org.junit.Test;

import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;

public class StatCaracteristiqueTest implements IStatsEnumTest<StatCaracteristique> {

    @Override
    public StatCaracteristique createStatsEnum() {
        return new StatCaracteristique();
    }


    @Test
    public void  testJSONFileStatCaracteristique(){
        testJSONFileEnum();
    }

    
  
}