package com.leekwars.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.fight.action.Action;
import com.leekwars.generator.fight.action.Actions;
import com.leekwars.generator.fight.julienStats.Stats.StatCaracteristique;
import com.leekwars.generator.fight.julienStats.Stats.StatDot;
import com.leekwars.generator.fight.julienStats.Stats.StatEffect;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.ChipsUtil;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.WeaponsUtil;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

public class Data {

	private static final String TAG = Data.class.getSimpleName();

	public static List<LocalDate> fullmoon = new ArrayList<>();

	static String path = "./data";



	public static void checkData(String api) {
		Log.i(TAG, "Check api: " + api);
		// File weaponsFile = new File("data/weapons.json");
		Log.i(TAG, "Load weapons from API...");
		JSONObject weapons = JSON.parseObject(get(api + "weapon/get-all", "")).getJSONObject("weapons");
		Util.writeFile(weapons.toJSONString(), "data/weapons.json");

		// File chipsFile = new File("data/chips.json");
		Log.i(TAG, "Load chips from API...");
		JSONObject chips = JSON.parseObject(get(api + "chip/get-all", "")).getJSONObject("chips");
		Util.writeFile(chips.toJSONString(), "data/chips.json");

		// File summonsFile = new File("data/summons.json");
		Log.i(TAG, "Load summons from API...");
		JSONObject summons = JSON.parseObject(get(api + "summon/get-templates", "")).getJSONObject("summon_templates");
		Util.writeFile(summons.toJSONString(), "data/summons.json");

		// File fullmoonFile = new File("data/fullmoon.json");
		Log.i(TAG, "Load fullmoon from API...");
		JSONArray f = JSON.parseArray(get(api + "fight/fullmoon", ""));
		for (var d : f) {
			var dateUTC = ZonedDateTime.of(LocalDateTime.parse((String) d), ZoneOffset.UTC);
			var dateLocal = dateUTC.withZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
			fullmoon.add(dateLocal);
		}
		// System.out.println("full moon = " + fullmoon);
		Util.writeFile(f.toJSONString(), "data/fullmoon.json");
	}

	public static void mcheckData(){
		WeaponsUtil.writeEffectWeapons();
		WeaponsUtil.writePatternWeapon();
		ChipsUtil.writeEffectChips();

		StatCaracteristique.provider_statCara.writeDefaultEnumJson();
		StatDot.provider_statDot.writeDefaultEnumJson();
		StatEffect.provider_statEffect .writeDefaultEnumJson();

		Data.writeStaticFieldsActions();
		Data.writeStaticFieldsAttack();

		Data.writeActions();

		

	}

	public static String actionsToJson(){
		return JSON.toJSONString(Action.actionsToJson(),SerializerFeature.DisableCircularReferenceDetect);

	}



	public static String attackStaticFieldsToJson(){

		
		Field[] fields = Attack.class.getDeclaredFields();

		String res = "{ " ;
		

		HashMap <Integer,String> map_static_fields_attack = new HashMap <Integer,String>();

		String atm_name = null;

		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) ) {
										
				
				try {
					
					atm_name = field.getName();
					if(  atm_name != null && atm_name.equals(atm_name.toUpperCase())){
					Integer atm_value = (Integer)field.get(null);
					if( atm_value != null){
					map_static_fields_attack.put(atm_value, atm_name);
					}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Integer atm_value = null;
		for (Entry<Integer, String> entry : map_static_fields_attack.entrySet()) {
			atm_name = entry.getValue();
			atm_value = entry.getKey();

			if(atm_name != null){
				res += Miscellaneous.continueAttributJson(atm_name , atm_value ) ;
            }
			
		}
        
		res = res.substring(0, res.length() - 1);
		res += Miscellaneous.finObjectJson();
		//Util.writeFile(res, "data/actions.json");

		return res ;




	}
	//Action.actionsToJson()
	
	public static String actionStaticFieldsToJson(int interval_size , String[] type_actions ){
		Field[] fields = Action.class.getDeclaredFields();


		int prec = -1;
		int count = 1;
		Integer atm_value = 0;
		String res = "{ " ;
		res+= Miscellaneous.continueAttributJson("interval_size" , interval_size-1) ;

		res += Miscellaneous.debutObjectJson(type_actions[0] );
		HashMap <Integer,String> map_static_fields_actions = new HashMap <Integer,String>();

		int[] limit_tab = new int[11];//new int[type_actions.length];
		Arrays.fill(limit_tab,0);

		String atm_name = null;

		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) ) {
			
				
				try {
					
					atm_name = field.getName();
					if(  atm_name != null && atm_name.equals(atm_name.toUpperCase())){
						
					atm_value = (Integer)field.get(null);
					if( atm_value != null){
					int idx = (int)(atm_value / interval_size);//atm_value==0 ? 0 :(int)Math.log10(atm_value );
					if(atm_value > limit_tab[idx])limit_tab[idx] = atm_value;
					if(atm_value !=null)map_static_fields_actions.put( atm_value,field.getName());
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		int atm_limit = 0;
		for( int i = 0 ; i < limit_tab.length ; i++){
			atm_limit = limit_tab[i];
			if(atm_limit == 0)continue;
			prec= interval_size*i ;
        for ( int k = prec ; k <= atm_limit ; k++){ 
			atm_name = map_static_fields_actions.get(k);
			if(atm_name != null){
				res += Miscellaneous.continueAttributJson(atm_name , k ) ;
            }
			
		}
		res = res.substring(0, res.length() - 1);
		res += Miscellaneous.continuefinObjectJson();
		if(count < type_actions.length){
		res += Miscellaneous.debutObjectJson(type_actions[count] );
		count++;
		}

	}
        
		res = res.substring(0, res.length() - 1);
		res += Miscellaneous.finObjectJson();
		//Util.writeFile(res, "data/actions.json");

		return res ;
	}


	public static void writeStaticFieldsActions(){
		
		String content =  Data.actionStaticFieldsToJson(Action.interval_size,Action.headers );

		String path_ = Data.path +File.separator +Miscellaneous.getDfPrefixFilename() +Miscellaneous.getJoinFilename()+ Action.class.getSimpleName() +".json";
		try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
			out.write(content);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	
	}

	public static void writeStaticFieldsAttack(){
		String content =   Data.attackStaticFieldsToJson();
		String path_ = Data.path +File.separator + Miscellaneous.getDfPrefixFilename() +Miscellaneous.getJoinFilename()+ Attack.class.getSimpleName() +".json";
		System.out.println("path_ = " + path_);
		try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
			out.write(content);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	public static void writeActions(){
		String content =   Data.actionsToJson();
		String path_ = Data.path +File.separator + Miscellaneous.getValuePrefixFilename() + Miscellaneous.getJoinFilename()+ Action.class.getSimpleName() +".json";
		System.out.println("path_ = " + path_);
		try (PrintWriter out = new PrintWriter(new FileWriter(path_))) {
			out.write(content);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

		

    private static String get(String url, String urlParameters) {
		Log.i(TAG, "get " + url);

		var client = HttpClient.newHttpClient();

		var request = HttpRequest.newBuilder(URI.create(url))
			.header("accept", "application/json")
			.build();

		try {
			var response = client.send(request, BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace(System.out);
		}
		return null;
    }

	public static boolean isFullMoon() {
		var today = LocalDate.now();
		// System.out.println("f = " + fullmoon);
		for (var d : fullmoon) {
			// System.out.println("d = " + d);
			if (d.equals(today)) return true;
		}
		return false;
	}
}