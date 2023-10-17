package com.leekwars.generator.fight.julienStats.SuperLeekwars.Item;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.chips.Chip;
import com.leekwars.generator.attack.chips.ChipType;

import leekscript.runner.AI;

class ChipBis extends myItem<Chip> {// IVisitorWeaponChip {

        public ChipBis(Chip chip) {
            super(chip);
        }
        public Attack getAttack(){
            return getSelf().getAttack();
        }
        public int getId(){
            return getSelf().getId();
        }
        public String getName(){
            return getSelf().getName();
        }
        public int getCost(){
            return getSelf().getCost();
        }
        //public int getLevel();
        public int getTemplate(){
            return getSelf().getTemplate();
        }

    }

    
    
