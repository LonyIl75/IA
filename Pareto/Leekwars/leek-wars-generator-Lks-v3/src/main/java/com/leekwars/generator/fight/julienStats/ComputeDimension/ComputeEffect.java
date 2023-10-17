package com.leekwars.generator.fight.julienStats.ComputeDimension;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.ChipType;
import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats._AffectStat;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;


import leekscript.runner.AI;
import leekscript.runner.LeekRunException;
import leekscript.runner.values.ArrayLeekValue;

public class ComputeEffect {



    AI _ai ; 



    public static ArrayLeekValue _getEffectItem(AI _ai , Attack attack) {
        ArrayLeekValue retour = new ArrayLeekValue(_ai);
        for (EffectParameters e : attack.getEffects()) {
            ArrayLeekValue effect = new ArrayLeekValue(_ai);
            try {
                effect.push(_ai, e.getId());
                effect.push(_ai, e.getValue1());
                effect.push(_ai, e.getValue1() + e.getValue2());
                effect.push(_ai, e.getTurns());
                effect.push(_ai, e.getTargets());
                effect.push(_ai, e.getModifiers());
                retour.push(_ai, effect);
            } catch (LeekRunException e1) {
                continue;
            }
        }
        return retour;
    }

    //ce qui renforce le type  : augmentedBy
    public static int _getEffectCharacteristic(int type) {
    switch (type) {
        case Effect.TYPE_DAMAGE:
            return Entity.CHARAC_STRENGTH;
        case Effect.TYPE_POISON:
        case Effect.TYPE_SHACKLE_MAGIC:
        case Effect.TYPE_SHACKLE_STRENGTH:
        case Effect.TYPE_SHACKLE_MP:
        case Effect.TYPE_SHACKLE_TP:
            return Entity.CHARAC_MAGIC;
        case Effect.TYPE_LIFE_DAMAGE:
            return Entity.CHARAC_LIFE;
        case Effect.TYPE_NOVA_DAMAGE:
        case Effect.TYPE_BUFF_AGILITY:
        case Effect.TYPE_BUFF_STRENGTH:
        case Effect.TYPE_BUFF_MP:
        case Effect.TYPE_BUFF_TP:
        case Effect.TYPE_BUFF_RESISTANCE:
        case Effect.TYPE_BUFF_WISDOM:
            return Entity.CHARAC_SCIENCE;
        case Effect.TYPE_DAMAGE_RETURN:
            return Entity.CHARAC_AGILITY;
        case Effect.TYPE_HEAL:
        case Effect.TYPE_VITALITY:
            return Entity.CHARAC_WISDOM;
        case Effect.TYPE_RELATIVE_SHIELD:
        case Effect.TYPE_ABSOLUTE_SHIELD:
            return Entity.CHARAC_RESISTANCE;
    }
    return -1;
}


public  static myEffect getIndexEffectEntrave(Stats stat_lanceur ,Stats stat_receveur, EffectParameters e){ //stat = lanceur 
    AffectStat affect_receveur = new AffectStat(stat_receveur); //stat2 = cible
    AffectStat affect_lanceur = new AffectStat(stat_lanceur); //stat = lance
    return getIndexEffectEntrave(affect_lanceur,affect_receveur,e);
}


    public  static myEffect getIndexEffectEntrave(AffectStat affect_lanceur ,AffectStat affect_receveur, EffectParameters e){ //stat = lanceur 

    
    

        //ON NE CAPTE PAS L EROSION 10 et 5 % 
        /* 
        double[] affect_baseStat_value = stat.getDefaultStatTab();
        double[] affect_atmStat_value = stat.getDefaultStatTab();
        double[] affect_Dot_value =  stat.getDefaultStatTab();
        */

        
    
        switch(e.getId()){
        
            case Effect.TYPE_DAMAGE:
                affect_receveur.getAffect().addAffectDot(enumDot.DAMAGE);
            break;

            case Effect.TYPE_HEAL:
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
                break;

            case Effect.TYPE_VITALITY :
                //----------------------------------
                //----------------------------------
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
                break;
            case Effect.TYPE_NOVA_VITALITY :
                //----------------------------------
                //----------------------------------
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
            break;
    
            case Effect.TYPE_LIFE_DAMAGE :

                affect_receveur.getAffectedBy().addAffectAtmStat(enumCaracteristique.LIFE);
                affect_receveur.getAffectedBy().addAffectAtmStat(enumCaracteristique.DAMAGE_RETURN);

                affect_lanceur.getAffect().addAffectAtmStat(enumCaracteristique.LIFE);

                affect_lanceur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.LIFE);
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
            break;
    
            case Effect.TYPE_NOVA_DAMAGE :
                //----------------------------------
                //----------------------------------
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
            break;
    
    
            case Effect.TYPE_POISON :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.LIFE);
                //----------------------------------
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE);
            break;
    
            case Effect.TYPE_SHACKLE_STRENGTH :
            case Effect.TYPE_RAW_BUFF_STRENGTH :
            case Effect.TYPE_BUFF_STRENGTH :
                 //----------------------------------
                 affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.STRENGTH); //ajoute de la force
                 //----------------------------------
            break;
    
            case Effect.TYPE_SHACKLE_AGILITY :
            case Effect.TYPE_RAW_BUFF_AGILITY :
            case Effect.TYPE_BUFF_AGILITY :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.AGILITY); //ajoute de l'agilite
                //----------------------------------
            break;
    
            case Effect.TYPE_RAW_BUFF_RESISTANCE :
            case Effect.TYPE_BUFF_RESISTANCE :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.RESISTANCE); //ajoute de la reistsance
                //----------------------------------
            break;
    
            case Effect.TYPE_SHACKLE_WISDOM :
            case Effect.TYPE_RAW_BUFF_WISDOM :
            case Effect.TYPE_BUFF_WISDOM :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.WISDOM); //ajoute de la sagesse
                //----------------------------------
            break;
    
            case Effect.TYPE_SHACKLE_MAGIC :
            case Effect.TYPE_RAW_BUFF_MAGIC :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.MAGIC); //ajoute de la magie
                //----------------------------------
            break;
    
            case Effect.TYPE_RAW_BUFF_SCIENCE :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.SCIENCE); //ajoute de la science
                //----------------------------------
            break;
    
    
            case Effect.TYPE_RAW_BUFF_POWER :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.POWER); //ajoute de la puissance
                //----------------------------------
            break;
    
    
            //--------------TO -----------------
    
                //--------------STAT TO -----------------
    
                    //MOVED 
                    case Effect.TYPE_MOVED_TO_MP :
                    break;
    
    
                //--------------DAMAGE DOT TO -----------------
    
                    //--------------POISON -----------------
    
                    case Effect.TYPE_POISON_TO_SCIENCE :
                        affect_receveur.getAffectedBy().addAffectDot(enumDot.POISON); 
                        //----------------------------------
                        affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.SCIENCE); //ajoute de la science
                    break;
    
    
                //--------------DAMAGE TO-----------------
    
                    //--------------DAMAGE -----------------
    
                    case Effect.TYPE_DAMAGE_TO_ABSOLUTE_SHIELD :
                        affect_receveur.getAffectedBy().addAffectDot(enumDot.DAMAGE);
                        //----------------------------------
                        affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.ABSOLUTE_SHIELD); //ajoute du bouclier absolu
                    break;
                    case Effect.TYPE_DAMAGE_TO_STRENGTH :
                        affect_receveur.getAffectedBy().addAffectDot(enumDot.DAMAGE);
                        //----------------------------------
                        affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.STRENGTH); //ajoute de la force
                    break;
    
                    //--------------NOVA DAMAGE -----------------
    
                    case Effect.TYPE_NOVA_DAMAGE_TO_MAGIC :
                        affect_receveur.getAffectedBy().addAffectDot(enumDot.NOVA_DAMAGE);
                        //----------------------------------
                        affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.MAGIC); //ajoute de la magie
                    break;
    
                
                //--------------KILL TO  -----------------
    
                    //--------------ALLY KILLED -----------------
                    case Effect.TYPE_ALLY_KILLED_TO_AGILITY :
                    break;
    
                    //--------------ENEMY KILLED -----------------
    
    
    
            case Effect.TYPE_RAW_RELATIVE_SHIELD :
            case Effect.TYPE_RELATIVE_SHIELD :
                affect_receveur.getAffect().addAffectDot(enumDot.DAMAGE);//permet de tamponner les degats de type enumDot.DAMAGE
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.RELATIVE_SHIELD); //ajoute du  bouclier relatif
                //----------------------------------
            break;
    
            case Effect.TYPE_RAW_ABSOLUTE_SHIELD :
            case Effect.TYPE_ABSOLUTE_SHIELD :
                affect_receveur.getAffect().addAffectDot(enumDot.DAMAGE);//permet de tamponner les degats de type enumDot.DAMAGE
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.ABSOLUTE_SHIELD); //ajoute du  bouclier absolu
                //----------------------------------
            break;
    
            case Effect.TYPE_VULNERABILITY :
                affect_receveur.getAffect().addAffectDot(enumDot.DAMAGE);//permet de tamponner les degats de type enumDot.DAMAGE
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.RELATIVE_SHIELD); //ajoute du  bouclier relatif
                //----------------------------------
            break;
    
            case Effect.TYPE_ABSOLUTE_VULNERABILITY :
                affect_receveur.getAffect().addAffectDot(enumDot.DAMAGE);//permet de tamponner les degats de type enumDot.DAMAGE
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.ABSOLUTE_SHIELD); //ajoute du  bouclier absolue
                //----------------------------------
            break;
    
    
            case Effect.TYPE_STEAL_ABSOLUTE_SHIELD :
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.ABSOLUTE_SHIELD);
                //----------------------------------
                affect_lanceur.getAffect().addAffectAtmStat(enumCaracteristique.ABSOLUTE_SHIELD); //ajoute du bouclier absolu
                //----------------------------------
            break;
            
    
            case Effect.TYPE_RAW_BUFF_TP :
            case Effect.TYPE_BUFF_TP :
            case Effect.TYPE_SHACKLE_TP :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.TP); //ajoute des points de tour
                //----------------------------------
            break;
    
    
            case Effect.TYPE_BUFF_MP :
            case Effect.TYPE_RAW_BUFF_MP :
            case Effect.TYPE_SHACKLE_MP :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.MP); //ajoute des points de mouvement
                //----------------------------------
            
            break;
    
            case Effect.TYPE_DEBUFF :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(affect_receveur.getAffect().setAllAtmStat()); //ajoute de la force
                //----------------------------------
            break;
    
            case Effect.TYPE_PERMUTATION :
                affect_receveur.getAffect().setIsPositional() ;
                affect_lanceur.getAffect().setIsPositional() ;

                affect_receveur.getAffectedBy().setIsPositional() ;
                affect_lanceur.getAffectedBy().setIsPositional();
            case Effect.TYPE_TELEPORT :
            case Effect.TYPE_ATTRACT :
            case Effect.TYPE_REPEL :
            case Effect.TYPE_PUSH :
                affect_receveur.getAffect().setIsPositional();
                affect_receveur.getAffectedBy().setIsPositional();
            break;
        
            case Effect.TYPE_REMOVE_SHACKLES :
                affect_receveur.getAffect().addAffectAtmStat(affect_receveur.getAffect().setAllAtmStat()); //ajoute de la force
            break;
            
            /* 
            case Effect.TYPE_PROPAGATION :
            affect,enumCaracteristique.); //si l'effet se propage 
            break;
            */
            
            case Effect.TYPE_DAMAGE_RETURN : //TODO : BUFF PERSONNEL LES DEGAT SONT INFLIGE A L ENEMI  
                //CELUI QUI LANCE LE DAMAGE RETURN 
                affect_lanceur.getAffectedBy().addAffectDot(enumDot.DAMAGE);//permet de tamponner les degats de type enumDot.DAMAGE
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.LIFE); //enleve des pv en tenant compte des shields et du retour de degats
               //----------------------------------
               affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE); //5% des degats en  pv max retiré 
            break;
    
            case Effect.TYPE_AFTEREFFECT :
                //----------------------------------
                affect_receveur.getAffect().addAffectAtmStat(enumCaracteristique.LIFE); //enleve des pv en tenant compte des shields et du retour de degats
                //----------------------------------
                affect_receveur.getAffect().addAffectBaseStat(enumCaracteristique.LIFE); //5% des degats en  pv max retiré
            break;
    
    
            
            case Effect.TYPE_SUMMON :
                affect_receveur.getAffectedBy().setIsBulb() ;
                affect_receveur.getAffect().setIsBulb();
            break;
          
            case Effect.TYPE_RESURRECT :
            case Effect.TYPE_KILL :
                affect_receveur.getAffect().setIsEntity();
                affect_receveur.getAffectedBy().setIsEntity();
            break;
    
            case Effect.TYPE_ANTIDOTE :
                affect_receveur.getAffect().addAffectDot(enumDot.POISON);//retire les degats de type enumDot.POISON
            break;
    
    
    
    
                  
            
            default:
                System.out.println("Erreur : setEntraveOrBoost(EffectParameters e) : e.getEffectType() != Entrave ou Boost");
        }
        int tmp = _getEffectCharacteristic( e.getId()) ;
        if( tmp != -1)affect_lanceur.getAffectedBy().addAffectAtmStat( StatCaracteristique.toEnum(tmp)); 
        myEffect effect = new myEffect(affect_receveur,affect_lanceur);
        effect.init(affect_lanceur ,affect_receveur ,e.getValue1() ,e.getValue2(),e.getTurns());
        return effect;

    }
    
}
