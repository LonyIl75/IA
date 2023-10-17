package com.leekwars.generator.fight.julienStats.SuperLeekwars.Effect;

import leekscript.runner.AI;
import leekscript.runner.LeekRunException;
import leekscript.runner.values.ArrayLeekValue;

public class baseEffect{
    
    ArrayLeekValue array;
    AI ai;
    int typeIndex;
    int turnIndex;
    int targetsIndex;
    int modifiersIndex;


    
    public static String getClassNameCell(){
        return "Effect";
    }
    


    public baseEffect(AI ai , ArrayLeekValue array , int typeIndex , int turnIndex , int targetsIndex , int modifiersIndex){
        this.ai = ai;
        this.array = array;
        this.typeIndex = typeIndex;
        this.turnIndex = turnIndex;
        this.targetsIndex = targetsIndex;
        this.modifiersIndex = modifiersIndex;
    }

    public int getType( ){
        try {
            return (int)array.get(ai,typeIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
    public int getTurns(){
        try {
            return (int)array.get(ai,turnIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
    public int getTargets( ){ //byte 

        try {
            return (int) array.get(ai,targetsIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
    public int getModifiers(){ //byte
        try {
            return (int)array.get(ai,modifiersIndex);
        } catch (LeekRunException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

  
}    