package com.leekwars.generator.fight.julienStats.SuperLeekwars.Item;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.leekwars.generator.Util;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienBdd.weaponPattern;
import com.leekwars.generator.fight.julienStats.ComputeDimension.ComputeEffect;
import com.leekwars.generator.fight.julienStats.ComputeDimension.myEffect;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

import leekscript.runner.AI;
import leekscript.runner.LeekRunException;

import leekscript.runner.values.ArrayLeekValue;
import leekscript.runner.values.Box;

public class WeaponsUtil extends ItemsUtil<Weapon>{
    
  
    public static WeaponsUtil provider_weaponsUtil = new WeaponsUtil();

    public WeaponsUtil() {
        
        super("weapons.json");
        
        this.setItems();
    }



public  static int _getId(int idItem , String _fullpathJson  ){
    return ItemsUtil._getId(idItem, _fullpathJson, "item");
}

@Override
public void load() {
    JSONObject weapons = JSON.parseObject(Util.readFile("data/weapons.json"));
    for (String id : weapons.keySet()) {
        JSONObject weapon = weapons.getJSONObject(id);
        Weapon _weap = new Weapon(weapon.getInteger("item"), (byte) 1, weapon.getInteger("cost"),
        weapon.getInteger("min_range"), weapon.getInteger("max_range"), weapon.getJSONArray("effects"),
        weapon.getByte("launch_type"), weapon.getByte("area"), weapon.getBoolean("los"),
        weapon.getInteger("template"), weapon.getString("name"), weapon.getJSONArray("passive_effects"));
    
        Weapons.addWeapon(_weap);
        idItemId.put(_weap.getId(),Integer.parseInt(id));
    }
}

@Override 
public void setItems(){
    if (Weapons.getTemplates().size() == 0) {
        this.load();
    }
    Weapons.getTemplates().forEach((k, v) -> {
		items.put( k,new WeaponBis(v));
	});
}

public static void writeEffectWeapons(){
    JSONObject json_effects = new JSONObject();
    JSONArray json_effects_array = new JSONArray();
    myEffect effect = null;
    Weapon weap ; 
    Stats states_1 = new Stats(); 
    Stats states_2 = new Stats();

    for ( Integer k :  WeaponsUtil.provider_weaponsUtil.getItems().keySet()){
        //System.out.println("idItem : "+k+" idWeapon : "+WeaponsUtil.provider_weaponsUtil.getItems().get(k).getName());
        weap = WeaponsUtil.provider_weaponsUtil.getItems().get(k).getItem();
        for (EffectParameters e : weap.getAttack().getEffects()){
            effect = ComputeEffect.getIndexEffectEntrave(states_1 ,states_2, e);
            json_effects_array.add( effect.toJson());
}

    json_effects.put( String.valueOf(k) , json_effects_array);
}
    
    String path_ = effect.getFilePath()+Miscellaneous.getJoinFilename() + Weapons.class.getSimpleName()+".json";
    
    try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
        out.write(JSON.toJSONString(json_effects,SerializerFeature.DisableCircularReferenceDetect));
    } catch (Exception exe) {
        exe.printStackTrace();
    }


}

public static void writePatternWeapon(){

    JSONObject json = new JSONObject();


    weaponPattern weaponPattern = null;


    List<EffectParameters> effects = null;
    for ( Integer k :  WeaponsUtil.provider_weaponsUtil.getItems().keySet()){
        System.out.println("idItem : "+k+" idWeapon : "+WeaponsUtil.provider_weaponsUtil.getItems().get(k).getName());
        try {
            weaponPattern =  new weaponPattern(k,k) ;
            json.put( String.valueOf( weaponPattern.getIdArme() ), weaponPattern.getJSON());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        
}

    String path_ = weaponPattern.getFilePath();
    try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
        out.write(JSON.toJSONString(json,SerializerFeature.DisableCircularReferenceDetect));
    } catch (Exception exe) {
        exe.printStackTrace();
    }



    }

    




/* 
public static Attack _getAttackItem( IVisitorWeaponChip visitorWC ){
    return visitorWC.when(new IVisitorWeaponChip.ICases<Attack>() {
        @Override
        public Attack is(WeaponBis weaponBis) {
            return weaponBis.getWeapon().getAttack();
        }
        @Override
        public Attack is(ChipBis chipBis) {
            return chipBis.getChip().getAttack();
        }
    });
}
*/



}
