package com.leekwars.generator.outcome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.leekwars.generator.fight.action.Actions;
import com.leekwars.generator.fight.statistics.StatisticsManager;
import com.leekwars.generator.leek.FarmerLog;

public class Outcome {
    /**
     * Fight: public data: entities, map, actions, flags, duration, ai times
     */
    public Actions fight;
    /**
     * Logs: debugs, marks, pauses
     */
    public Map<Integer, FarmerLog> logs = new TreeMap<Integer, FarmerLog>();
    /**
     * Winner team id
     */
	public int winner;

	public Map<Integer, String> useGene = new HashMap<Integer, String>();
	
	/**
	 * Duration
	 */
	public int duration;
    /**
     * Fight statistics
     */
	public StatisticsManager statistics;
	/**
	 * Exception
	 */
	public Exception exception = null;

	public long analyzeTime = 0;

	public long compilationTime = 0;

	public long executionTime = 0;

	public HashMap<Integer , ArrayList<double[][]>> history  = new HashMap<Integer , ArrayList<double[][]>>(); 

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		JSONObject logsJSON = new JSONObject();
		for (Integer farmer : logs.keySet()) {
			logsJSON.put(String.valueOf(farmer), logs.get(farmer).toJSON());
		}

	
		JSONObject historyJson = new JSONObject();
		String _str = "";
	
		for (Integer ent_id : history.keySet()) {
			_str = JSON.toJSONString(history.get(ent_id), SerializerFeature.DisableCircularReferenceDetect);
			JSONArray _arr = JSON.parseArray(_str);
			historyJson.put(String.valueOf(ent_id),_arr);
		}


		JSONObject  useGeneJson  = new JSONObject();
		for (Integer ent_id : useGene.keySet()) {
			_str = useGene.get(ent_id);
			JSONArray _arr = JSON.parseArray(_str);
			useGeneJson.put(String.valueOf(ent_id),_arr);
		}


		json.put("used_genes", useGeneJson);
		json.put("history",  historyJson);
		json.put("fight", fight.toJSON());
		json.put("logs", logsJSON);
		json.put("winner", winner);
		json.put("duration", duration);
		json.put("analyze_time", analyzeTime);
		json.put("compilation_time", compilationTime);
		json.put("execution_time", executionTime);
		return json;
	}

	public String toString() {
		return toJson().toJSONString();
	}
}