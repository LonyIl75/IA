package com.leekwars;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.leekwars.generator.Data;
import com.leekwars.generator.Generator;
import com.leekwars.generator.Log;
import com.leekwars.generator.genetics.LaunchGeneration;
import com.leekwars.generator.genetics.LaunchGenerationParam;
import com.leekwars.generator.genetics.LaunchGenerationThread;
import com.leekwars.generator.outcome.Outcome;
import com.leekwars.generator.scenario.Scenario;
import com.leekwars.generator.test.LocalDbFileSystem;
import com.leekwars.generator.test.LocalDbRegisterManager;
import com.leekwars.generator.test.LocalTrophyManager;

import leekscript.compiler.LeekScript;
import leekscript.compiler.IACompiler.AnalyzeResult;
import leekscript.compiler.resolver.NativeFileSystem;

public class Main {

	private static final String TAG = Main.class.getSimpleName();

	public static void main(String[] args) {
		String file = null;
		boolean nocache = false;
		boolean db_resolver = false;
		boolean verbose = false;
		boolean analyze = false;
		int farmer = 0;
		int folder = 0;
		String path_adn = null ; 

		for (String arg : args) {
			if (arg.startsWith("--")) {
				switch (arg.substring(2)) {
					case "nocache": nocache = true; break;
					case "dbresolver": db_resolver = true; break;
					case "verbose": verbose = true; break;
					case "analyze": analyze = true; break;
				}
				if (arg.startsWith("--farmer=")) {
					farmer = Integer.parseInt(arg.substring("--farmer=".length()));
				} else if (arg.startsWith("--folder=")) {
					folder = Integer.parseInt(arg.substring("--folder=".length()));
				}
				else if (arg.startsWith("--adn=")) {
					path_adn = arg;
				}
			} else {
				file = arg;
			}
		}


		String root_path = System.getProperty("user.dir");

		file =(file == null ? new String("test/ai/scenario/scenario-test.json") : file);
		Scenario scenario;


		path_adn = (path_adn  == null  ?  String.join( File.separator , root_path  , "adn_json.json" ) : path_adn ); 

		File dir_data = new File("../AfterFight/data");

		File dir_outcome = new File("../AfterFight/outcome");

		Data.mcheckData();

		boolean b1 = (dir_outcome.isDirectory() ?true : dir_outcome.mkdir() );
		boolean  b2= (dir_data.isDirectory() ? true : dir_data.mkdir());

		scenario = Scenario.fromFile(new File(file));
		if (scenario == null) {
			System.out.println("Failed to parse scenario!");
			return ;
		}


		System.out.println("ROOT : "  + root_path);



		if(b1 && b2){
			int port = 51747;
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


			LaunchGenerationParam params = null;
			HashMap<String, LaunchGenerationThread> threads =  new HashMap<String, LaunchGenerationThread>();

			//LaunchGenerationThread.setScenario( scenario);
			
			for (String key : keys_adn){
				splits = key.split("_");
				id_gen = Integer.parseInt(splits[0]);
				id_fight = Integer.parseInt(splits[1]);
				path_gen= path_gen_0+File.separator  +splits[0];
				dir_ = new File(path_gen);

				dir_.mkdir();
				dir_ = new File(path_gen+ File.separator + splits[1]);
				dir_.mkdir();
				
				params  = new LaunchGenerationParam(scenario, id_gen, id_fight, path_adn );
				threads.put(key ,  new LaunchGenerationThread( params, l_gen));
				threads.get(key).start();

			}

			



			for (LaunchGenerationThread myThread : threads.values()) {
				try {
					myThread.join();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		
		
		


			l_gen.close_all() ;
	}
}

}
	


