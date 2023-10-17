package com.leekwars.generator.fight.julienStats.Stats;

import java.util.ArrayList;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.Util;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;

import com.leekwars.generator.leek.Stats;

public class StatCaracteristique extends IStatsEnum<enumCaracteristique >  {
    
    public static StatCaracteristique provider_statCara  = new StatCaracteristique();

    

    public StatCaracteristique(){
    }
        
    @Override
    public  String getClassName(){
        return "enumCaracteristique";
    }




    @Override 
    public enumCaracteristique[] values(){
        return enumCaracteristique.values();
    }
    

    public static ArrayList<enumCaracteristique> getShield(){
        ArrayList<enumCaracteristique> affect = new ArrayList<enumCaracteristique>();
        affect.add(enumCaracteristique.ABSOLUTE_SHIELD);
        affect.add(enumCaracteristique.RELATIVE_SHIELD);
        return affect;
    }

    public static ArrayList<enumCaracteristique> getHeal(){
        ArrayList<enumCaracteristique> affect = new ArrayList<enumCaracteristique>();
        affect.add(enumCaracteristique.LIFE);
        return affect;
    }


    @Override
    public int getIndexOf(enumCaracteristique c){
        switch (c) {
            case TP:
                return Entity.CHARAC_TP;
            case STRENGTH:
                return Entity.CHARAC_STRENGTH;
            case SCIENCE:
                return Entity.CHARAC_SCIENCE;
            case MAGIC:
                return Entity.CHARAC_MAGIC;
            case AGILITY:
                return Entity.CHARAC_AGILITY;
            case MP:
                return Entity.CHARAC_MP;
            case WISDOM:
                return Entity.CHARAC_WISDOM;
            case RESISTANCE:
                return Entity.CHARAC_RESISTANCE;
            case FREQUENCY:
                return Entity.CHARAC_FREQUENCY;
            case LIFE:
                return Entity.CHARAC_LIFE;
            case POWER:
                return Entity.CHARAC_POWER;
            case ABSOLUTE_SHIELD:
                return Entity.CHARAC_ABSOLUTE_SHIELD;
            case RELATIVE_SHIELD:
                return Entity.CHARAC_RELATIVE_SHIELD;
            case DAMAGE_RETURN  :
                return Entity.CHARAC_DAMAGE_RETURN;
            default:
                return IStatsEnum.getInvalidIndexOf();
        }
        }

        

        public static  enumCaracteristique toEnum(int nb){
        switch (nb) {
            case Entity.CHARAC_TP:
                return enumCaracteristique.TP;
            case Entity.CHARAC_STRENGTH:
                return enumCaracteristique.STRENGTH;
            case Entity.CHARAC_SCIENCE:
                return enumCaracteristique.SCIENCE;
            case Entity.CHARAC_MAGIC:
                return enumCaracteristique.MAGIC;
            case Entity.CHARAC_AGILITY:
                return enumCaracteristique.AGILITY;
            case Entity.CHARAC_MP:
                return enumCaracteristique.MP;
            case Entity.CHARAC_WISDOM:
                return enumCaracteristique.WISDOM;
            case Entity.CHARAC_RESISTANCE:
                return enumCaracteristique.RESISTANCE;
            case Entity.CHARAC_FREQUENCY:
                return enumCaracteristique.FREQUENCY;
            case Entity.CHARAC_LIFE:
                return enumCaracteristique.LIFE;
            case Entity.CHARAC_POWER:
                return enumCaracteristique.POWER;
            case Entity.CHARAC_ABSOLUTE_SHIELD:
                return enumCaracteristique.ABSOLUTE_SHIELD;
            case Entity.CHARAC_RELATIVE_SHIELD:
                return enumCaracteristique.RELATIVE_SHIELD;
            case Entity.CHARAC_DAMAGE_RETURN:
                return enumCaracteristique.DAMAGE_RETURN;
            default:
                return null;


        }
    }

      
        public Stats getArrFromJsonFile(String jsonFilename,String key_stop,int k)
        {
            Stats s = new Stats();
            JSONObject scenario = JSON.parseObject(Util.readFile( jsonFilename));
			for (String id :scenario.keySet()) {
                if(id.equals(key_stop)){
                    JSONArray arr = scenario.getJSONArray(id);
                    Iterator<Object> iterator = arr.iterator();

                   for (int z =0 ; iterator.hasNext();z++) {
                        if(z==k){
                            
                        //System.out.println(key_stop+" "+iterator);
                        JSONArray arr2 = (JSONArray) iterator.next();
                        Iterator<Object> iterator2 = arr2.iterator();
                        while (iterator2.hasNext()) {
                                JSONObject jsonObject = (JSONObject) iterator2.next();
                                //System.out.println(" "+iterator2);
                                for (String key : jsonObject.keySet()) {
                                    for (enumCaracteristique c : values()) {
                                        if(key.equals(c.name().toLowerCase())){
                                            s.setStat(getIndexOf(c), (int)jsonObject.get(key));
                                        }
                                    }
                                }
                            }
                   
                }

            }
        }
    }
    //System.out.println("TreeMap "+s.toString());
    
    return s;
        }

       
        public String getMethodName(enumCaracteristique c){
            switch (c) {
                case TP:
                    return "getTP";
                case STRENGTH:
                    return "getStrength";
                case SCIENCE:
                    return "getScience";
                case MAGIC:
                    return "getMagic";
                case AGILITY:
                    return "getAgility";
                case MP:
                    return "getMP";
                case WISDOM:
                    return "getWisdom";
                case RESISTANCE:
                    return "getResistance";
                case FREQUENCY:
                    return "getFrequency";
                case LIFE:
                    return "getLife";
                case POWER:
                    return "getPower";
                case ABSOLUTE_SHIELD:
                    return "getAbsoluteShield";
                case RELATIVE_SHIELD:
                    return "getRelativeShield";
                case DAMAGE_RETURN  :
                    return "getDamageReturn";
                default:
                    return "";
            }
            }
    
            public String getBaseMethodName(enumCaracteristique c){
                switch (c) {
                    case TP:
                        return "getTotalTP"; 
                    case MP:
                        return "getTotalMP"; 
                    case LIFE:
                        return "getTotalLife"; 
                    default:
                        return getMethodName(c);
                }
                }
        

        
}
