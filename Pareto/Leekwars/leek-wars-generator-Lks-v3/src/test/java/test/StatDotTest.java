package test;


import org.junit.Test;

import com.leekwars.generator.fight.julienStats.Stats.StatDot;

public class StatDotTest implements IStatsEnumTest<StatDot> {

    @Override
    public StatDot createStatsEnum() {
        return new StatDot();
    }

    @Test
    public void  testJSONFileStatDot(){
        testJSONFileEnum();
    }


    
  
}