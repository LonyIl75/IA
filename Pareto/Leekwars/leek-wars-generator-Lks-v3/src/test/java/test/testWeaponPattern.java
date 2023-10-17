package test;

import com.leekwars.generator.fight.julienBdd.weaponPattern;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.WeaponsUtil;
import com.leekwars.generator.fight.julienStats.old.statEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.security.auth.kerberos.KerberosTicket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testWeaponPattern {

    weaponPattern weapPattern_laser , weapPattern_magnum, weapPattern_neutrino, weapPattern_lightninger, weapPattern_grenadeLauncher;
    int idItem_laser =42;//6; //laser 
    int idItem_magnum=45;//5; //cercle complet
    //int idItem_neutrino=182;//27; // X diagonal
    int idItem_lightninger=180;//25; //star
    int idItem_grenadeLauncher =43;//7; //cercle troué centre


    Weapon weapon_laser ;
    int idItemArme_laser ;

    Weapon weapon_magnum ;
    int idItemArme_magnum ;

    Weapon weapon_lightninger ;
    int idItemArme_lightninger ;

    

    @Before
    public void setUp() throws Exception {

    
        /* 
    weapPattern_laser = new weaponPattern(idItem_laser, idItem_laser);
    weapPattern_magnum = new weaponPattern(idItem_magnum, idItem_magnum);
    weapPattern_neutrino = new weaponPattern(idItem_neutrino, idItem_neutrino);
    weapPattern_lightninger = new weaponPattern(idItem_lightninger, idItem_lightninger);
    weapPattern_grenadeLauncher = new weaponPattern(idItem_grenadeLauncher, idItem_grenadeLauncher);
    */


    }
    @Test
    public void testLaser()throws Exception {


        weaponPattern weaponPattern = null;

        JSONObject json = new JSONObject();
        for ( Integer k : WeaponsUtil.provider_weaponsUtil.getItems().keySet()){
            System.out.println("idItem : "+k+" idWeapon : "+WeaponsUtil.provider_weaponsUtil.getItems().get(k).getName());
            weaponPattern =  new weaponPattern(k,k) ; 
        
            json.put( String.valueOf( weaponPattern.getIdArme() ), weaponPattern.getJSON());
            
        }


            String path_ = weaponPattern.getFilePath();
            try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
                out.write(json.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
            }



        
    
    }
    


    /* 
    @Test 
    public  void tesWeaponCost() throws Exception{

        int cst = 0;
        Weapon weapon;
        int cst_expected =0;
        int idItem2 =0;
        for(int idItem : Weapons.getTemplates().keySet()){
            cst= statEntity._getWeaponCost(idItem);
            //idItem2 = statEntity._getIdWeapon(idItem);
            weapon = weaponPattern._loadWeapon(   idItem);
            cst_expected =weapon.getCost();
            Assert.assertEquals(  cst_expected, cst);

        }
      
    }*/

    
}
