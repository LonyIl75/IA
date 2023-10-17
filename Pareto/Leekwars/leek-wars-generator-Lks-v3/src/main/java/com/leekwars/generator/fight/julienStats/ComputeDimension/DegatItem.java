package com.leekwars.generator.fight.julienStats.ComputeDimension;


import java.util.ArrayList;

import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.old.Ask;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Effect.EffectItem;
import com.leekwars.generator.fight.julienStats.enCours.LazyMonome;

import leekscript.runner.AI;
import leekscript.runner.values.ArrayLeekValue;
import leekscript.runner.values.Box;

public abstract class  DegatItem implements Ask {

AI _ai ;
double degatMeanAtm ; // le truc a mettre en cache 
int idItem ;
Stats stats ; 
ArrayList<EffectItem> itemEffects ; 

public DegatItem(AI _ai , int idItem , Stats stats){
    this._ai = _ai;
    this.idItem = idItem;
    this.stats = stats;
    
}

public double getDegatMeanAtm(){
    return degatMeanAtm;
}



public abstract void updateDamageAtm( );

public static  double meanDegat(  EffectItem dmg_eff ){
    return Miscellaneous.mean2( dmg_eff.getMin(),dmg_eff.getMax());
}

public  void initEffectsItem( ){
    try {
        for(var e :  getItemEffects() ){ //changement 
            EffectItem weaponEffect =  new EffectItem (_ai , (ArrayLeekValue ) e);
            if(weaponEffect != null)itemEffects.add(weaponEffect);
        }
    } catch (Exception e) {
        e.printStackTrace();
        itemEffects = null;
    }
}

protected abstract ArrayLeekValue getItemEffects();

public static  double getDefaultAOE(){
    return 1.0;
}
public static  double getDefaultJet(){
    return 1.0;
}
public static  double getDefaultCriticalPower(){
    return 1.0;
}
/* 
public static Damage getRawDamage (Stats stats , myItem _item){
    return getRawDamage( stats, _item.getAttack());
}
public static Damage getRawDamage(Stats stats, Attack attack ){
    for (myEffect e :  getLazyEffectsAttack(  attack , stats ) )
    return getRawDamage(   getValueEffectDamage(  attack , stats ) ,  attack.getMinDamage(), attack.getMaxDamage(), getDefaultAOE());
}
public static double getRawDamage(  LazyMonome[] monomes  ,  double min , double max ,  double aoe ){
    return getRawDamage( monomes , min , max , aoe , getDefaultJet() , getDefaultCriticalPower() );
}

*/

public static double getRawDamage( LazyMonome[] monomes ,  double min , double max , double aoe , double jet , double criticalPower ){
     double avg =  min + jet * max ;
     double dmg = 0 ;
     for (int i = 0; i < monomes.length; i++) {
         dmg *= (1 + ( monomes[i].compute() /100.0 ) );
     }
    return (avg * dmg * aoe * criticalPower);
    
}


/* 
//TODO:
    getDamageWithShield
    getDamageWithLifeSteal (x 2)
    getDamageWithReturnDamage (x 2)
    getDamageWithErosion 
//src : attack/effect/EffectDamage.java 
*/
       

/* 
@Override
public LazyFunction geAttribut(String attribut) {
    
    if( attribut.equals("") ){
        return (Object obj  ) -> {return ((DegatWeapon)obj).getDamageWeapon( );};
    }
    else if (attribut.equals("y")){
        return (Object obj) -> {return ((MapDegatArme)obj).getY();};
    }
    else{
        return (Object obj) ->{return Double.NaN;};
    }
}
*/




    
}
