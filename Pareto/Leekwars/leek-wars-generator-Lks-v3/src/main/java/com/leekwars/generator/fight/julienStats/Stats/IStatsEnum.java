package com.leekwars.generator.fight.julienStats.Stats;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

public abstract class IStatsEnum<T extends Enum<T>> {


    static String path = "./data";

    public abstract String getClassName();

    public String getFilePath(){
        return path+File.separator+Miscellaneous.getDfPrefixFilename()+ Miscellaneous.getJoinFilename()+getClassName()+".json";
    }

    public int getTaille(){
        return values().length;
    }


    public  abstract int getIndexOf(T c);

    public abstract T[] values();

    public static int getInvalidIndexOf(){
        return -1;
    }

   
    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        JSONArray idsType = new JSONArray();
        JSONArray namesType = new JSONArray();
        json.put("name", getClassName());
        for (T type : values()){
            idsType.add(getIndexOf(type));
            namesType.add(type.name());
           
        }
        json.put("enumIndex", idsType);
        json.put("enumName", namesType);

        return json;
    }

    public class JsonEnum {
        public String name;
        public int[] enumIndex;
        public String[] enumName;
    }

    
    public void writeDefaultEnumJson(){
        String path_enum = getFilePath();
        try (PrintWriter out = new PrintWriter(new FileWriter(path_enum))) {
            out.write(getJSON().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  


    public enum enumCaracteristique {
        TP , STRENGTH , SCIENCE , MAGIC , AGILITY , MP ,  WISDOM , RESISTANCE , FREQUENCY ,LIFE , POWER,ABSOLUTE_SHIELD , RELATIVE_SHIELD , DAMAGE_RETURN 
        };

    public enum NegCaracteristique {
        EFFECT_ABSOLUTE_VULNERABILITY, EFFECT_VULNERABILITY
        };
    public enum enumDot {
            DAMAGE,
            POISON,
            NOVA_DAMAGE,
            LIFE_DAMAGE,
            //DAMAGE_RETURN,
            SHACKLE_STRENGTH,
            SHACKLE_MAGIC,
            SHACKLE_TP,
            SHACKLE_MP,
            AFTEREFFECT
        };

    public enum enumEffect{
        TYPE_DAMAGE,TYPE_HEAL,
        TYPE_VITALITY, TYPE_NOVA_VITALITY, TYPE_LIFE_DAMAGE, TYPE_NOVA_DAMAGE, TYPE_POISON, TYPE_SHACKLE_STRENGTH, TYPE_RAW_BUFF_STRENGTH, TYPE_BUFF_STRENGTH, TYPE_SHACKLE_AGILITY, TYPE_RAW_BUFF_AGILITY, TYPE_BUFF_AGILITY, TYPE_RAW_BUFF_RESISTANCE, TYPE_BUFF_RESISTANCE, TYPE_SHACKLE_WISDOM, TYPE_RAW_BUFF_WISDOM, TYPE_BUFF_WISDOM, TYPE_SHACKLE_MAGIC, TYPE_RAW_BUFF_MAGIC, TYPE_RAW_BUFF_SCIENCE, TYPE_RAW_BUFF_POWER, TYPE_MOVED_TO_MP, TYPE_POISON_TO_SCIENCE, TYPE_DAMAGE_TO_ABSOLUTE_SHIELD, TYPE_DAMAGE_TO_STRENGTH, TYPE_NOVA_DAMAGE_TO_MAGIC, TYPE_ALLY_KILLED_TO_AGILITY, TYPE_RAW_RELATIVE_SHIELD, TYPE_RELATIVE_SHIELD, TYPE_RAW_ABSOLUTE_SHIELD, TYPE_ABSOLUTE_SHIELD, TYPE_VULNERABILITY, TYPE_ABSOLUTE_VULNERABILITY, TYPE_STEAL_ABSOLUTE_SHIELD, TYPE_RAW_BUFF_TP, TYPE_BUFF_TP, TYPE_SHACKLE_TP, TYPE_BUFF_MP, TYPE_RAW_BUFF_MP, TYPE_SHACKLE_MP, TYPE_DEBUFF, TYPE_PERMUTATION, TYPE_TELEPORT, TYPE_ATTRACT, TYPE_REPEL, TYPE_PUSH, TYPE_REMOVE_SHACKLES, TYPE_PROPAGATION, TYPE_DAMAGE_RETURN, TYPE_AFTEREFFECT, TYPE_SUMMON, TYPE_RESURRECT, TYPE_KILL, TYPE_ANTIDOTE
            //,TYPE_DAMAGE,TYPE_HEAL        

        };
    

}