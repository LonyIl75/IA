package com.leekwars.generator.genetics;

import java.io.File;
import java.util.ArrayList;

import com.leekwars.generator.scenario.Scenario;

public class LaunchCombatParam  {
    public Scenario scenario;
    public int id_gen;
    public int id_fight;
    public String  filename;
    public ArrayList<modifierField> modifers ;

    public LaunchCombatParam( Scenario scenario, int id_gen, int id_fight, String filename , ArrayList<modifierField> _modifers){
        this.scenario = scenario;
        this.id_gen = id_gen;
        this.id_fight = id_fight;
        this. filename =  filename;
        this.modifers = _modifers;
    }

    public  Scenario  getScenario() {
        return scenario;

    }

    public ArrayList<modifierField> getModifers() {
        return modifers;
    }

    public int getId_gen() {
        return id_gen;
    }
    public int getId_fight() {
        return id_fight;
    }
    public String getFilename() {
        return  filename;
    }




}
