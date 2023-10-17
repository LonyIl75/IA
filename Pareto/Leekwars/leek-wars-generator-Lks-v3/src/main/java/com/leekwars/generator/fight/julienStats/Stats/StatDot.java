package com.leekwars.generator.fight.julienStats.Stats;

import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;


public class StatDot extends IStatsEnum<enumDot > {

    public static StatDot provider_statDot  = new StatDot(); 
    @Override
    public String getClassName() {
        return "enumDot";
    }

    @Override
    public int getIndexOf(enumDot c) {
        switch( c){
            case DAMAGE:
                return Effect.TYPE_DAMAGE;
            case POISON:
                return Effect.TYPE_POISON;
            case NOVA_DAMAGE:
                return Effect.TYPE_NOVA_DAMAGE;
            case LIFE_DAMAGE:
                return Effect.TYPE_LIFE_DAMAGE;
            case SHACKLE_STRENGTH:
                return Effect.TYPE_SHACKLE_STRENGTH; //
            case SHACKLE_MAGIC:
                return Effect.TYPE_SHACKLE_MAGIC; //
            case SHACKLE_TP://
                return Effect.TYPE_SHACKLE_TP; //
            case SHACKLE_MP:
                return Effect.TYPE_SHACKLE_MP;//
            case AFTEREFFECT:
                return Effect.TYPE_AFTEREFFECT;
            default:
                return -1;
        }
    }
 
    public static enumDot toEnum(int idx){

        switch(idx){
            case Effect.TYPE_DAMAGE:
                return enumDot.DAMAGE;
            case Effect.TYPE_POISON:
                return enumDot.POISON;
            case Effect.TYPE_NOVA_DAMAGE:
                return enumDot.NOVA_DAMAGE;
            case Effect.TYPE_LIFE_DAMAGE:
                return enumDot.LIFE_DAMAGE;
            case Effect.TYPE_SHACKLE_STRENGTH:
                return enumDot.SHACKLE_STRENGTH;
            case Effect.TYPE_SHACKLE_MAGIC:
                return enumDot.SHACKLE_MAGIC;
            case Effect.TYPE_SHACKLE_TP:
                return enumDot.SHACKLE_TP;
            case Effect.TYPE_SHACKLE_MP:
                return enumDot.SHACKLE_MP;
            case Effect.TYPE_AFTEREFFECT:
                return enumDot.AFTEREFFECT;
            default:
                return null;
        }
    }

    /*
     * 
     * DIRECT(101),
	NOVA(107),
	RETURN(108),
    LIFE(109),
    POISON(110),
    AFTEREFFECT(110)
     */
    @Override
    public enumDot[] values() {
        return enumDot.values();
    }


   
    
}
