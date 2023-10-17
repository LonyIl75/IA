package com.leekwars.generator.fight.julienStats.Stats;

import java.util.ArrayList;

import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.util.MySet;
import com.leekwars.generator.fight.julienStats.util.UtilBin;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.julienBdd.weaponPattern;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;

public class _AffectStat {
    /*MySet affect_baseStat;
    MySet affect_atmStat;
    MySet affect_Dot;

    MySet affectedBy_atmStat;
    MySet affectedBy_baseStat;
    MySet affectedBy_Dot;*/
    ArrayList<MySet> affect_stat = new ArrayList<MySet>();

    int nbStat ;
    static int index_base ,index_atm ,index_dot ;

    int is ;

    public void setIsPositional() {
        is= UtilBin.setBit(is,0);
    }
    public void setIsBulb() {
        is= UtilBin.setBit(is,1);
    }
    public void setIsEntity() {
        is= UtilBin.setBit(is,2);
    }

    public int getIs() {
        return is;
    }
    Stats stat ; 

    public String toString(){
        String res ="";
       for (MySet mySet : affect_stat) {
        res+=(mySet.toString()+",");
    }
    res+=weaponPattern.toStringBinary(is);

    return res;
    }
    public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("stat_base", getAffectBaseStat().toJson());
		json.put("stat_atm", getAffectAtmStat().toJson());
		json.put("stat_dot", getAffectDot().toJson());
		return json;
	} 

    public _AffectStat(Stats stat) {
        this.stat = stat ;
        nbStat =0 ;
        is = 0 ;
        init();
    }

    public _AffectStat() {
        this.stat = new Stats() ;
        nbStat =0 ;
        is = 0 ;
        init();
    }



    public void init(){
        addStat(stat.base_caracs.getInitSet()); index_base = 0;
        addStat(stat.atm_caracs.getInitSet()); index_atm = 1 ;
        addStat(stat.dot.getInitSet()); index_dot = 2 ;

    }

    private void addStat(MySet set){
        affect_stat.add(set);
        nbStat++;
    }
    public int getNbStat() {
        return nbStat;
    }

    public Stats getStat() {
        return stat;
    }


    public MySet getAffectBaseStat() {
        return affect_stat.get(index_base);
    }

    public MySet getAffectAtmStat() {
        return affect_stat.get(index_atm);
    }

    public MySet getAffectDot() {
        return affect_stat.get(index_dot);
    }


    
    public void addAffectAtmStat(enumCaracteristique cara) {

        stat.atm_caracs.addToSet(getAffectAtmStat(),cara);

    }
    public void addAffectBaseStat(enumCaracteristique cara) {
        stat.base_caracs.addToSet(getAffectBaseStat(),cara);
    }

    public void addAffectDot(enumDot dot) {

        stat.dot.addToSet(getAffectDot(),dot);
    
    }



    public void addAffectAtmStat(MySet set ) {
        stat.atm_caracs.fusionSet(getAffectAtmStat(),set);
    }
    public void addAffectBaseStat(MySet set ) {
        stat.base_caracs.fusionSet(getAffectBaseStat(),set);
    }

    public void addAffectDot(MySet set ) {
        stat.dot.fusionSet(getAffectDot(),set);
    }



    public void addAffectDot(ArrayList<enumDot> dots) {
        stat.dot.addToSet(getAffectDot(),dots);
    }
    


    public MySet setAllAtmStat(){
        return stat.atm_caracs.setAllEnum();
    }
    public MySet setAllBaseStat(){
        return stat.base_caracs.setAllEnum();
    }
    public MySet setAllDot(){
        return stat.dot.setAllEnum();
    }





}