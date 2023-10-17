package com.leekwars.generator.fight.julienStats.SuperLeekwars.Item;

import com.leekwars.generator.attack.Attack;

public abstract class  myItem<T> {
    T item ;
    
    public myItem(T item) {
        this.item = item;
        
    }

    public  T getSelf(){
        return item;
    }
    public abstract Attack getAttack();
    public abstract int getId();
    public abstract String getName();
    public abstract int getCost();
    //public int getLevel();
    public abstract int getTemplate();
    //public byte getType();

    public T getItem(){
        return item;
    }

    public int _getItemIdItem(){
        return getAttack().getItemId();
    
    }
}
/*
 *  public abstract int getTemplate(){
        return ((myItem)getSelf()).getTemplate();
    }
 */
