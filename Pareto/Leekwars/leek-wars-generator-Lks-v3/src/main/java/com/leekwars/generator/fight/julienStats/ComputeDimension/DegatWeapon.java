package com.leekwars.generator.fight.julienStats.ComputeDimension;

import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Effect.EffectItem;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyFunction;

import leekscript.runner.AI;
import leekscript.runner.values.ArrayLeekValue;
import leekscript.runner.values.Box;

public class DegatWeapon  extends DegatItem {

    

    public DegatWeapon(AI ai, int idWeapon ,Stats stats) {
        super(ai, idWeapon,stats);
    }
    
    @Override
    public  void updateDamageAtm( ){
        double dmg =0;
    
        try {
            for(EffectItem weff :  this.itemEffects ){
                dmg += 0 ; //getValueEffectDamage(  weff , stats);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.degatMeanAtm = dmg; // /_getWeaponCost(idWeapon)
    }
    
    @Override
    protected ArrayLeekValue  getItemEffects(){
        return _getWeaponEffects( _ai ,idItem); 
    }

    private static ArrayLeekValue _getWeaponEffects( AI ai , int idWeapon)  {
        Weapon template = Weapons.getWeapon(idWeapon);
        if (template == null)
            return null;
        return ComputeEffect._getEffectItem( ai , template.getAttack());
       
    }



    @Override
    public LazyFunction geAttribut(String attribut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geAttribut'");
    }
    
    




    
}
