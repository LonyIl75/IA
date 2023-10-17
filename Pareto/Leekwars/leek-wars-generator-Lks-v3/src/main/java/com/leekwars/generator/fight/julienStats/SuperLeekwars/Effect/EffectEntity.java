package com.leekwars.generator.fight.julienStats.SuperLeekwars.Effect;

import leekscript.runner.AI;
import leekscript.runner.LeekRunException;
import leekscript.runner.values.ArrayLeekValue;


//https://leekwars.com/encyclopedia/fr/getEffects

public class EffectEntity extends baseEffect  {
    int  valueIndex ;
    int caster_idIndex;
    int criticalIndex;
    int item_idIndex;

    public EffectEntity  (AI ai , ArrayLeekValue array ){
        super(ai ,array,0,3,6,7);
        valueIndex = 1;
        caster_idIndex = 2;
        criticalIndex = 4;
        item_idIndex = 5;
    }

    public int getValue(){ 

        try {
            return (int) array.get(ai,valueIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
    public int getCaster_id( ){ 
        try {
            return (int)array.get(ai,caster_idIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    public int getCritical(){
        try {
            return (int)array.get(ai,criticalIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    public int getItem_id( ){ 
        try {
            return (int)array.get(ai,item_idIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }


    
}