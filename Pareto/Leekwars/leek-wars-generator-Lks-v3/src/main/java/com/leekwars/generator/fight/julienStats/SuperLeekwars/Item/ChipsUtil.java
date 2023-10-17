package com.leekwars.generator.fight.julienStats.SuperLeekwars.Item;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.leekwars.generator.Util;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.ChipType;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.fight.julienStats.ComputeDimension.ComputeEffect;
import com.leekwars.generator.fight.julienStats.ComputeDimension.myEffect;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

public class ChipsUtil extends ItemsUtil<Chip>{

  
    public static ChipsUtil provider_chipsUtil = new ChipsUtil();

    public ChipsUtil () {
        super("chips.json");
        this.setItems();
    }



    public  static int _getId(int idItem , String _fullpathJson  ){
        return ItemsUtil._getId(idItem, _fullpathJson, null);
    }


@Override
public void load() {
        JSONObject chips = JSON.parseObject(Util.readFile("data/chips.json"));
        for (String id : chips.keySet()) {
            JSONObject chip = chips.getJSONObject(id);

            int chip_type_= ((JSONObject)chip.getJSONArray("effects").get(0)).getInteger("type");
            Chip _chip =new Chip(Integer.parseInt(id), chip.getInteger("cost"), chip.getInteger("min_range"),
                    chip.getInteger("max_range"), chip.getJSONArray("effects"), chip.getByte("launch_type"),
                    chip.getByte("area"), chip.getBoolean("los"), chip.getInteger("cooldown"),
                    chip.getBoolean("team_cooldown"), chip.getInteger("initial_cooldown"), chip.getInteger("level"),
                    chip.getInteger("template"),
                    chip.getString("name"),
                    ChipType.values()[chip_type_]);
            Chips.addChip( _chip);
            idItemId.put(_chip.getId(),Integer.parseInt(id));
        }
 
}

@Override 
public void setItems(){
    if (Chips.getTemplates().size() == 0) {
        this.load();
    }

    Chips.getTemplates().forEach((k, v) -> {
		items.put( k,new ChipBis(v));
	});
}


public static void writeEffectChips(){
    JSONObject json_effects = new JSONObject();
    JSONArray json_effects_array = new JSONArray();
    myEffect effect = null;
    Chip chip ; 
    Stats states_1 = new Stats(); 
    Stats states_2 = new Stats();

    for ( Integer k :  ChipsUtil.provider_chipsUtil.getItems().keySet()){
        //System.out.println("idItem : "+k+" idWeapon : "+WeaponsUtil.provider_weaponsUtil.getItems().get(k).getName());
        chip = ChipsUtil.provider_chipsUtil.getItems().get(k).getItem();
        for (EffectParameters e : chip.getAttack().getEffects()){
            effect = ComputeEffect.getIndexEffectEntrave(states_1 ,states_2, e);
            json_effects_array.add( effect.toJson());
}

    json_effects.put( String.valueOf( k ), json_effects_array);
}

    String path_ = effect.getFilePath()+Miscellaneous.getJoinFilename() + Chips.class.getSimpleName()+".json";
    try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
        out.write(JSON.toJSONString(json_effects,SerializerFeature.DisableCircularReferenceDetect));
    } catch (Exception exe) {
        exe.printStackTrace();
    }


}


}
