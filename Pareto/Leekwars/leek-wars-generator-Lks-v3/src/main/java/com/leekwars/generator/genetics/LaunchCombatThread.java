package com.leekwars.generator.genetics;

import com.leekwars.generator.scenario.Scenario;

public class LaunchCombatThread extends Thread {

    LaunchCombatParam params;
    //static Scenario scenario = null;
    LaunchGeneration obj  ; 

    public LaunchCombatThread(LaunchCombatParam _params , LaunchGeneration _obj){
        this.params = _params;
        /*if( scenario != null)
            this.params.scenario = LaunchGenerationThread.scenario;*/
        this.obj = _obj;
    }
    public void run() {
      obj.launchCombat(params);
    }

    /*public static void setScenario(Scenario _scenario){
        scenario = _scenario;
    }*/
    
}
