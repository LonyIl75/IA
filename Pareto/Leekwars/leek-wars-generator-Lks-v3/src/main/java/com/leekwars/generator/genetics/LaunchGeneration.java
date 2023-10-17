package com.leekwars.generator.genetics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leekwars.generator.Generator;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.julienStats.SendPython.MyThread;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.fight.julienStats.util.OutcomeParse;
import com.leekwars.generator.outcome.Outcome;
import com.leekwars.generator.scenario.Scenario;
import com.leekwars.generator.test.LocalDbRegisterManager;
import com.leekwars.generator.test.LocalTrophyManager;

import leekscript.compiler.LeekScript;
import leekscript.compiler.resolver.NativeFileSystem;
import leekscript.runner.GenConfig;
import leekscript.runner.utilGen.readAdn;

public class LaunchGeneration {

    final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    private Generator generator;

    String user_dir ;    
    String parent_dir ;
    String str_python_path ;

    String csv_python_outcome_path ;
    String json_fight_outcome_path ;


    String str_client_python_file ;
    String str_server_python_file ;

    Thread t_java , t_pyth , t_serv ; 

    String host ;
    int port ; 

    String path_gen_0 = new String("../AfterFight/data");

    String adn_json = System.getProperty("user.dir")+ File.separator + "adn_json.json";


    public LaunchGeneration(String _host , int  _port) {
        this.generator =  new Generator();

        user_dir =System.getProperty("user.dir");   

        parent_dir = user_dir.substring(0,  user_dir.lastIndexOf(File.separator));
        str_python_path = Paths.get(parent_dir, "AfterFight", "src").toString();

        csv_python_outcome_path = Paths.get(parent_dir , "AfterFight", "data").toString();
        json_fight_outcome_path = Paths.get(parent_dir , "AfterFight", "outcome").toString();

        str_client_python_file = Paths.get(str_python_path,  "ClientReaderFilesActions.py").toString();
        str_server_python_file = Paths.get(str_python_path, "ServWriterFilesActions.py").toString();

        host = _host;
        port = _port;


    }

    public LaunchGeneration( String _host , int  _port , String _str_python_path , String _csv_python_outcome_path , String _json_fight_outcome_path , String _str_client_python_file , String _str_server_python_file) {
        this.generator =  new Generator();

        user_dir =System.getProperty("user.dir");   

        parent_dir = user_dir.substring(0,  user_dir.lastIndexOf(File.separator));
        str_python_path = _str_python_path;

        csv_python_outcome_path = _csv_python_outcome_path;
        json_fight_outcome_path = _json_fight_outcome_path;

        str_client_python_file = _str_client_python_file;
        str_server_python_file = _str_server_python_file;

        host = _host;
        port = _port;


        
    }
   
    public LaunchGeneration( String _host , int  _port , String _str_python_path , String _csv_python_outcome_path , String _json_fight_outcome_path , String _str_client_python_file , String _str_server_python_file , String _adn_json) {
        this(_host ,  _port , _str_python_path , _csv_python_outcome_path , _json_fight_outcome_path , _str_client_python_file , _str_server_python_file);
  
        adn_json = _adn_json;
        
    }
    
    
    public void init_pythonServer() {
        ServWriterActionsParameters swap = new ServWriterActionsParameters(json_fight_outcome_path ,host ,port);
        t_serv = new Thread (new JavaPython(str_server_python_file,"SERV_WRITER",swap));
        t_serv.start();
    }

    public void init_pythonClient() {
        ClientReaderFilesActions crfa_py = new  ClientReaderFilesActions(csv_python_outcome_path ,json_fight_outcome_path,host ,port,enumDistant.TypeClient.CLIENT_PYTHON);
        t_pyth = new Thread (new JavaPython(str_client_python_file ,"CLIENT_PYTHON",crfa_py));;
        t_pyth.start();
       
    }

    public void init_javaClient() {
        t_java = new Thread (new MyThread(enumDistant.typeClientToString(enumDistant.TypeClient.CLIENT_JAVA),host,port,queue,-1));
        t_java.start();
    }

    public void close_javaClient() throws InterruptedException{
        if(t_java != null){
        queue.put("END");
        queue.put(String.valueOf(-1));
        t_java.join();
        }
    }
    public void close_pythonClient() throws InterruptedException{
        if(t_pyth != null)
        t_pyth.join();
    }

    public void close_pythonServer() throws InterruptedException{
        if(t_serv != null)
            t_serv.join();
    }

    public void init_all() {
        init_pythonServer();
        init_pythonClient();
        init_javaClient();
    }

    public void close_all()  {
        try {
            close_javaClient();
            close_pythonClient();
            close_pythonServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Outcome launchCombat( LaunchCombatParam params){
        return launchCombat(params.getScenario() , params.getId_gen() ,params.getId_fight()  ,  params.getFilename() , params.getModifers() );
    }



    public Outcome launchCombat( Scenario scenario , int num_generation , int num_figth,String filename , ArrayList<modifierField> modifiers) {
        java.util.Map<Integer, Entity> map_entities = new java.util.HashMap<Integer, Entity>();
        LeekScript.setFileSystem(new NativeFileSystem());

 
        Outcome out = generator.runScenario(scenario, null, new LocalDbRegisterManager(), new LocalTrophyManager(),map_entities,new GenConfig(num_generation ,  num_figth, filename ) , modifiers  );//,map_entities);

        String fight_toString = out.toString() ; 
        //on enleve les logs :

        

        String log_regex ="\"logs\":\\{[^\\}]+\\}";
        //System.out.println(fight_toString);
        fight_toString = fight_toString.replaceFirst(log_regex, "\"logs\":{");

     

        //System.out.println(fight_toString);

        //System.out.println( fight_toString );
        List<Integer> arr_ids = OutcomeParse.getFigthIds (fight_toString);

        List<Integer> arr_ids_2 = OutcomeParse.getLeekIds(fight_toString);

        List<String> arr_ids_3 = OutcomeParse.converIntIdToStringId (arr_ids_2);

        String res_str = String.join("-", arr_ids_3);

        long epochTime = System.currentTimeMillis();
        String json_filename = String.join("_",String.valueOf(num_generation),String.valueOf(num_figth),res_str,Long.toString(epochTime))+".json";


        try {
            queue.put( MyThread.embedMessage (json_filename ,fight_toString));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return out;
      
    }
    static public Outcome  runScenario(String scenario_file , Generator generator, java.util.Map<Integer, Entity> map_entities) {
        Scenario scenario = Scenario.fromFile(new File(scenario_file));
        if (scenario == null) {
            System.out.println("Failed to parse scenario!");
            return null;
        }
            Outcome out = generator.runScenario(scenario, null, new LocalDbRegisterManager(), new LocalTrophyManager(),map_entities);//,map_entities);

            return out;
    }

public void launchGeneration(LaunchGenerationParam params){
    launchGeneration(params.getKeys_adn(),params.getScenario());
}

public void launchGeneration(ArrayList<String> keys_adn,Scenario scenario){
    LaunchCombatParam params = null;
    HashMap<String, LaunchCombatThread> threads =  new HashMap<String, LaunchCombatThread>();

    

    String path_gen = new String();
    File dir_ = null;


    int id_gen = -1;
    int id_fight = -1 ;
    String[] splits = null;

    ArrayList<modifierField> modifiers = new ArrayList<modifierField>();
    String split_gen , split_fight ;

    String token = null;
    String key ; 


    boolean isCreated = false;
    for (String _key : keys_adn){
        key=_key.replaceAll("\\s+", "");
        splits = key.split(LaunchGeneration.TOKEN_MODIFIERS);
        if(splits.length > 1){
            for( modifierField f : modifyFieldsAI.getFields()){
                token = modifyFieldsAI.getToken(f) ;
                if(splits[1].contains( token )){
                    modifiers.add(f);
                }
            }
            key= splits[1];

        }else{
            key = splits[0];
        }
        splits = key.split("_");
        split_gen = splits[0].replaceFirst("\\D*(\\d*).*", "$1");
        split_fight= splits[1].replaceFirst("\\D*(\\d*).*", "$1");
        key = split_gen+"_"+split_fight;
        id_gen = Integer.parseInt(split_gen);
        id_fight = Integer.parseInt( split_fight );
        path_gen= path_gen_0+File.separator  +split_gen;
        isCreated =  Miscellaneous.createIfNotExist(path_gen);

        isCreated =  Miscellaneous.createIfNotExist(path_gen+ File.separator + split_fight);

        
        params  = new LaunchCombatParam(scenario, id_gen, id_fight,adn_json,modifiers);
        threads.put(key ,  new LaunchCombatThread( params, this));
        threads.get(key).start();
        try {
            threads.get(key).join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /*for (LaunchCombatThread myThread : threads.values()) {
        try {
            myThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/



}

public static String scenario_file_str_base =  DefaultConfigMain.instance.scenario_file_str_base; 

public static Scenario scenario_base = Miscellaneous.scenarioFromFile( scenario_file_str_base);


public String scenario_file_str  = scenario_file_str_base;
public Scenario scenario = scenario_base;

public void setScenario ( String scenario_file_str){
    this.scenario_file_str = scenario_file_str;
    this.scenario = Miscellaneous.scenarioFromFile( scenario_file_str);
}

public final static String TOKEN_END = "END";
public final static String TOKEN_SEP_GENERATION = ";";
public final static String TOKEN_SEP_SCENARIO = ":";
public final static String TOKEN_SEP_FIGHT = ",";
public final static String TOKEN_MODIFIERS = "!";



public static boolean isEnd(String message ){
    return message.equals(TOKEN_END) ;
}


public static String __pathScenario = "test/ai/scenario/";
static mSocket leSocket = null;

public boolean launchMain() {
    //connect python Robin 

    leSocket = new mSocket("localhost" ,  54786);

    boolean res = true ; 
    
    String message = "" ;
    ArrayList<String> keys_adn = DefaultConfigMain.instance.keys_adn;

    String scenario_str = new String(scenario_file_str_base) ;
    Scenario scenario = new Scenario (scenario_base) ; 
    

    String reste_message = "";
    String[]splits = null;

    HashMap<String,LaunchGenerationThread>  generationsThreads = new HashMap<String,LaunchGenerationThread>();
    init_all();
    String reception_m = new String();
  
    try {   
    res= leSocket.init();

    char[] mybytearray  = new char[1024];
        
    if(res)
    while (  ! LaunchGeneration.isEnd(message) ) {
        if(message.length() < 3){
            leSocket.msend("READY" ) ;
            leSocket.mrecv (mybytearray  ) ;
            reception_m = new String(mybytearray);
            System.out.println("reception_m " + reception_m);
            if( reception_m.contains(TOKEN_END)){
                reception_m = reception_m.replace(TOKEN_END, "");
                reception_m = reception_m.trim()+TOKEN_END;
            }

            message  += (reception_m != null ? reception_m : "") ;
        }

        System.out.println(message);
  

        
        
        if(message != ""){
            //System.out.println(message);
                    splits = message.split(TOKEN_SEP_GENERATION,2);
            if(splits.length == 1){				
                String tmp = message.substring(message.length()-TOKEN_END.length());
                if(tmp.equals(TOKEN_END) ){
                    reste_message = TOKEN_END;
                    message = message.substring(0,message.length()-TOKEN_END.length()).replaceAll("\\s+", "");
                    if(LaunchGeneration.isEnd(message )){
                        System.out.println("message finis " +message);
                        break;
                    }

                }else{
                System.out.println("message non finis " +message);
                continue ; 
                }
            }else{
                reste_message = splits[1].replaceAll("\\s+", "");
                message = splits[0].replaceAll("\\s+", "");
            }
            
        
            System.out.println("MESSAGE|" +message +"|RESTE " + reste_message);


            //traitement message
            splits = message.split(TOKEN_SEP_SCENARIO,2);
            if(splits.length > 1 )scenario_str = __pathScenario+splits[0];
            else scenario_str = DefaultConfigMain.instance.scenario_file_str_base;
            //readAdn.getFromFile(adn_json, reste_message, lst_str_final);
            scenario =  Miscellaneous.scenarioFromFile( scenario_str);

            message = splits[(splits.length > 1 ? 1 :0)]; ; 
            keys_adn = new ArrayList( Arrays.asList(message.split( TOKEN_SEP_FIGHT)));
            System.out.println("ICI MESS " +message);
            message = reste_message;
            if(message.length() > 0)
            message=message.replace("\0","");
            //lancement generation
            String key  = keys_adn.get(keys_adn.size()-1);
            generationsThreads.put(  key  , new LaunchGenerationThread( new LaunchGenerationParam(scenario,keys_adn), this )) ;
            generationsThreads.get(key).start();
            try {
                generationsThreads.get(key).join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("ICI SCENAR " +scenario_str);
        }
        else{
            System.out.println("message vide");
            
            break;
        }

        
        



        
        
    }
}
catch (IOException e) {
    System.out.println("Erreur Lecture ");
    e.printStackTrace();
    res= false ;
}


   /*  for (LaunchGenerationThread myThread : generationsThreads.values()) {
        try {
            myThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    close_all();
    try {
        leSocket.msockclose();
    } catch (IOException e) {
        e.printStackTrace();
    }

    Runtime.getRuntime().addShutdownHook(new Thread(() -> { 
        try {
            System.out.println("Shutdown Hook is running !");
            close_all();
            leSocket.msockclose();
            
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }));

    return res;

}

}