package com.leekwars.generator.fight.julienStats.Stats;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;

public class ArrayCaracteristique  {

    public static Stat<enumCaracteristique,StatCaracteristique> caracteristiques_controller;
    private double [] caracteristiques ;

    public ArrayCaracteristique(){
        caracteristiques_controller = new Stat<enumCaracteristique,StatCaracteristique>(new StatCaracteristique() , false);
        caracteristiques = new double[caracteristiques_controller.getSize()];
    }

    public double get(enumCaracteristique c){
        return caracteristiques_controller.getSat(c, caracteristiques);
    }
    public void set(enumCaracteristique c, double value){
        caracteristiques_controller.setStat(c, caracteristiques, value);
    }

    public JSONArray toJson() {

        JSONArray jsonCaracteristiques = new JSONArray();
        for (enumCaracteristique c : enumCaracteristique.values()){
            jsonCaracteristiques.add(get(c));
        }
        return  jsonCaracteristiques;

    }

    
    
}
