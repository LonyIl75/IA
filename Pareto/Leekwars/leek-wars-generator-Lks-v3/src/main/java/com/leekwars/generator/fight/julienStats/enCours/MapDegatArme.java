package com.leekwars.generator.fight.julienStats.enCours;

import java.util.ArrayList;

import com.leekwars.generator.fight.julienBdd.weaponPattern;

public class MapDegatArme { // implements Ask{


    
static int nbArme =4 ; 


//STATIQUE :

    int [] idArme ; // idArme[0] = idPremièreArme , idArme[1] = idDeuxièmeArme , ect
    //2^0 : idArme[0] ;2^1 : idArme[1] ;2^2 : idArme[2] ; ect 

        
    ArrayList<weaponPattern>  weaps;

    char[] [] degatMapIdsArme; // binaire : 0= aucune arme , 1 = première arme , 2 = deuxième arme ,8 = quatrième arme , 3 = première et deuxième arme , ect 
    //degatMapIdsArme[i][j] = sum(k=0,4)[indicateur_k*2^k] avec i<32 et j<32 
    char[] [] degatMapAOEIdsArme; //  binaire : 0= aucune arme , 1 = première arme , 2 = deuxième arme ,8 = quatrième arme , 3 = première et deuxième arme , ect

    char [] nbCellCoverByArme ; // nbCellCoverByArme[0] = nbCellCoverByPremièreArme , nbCellCoverByArme[1] = nbCellCoverByDeuxièmeArme , ect
    //=> 256 case max 



    float  aoe_mean_percent =0.5f; //100+60+20=180 ; 180/3=0.6 ; on est optimiste alors 0.5
    double [] degatArmeMean_base; 
    float mean_degat_base; // moyenne des dégats infligés par l'entité claculer a partir nbCellCoverByArme et degatArmeMean_base

    //Stat sur l'ensemble des armes : aggreg : max , moy , ect

    int MSD; // rayon de la degatMap arrondi sup <=> MSD : maxShootDistance

    int MDR; // rayon de la degatMap arrondi inf <=> MDR : maxDistanceReconaissance

//DYNAMIQUE :


    //Pour chaque arme dans weaps : 

    int [] rankArme ;

    double [] degatArmeMean_atm ; //update a chaque entrave
    float mean_degat_atm; // moyenne des dégats infligés par l'entité claculer a partir nbCellCoverByArme et degatArmeMean_atm

    /* 
    @Override 
    public LazyFunction geAttribut(String attribut){
        return null;

    }*/


public  static int getNbArme(){
    return nbArme;

}

public void initMapArme(){

    degatMapIdsArme= new char [weaponPattern.getSizePattern()][weaponPattern.getSizePattern()];
    degatMapAOEIdsArme= new char [weaponPattern.getSizePattern()][weaponPattern.getSizePattern()];
    nbCellCoverByArme = new char [getNbArme()];
}
public void initDegatArme(){
    degatArmeMean_atm = new double [getNbArme()];
    degatArmeMean_base = new double [getNbArme()];
    rankArme = new int [getNbArme()];

}
public void initWeaponPattern(){
    weaps = new ArrayList<weaponPattern>(getNbArme());
}



private void setIdArme(int [] _idArme){
    for(int i =0 ; i<getNbArme() ; i++){
        idArme[i]=_idArme[i];
    }

}


private void setWeaponPattern() throws Exception {

    for (int i =0 ; i<getNbArme() ; i++){
            weaps.add(new weaponPattern(idArme[i], idArme[i]));
    }

}

private void setMDR(){
    this.MDR=this.MSD+getCstMDR();
}

private void setMSD(){
    MSD = maxShootDistance();
    setMDR();
    

}
public  int maxShootDistance(){
    int maxShootDistance = -1;
    int tmp;
    for (weaponPattern weap : weaps){
        tmp=weap.getMaxRange();
        if ( tmp >maxShootDistance){
            maxShootDistance =  tmp;
        }
    }
    return maxShootDistance;
}

public void setMapsArme() throws Exception{
    weaponPattern wp;
    for (int i =0 ; i<weaps.size() ; i++){
        wp=weaps.get(i);
        nbCellCoverByArme[i]=(char)weaponPattern.addFilterWeapon(degatMapIdsArme , wp.getDegatMap() ,  i);
        nbCellCoverByArme[i]=(char)weaponPattern.addFilterWeapon(degatMapAOEIdsArme , wp.getDegatMapAOE() , i);
}
}

public void updateRankArme(){
    double degatMean =0;
    for(int i=0 ; i<getNbArme() ; i++){
        degatMean = degatArmeMean_atm[i];
        for(int j=0 ; j<getNbArme() ; j++){
            if(i!=j && degatMean<degatArmeMean_atm[j]){
                rankArme[i]++;
            }
            }
        }
        
    }
/* 
    public void updateDegatMean(Stats stats){

        mean_degat_atm = computeMeanDeg(idArme , stats);
    }
    public static float  computeMeanDeg(int idArme , Stats stats){
        float  mean_degat =0;
         for (int i = 0 ; i<getNbArme() ; i++){
             mean_degat+=  WeaponUtil.getDamageWeapon(idArme,stats);
         }
        return mean_degat;
     
     }
     */
     public static int getCstMDR(){
         return 12;
     
     }



    
}
