package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.Chips;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.ChipsUtil;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.ItemsUtil;


public class testChipsUtil {
    
    int idChip_ice = 2 ; // attaque_chip
    int idChip_bandage =3; // soin_chip
    int idChip_shield =20; // protection_chip
    int idChip_teleportation =59; // tactique_chip
    int idChip_knowledge = 155; // amelioration_chip
    int idChip_plague = 99; // poison_chip
    int idChip_soporific = 95; // entrave_chip
    int idChip_transmutation = 161; //soin_specialB_chip
    int idChip_thorn = 100; // renvoi_chip
    int idChip_desintegration = 160; // attaque_specialB_chip
    int idChip_punishment = 114; // attaque_specialV_chip

    Chip chip_ice , chip_bandage, chip_shield, chip_teleportation, chip_knowledge, chip_plague, chip_soporific, chip_transmutation, chip_thorn, chip_desintegration, chip_punishment;

    int idItem_ice = idChip_ice;
    int idItem_bandage = idChip_bandage;
    int idItem_shield = idChip_shield;
    int idItem_teleportation = idChip_teleportation;
    int idItem_knowledge = idChip_knowledge;
    int idItem_plague = idChip_plague;
    int idItem_soporific = idChip_soporific;
    int idItem_transmutation = idChip_transmutation;
    int idItem_thorn = idChip_thorn;
    int idItem_desintegration = idChip_desintegration;
    int idItem_punishment = idChip_punishment;


    
  

    @Before
    public void setUp() throws Exception {


        chip_ice = Chips.getChip(idItem_ice );
        chip_bandage = Chips.getChip(idItem_bandage );
        chip_shield = Chips.getChip(idItem_shield );
        chip_teleportation = Chips.getChip(idItem_teleportation );
        chip_knowledge = Chips.getChip(idItem_knowledge );
        chip_plague = Chips.getChip(idItem_plague );
        chip_soporific = Chips.getChip(idItem_soporific );
        chip_transmutation = Chips.getChip(idItem_transmutation );
        chip_thorn = Chips.getChip(idItem_thorn );
        chip_desintegration = Chips.getChip(idItem_desintegration );
        chip_punishment = Chips.getChip(idItem_punishment );

    }

    @Test
    public void test_getIdItemId() {
        boolean isEqual = true;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_ice)==idChip_ice;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_bandage)==idChip_bandage;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_shield)==idChip_shield;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_teleportation)==idChip_teleportation;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_knowledge)==idChip_knowledge;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_plague)==idChip_plague;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_soporific)==idChip_soporific;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_transmutation)==idChip_transmutation;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_thorn)==idChip_thorn;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_desintegration)==idChip_desintegration;
        isEqual = isEqual && ChipsUtil.provider_chipsUtil.getId(idItem_punishment)==idChip_punishment;
       
        Assert.assertTrue(isEqual);
        
    }

    @Test
    public void test_getItems() {
        boolean isEqual = true;
        isEqual = isEqual &&  chip_ice.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_ice).getSelf());
        isEqual = isEqual &&  chip_bandage.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_bandage).getSelf());
        isEqual = isEqual &&  chip_shield.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_shield).getSelf());
        isEqual = isEqual &&  chip_teleportation.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_teleportation).getSelf());
        isEqual = isEqual &&  chip_knowledge.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_knowledge).getSelf());
        isEqual = isEqual &&  chip_plague.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_plague).getSelf());
        isEqual = isEqual &&  chip_soporific.equals((Chip)ChipsUtil.provider_chipsUtil.getItems().get(idItem_soporific).getSelf());
        Assert.assertTrue(isEqual);
        
    }
    @Test
    public void test_getIdItem() {
        boolean isEqual = true;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_ice)==idItem_ice;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_bandage)==idItem_bandage;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_shield)==idItem_shield;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_teleportation)==idItem_teleportation;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_knowledge)==idItem_knowledge;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_plague)==idItem_plague;
        isEqual = isEqual &&  ChipsUtil.provider_chipsUtil.getIdItem(idChip_soporific)==idItem_soporific;
        Assert.assertTrue(isEqual);
        
    }



    @Test
    public void test_getId(){
        boolean isEqual = true;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_ice,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_ice;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_bandage,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_bandage;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_shield,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_shield;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_teleportation,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_teleportation;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_knowledge,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_knowledge;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_plague,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_plague;
        isEqual = isEqual &&  ChipsUtil._getId(idItem_soporific,ChipsUtil.provider_chipsUtil.getItemPath())==idChip_soporific;
        Assert.assertTrue(isEqual);
        
    }

    
    
}
