package com.leekwars.generator.fight.statistics;

import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.StatDot;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.julienStats.Stats.Stat;

import java.util.ArrayList; 
import java.util.HashMap;
import com.leekwars.generator.fight.julienStats.Stats.ArrayCaracteristique;
public class StatisticsManagerBis {

    //I'm sorry 
    public  HashMap<Integer,ArrayList<Integer>> nodes_activation ;
    public  HashMap<Integer,ArrayList<Integer>> leaf_activation ; //action et predicat atomique 

    public HashMap<Integer,ArrayCaracteristique> caracteristiques ;


    /* 
    Stat<enumDot,StatDot>dots_controller;
    public HashMap<Integer,ArrayList<enumCaracteristique >> dots ;

    Stat<enumCaracteristique,StatCaracteristique> effects_controller ;
    public HashMap<Integer,ArrayList<enumCaracteristique >> effects ;
    */


    public static final Object notSetAlready (){
        return null ; 
    }
    public StatisticsManagerBis(){
        nodes_activation = (HashMap<Integer, ArrayList<Integer>>) notSetAlready ();
        leaf_activation = (HashMap<Integer, ArrayList<Integer>>) notSetAlready () ;
        caracteristiques = new HashMap<>();
    }
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if(nodes_activation != notSetAlready () ) json.put("nodes_activation", nodes_activation);
        if(nodes_activation != notSetAlready () ) json.put("leaf_activation", leaf_activation);

        JSONArray json_caracteristique = new JSONArray();
        for (Integer id : caracteristiques.keySet()){
            JSONObject json_caracteristique_id = new JSONObject();
            json_caracteristique_id.put(id.toString(), caracteristiques.get(id).toJson());
            json_caracteristique.add(json_caracteristique_id);
        }
        json.put("caracteristiques", json_caracteristique);
        return json;
    }
    
    public void addCaracteristique(int id, ArrayCaracteristique c){
        caracteristiques.put(id, c);
    }

    
    
}
