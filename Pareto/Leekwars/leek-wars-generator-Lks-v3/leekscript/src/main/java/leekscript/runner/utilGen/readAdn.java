package leekscript.runner.utilGen;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import leekscript.runner.GenConfig;

public class readAdn {

    static String rep_temp = "tmps/tmp";
    public static Object getFromFile( String filename_json , String key , String key_elem , String attribut_key ){

 
                Object res = null;
        String read;
        try {
           
            int idx_path = filename_json.lastIndexOf(File.separator) ;
            String base_path = filename_json.substring(0, idx_path);
            String filename = filename_json.substring(idx_path+1);
            int idx_ext = filename.lastIndexOf('.') ; 
            String base_filename  = filename.substring(0, idx_ext); 
            String ext_filename  = filename.substring(idx_ext+1);
            String[]splits = key.split("_");

            Path path = Paths.get(base_path+File.separator+rep_temp +File.separator +splits[0]+ File.separator+base_filename+"_"+splits[1]+"."+ext_filename);
            read = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            
            JSONObject objjo = JSON.parseObject(read);
            JSONObject firstName = (JSONObject)((JSONObject)objjo.get(key)).get(key_elem);
            if(firstName != null){
                res = firstName.get(attribut_key);
                //System.out.println("FIRSTNAME "  + firstName.toJSONString());
                //System.out.println(" \n\n AdnFromFile ADN " +res +"\n\n\n");
                return res ;
            }

        } catch (Exception e) {
            System.out.println(" \n\n  EXCEPTION  AdnFromFile \n\n ");
            e.printStackTrace();
        }
        System.out.println(" \n\n AdnFromFile ADN NULL \n\n\n");
        return res ;
        }
}
