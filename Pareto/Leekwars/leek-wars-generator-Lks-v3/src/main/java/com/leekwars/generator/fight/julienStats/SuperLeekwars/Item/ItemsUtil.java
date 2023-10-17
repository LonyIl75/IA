package com.leekwars.generator.fight.julienStats.SuperLeekwars.Item;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.Util;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.weapons.Weapon;

import java.util.TreeMap;

public abstract class ItemsUtil<T>{

    public final String itemDataPath;
    public final String filenameJson ;
    public final String fullpathJson ; 

      //JULIEN IDEE :
    /*
    WeaponsBis
    Map<Integer, myItem> items  ;
    */
    //REFUSER PAR JULIEN :
    /*
     * Meme si je ne suis pas convaincu PILOW n'a pas fait comme ca pour checkData 
     */

    Map<Integer, myItem<T>> items  =new TreeMap<Integer ,  myItem<T>>();
    Map<Integer,Integer> idItemId =new TreeMap<Integer,Integer>(); 

    public ItemsUtil(String _filenameJson){
       this("data",_filenameJson);
    }


    public ItemsUtil(String path , String _filenameJson){
        itemDataPath = path;
        filenameJson = _filenameJson;
        fullpathJson = itemDataPath + "/" + filenameJson; // File.separator 
        loadAndSetItems();
    }

    public Map<Integer, myItem<T>> getItems() {
        return items;
    }
    public Map<Integer, Integer> getIdItemId() {
        return idItemId;
    }



    public String getItemPath(){
        return fullpathJson;
    }


    public abstract void setItems();

    //tester
    public   static int _getId(int idItem , String _fullpathJson ,String keyItem ){
        int id_element =0;
        JSONObject items = JSON.parseObject(Util.readFile(_fullpathJson));
        for (String id : items.keySet()) {
                JSONObject item = items.getJSONObject(id);
                id_element = (keyItem!=null ? item.getInteger(keyItem) : Integer.parseInt(id)) ;
                if(id_element==idItem){
                    return Integer.parseInt(id);
                }
            }
        return -1;
    }

    //tester
    public int getId(int idItem){
        check();
        return idItemId.get(idItem);
    }

    //retourne l'id de l'arme a partir de l'id item de l'arme 
    // return -1 si l'arme identifié par idItemWeaponn'est pas trouvée sinon l'id de l'arme
   
    public int getIdItem(int id){
        for (Integer idItem : idItemId.keySet()) {
            if(this.getId(idItem)==id){
                return idItem;
            }
        }
        return -1;
    }

    public abstract void load();
    public boolean isInitialized() {
        return items != null && items.size()>0;
}

    public void resetItems(){
        items.clear();
        loadAndSetItems();

    }
    public void loadAndSetItems(){
        load();
        setItems();
    }

    public void check() {
        if(!isInitialized()){
            loadAndSetItems();
        }
    }


    public  T getItem(int idItem){
    
        check();
        myItem<T> l=  items.get(idItem);
        if(l!=null) return l.getSelf();
        else {
            System.out.println("ITEM NOT FOUND : "+idItem);
            return null;
        }
    }

    

    
}
