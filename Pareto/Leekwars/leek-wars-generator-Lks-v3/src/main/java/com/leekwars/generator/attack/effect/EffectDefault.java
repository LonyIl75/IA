package com.leekwars.generator.attack.effect;

import com.leekwars.generator.leek.Stats;

public class EffectDefault  extends Effect{


    public int getTurn() {
        return turns;
    }

    public double getAoe() {
        return aoe;
    }

    public boolean getCritical(){
        return critical;
    }
    public double getCriticalPower() {
        return criticalPower;
    }
    public Stats getStats() {
        return stats;
    }
    public int getLogID() {
        return logID;
    }
    public int getValue() {
        return value;
    }
    public int getPropagaet() {
        return propagate;
    }


    
    
}
