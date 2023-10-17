package com.leekwars.generator.fight.julienStats.lazyComputing;

import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum;

public class LazyResult <G extends Enum<G> ,T extends IStatsEnum<G>>{
    public LazyFunction<G,T> get_stats;
    public double value ;
    public int storedVersion ;

    public Object obj;
    public G index ;

    public LazyResult(LazyFunction<G,T> get_stats , Object obj ,G index ) {
        this.obj = obj;
        this.get_stats = get_stats;
        this.index = index;
        this.value = df_value();
        this.storedVersion = 0;
    }

    static public double df_value(){
        return Double.NaN;
    }

    public double getValue(int askVersion ) {
        if (storedVersion < askVersion) {
            get_stats.update(obj,index);
            value =Double.valueOf( (Integer)get_stats.exec(obj,index));
            storedVersion = askVersion ;
            System.out.println("New value demander");
        }

        return value;
    }
    public G getIndex(){
        return index;
    }

    public String toString(){
        return "LazyResult : \n Version : "+storedVersion+ "\nEnum :"+ index.name() + "\n Value: " + value+"\n Object"+obj.toString();
    }
  

}
/*
 * 
 * public interface Operator {
    int apply (int a, int b);
}

public enum SimpleOperators implements Operator {
    PLUS { 
        int apply(int a, int b) { return a + b; }
    },
    MINUS { 
        int apply(int a, int b) { return a - b; }
    };
}

public enum ComplexOperators implements Operator {
    // can't think of an example right now :-/
}
 */