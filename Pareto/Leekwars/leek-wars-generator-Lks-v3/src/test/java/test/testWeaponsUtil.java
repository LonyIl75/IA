package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.ItemsUtil;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.WeaponsUtil;

public class testWeaponsUtil {


    String jsonFileWeapon = "data/weapon.json";
    String jsonFileChip = "data/chip.json";

    int idWeapon_laser =6;//42;//6; //laser 
    int idWeapon_magnum=5;//45;//5; //cercle complet
    //int idWeapon_neutrino=27;//182;//27; // X diagonal
    int idWeapon_lightninger=25;//180;//25; //star
    int idWeapon_grenadeLauncher =7;//73;//7; //cercle troué centre

    
    int idItem_laser =42;//6; //laser 
    int idItem_magnum=45;//5; //cercle complet
    //int idItem_neutrino=182;//27; // X diagonal
    int idItem_lightninger=180;//25; //star
    int idItem_grenadeLauncher =43;//7; //cercle troué centre

    Weapon weapon_laser , weapon_magnum, weapon_neutrino, weapon_lightninger, weapon_grenadeLauncher;

 

    @Before
    public void setUp() throws Exception {
        WeaponsUtil.provider_weaponsUtil = new WeaponsUtil();
        WeaponsUtil.provider_weaponsUtil.setItems();

        weapon_laser = Weapons.getWeapon(idItem_laser );
        weapon_magnum = Weapons.getWeapon(idItem_magnum );
        //weapon_neutrino = Weapons.getWeapon(idItem_neutrino );


    }

    @Test
    public void test_getIdItemId() {
        boolean isEqual = true;
        isEqual = isEqual && WeaponsUtil.provider_weaponsUtil.getId(idItem_laser)==idWeapon_laser;
        isEqual = isEqual && WeaponsUtil.provider_weaponsUtil.getId(idItem_magnum)==idWeapon_magnum;
        //isEqual = isEqual && WeaponsUtil.provider_weaponsUtil.getId(idItem_neutrino)==idWeapon_neutrino;
        Assert.assertTrue(isEqual);
        
    }

    @Test
    public void test_getItems() {
        boolean isEqual = true;
        isEqual = isEqual &&  weapon_laser.equals((Weapon)WeaponsUtil.provider_weaponsUtil.getItems().get(idItem_laser).getSelf());
        isEqual = isEqual &&  weapon_magnum.equals((Weapon)WeaponsUtil.provider_weaponsUtil.getItems().get(idItem_magnum).getSelf());
        //isEqual = isEqual &&  weapon_neutrino.equals((Weapon)WeaponsUtil.provider_weaponsUtil.getItems().get(idItem_neutrino).getSelf());
        Assert.assertTrue(isEqual);
        
    }
    @Test
    public void test_getIdItem() {
        boolean isEqual = true;
        isEqual = isEqual &&  WeaponsUtil.provider_weaponsUtil.getIdItem(idWeapon_laser)==idItem_laser;
        isEqual = isEqual &&  WeaponsUtil.provider_weaponsUtil.getIdItem(idWeapon_magnum)==idItem_magnum;
        //isEqual = isEqual &&  WeaponsUtil.provider_weaponsUtil.getIdItem(idWeapon_neutrino)==idItem_neutrino;
        Assert.assertTrue(isEqual);
        
    }



    @Test
    public void test_getId(){
        boolean isEqual = true;
        isEqual = isEqual &&  WeaponsUtil._getId(idItem_laser,WeaponsUtil.provider_weaponsUtil.getItemPath())==idWeapon_laser;
        isEqual = isEqual &&  WeaponsUtil._getId(idItem_magnum,WeaponsUtil.provider_weaponsUtil.getItemPath())==idWeapon_magnum;
        //isEqual = isEqual &&  WeaponsUtil._getId(idItem_neutrino,WeaponsUtil.provider_weaponsUtil.getItemPath())==idWeapon_neutrino;
        Assert.assertTrue(isEqual);
        
    }

    
    
}
