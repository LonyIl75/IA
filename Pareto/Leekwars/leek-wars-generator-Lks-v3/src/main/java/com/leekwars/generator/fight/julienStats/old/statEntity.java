package com.leekwars.generator.fight.julienStats.old;

import com.leekwars.generator.attack.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.Util;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienBdd.weaponPattern;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.fight.julienStats.ComputeDimension.DegatItem;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Map.myCell;

import leekscript.runner.LeekRunException;
import leekscript.runner.values.ArrayLeekValue;

import com.leekwars.generator.attack.effect.Effect;

public class statEntity {

EntityAI entityAI;

Stats stats;
    
int [] idArme ;

DegatItem DegatArme;

myCell topLeftCorner , topRightCorner , bottomLeftCorner , bottomRightCorner ; // int[2] car leekwars utilise int[2] pour les cells  place des coins de la degatMap

myCell position ;// int[2] car leekwars utilise int[2] pour les cells position de l'entité update a chaque mouvement 



static final int nbArme = 4;

public static int getNbArme(){
    return nbArme;

}/* 

public void init(){
    initMapArme();
    idArme = new int [getNbArme()];
    initEntrave();
    initCorner();
    initPosition();
    stats = new Stats();
    initWeaponPattern();



}

public void initPosition(){
    position =  new myCell();

}
public void initCorner(){
    topLeftCorner = new myCell();
    topRightCorner = new myCell();
    bottomLeftCorner = new myCell();
    bottomRightCorner = new myCell();

}



public void statInit(){
    init();
    this.entityAI = null;

    MSD = 0 ;
    MDR = 0 ;

    mean_degat_atm = 0 ; 
    mean_degat_base = 0 ;


}
public  statEntity(EntityAI entityAI,int [] _idArme){
    statInit();
    this.entityAI = entityAI;
    setIdArme(_idArme);

    try {
        setStatEntity( );
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    
}
//ICI 
public void setIdArme(int [] _idArme){
    for(int i =0 ; i<getNbArme() ; i++){
        idArme[i]=_idArme[i];
    }

}

private void setStatEntity() throws Exception{
    this.update();
    setWeaponPattern() ;
    setMSD();
    setMDR();
    setMapsArme();
}




//////////////////////////update//////////////////////////



public void update (){

        updateShield();
        updateStats();
        updatePos();
        updateCorner();
        updateRankArme();
    

}



public void updateEntrave(){
    int e_turn = 0;
    int e_id=0;
    Object df_obj = null;

    
    for (EffectParameters e : entityAI.leek().getPassiveEffects( )){
        e_turn= e.getTurns();
        e_id=e.getId();
        if(e_turn>0){
            entraveOrBoostTurn[e_id]= (char) e_turn;
            entraveOrBoostValue[e_id]=  getEffectValue( e);
        }
    }
}


public void updateCorner(){
    int x = position.getX();
    int y = position.getY();
    int x1 = x - MSD;
    int x2 = x + MSD;
    int y1 = y - MSD;
    int y2 = y + MSD;
    topLeftCorner =  new myCell(x1,y1);
    topRightCorner = new myCell(x2,y1);
    bottomLeftCorner = new myCell(x1,y2);
    bottomRightCorner = new myCell(x2,y2);
}


public void updatePos(){
    try {
        int cell =  (int) entityAI.getCell(null);
        position.setX((int) entityAI.getCellX(cell));
        position.setY((int) entityAI.getCellY(cell));
    } catch (LeekRunException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}
public void updateBoostEntrave(){

}
public void updateStats(){
    try {
        stats.setAtmCaracs(StatsEnum.Caracteristique.AGILITY , (float)entityAI.getAgility(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.STRENGTH , (float)entityAI.getStrength(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.MAGIC , (float)entityAI.getMagic(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.MP , (float)entityAI.getMP(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.TP , (float)entityAI.getTP(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.RESISTANCE , (float)entityAI.getResistance(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.WISDOM , (float)entityAI.getWisdom(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.SCIENCE , (float)entityAI.getScience(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.FREQUENCY , (float)entityAI.getFrequency(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.LIFE , (float)entityAI.getLife(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.ABSOLUTE_SHIELD , (float)entityAI.getAbsoluteShield(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.RELATIVE_SHIELD , (float)entityAI.getRelativeShield(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.DAMAGE_RETURN , (float)entityAI.getDamageReturn(null));
        stats.setAtmCaracs(StatsEnum.Caracteristique.POWER , (float)entityAI.getPower(null));

    } catch (LeekRunException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}


public  List<EffectParameters> _getChipEffects( int idChip)  {
    Chip template = Chips.getChip(idChip);

    return template.getAttack().getEffects();
}

public  void initDamageWeaponBase(){
    int idWeapon =0;
    Weapon template;
    EffectParameters dmg_eff;
    for(int i = 0 ; i<idArme.length ; i++){
        idWeapon = idArme[i];
        template = Weapons.getWeapon(idWeapon);
        dmg_eff=WeaponUtil._getWeaponEffects( idWeapon).get(0);
        degatArmeMean_base[i]=WeaponUtil.meanDegat(dmg_eff );

    }

}
*/

public int getIndexArme(int indexArme){
    for (int i = 0; i < idArme.length; i++) {
        if(idArme[i]==indexArme){
            return i;
        }
    }
    return -1;

}


/* 
public static int _getItemIdWeaponP(weaponPattern weapP){
    return WeaponUtil._getItemIdWeapon(weapP.getWeapon());

}



public static int _getChipCost(int idChip){
    Chip template = Chips.getChip(idChip);
    return template.getCost();


}
*/



}
