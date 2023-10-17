package com.leekwars.generator.fight.julienStats.Stats;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumDot;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyFunction;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyResult;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.fight.julienStats.util.MySet;

import leekscript.runner.values.ArrayLeekValue;



public class LazyStats  <G extends Enum<G> , T extends IStatsEnum<G>> extends Stat<G,T> {



    static LazyBaseCaracteristique lazy_baseCara = new LazyBaseCaracteristique(StatCaracteristique.provider_statCara);
    static LazyCaracteristique lazy_statCara = new LazyCaracteristique(StatCaracteristique.provider_statCara);
    static LazyDot lazy_statDot = new LazyDot(StatDot.provider_statDot);

    ArrayList<LazyResult <G ,T>> lazyResults ;

    public LazyStats(EntityAI entity , T type  , boolean isBase){
        super(type, isBase);
        lazyResults = (ArrayList<LazyResult <G ,T>>)init_lazyResults(entity);

    
    }
    public ArrayList<LazyResult <G ,T>> getLazyResults(){
        return lazyResults;
    }
    public String toString(){
        String res_str = "";
        for (LazyResult <G ,T> l : lazyResults){
            res_str += l.toString() + ",";
        }
        return res_str;
    }

    public ArrayList<?> init_lazyResults(EntityAI entity ){
        ArrayList<?> lazyResults  = new ArrayList<>();

        if (type instanceof StatCaracteristique){
            if (isBase){
                lazyResults = lazy_baseCara.init_lazyResults(entity);
            }
            else{
                lazyResults = lazy_statCara.init_lazyResults(entity);
            }
        }
        else if (type instanceof StatDot){
            lazyResults = lazy_statDot.init_lazyResults(entity);
        }
        return lazyResults;
    }


    
    public static class LazyCaracteristique extends LazyFunction<enumCaracteristique,StatCaracteristique>{
    
        public LazyCaracteristique(StatCaracteristique provider) {
            super(provider);
        }


        @Override
        public Object exec(Object _entity , enumCaracteristique  c ){
            EntityAI entity = (EntityAI) _entity;
            String name_method = StatCaracteristique.provider_statCara.getMethodName(c);
            System.out.println("name_method : " + name_method);
            try{

                Class[] cArg = new Class[1];
                cArg[0] = Object.class;
                Method method =  entity.getClass().getMethod(name_method, cArg);//getDeclaredMethod si private sinon getMethod NB: aucune method private mise a part put cell dans EntityAI
                Object [] n = {null};
                Object v = method.invoke(entity, n);
                return  v;
        }
        catch(Exception e){
            System.out.println("Exception in LazyCaracteristique.exec");
            return 0;
        }
        }


        @Override
        public void update(Object obj, enumCaracteristique index) {
            return;
        }
    
    }
    

    
    public static class LazyBaseCaracteristique extends LazyFunction<enumCaracteristique,StatCaracteristique>{



        public LazyBaseCaracteristique(StatCaracteristique provider) {
            super(provider);

        }


        @Override
        public Object exec(Object _entity , enumCaracteristique  c ){
            EntityAI entity = (EntityAI) _entity;
            String name_method = StatCaracteristique.provider_statCara.getBaseMethodName(c); //
            try{
                Class[] cArg = new Class[1];
                cArg[0] = Object.class;
                Method method =  entity.getClass().getMethod(name_method, cArg); //getDeclaredMethod si private sinon getMethod
                Object [] n = {null};
                Object v = method.invoke(entity, n);
                return v;
        }
        catch(Exception e){
            System.out.println("Exception in LazyCaracteristique.exec");
            return 0;
        }
        }


        @Override
        public void update(Object obj, enumCaracteristique index) {
            return;
        }
    }



    public static class LazyDot extends LazyFunction<enumDot,StatDot>{

        List<Effect> _effects ;

        public LazyDot(StatDot provider){
            super(provider);
        }

        @Override
        public Object exec(Object _entity , enumDot  _dot ){

            EntityAI entity = (EntityAI) _entity;
            int _id = StatDot.provider_statDot.getIndexOf(_dot);
            for (Effect effect : _effects) {
                if (effect.getID() == _id ) {
                    return 1 ;//return effect.getValue();
                }
            }
            return -1;

        }
        @Override
        public void update(Object _entity,  enumDot  index) {
            EntityAI entity = (EntityAI) _entity;
            _effects = entity.leek().getEffects();
        }
    }
        
}