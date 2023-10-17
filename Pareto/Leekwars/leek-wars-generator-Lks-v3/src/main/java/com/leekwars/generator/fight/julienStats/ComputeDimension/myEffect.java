package com.leekwars.generator.fight.julienStats.ComputeDimension;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

public class myEffect{
    public AffectStat affect_receveur;
    public AffectStat affect_lanceur ;

    //public Stats lanceur , cible ;

    //public double weight1 , weight2 ;
    public double mean_weight ; 
    public int turns ;

    public myEffect(AffectStat affect_receveur ,AffectStat affect_lanceur ) {

        /*this.lanceur=stat_lanceur;
        this.cible = stat_receveur;*/
        this.affect_receveur= affect_receveur;
        this.affect_lanceur  = affect_lanceur;
        /*this.weight1 = 0.0;
        this.weight2 = 0.0;*/
        this.mean_weight = 0.0;
        this.turns = 0;

    
    }

    static String path = "./data";

    public  static String getClassName(){
        return "myEffect";
    }

    public   String getFilePath(){
        return path+File.separator+getClassName();//+".json";
    }


    public void init (AffectStat aff_lanceur ,AffectStat aff_receveur, double _weight1 , double _weight2 , int _turns) {
        if(isCompatible(aff_lanceur , aff_receveur) ){
        this.affect_receveur= aff_receveur;
        this.affect_lanceur  = aff_lanceur;
        /*this.weight1 = _weight1;
        this.weight2 = _weight2;*/
        this.mean_weight = Miscellaneous.mean2(_weight1 , _weight2);
        this.turns = _turns;
        }
        else{
            System.out.println("Erreur : les affectations ne sont pas compatibles");
        }
    }

    public boolean isCompatible(AffectStat aff_lanceur ,AffectStat aff_receveur){

        return (this.affect_receveur== null || affect_receveur.getAffect().getNbStat()==this.affect_receveur.getAffect().getNbStat()) && (this.affect_lanceur== null || affect_lanceur.getAffect().getNbStat()==this.affect_lanceur.getAffect().getNbStat() );

    }

    public String toString(){
        return //"Lanceur : "+this.lanceur.toString()+"\nCible : "+this.cible.toString()+
        "\nAffectation lanceur : "+this.affect_lanceur.toString()+"\nAffectation receveur : "+this.affect_receveur.toString()+"\nMean weight : "+this.mean_weight+"\nTurns : "+this.turns;
    }
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("receveur", affect_receveur.toJson());
        json.put("lanceur", affect_lanceur.toJson());
        return json ;
    }
    public  static JSONObject weaponToJson  (Stats states_11 , Stats states_22, Weapon weap ){
        AffectStat affect_stat_11 = null;
		AffectStat affect_stat_22 = null;
        myEffect effect = null;
        for (EffectParameters e : weap.getAttack().getEffects()){
            //System.out.println(e.toString());
        if(effect != null){
            affect_stat_11= effect.getAffect_lanceur();
            affect_stat_22= effect.getAffect_receveur() ;
            effect = ComputeEffect.getIndexEffectEntrave(affect_stat_11 ,affect_stat_22, e);
        }else{
            effect = ComputeEffect.getIndexEffectEntrave(states_11 ,states_22, e);
        }
        //System.out.println(effect.toString());
    }
    return effect.toJson();
    
    }



    public AffectStat getAffect_receveur() {
        return affect_receveur;
    }

    public AffectStat getAffect_lanceur() {
        return affect_lanceur;
    }

    public double getMeanWeight() {
        return mean_weight;
    }

    public int getTurns() {
        return turns;
    }
    

    /*public Stats getLanceur() {
       return lanceur;
    }

    public Stats getCible() {
        return cible;
    }

    public void setLanceur(Stats lanceur) {
        this.lanceur = lanceur;
    }

    public void setCible(Stats cible) {
        this.cible = cible;
    }*/

    




}

