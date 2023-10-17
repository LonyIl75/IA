package com.leekwars.generator.genetics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.leekwars.generator.scenario.Scenario;

public class LaunchGenerationParam {

    public Scenario scenario;
    public ArrayList<String> keys_adn;

    public LaunchGenerationParam(Scenario scenario, ArrayList<String> keys_adn){
        this.scenario = scenario;
        this.keys_adn = keys_adn;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public ArrayList<String> getKeys_adn() {
        return keys_adn;
    }



}
