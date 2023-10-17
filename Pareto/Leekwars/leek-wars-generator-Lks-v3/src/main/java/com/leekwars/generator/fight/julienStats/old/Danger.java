package com.leekwars.generator.fight.julienStats.old;


import java.lang.reflect.Method;
import java.util.*;

import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.ChipType;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.Fight;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.maps.Cell;



public class Danger {

    
   
    HashMap<String ,Runnable> hash_funStatDanger ; 


    
    class StatEntity{
        public Entity entity ;
        public int distance ;
        public double mean_damage = -1;
        public int max_damage = -1;
        public double mean_heal = -1;
        public int max_heal = -1;

        public StatEntity(Entity entity, int distance, double mean_damage, int max_damage, double mean_heal, int max_heal) {
            this.entity = entity;
            this.distance = distance;
            this.mean_damage = mean_damage;
            this.max_damage = max_damage;
            this.mean_heal = mean_heal;
            this.max_heal = max_heal;
        }

        public StatEntity(Entity entity, int distance) {
            this.entity = entity;
            this.distance = distance;
        }

    }

    public ArrayList<StatEntity> E_neighbors ;
    public ArrayList<StatEntity> A_neighbors ;
    
    public HashMap<String , ArrayList<Double>> statDanger ; 

    public static  ArrayList<statString> default_functions = new ArrayList<statString>(){{
            add(new statString(Arrays.asList("E","N","meanDamage","D")));
            add(new statString(Arrays.asList("E","N","meanDistance","C")));
            add(new statString(Arrays.asList("E","N","quantityOf","Q")));
            add(new statString(Arrays.asList("ME","hp","H")));
            add(new statString(Arrays.asList("ME","relativeShield","H")));
            add(new statString(Arrays.asList("ME","absoluteShield","H")));
            add(new statString(Arrays.asList("ME","regenHeal","H")));
            add(new statString(Arrays.asList("ME","notusedPM","M")));
            add(new statString(Arrays.asList("ME","DoT","D")));
            add(new statString(Arrays.asList("A","N","quantityOf","Q")));
            add(new statString(Arrays.asList("A","N","meanDistance","C")));
            add(new statString(Arrays.asList("A","N","meanDamage","D")));
    
        }};
    

        public Danger(){
            for (int i = 0; i < default_functions.size(); i++) {
                add_default_function(i);
            }
      
        }

    public  static statString getDefaultFunction(int i){
        return default_functions.get(i);
    }

      //PAS SUR 
      public boolean isValidFunctionName(statString str) {
        return hash_funStatDanger.containsKey(str.getFunctionName());
    }
    
    private void add_default_function(int i){
        statString _statString = getDefaultFunction(i);
        String  method_name =  _statString.getFullName();
        add_function(_statString.getStorageName() , method_name );
    }

    private void add_function(String storage_name , String method_name){
        hash_funStatDanger.put(storage_name, () -> {
            try {
                Danger.class.getMethod(method_name);
            } catch (NoSuchMethodException | SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        statDanger.put(storage_name,new ArrayList<>());
    }


    public void add_custom_function (String storage_name ){//, Runnable function ) {
    
            statString _statString = new statString(storage_name);
            String method_name = _statString.getFullName();
            add_function(storage_name , method_name );
        
    }

/* 
public boolean isInNeighbourhood(Entity entity1,type_of_entity tentity2){
    getEntitiesAround(
    return entity1.getDistance(entity2) <= 1;
}
*/

public boolean _isInlineWeapon(Entity entity  , int id ) {
    Weapon template = id == -1 ? (entity.getWeapon() == null ? null : entity.getWeapon()) : Weapons.getWeapon(id);
    if (template == null)
        return false;
    return template.getAttack().getLaunchType() == Attack.LAUNCH_TYPE_LINE;
}

public boolean _isInlineChip( int id ) {
    Chip chip = Chips.getChip(id);
    if (chip == null)
        return false;
    return chip.getAttack().getLaunchType() == Attack.LAUNCH_TYPE_LINE;
}


public boolean _isOnSameLine(Fight fight , int c1, int c2) {
    Cell cell1 = fight.getMap().getCell(c1);
    if (cell1 == null)
        return false;
    Cell cell2 = fight.getMap().getCell(c2);
    if (cell2 == null)
        return false;
    return cell1.getX() == cell2.getX() || cell1.getY() == cell2.getY();
}




public int getMaxPorteeWeapon(Fight fight , Entity entity1 , Entity entity2){

    int max_portee = 0 ; 
    int range =0;
    for (Weapon weapon : entity2.getWeapons()) {
        range = weapon.getAttack().getMaxRange();
        if(range >  entity1.getDistance(entity2)  && range > max_portee){
            if(_isInlineWeapon(entity1,weapon.getId())){
                if(_isOnSameLine(fight,entity1.getCell().getId(),entity2.getCell().getId())){
                    max_portee = range;
                }

            }else{
                max_portee = range;
            }

        }
    }
    return max_portee;
}
public boolean isDamageChip(int id){
    Chip chip = Chips.getChip(id);
    if (chip == null)
        return false;
    ChipType typeOfChip = chip.getChipType();
    return typeOfChip == ChipType.DAMAGE || typeOfChip == ChipType.POISON || typeOfChip == ChipType.SHACKLE ;
}

public boolean isSupportChip(int id){
    return !isDamageChip(id) ;
}


public int getMaxPorteeChip(Fight fight , Entity entity1 , Entity entity2,boolean isSupport ){
    int max_portee = 0 ; 
    int range =0;
    for (Chip chip : entity2.getChips()) {
        if(isSupport?isDamageChip(chip.getId()):isSupportChip(chip.getId())){
            continue;
        }
        range = chip.getAttack().getMaxRange();
        if(range >  entity1.getDistance(entity2)  && range > max_portee){
            if(_isInlineChip(chip.getId())){
                if(_isOnSameLine(fight,entity1.getCell().getId(),entity2.getCell().getId())){
                    max_portee = range;
                }

            }else{
                max_portee = range;
            }

        }
    }
    return max_portee;

}

private int getNotInNeighborsConst (){
    return -1;
}


private boolean isInNeighbors (int dist ){
    return dist!=getNotInNeighborsConst ();
}
public int isInNeighbourhoodWeapon(Fight fight ,Entity entity1,Entity entity2,boolean isSupport){
    if(isSupport){
        return getNotInNeighborsConst () ; 
    }
    int dist = entity1.getDistance(entity2) ; 
    int max_portee = getMaxPorteeWeapon(fight ,entity1,entity2) ;
    return  dist <= max_portee ? max_portee : getNotInNeighborsConst () ;
}

public int isInNeighbourhoodChip(Fight fight ,Entity entity1,Entity entity2,boolean isSupport){
    int dist = entity1.getDistance(entity2) ; 
    int max_portee = getMaxPorteeChip(fight ,entity1,entity2,isSupport) ;
    return dist <= max_portee ? max_portee : getNotInNeighborsConst () ;
}

public int isInNeighbourhood(Fight fight ,Entity entity1,Entity entity2,boolean isSupport){
    int weaponN = isInNeighbourhoodWeapon(fight ,entity1,entity2,isSupport);
    int chipN = isInNeighbourhoodChip(fight ,entity1,entity2,isSupport);
    return isInNeighbors (weaponN ) ? weaponN :( isInNeighbors (chipN) ? chipN : getNotInNeighborsConst () ) ;
}


public void _setEntitiesAround(Fight fight ,Entity entity1){
    A_neighbors.clear();
    E_neighbors.clear();
    boolean isAlly=false ;
    int dist =0;
    for (Entity entity2 : fight.getEntities().values()) {
        if(_isSame(entity2,entity1) ){
            continue;
        }
        isAlly=_isAlly(entity1,entity2);
        dist = isInNeighbourhood(fight ,entity1,entity2,isAlly);

        if(isInNeighbors (dist )){
            if(isAlly ){
                A_neighbors.add(new StatEntity(entity2,dist));
            }
            else{
                E_neighbors.add(new StatEntity(entity2,dist));
            }
    }
    }

}

public static double E_N_meanDamage_D(Entity entity){
    return -1.0; //TODO 

}

public static double A_N_meanDamage_D(Entity entity){
    return -1.0; //TODO 

}
public  double E_N_meanDistance_C(Fight fight ){
    int sum = 0;
    for (StatEntity entity2 : E_neighbors) {
        sum+=entity2.distance;
    }
    double mean = (double)sum/E_neighbors.size();
    return mean;

}


public  double A_N_meanDistance_C(Fight fight ){
    int sum = 0;
    for (StatEntity entity2 : A_neighbors) {
        sum+=entity2.distance;
    }
    double mean = (double)sum/A_neighbors.size();
    return mean;

}
public boolean _isAlly(Entity entity1,Entity entity2){
    return entity1.getTeam() == entity2.getTeam();
}
public boolean _isEnnemy(Entity entity1,Entity entity2){
    return !_isAlly(entity1,entity2);
}
public boolean _isSame(Entity entity1,Entity entity2){
    return entity1.getId() == entity2.getId();
}

public  double E_N_quantityOf_Q(Fight fight , Entity entity){
    return E_neighbors.size();

}

public static int ME_hp_H(Entity entity){
    return entity.getLife();
}

public static int ME_relativeShield_H(Entity entity){
    return entity.getRelativeShield();

}

public static int ME_absoluteShield_H(Entity entity){
    return entity.getAbsoluteShield();
}

public static double ME_regenHeal_H(Entity entity){
    return -1.0; //TODO
}

public static int ME_notusedPM_M(Entity entity){
    return entity.getMP();
}

public static double ME_DoT_D(Entity entity){
    return -1.0; //TODO

}

public int  A_N_quantityOf_Q(Entity entity){
    return A_neighbors.size();
}











    
}
