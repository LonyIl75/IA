package com.leekwars.generator.fight.julienStats.enCours;

import java.util.ArrayList;

import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienStats.BoostEnum;
import com.leekwars.generator.fight.julienStats.Stats.LazyStats;
import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.StatDot;

import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyFunction;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;


public class Stats  {
    

    public EntityAI entity;

    public LazyStats<enumCaracteristique,StatCaracteristique> base_caracs;

    public LazyStats<enumCaracteristique,StatCaracteristique>atm_caracs;//update a chaque entrave

    public LazyStats<enumDot,StatDot> dot;//update a chaque entrave
    
    BoostEnum  entraveOrBoost ; 
    public Stats(EntityAI entity ){
        this.entity = entity;
        base_caracs = new LazyStats<enumCaracteristique,StatCaracteristique>(entity, new StatCaracteristique(), true);
        atm_caracs = new LazyStats<enumCaracteristique,StatCaracteristique>(entity, new StatCaracteristique(), false);
        dot = new LazyStats<enumDot,StatDot>(entity, new StatDot(), false);
        entraveOrBoost = new BoostEnum();


    }
    
    public Stats(){
        this.entity = new EntityAI(0, 11);
        base_caracs = new LazyStats<enumCaracteristique,StatCaracteristique>(entity, new StatCaracteristique(), true);
        atm_caracs = new LazyStats<enumCaracteristique,StatCaracteristique>(entity, new StatCaracteristique(), false);
        dot = new LazyStats<enumDot,StatDot>(entity, new StatDot(), false);
        entraveOrBoost = new BoostEnum();


    }


    public String toString(){
        return "ATM caracs : " + atm_caracs.toString() +"DOT  : " + dot.toString() +"EntraveOrBoost  : " + entraveOrBoost.toString(); 
    }

    /* 

    //ENTRAVE OR BOOST

    public int getEntraveOrBoostTurn(int carac){
        return entraveOrBoostTurn[carac];
    }


     //ENTRAVE OR BOOST

    public int getEntraveOrBoostTurn(enumCaracteristique carac){
        return getEntraveOrBoostTurn(entraveOrBoostValue.enumToIndex(carac));
    }



      //constructeur par défaut :
    public Stats() {
        this(getDefaultTab(),getDefaultTab(),getDefaultTab(),getDefaultTab());
        
    }


    //constructeur par copie :

    public Stats(double[] _caracs) {
        this(_caracs,getDefaultTab(),getDefaultTab(),getDefaultTab());
    }


    public Stats(double[] _caracs , double [] _atm_caracs) {
        this( _caracs, _atm_caracs,getDefaultTab(),getDefaultTab());
    }

    public Stats(double[] _caracs , double [] _atm_caracs, double [] _entraveOrBoostValue) {
        this( _caracs, _atm_caracs,_entraveOrBoostValue,getDefaultTab());
    }
    public Stats(double[] _caracs  , double [] _atm_caracs, double [] _entraveOrBoostValue , double [] _entraveOrBoostTurn) {
        this.init_caracs=Miscellaneous.cpyArr(this.init_caracs , _caracs);
        this.atm_caracs=Miscellaneous.cpyArr(this.atm_caracs , _atm_caracs);
        this.entraveOrBoostValue=Miscellaneous.cpyArr(this.entraveOrBoostValue , _entraveOrBoostValue);
        this.entraveOrBoostTurn=Miscellaneous.cpyArr(this.entraveOrBoostTurn , _entraveOrBoostTurn);
    }
    


    public Stats(Stats _stats) {
        this( _stats.getInitCaracs(), _stats.getAtmCaracs(), _stats.getEntraveOrBoostValue(), _stats.getEntraveOrBoostTurn());

    }


    public double[] getInitCaracs() {
        return Miscellaneous.cpyArr(null,init_caracs);
    }

    public void setInitCaracs(double[] init_caracs) {
        if(init_caracs.length != getSizeInitCaracs()){
            System.out.println("Erreur : atm_caracs.length != getSizeCaracsInit()");
        }else{
        this.init_caracs = Miscellaneous.cpyArr(this.init_caracs,init_caracs);
        }
    }

    public double[] getAtmCaracs() {
        return Miscellaneous.cpyArr(null,atm_caracs);
    }

    public void setAtmCaracs(double[] atm_caracs) {
        if(atm_caracs.length != getSizeAtmCaracs()){
            System.out.println("Erreur : atm_caracs.length != getSizeCaracsAtm()");
        }else{

        this.atm_caracs = Miscellaneous.cpyArr(this.atm_caracs,atm_caracs);
        }
    }

  
    public double[] getEntraveOrBoostValue() {
        return Miscellaneous.cpyArr(null,entraveOrBoostValue);
    }
    public double[] getEntraveOrBoostTurn() {
        return Miscellaneous.cpyArr(null,entraveOrBoostTurn);
    }
    public void setEntraveOrBoostTurn(double[] entraveOrBoostTurn) {
        if(entraveOrBoostTurn.length != entraveOrBoostTurn()){
            System.out.println("Erreur : entraveOrBoostTurn.length != getSizeCaracsAtm()");
        }else{
        this.entraveOrBoostTurn = Miscellaneous.cpyArr(this.entraveOrBoostTurn,entraveOrBoostTurn);
        }
    }
    public void setEntraveOrBoostValue(double[] entraveOrBoostValue) {
        if(entraveOrBoostValue.length != getSizeEntraveOrBoostValue()){
            System.out.println("Erreur : entraveOrBoostValue.length != getSizeCaracsAtm()");
        }else{
        this.entraveOrBoostValue = Miscellaneous.cpyArr(this.entraveOrBoostValue,entraveOrBoostValue);
        }

    }
    public static int getSizeInitCaracs(){
        return StatsEnum.getSizeCaracteristique();
    }

    public static int getSizeAtmCaracs(){
        return StatsEnum.getSizeCaracteristique();
    }
    public static int getSizeEntraveOrBoostValue(){
        return StatsEnum.getSizeCaracteristique();
    }
    public static int entraveOrBoostTurn()    {
        return StatsEnum.getSizeCaracteristique();
    }

    
    public void setAtmCaracs(Caracteristique cara , double value){
        atm_caracs[StatsEnum.getIndexOfCara(cara)] =  value;
    }

    public void setInitCaracs(Caracteristique cara , double value){
        init_caracs[StatsEnum.getIndexOfCara(cara)] =  value;
    }
    public void setEntraveOrBoostValue(Caracteristique cara , double value){
        entraveOrBoostValue[StatsEnum.getIndexOfCara(cara)] =  value;
    }
    public void setEntraveOrBoostTurn(Caracteristique cara , double value){
        entraveOrBoostTurn[StatsEnum.getIndexOfCara(cara)] =  value;
    }

    private static final int getIndexOfCarasArr(){
        return 0;
    }

    private static final int getIndexOfShieldsArr(){
        return 1;
    }



    @Override
    public LazyFunction geAttribut(String attribut) {
        // TODO Auto-generated method stub
        return null;
    }

    */



}
