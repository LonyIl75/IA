package com.leekwars.generator.fight.julienStats.Stats;

import java.util.ArrayList;

import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumEffect;

public class StatEffect extends IStatsEnum<enumEffect >  {


    public static  StatEffect  provider_statEffect = new StatEffect();
    @Override
    public String getClassName() {
        return "enumEffect";
    }
    @Override
    public int getIndexOf(enumEffect c) {

        switch(c){
            case TYPE_HEAL :
                return Effect.TYPE_HEAL;
            case TYPE_DAMAGE:
                return Effect.TYPE_DAMAGE;
            case TYPE_VITALITY :
                return Effect.TYPE_VITALITY;
            case TYPE_NOVA_VITALITY :
                return Effect.TYPE_NOVA_VITALITY;
            case TYPE_LIFE_DAMAGE :
                return Effect.TYPE_LIFE_DAMAGE;
            case TYPE_NOVA_DAMAGE :
                return Effect.TYPE_NOVA_DAMAGE;
            case TYPE_POISON :
                return Effect.TYPE_POISON;
            case TYPE_SHACKLE_STRENGTH :
                return Effect.TYPE_SHACKLE_STRENGTH;
            case TYPE_RAW_BUFF_STRENGTH :
                return Effect.TYPE_RAW_BUFF_STRENGTH;
            case TYPE_BUFF_STRENGTH :
                return Effect.TYPE_BUFF_STRENGTH;
            case TYPE_SHACKLE_AGILITY :
                return Effect.TYPE_SHACKLE_AGILITY;
            case TYPE_RAW_BUFF_AGILITY :
                return Effect.TYPE_RAW_BUFF_AGILITY;
            case TYPE_BUFF_AGILITY :
                return Effect.TYPE_BUFF_AGILITY;
            case TYPE_RAW_BUFF_RESISTANCE :
                return Effect.TYPE_RAW_BUFF_RESISTANCE;
            case TYPE_BUFF_RESISTANCE :
                return Effect.TYPE_BUFF_RESISTANCE;
            case TYPE_SHACKLE_WISDOM :
                return Effect.TYPE_SHACKLE_WISDOM;
            case TYPE_RAW_BUFF_WISDOM :
                return Effect.TYPE_RAW_BUFF_WISDOM;
            case TYPE_BUFF_WISDOM :
                return Effect.TYPE_BUFF_WISDOM;
            case TYPE_SHACKLE_MAGIC :
                return Effect.TYPE_SHACKLE_MAGIC;
            case TYPE_RAW_BUFF_MAGIC :
                return Effect.TYPE_RAW_BUFF_MAGIC;
            case TYPE_RAW_BUFF_SCIENCE :
                return Effect.TYPE_RAW_BUFF_SCIENCE;
            case TYPE_RAW_BUFF_POWER :
                return Effect.TYPE_RAW_BUFF_POWER;
            case TYPE_MOVED_TO_MP :
                return Effect.TYPE_MOVED_TO_MP;
            case TYPE_POISON_TO_SCIENCE :
                return Effect.TYPE_POISON_TO_SCIENCE;
            case TYPE_DAMAGE_TO_ABSOLUTE_SHIELD :
                return Effect.TYPE_DAMAGE_TO_ABSOLUTE_SHIELD;
            case TYPE_DAMAGE_TO_STRENGTH :
                return Effect.TYPE_DAMAGE_TO_STRENGTH;
            case TYPE_NOVA_DAMAGE_TO_MAGIC :
                return Effect.TYPE_NOVA_DAMAGE_TO_MAGIC;
            case TYPE_ALLY_KILLED_TO_AGILITY :
                return Effect.TYPE_ALLY_KILLED_TO_AGILITY;
            case TYPE_RAW_RELATIVE_SHIELD :
                return Effect.TYPE_RAW_RELATIVE_SHIELD;
            case TYPE_RELATIVE_SHIELD :
                return Effect.TYPE_RELATIVE_SHIELD;
            case TYPE_RAW_ABSOLUTE_SHIELD :
                return Effect.TYPE_RAW_ABSOLUTE_SHIELD;
            case TYPE_ABSOLUTE_SHIELD :
                return Effect.TYPE_ABSOLUTE_SHIELD;
            case TYPE_VULNERABILITY :
                return Effect.TYPE_VULNERABILITY;
            case TYPE_ABSOLUTE_VULNERABILITY :
                return Effect.TYPE_ABSOLUTE_VULNERABILITY;
            case TYPE_STEAL_ABSOLUTE_SHIELD :
                return Effect.TYPE_STEAL_ABSOLUTE_SHIELD;
            case TYPE_RAW_BUFF_TP :
                return Effect.TYPE_RAW_BUFF_TP;
            case TYPE_BUFF_TP :
                return Effect.TYPE_BUFF_TP;
            case TYPE_SHACKLE_TP :
                return Effect.TYPE_SHACKLE_TP;
            case TYPE_BUFF_MP :
                return Effect.TYPE_BUFF_MP;
            case TYPE_RAW_BUFF_MP :
                return Effect.TYPE_RAW_BUFF_MP;
            case TYPE_SHACKLE_MP :
                return Effect.TYPE_SHACKLE_MP;
            case TYPE_DEBUFF :
                return Effect.TYPE_DEBUFF;
            case TYPE_PERMUTATION :
                return Effect.TYPE_PERMUTATION;
            case TYPE_TELEPORT :
                return Effect.TYPE_TELEPORT;
            case TYPE_ATTRACT :
                return Effect.TYPE_ATTRACT;
            case TYPE_REPEL :
                return Effect.TYPE_REPEL;
            case TYPE_PUSH :
                return Effect.TYPE_PUSH;
            case TYPE_REMOVE_SHACKLES :
                return Effect.TYPE_REMOVE_SHACKLES;
            case TYPE_PROPAGATION :
                return Effect.TYPE_PROPAGATION;
            case TYPE_DAMAGE_RETURN :
                return Effect.TYPE_DAMAGE_RETURN;
            case TYPE_AFTEREFFECT :
                return Effect.TYPE_AFTEREFFECT;
            case TYPE_SUMMON :
                return Effect.TYPE_SUMMON;
            case TYPE_RESURRECT :
                return Effect.TYPE_RESURRECT;
            case TYPE_KILL :
                return Effect.TYPE_KILL;
            case TYPE_ANTIDOTE :
                return Effect.TYPE_ANTIDOTE;
            default:    
                return -1;
            
        }
     
    }

    public static enumEffect toEnum(int i) {
        switch(i){
            case Effect.TYPE_HEAL :
                return enumEffect.TYPE_HEAL;
            case Effect.TYPE_DAMAGE :
                return enumEffect.TYPE_DAMAGE;
            case Effect.TYPE_VITALITY :
                return enumEffect.TYPE_VITALITY;
            case Effect.TYPE_NOVA_VITALITY :
                return enumEffect.TYPE_NOVA_VITALITY;
            case Effect.TYPE_LIFE_DAMAGE :
                return enumEffect.TYPE_LIFE_DAMAGE;
            case Effect.TYPE_NOVA_DAMAGE :
                return enumEffect.TYPE_NOVA_DAMAGE;                                                     
            case Effect.TYPE_POISON :
                return enumEffect.TYPE_POISON;
            case Effect.TYPE_SHACKLE_STRENGTH :
                return enumEffect.TYPE_SHACKLE_STRENGTH;
            case Effect.TYPE_RAW_BUFF_STRENGTH :
                return enumEffect.TYPE_RAW_BUFF_STRENGTH;
            case Effect.TYPE_BUFF_STRENGTH :
                return enumEffect.TYPE_BUFF_STRENGTH;
            case Effect.TYPE_SHACKLE_AGILITY :
                return enumEffect.TYPE_SHACKLE_AGILITY;
            case Effect.TYPE_RAW_BUFF_AGILITY : 
                return enumEffect.TYPE_RAW_BUFF_AGILITY;
            case Effect.TYPE_BUFF_AGILITY :
                return enumEffect.TYPE_BUFF_AGILITY;
            case Effect.TYPE_RAW_BUFF_RESISTANCE :
                return enumEffect.TYPE_RAW_BUFF_RESISTANCE;
            case Effect.TYPE_BUFF_RESISTANCE :
                return enumEffect.TYPE_BUFF_RESISTANCE;
            case Effect.TYPE_SHACKLE_WISDOM :   
                return enumEffect.TYPE_SHACKLE_WISDOM;
            case Effect.TYPE_RAW_BUFF_WISDOM :
                return enumEffect.TYPE_RAW_BUFF_WISDOM;
            case Effect.TYPE_BUFF_WISDOM :
                return enumEffect.TYPE_BUFF_WISDOM;
            case Effect.TYPE_SHACKLE_MAGIC :
                return enumEffect.TYPE_SHACKLE_MAGIC;
            case Effect.TYPE_RAW_BUFF_MAGIC :
                return enumEffect.TYPE_RAW_BUFF_MAGIC;
            case Effect.TYPE_RAW_BUFF_SCIENCE :
                return enumEffect.TYPE_RAW_BUFF_SCIENCE;
            case Effect.TYPE_RAW_BUFF_POWER :   
                return enumEffect.TYPE_RAW_BUFF_POWER;
            case Effect.TYPE_MOVED_TO_MP :
                return enumEffect.TYPE_MOVED_TO_MP;
            case Effect.TYPE_POISON_TO_SCIENCE :
                return enumEffect.TYPE_POISON_TO_SCIENCE;
            case Effect.TYPE_DAMAGE_TO_ABSOLUTE_SHIELD :
                return enumEffect.TYPE_DAMAGE_TO_ABSOLUTE_SHIELD;
            case Effect.TYPE_DAMAGE_TO_STRENGTH :
                return enumEffect.TYPE_DAMAGE_TO_STRENGTH;
            case Effect.TYPE_NOVA_DAMAGE_TO_MAGIC :
                return enumEffect.TYPE_NOVA_DAMAGE_TO_MAGIC;
            case Effect.TYPE_ALLY_KILLED_TO_AGILITY :
                return enumEffect.TYPE_ALLY_KILLED_TO_AGILITY;
            case Effect.TYPE_RAW_RELATIVE_SHIELD :
                return enumEffect.TYPE_RAW_RELATIVE_SHIELD;
            case Effect.TYPE_RELATIVE_SHIELD :
                return enumEffect.TYPE_RELATIVE_SHIELD;
            case Effect.TYPE_RAW_ABSOLUTE_SHIELD :
                return enumEffect.TYPE_RAW_ABSOLUTE_SHIELD;
            case Effect.TYPE_ABSOLUTE_SHIELD :
                return enumEffect.TYPE_ABSOLUTE_SHIELD;
            case Effect.TYPE_VULNERABILITY :
                return enumEffect.TYPE_VULNERABILITY;
            case Effect.TYPE_ABSOLUTE_VULNERABILITY :
                return enumEffect.TYPE_ABSOLUTE_VULNERABILITY;
            case Effect.TYPE_STEAL_ABSOLUTE_SHIELD :
                return enumEffect.TYPE_STEAL_ABSOLUTE_SHIELD;
            case Effect.TYPE_RAW_BUFF_TP :
                return enumEffect.TYPE_RAW_BUFF_TP;
            case Effect.TYPE_BUFF_TP :
                return enumEffect.TYPE_BUFF_TP;
            case Effect.TYPE_SHACKLE_TP :
                return enumEffect.TYPE_SHACKLE_TP;
            case Effect.TYPE_BUFF_MP :
                return enumEffect.TYPE_BUFF_MP;
            case Effect.TYPE_RAW_BUFF_MP :  
                return enumEffect.TYPE_RAW_BUFF_MP;
            case Effect.TYPE_SHACKLE_MP :
                return enumEffect.TYPE_SHACKLE_MP;
            case Effect.TYPE_DEBUFF :
                return enumEffect.TYPE_DEBUFF;
            case Effect.TYPE_PERMUTATION :
                return enumEffect.TYPE_PERMUTATION;
            case Effect.TYPE_TELEPORT :
                return enumEffect.TYPE_TELEPORT;
            case Effect.TYPE_ATTRACT :
                return enumEffect.TYPE_ATTRACT;
            case Effect.TYPE_REPEL :
                return enumEffect.TYPE_REPEL;
            case Effect.TYPE_PUSH :
                return enumEffect.TYPE_PUSH;
            case Effect.TYPE_REMOVE_SHACKLES :  
                return enumEffect.TYPE_REMOVE_SHACKLES;
            case Effect.TYPE_PROPAGATION :  
                return enumEffect.TYPE_PROPAGATION;
            case Effect.TYPE_DAMAGE_RETURN :
                return enumEffect.TYPE_DAMAGE_RETURN;
            case Effect.TYPE_AFTEREFFECT :
                return enumEffect.TYPE_AFTEREFFECT;
            case Effect.TYPE_SUMMON :
                return enumEffect.TYPE_SUMMON;
            case Effect.TYPE_RESURRECT :
                return enumEffect.TYPE_RESURRECT;
            case Effect.TYPE_KILL :
                return enumEffect.TYPE_KILL;
            case Effect.TYPE_ANTIDOTE :
                return enumEffect.TYPE_ANTIDOTE;
            default:
                return null;
            
        }
    }
    

    @Override
    public enumEffect[] values() {
        return enumEffect.values();
    }


}
    
