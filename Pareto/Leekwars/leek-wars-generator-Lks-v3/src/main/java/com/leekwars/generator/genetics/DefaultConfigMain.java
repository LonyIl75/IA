package com.leekwars.generator.genetics;

import java.io.File;
import java.util.ArrayList;

import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.scenario.Scenario;

public class DefaultConfigMain {

public String scenario_file_str_base ;  


public   int idGen;
public int nbFight ;



public String sepGenFight ;

public ArrayList<String> keys_adn  ; 

public static DefaultConfigMain instance = new DefaultConfigMain();

private DefaultConfigMain( ){
    scenario_file_str_base = new String("test/ai/scenario/scenario-test.json"); 
    idGen = 1;
    nbFight = 10;
    sepGenFight = new String("_");
    keys_adn = Miscellaneous. mergeLst ( sepGenFight  , Miscellaneous.lstFill (String.valueOf(idGen) , nbFight ) , Miscellaneous.ascNb(1,nbFight) ) ;
}

    
}
