package com.leekwars.generator.fight.julienStats.Stats;

import java.util.ArrayList;

import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.fight.julienStats.util.MySet;

public class Stat <G extends Enum<G> , T extends IStatsEnum<G>> {

    T type;
    boolean isBase;

    public Stat(T type , boolean isBase){
        this.type = type;
        this.isBase = isBase;
    }
    

    public  double[] getDefaultStatTab(){
        return Miscellaneous.emptyStatArr(null , getSize());
    }

    public int getSize(){
        return enum_values().length;
    }

    public G[] enum_values(){
        return type.values();
    }
    public int enumToIndex(G enum_stat){
        return type.getIndexOf(enum_stat);
    }

    public  MySet getInitSet(){
        return new MySet(getSize());
    }

    public void addToSet(MySet set , G c){
        set.AddElement(enumToIndex(c));
    }
    public void addToSet(MySet set , ArrayList<G> c){
        for ( G c1 : c){
            set.AddElement(enumToIndex(c1));
        }
    }

    /*public double getSat(myEntityAI<G> entity,G enum_stat , int index_version_rq ){
        if( version_atm[index_version_rq] > version_stored[enumToIndex(enum_stat)]){
            entity.updateStat(enum_stat);
        }
        return statTab[enumToIndex(enum_stat)];
    }*/

    public double getSat(G enum_stat , double[] statTab ){
        return statTab[enumToIndex(enum_stat)];
    }

 

    public void setStat(G enum_stat  , double[] statTab , double val){
        if( val < 0){
            return;
        }
        statTab[enumToIndex(enum_stat)] = val;
    }



    public  MySet arrEnumToSet(ArrayList<G> arr_cara ){
        MySet set = getInitSet();
        for ( G c : arr_cara){
            set.AddElement( enumToIndex(c));
        }
        return set;
    }
    public void fusionSet(MySet set1 , MySet set2 ){
        set1.orSet(set2);
    }
    public void fusionSet(MySet set1 , ArrayList<G> arr_cara ){
        MySet set2 = arrEnumToSet(arr_cara);
        set1.orSet(set2);
    }



    public  MySet setAllEnum(){
        return MySet.getOne(getSize());
    }


    
}
