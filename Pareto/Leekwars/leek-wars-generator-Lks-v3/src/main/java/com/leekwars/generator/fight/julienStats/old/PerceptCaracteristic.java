package com.leekwars.generator.fight.julienStats.old;


//import com.leekwars.generator.fight.julienStats.Stats.StatsEnum.Caracteristique;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyFunction;

public class PerceptCaracteristic{
 /*
    static LazyFunction[] percepts = new LazyFunction[StatsEnum.getSizeCaracteristique()];
    //getIndexOfCara(
    public static void init() {
        Caracteristique cara;
        for(int i = 0; i < StatsEnum.getSizeCaracteristique(); i++) {
            cara = StatsEnum.intToCarac(i);
            percepts[i] = new LazyFunction() {
                @Override
                public double exec(Object obj , int index ) {
                    return ((PerceptEffect) obj).getCaracteristique(index);
                }

                @Override
                public void update(Object obj, int index ) {
                    ((PerceptEffect) obj).setCaracteristique(index);
                }
            };
        }*/

       
    
}
 /*percepts[StatsEnum.myEffectEnum.DAMAGE.ordinal()] = new LazyFunction() {
    @Override
    public double exec(Object obj) {
        return ((PerceptEffect) obj).getDamage();
    }

    @Override
    public void update(Object obj) {
        ((PerceptEffect) obj).setDamage();
    }
};*/