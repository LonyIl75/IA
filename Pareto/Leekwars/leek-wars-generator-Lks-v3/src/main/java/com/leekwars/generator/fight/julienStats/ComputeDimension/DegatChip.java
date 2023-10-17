package com.leekwars.generator.fight.julienStats.ComputeDimension;

import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.fight.julienStats.enCours.Stats;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Effect.EffectItem;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyFunction;

import leekscript.runner.AI;
import leekscript.runner.values.ArrayLeekValue;
import leekscript.runner.values.Box;

public class DegatChip extends DegatItem {


    
    public DegatChip (AI ai, int idChip ,Stats stats) {
        super(ai, idChip,stats);
    }


    @Override
    public  void updateDamageAtm( ){
        double dmg =0;
    
        try {
            for(EffectItem ceff :  this.itemEffects ){
                dmg += 0 ; //getValueEffectDamage(  ceff , stats);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.degatMeanAtm = dmg; // /_getWeaponCost(idWeapon)
    }
    

    @Override
    protected ArrayLeekValue  getItemEffects(){
        return _getChipEffects( _ai ,idItem); 
    }

    private static ArrayLeekValue  _getChipEffects( AI ai , int idChip)  {
        Chip template = Chips.getChip(idChip);
        if (template == null)
            return null;
        return ComputeEffect._getEffectItem(ai , template.getAttack()) ;
       
    }


    @Override
    public LazyFunction geAttribut(String attribut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geAttribut'");
    }
    




    
    
        
    
}
