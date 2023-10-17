package com.leekwars.generator.genetics;

public class LaunchGenerationThread extends Thread{

    public LaunchGenerationParam params;
    public LaunchGeneration obj  ;

    public LaunchGenerationThread(LaunchGenerationParam params, LaunchGeneration obj){
        this.params = params;
        this.obj = obj;
    }
    
    public void run(){
        obj.launchGeneration( params);
	
	
	
    }
    
    
}
