package com.leekwars.generator.fight.julienStats.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutcomeParse {

    static String  regex_first_fightId = "(?<=\"leeks\":\\[)(?:|(?:\\{(?:[^\\}]*)\\},))(?:\\{(?:[^\\}]*)\"id\":(\\d+)(?:[^\\}]*\\}),?)"; //dans le tableau leeks
    static String  regex_first_leekId ="(?<=\"dead\":\\{)\"(\\d+)\"(?:[^,]+,)"; // dans dead 

    public static  List<Integer> parseOutcome (String str_outcome , String regex ){

        String bn = new String(str_outcome);
        List<Integer> arr_ids = new ArrayList<Integer>();
        Pattern p = Pattern.compile( regex );
        Matcher m = p.matcher(bn);
        int _start , _end;
        while(m.find()){
            _start = m.start();
            _end = m.end();
            bn=bn.substring(0,m.start())+bn.substring(m.end(),bn.length());
            arr_ids.add(Integer.parseInt(m.group(1)));
            m = p.matcher(bn);
    
        }
        return arr_ids;
    }

    public static List<Integer> getFigthIds (String str_outcome ){
        return parseOutcome(str_outcome, regex_first_fightId);
    }

    public static List<Integer> getLeekIds (String str_outcome ){
        return parseOutcome(str_outcome, regex_first_leekId);
    }

    public static List<String> converIntIdToStringId (List<Integer> arr_ids){
        List<String> arr_ids_3 = new ArrayList<String>();
        for (Integer id : arr_ids) {
            arr_ids_3.add(String.valueOf(id));
        }
        return arr_ids_3;
    }

    
}
