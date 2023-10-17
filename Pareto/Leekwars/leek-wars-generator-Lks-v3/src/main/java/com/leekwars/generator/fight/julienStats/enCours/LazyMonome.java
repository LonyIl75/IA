package com.leekwars.generator.fight.julienStats.enCours;

import com.leekwars.generator.fight.julienStats.lazyComputing.LazyResult;

public class LazyMonome {
    public LazyResult lz_res ;
    public double coef;

    public LazyMonome(double coef , LazyResult lz_res) {
        this.coef = coef;
        this.lz_res = lz_res;
    }
    public double compute() {
        return -1.0 ;//coef * lz_res.getValue();
    }

   
}