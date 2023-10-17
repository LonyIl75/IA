package com.leekwars.generator.fight.julienStats.SuperLeekwars.Effect;

import leekscript.runner.AI;
import leekscript.runner.LeekRunException;
import leekscript.runner.values.ArrayLeekValue;

//https://leekwars.com/encyclopedia/fr/getWeaponEffects
//https://leekwars.com/encyclopedia/fr/getChipEffects
public class EffectItem  extends baseEffect{

     int minIndex, maxIndex;

    public static String getClassNameCell(){
        return baseEffect.getClassNameCell()+"Item";

    }

   
    public EffectItem (AI ai  ,ArrayLeekValue array ){
        super(ai,array,0,3,4,5);
        minIndex = 1;
        maxIndex = 2;
    }

    public int getMin(){ 

        try {
            return (int) array.get(ai,minIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
    public int getMax(){ 
        try {
            return (int)array.get(ai,maxIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }



}