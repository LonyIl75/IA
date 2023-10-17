package test;


import org.junit.Test;

import com.leekwars.generator.fight.julienStats.Stats.StatEffect;

public class StatEffectTest implements IStatsEnumTest<StatEffect> {

    @Override
    public StatEffect createStatsEnum() {
        return new StatEffect();
    }

    @Test
    public void  testJSONFileStatEffect(){
        testJSONFileEnum();
    }
  
}