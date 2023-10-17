package com.leekwars.generator.fight.julienStats.SuperLeekwars.Item;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.weapons.Weapon;

import leekscript.runner.AI;

class WeaponBis extends myItem<Weapon> {


    public WeaponBis(Weapon item) {
        super(item);
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