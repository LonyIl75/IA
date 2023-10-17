package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import com.leekwars.generator.genetics.ClientReaderFilesActions;
import com.leekwars.generator.genetics.DefaultConfigMain;
import com.leekwars.generator.genetics.JavaPython;

import com.leekwars.generator.genetics.LaunchCombatParam;
import com.leekwars.generator.genetics.LaunchCombatThread;
import com.leekwars.generator.genetics.LaunchGeneration;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.fight.entity.EntityAI;
import com.leekwars.generator.fight.julienBdd.weaponPattern;
import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats._AffectStat;
import com.leekwars.generator.fight.julienStats.Stats.LazyStats.LazyBaseCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.LazyStats.LazyCaracteristique;
import com.leekwars.generator.fight.julienStats.lazyComputing.LazyResult;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;
import com.leekwars.generator.leek.Leek;
import com.leekwars.generator.fight.julienStats.enCours.Stats;

import com.leekwars.generator.maps.Map;
import com.leekwars.generator.outcome.Outcome;
import com.leekwars.generator.scenario.Scenario;
import com.leekwars.generator.test.LocalDbRegisterManager;
import com.leekwars.generator.test.LocalTrophyManager;
import com.leekwars.generator.fight.julienStats.Stats.IStatsEnum.enumCaracteristique;
import leekscript.LSException;
import leekscript.runner.AI;
import leekscript.runner.utilGen.readAdn;
import leekscript.runner.values.ArrayLeekValue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.leekwars.generator.Data;
import com.leekwars.generator.Generator;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.Fight;
import com.leekwars.generator.fight.action.Action;
import com.leekwars.generator.fight.action.ActionAIError;
import com.leekwars.generator.fight.action.ActionAddEffect;
import com.leekwars.generator.fight.action.ActionChestOpened;
import com.leekwars.generator.fight.action.ActionDamage;
import com.leekwars.generator.fight.action.ActionEndTurn;
import com.leekwars.generator.fight.action.ActionEntityDie;
import com.leekwars.generator.fight.action.ActionEntityTurn;
import com.leekwars.generator.fight.action.ActionHeal;
import com.leekwars.generator.fight.action.ActionInvocation;
import com.leekwars.generator.fight.action.ActionKill;
import com.leekwars.generator.fight.action.ActionLama;
import com.leekwars.generator.fight.action.ActionLoseMP;
import com.leekwars.generator.fight.action.ActionLoseTP;
import com.leekwars.generator.fight.action.ActionMove;
import com.leekwars.generator.fight.action.ActionNewTurn;
import com.leekwars.generator.fight.action.ActionNovaVitality;
import com.leekwars.generator.fight.action.ActionReduceEffects;
import com.leekwars.generator.fight.action.ActionRemoveEffect;
import com.leekwars.generator.fight.action.ActionRemovePoisons;
import com.leekwars.generator.fight.action.ActionRemoveShackles;
import com.leekwars.generator.fight.action.ActionResurrect;
import com.leekwars.generator.fight.action.ActionSay;
import com.leekwars.generator.fight.action.ActionSetWeapon;
import com.leekwars.generator.fight.action.ActionShowCell;
import com.leekwars.generator.fight.action.ActionStackEffect;
import com.leekwars.generator.fight.action.ActionStartFight;
import com.leekwars.generator.fight.action.ActionUpdateEffect;
import com.leekwars.generator.fight.action.ActionUseChip;
import com.leekwars.generator.fight.action.ActionUseWeapon;
import com.leekwars.generator.fight.action.ActionVitality;
import com.leekwars.generator.fight.action.DamageType;
import com.leekwars.generator.fight.entity.Entity;
import com.leekwars.generator.genetics.ServWriterActionsParameters;

import com.leekwars.generator.fight.julienStats.Stats.LazyStats;
import com.leekwars.generator.fight.julienStats.ComputeDimension.AffectStat;
import com.leekwars.generator.fight.julienStats.ComputeDimension.ComputeEffect;
import com.leekwars.generator.fight.julienStats.ComputeDimension.myEffect;
import com.leekwars.generator.fight.julienStats.SendPython.MyThread;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.ChipsUtil;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.ItemsUtil;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.WeaponsUtil;
import com.leekwars.generator.genetics.enumDistant;
import com.leekwars.generator.genetics.mSocket;


public class testLazy {


	public class TestThreadParam{
		String filename_json ; int num_gen ; int num_fight;

		public TestThreadParam ( String filename_json , int num_gen , int num_fight ) {
			this.filename_json = filename_json ; this.num_gen = num_gen ; this.num_fight = num_fight ;
		}

		public String getFilename_json() {
			return filename_json;
		}


		public int getNum_gen() {
			return num_gen;
		}


		public int getNum_fight() {
			return num_fight;
		}



	}
	public class TestThread extends Thread {
		TestThreadParam params;

		public TestThread (TestThreadParam _params) {
			params = _params;
		}

		public void run() {
			try {
				TimeUnit.SECONDS.sleep(params.getNum_fight());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String jn_str = String.join(String.valueOf(params.getNum_gen()) , String.valueOf(params.getNum_fight()),String.valueOf(0 ));
			System.out.println("RESULT : " + readAdn.getFromFile( params.getFilename_json() , jn_str , "adn"));
		  }

	}
 
    
    //EntityAI entity_ai =  mLeek1.getAI();
    //LazyCaracteristique lazy_statCara = new LazyCaracteristique(entity_ai);

	private Generator generator;
	private Fight mFight;
	private Leek mLeek1;
	private Leek mLeek2;
	private AI ai;
	
	
	String scenario_file_str = new String("test/ai/scenario/scenario-test.json");
    StatCaracteristique provider_statCara  = new StatCaracteristique();
    LazyCaracteristique lazy_statCara = new LazyCaracteristique(provider_statCara);
    LazyBaseCaracteristique lazy_baseCara = new LazyBaseCaracteristique(provider_statCara);
    final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	
	java.util.Map<Integer, Entity> map_entities ;
	Outcome out ;
	EntityAI test_ai_1 , test_ai_2 ;

	Stats states_1,states_2;



	int idWeapon_laser =6;//42;//6; //laser 
    int idWeapon_magnum=5;//45;//5; //cercle complet
    int idWeapon_neutrino=27;//182;//27; // X diagonal
    int idWeapon_lightninger=25;//180;//25; //star
    int idWeapon_grenadeLauncher =7;//73;//7; //cercle troué centre

	int idItemJlaser=115;

    
    int idItem_laser =42;//6; //laser 
    int idItem_magnum=45;//5; //cercle complet
    int idItem_neutrino=182;//27; // X diagonal
    int idItem_lightninger=180;//25; //star
    int idItem_grenadeLauncher =43;//7; //cercle troué centre

	int idItem_laser_2 =42;//6; //laser

    Weapon weapon_laser , weapon_magnum, weapon_neutrino, weapon_lightninger, weapon_grenadeLauncher  ,weapon_jlaser;

 


	HashMap<Integer,int[][]> hash_effect = new HashMap<>();

	boolean b1;
	boolean b2;
	File dir_data;
	File dir_outcome;

	
	Scenario scenario;

	@Before
	public void setUp() throws Exception {
		generator = new Generator();

		/*int [][][] effects =  {
			{{Entity.CHARAC_LIFE},{Effect.TYPE_DAMAGE}},//1
			{ {Entity.CHARAC_WISDOM},{Entity.CHARAC_LIFE}},//2
			{{},{Effect.TYPE_DAMAGE,Entity.CHARAC_ABSOLUTE_SHIELD}},//26 et 27
			{{Entity.CHARAC_ABSOLUTE_SHIELD},{Entity.CHARAC_ABSOLUTE_SHIELD}}//29
		}; 
		
			hash_effect.put(1, effects[0]);
			hash_effect.put(2, effects[1]);
			hash_effect.put(26, effects[2]);
			hash_effect.put(27, effects[2]);
			hash_effect.put(29, effects[3]);*/
		  	weaponPattern.debug_mode =false;



			

		dir_data = new File("../AfterFight/data");

		dir_outcome = new File("../AfterFight/outcome");


		//Miscellaneous.deleteRecur(dir_data);

		//Miscellaneous.deleteRecur(dir_outcome);
		if( !(dir_data.exists()))
			b2= dir_data.mkdir();
		else {
			File newDir = new File(dir_data + "_" + System.currentTimeMillis());
    		dir_data.renameTo(newDir);
			b2 = dir_data.mkdir();
		}
		if( !(dir_outcome.exists()))
			b1 = dir_outcome.mkdir();
		else {
			File newDir = new File(dir_outcome + "_" + System.currentTimeMillis());
			dir_outcome.renameTo(newDir);
			b1 = dir_outcome.mkdir();
		}
		


		/*generator = new Generator();
		mFight = new Fight(generator);
		mLeek1 = new Leek(1, "Test");
		mLeek2 = new Leek(2, "Bob");

		mFight.addEntity(0, mLeek1);
		mFight.addEntity(1, mLeek2);

		mFight.initFight();
		//ai = new DefaultUserAI();

		map_entities = new java.util.HashMap<Integer, Entity>();

		scenario = Scenario.fromFile(new File(scenario_file_str));
        if (scenario == null) {
            System.out.println("Failed to parse scenario!");
            return ;
        }

		
		out = LaunchGeneration.runScenario(scenario_file_str, generator,  map_entities);
		test_ai_1 = ((Entity) map_entities.values().toArray()[0]).getAI();
		test_ai_2 = ((Entity) map_entities.values().toArray()[1]).getAI();

		states_1 = new Stats(test_ai_1);
		states_2 = new Stats(test_ai_2);


        weapon_laser = Weapons.getWeapon(idItem_laser );
        weapon_magnum = Weapons.getWeapon(idItem_magnum );
        weapon_neutrino = Weapons.getWeapon(idItem_neutrino );
		weapon_jlaser = Weapons.getWeapon(idItemJlaser);*/


	
	}
 
    @Test 
    public void test_actm_test () throws Exception{

		ArrayList<String> keys_adn = new ArrayList<String>();

		for (int num_gen =1 ; num_gen< 2 ; num_gen++){
			for (int num_fight = 1 ; num_fight < 10; num_fight++){
				keys_adn.add(String.valueOf(num_gen)+"_"+String.valueOf(num_fight));
			}
		}


		String adn_filename = System.getProperty("user.dir")+ File.separator + "adn_json.json";
		TestThread thread_1 = new TestThread(new TestThreadParam(adn_filename,1,1));


		thread_1.start();
		HashMap<String, TestThread> threads =  new HashMap<String,TestThread>();

		int id_gen 		= 0;
		int id_fight 	= 0;

		String [] splits = null;

		
		for (String key : keys_adn){
			splits = key.split("_");
			id_gen = Integer.parseInt(splits[0]);
			id_fight = Integer.parseInt(splits[1]);
			
			thread_1 = new TestThread(new TestThreadParam(adn_filename,id_gen,id_fight));
        	threads.put(key ,  thread_1);
    		threads.get(key).start();

        }

		for (TestThread myThread : threads.values()) {
			try {
				myThread.join(); 
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}




	}

	@Test 
	public void test_socket_pyth_java() throws Exception{
		mSocket leSocket = new mSocket("localhost" , 4242);
		System.out.println(leSocket.init()) ;
		String message = new  String();
		message =leSocket.mrecv( );
		System.out.println(message);
		leSocket.msockclose();

	}

	@Test 
	public void test_final_socket () throws Exception{
		LaunchGeneration lg  = new LaunchGeneration("localhost" , 59345);
		lg.launchMain();
		

		

	}

	@Test 
	public void test_data() throws Exception{
		Data.mcheckData();
	}

	@Test 
	public void test_attackToJson() throws Exception{

		 /*JSONObject json_obj = 	new  JSONObject();
		 json_obj.putAll(ActionAddEffect.getProvider().getDfJson());
		 json_obj.putAll(ActionAIError.getProvider().getDfJson());
		 json_obj.putAll(ActionChestOpened.getProvider().getDfJson());
		 json_obj.putAll(ActionDamage.getProvider().getDfJson());
		 json_obj.putAll(ActionEndTurn.getProvider().getDfJson());
		 json_obj.putAll(ActionEntityDie.getProvider().getDfJson());
		 json_obj.putAll(ActionEntityTurn.getProvider().getDfJson());
		 json_obj.putAll(ActionHeal.getProvider().getDfJson());
		 json_obj.putAll(ActionInvocation.getProvider().getDfJson());
		 json_obj.putAll(ActionKill.getProvider().getDfJson());
		 json_obj.putAll(ActionLama.getProvider().getDfJson());
		 json_obj.putAll(ActionLoseMP.getProvider().getDfJson());
		 json_obj.putAll(ActionLoseTP.getProvider().getDfJson());
		 json_obj.putAll(ActionMove.getProvider().getDfJson());
		 json_obj.putAll(ActionNewTurn.getProvider().getDfJson());
		 json_obj.putAll(ActionNovaVitality.getProvider().getDfJson());
		 json_obj.putAll(ActionReduceEffects.getProvider().getDfJson());
		 json_obj.putAll(ActionRemoveEffect.getProvider().getDfJson());
		 json_obj.putAll(ActionRemovePoisons.getProvider().getDfJson());
		 json_obj.putAll(ActionRemoveShackles.getProvider().getDfJson());
		 json_obj.putAll(ActionResurrect.getProvider().getDfJson());
		 json_obj.putAll(ActionSay.getProvider().getDfJson());
		 json_obj.putAll(ActionSetWeapon.getProvider().getDfJson());
		 json_obj.putAll(ActionShowCell.getProvider().getDfJson());
		 json_obj.putAll(ActionStackEffect.getProvider().getDfJson());
		 json_obj.putAll(ActionStartFight.getProvider().getDfJson());
		 json_obj.putAll(ActionUpdateEffect.getProvider().getDfJson());
		 json_obj.putAll(ActionUseChip.getProvider().getDfJson());
		 json_obj.putAll(ActionUseWeapon.getProvider().getDfJson());
		 json_obj.putAll(ActionVitality.getProvider().getDfJson());*/
		 //System.out.println(json_obj.toString());

		
	}
	@Test 
	public void test_parse () throws Exception{

		 final  String TOKEN_END = "END";
		 final  String TOKEN_SEP_GENERATION = ";";
		 final  String TOKEN_SEP_SCENARIO = ":";
		final  String TOKEN_SEP_FIGHT = ",";

		
		String message = ""  ;
		ArrayList<String> keys_adn = DefaultConfigMain.instance.keys_adn;
		String scenario_str = DefaultConfigMain.instance.scenario_file_str_base; 
		//String scenario_str = new String(scenario_file_str_base) ;
		//Scenario scenario = new Scenario (scenario_base) ; 
		
	
		String reste_message = "";
		String[]splits = null;
	
		message +="" ;//recvall() ;
	
		String[] lst_str = {"1_1,1_2,1_3,1_4,1_5,1_6,1_7,1_8,1_9,1_10", "2_1,2_2,2_3,2_4,2_5,2_6,2_7,2_8,2_9,2_10", "scenario3.json:3_1,3_2,3_3,3_4,3_5,3_6,3_7,3_8,3_9,3_10"};
		String lst_str_final = String.join(TOKEN_SEP_GENERATION ,lst_str)+ TOKEN_END;
	
		message = lst_str_final  ;
		while (! LaunchGeneration.isEnd(message) ) {
	  
			
			//message += recvall() ;
	
			
			
			if(message != ""){
				//System.out.println(message);
				splits = message.split(TOKEN_SEP_GENERATION,2);
				if(splits.length == 1){				
					String tmp = message.substring(message.length()-TOKEN_END.length());
					if(tmp.equals(TOKEN_END) ){
						reste_message = TOKEN_END;
						message = message.substring(0,message.length()-TOKEN_END.length());
					}else{
					System.out.println("message non finis " +message);
					continue ; 
					}
				}else{
					reste_message = splits[1];
					message = splits[0];
				}
				
				
				System.out.println("MESSAGE|" +message +"|RESTE " + reste_message);
	
	
				//traitement message
				splits = message.split(TOKEN_SEP_SCENARIO,2);
				if(splits.length > 1 )scenario_str = splits[0];
				else scenario_str = DefaultConfigMain.instance.scenario_file_str_base;
				//scenario =  Miscellaneous.scenarioFromFile( scenario_str);
	
				splits = splits[(splits.length > 1 ? 1 :0)].split( TOKEN_SEP_FIGHT); ; 
				keys_adn = new ArrayList( Arrays.asList(splits));
				System.out.println("ICI MESS " +message);
				message = reste_message;
				
				System.out.println("ICI SCENAR " +scenario_str);
			}
			else{
				System.out.println("message vide");
				break;
			}
			
	
	
	
			
			
		}
	
			

	
	}
 
    @Test 
    public void test_actm () throws Exception{

		//getAdnFromFile : filename_json = D:\Master\S2\TER\GitLabTER\leek-wars-generator-Lks-v3\adn_json.json num_gen = 147 num_fight = 2

		System.out.println(b1);
		System.out.println(b2);
		if(b1 && b2){
        int port = 51922;
        String host = "localhost";


		String path_gen_0 = new String("../AfterFight/data");
		String path_gen = new String();
		File dir_ = null;


        LaunchGeneration l_gen = new LaunchGeneration(  host ,port );

        l_gen.init_all();

		ArrayList<String> keys_adn = new ArrayList<String>();

		for (int num_gen =1 ; num_gen< 2 ; num_gen++){
			for (int num_fight = 1 ; num_fight < 10; num_fight++){
				keys_adn.add(String.valueOf(num_gen)+"_"+String.valueOf(num_fight));
			}
		}


		int id_gen = -1;
        int id_fight = -1 ;
		String[] splits = null;


		LaunchCombatParam params = null;
		HashMap<String, LaunchCombatThread> threads =  new HashMap<String, LaunchCombatThread>();

		//LaunchCombatThread.setScenario( scenario);
		
		for (String key : keys_adn){
			splits = key.split("_");
			id_gen = Integer.parseInt(splits[0]);
			id_fight = Integer.parseInt(splits[1]);
			path_gen= path_gen_0+File.separator  +splits[0];
			dir_ = new File(path_gen);

			dir_.mkdir();
			dir_ = new File(path_gen+ File.separator + splits[1]);
			dir_.mkdir();
			
			params  = new LaunchCombatParam(scenario, id_gen, id_fight, System.getProperty("user.dir")+ File.separator + "adn_json.json");
        	threads.put(key ,  new LaunchCombatThread( params, l_gen));
    		threads.get(key).start();

        }

		

	   /*int count =  0 ;
		while( count < threads.size()){
			for (String key : keys_adn) {
				LaunchCombatThread  myThread = threads.get(key);
				if(myThread != null ){
					
					try {
						myThread.join(); 
						threads.replace(key,null);
						count++;
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
			}
	}*/

		for (LaunchCombatThread myThread : threads.values()) {
			try {
				myThread.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("END \n\n\n\n\n ATTENDS PYTHON ");

	
	
	


        l_gen.close_all() ;
	}
	

		//if(b1)Miscellaneous.deleteRecur(dir_data);

		//if(b2)Miscellaneous.deleteRecur(dir_outcome);
}

@Test 
public void test_ee () throws Exception{
	Miscellaneous.deleteRecur(dir_data);

	Miscellaneous.deleteRecur(dir_outcome);
}





}
