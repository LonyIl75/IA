
package com.leekwars.generator.fight.julienStats.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.scenario.Scenario;

public class Miscellaneous {
    
//MATH :



    public static double mean2( double a , double b){
        return (a+b)/2;
    }

    
//ARRAYLIST :

public static ArrayList<Integer> reverseArrayList(ArrayList<Integer> alist)
{
    // Arraylist for storing reversed elements
    ArrayList<Integer> revArrayList = new ArrayList<Integer>();
    for (int i = alist.size() - 1; i >= 0; i--) {

        // Append the elements in reverse order
        revArrayList.add(alist.get(i));
    }

    // Return the reversed arraylist
    return revArrayList;
}


//ARRAY :

public static int[] cpyArr(int[] dst_arr1 , int[] src_arr2){
    if(isNotSet(dst_arr1)) dst_arr1 = new int[src_arr2.length];
    for (int i = 0 ; i<dst_arr1.length ; i++){
        dst_arr1[i]=src_arr2[i];
    }
    return  dst_arr1;
}
public static float[] cpyArr(float[]  dst_arr1 , float[] src_arr2){
    if(isNotSet(dst_arr1)) dst_arr1 = new float[src_arr2.length];
    for (int i = 0 ; i<dst_arr1.length ; i++){
        dst_arr1[i]=src_arr2[i];
    }
    return  dst_arr1;
}
public static double[] cpyArr(double[] dst_arr1 , double[] src_arr2){
    if(isNotSet(dst_arr1)) dst_arr1 = new double[src_arr2.length];
    for (int i = 0 ; i<dst_arr1.length ; i++){
        dst_arr1[i]=src_arr2[i];
    }
    return  dst_arr1;
}


public static boolean isEmptyStatArr(double[]stat_tab ){
    return !isNotSet(stat_tab)  && isEmptyArrDbl(stat_tab);
}


public static double[] emptyStatArr(double[] stat_tab , int taille){
    if( isEmptyStatArr(stat_tab) ){
        return stat_tab;
    }
    else{
        return zerosArrDbl(taille);

    }
}
public static double[] seriesArrDbl(int taille){
    double[] arr = new double[taille];
    for (int i = 0 ; i<taille ; i++){
        arr[i]=(double)i;
    }
    return arr;
}

public static int[] zerosArrInt(int taille ){
   
    int[] arr = new int[taille];
    for (int i = 0 ; i<taille ; i++){
        arr[i]=0;
    }
    return arr;
}
public static float[] zerosArrFlt(int taille){

    float[] arr = new float[taille];
    int[] zarrInt =  zerosArrInt(taille);
    for (int i = 0 ; i<taille ; i++){
        arr[i]=(float)zarrInt[i];
    }
    return arr;
    
}
public static double[] zerosArrDbl(int taille){
 
    double[] arr = new double[taille];
    int[] zarrInt =  zerosArrInt(taille);
    for (int i = 0 ; i<taille ; i++){
        arr[i]=(double)zarrInt[i];
    }
    return arr;
}


public static boolean isNotSet(float [] arr){
    return arr == null || arr.length == 0;
}
public static boolean isNotSet(int [] arr){
    return arr == null || arr.length == 0;
}
public static boolean isNotSet(double [] arr){
    return arr == null || arr.length == 0;
}

public static boolean isEmptyArrFlt( float [] arr){
    if(isNotSet(arr)) return true;
    return Arrays.equals(arr, zerosArrFlt(arr.length));

}

public static boolean isEmptyArrInt( int [] arr){
    if(isNotSet(arr)) return true;
    return Arrays.equals(arr, zerosArrInt(arr.length));

}

public static boolean isEmptyArrDbl( double [] arr){
    if(isNotSet(arr))return true;
    return Arrays.equals(arr, zerosArrDbl(arr.length));

}


//FILE 


public static boolean deleteRecur(File pFile) {
    boolean bResult = false;

    if(pFile.exists()) {
        if(pFile.isDirectory()) {
            String[] strFiles = pFile.list();

                for(String strFilename: strFiles) {
                    File fileToDelete = new File(pFile, strFilename);

                    deleteRecur(fileToDelete);
                }
                pFile.delete();
            
        } else {
            pFile.delete();
        }
    }

    return bResult;
}

public static String  getDfPrefixFilename(){
    return "df";
}
public static String getJoinFilename(){
    return "_";
}

public static String valuePrefix(){
    return "value";
}
public static String getValuePrefixFilename(){
    return Miscellaneous.getDfPrefixFilename()+Miscellaneous.getJoinFilename() +Miscellaneous.valuePrefix()  ;
}



public static String attributJson(String name , Object value ){
    return new String(" \"" + name + "\"" + " :" + value.toString() ) ;
}
public static String continueAttributJson(String name , Object value ){
    return attributJson( name , value ) + "," ;
}

public static String debutObjectJson(String name ){
    return new String(" \"" + name + "\"" + " :{" ) ;
}

public static String finObjectJson(){
    return new String(" }" ) ;
}

public static String continuefinObjectJson(){
    return finObjectJson() + "," ;
}

public static String debutArrayJson(String name ){
    return new String(" \"" + name + "\"" + " :[" ) ;
}

public static Scenario scenarioFromFile(String scenario_file_str ){
    return scenarioFromFile(scenario_file_str , null );
}
public static Scenario scenarioFromFile(String scenario_file_str , HashMap<String , JSONObject > hash){
    Scenario scenario = null ;
    if (hash == null) {
        scenario = Scenario.fromFile(new File(scenario_file_str));
    }else{
        scenario = Scenario.fromFile(new File(scenario_file_str) , hash );
    }
    if (scenario== null) {
        System.out.println("Failed to parse scenario!");
        return null;
    }
    return scenario;
}
public static ArrayList<String > lstFill ( String content , int nb){
    ArrayList<String > lst = new ArrayList<String >();
    for (int i = 0 ; i< nb ; i++){
        lst.add(content);
    }
    return lst;
    
}
public static ArrayList<String > ascNb ( int max ){
    return ascNb(0, max);

    
}

public static ArrayList<String > ascNb ( int min , int max ){
    ArrayList<String > lst = new ArrayList<String >();
    for (int i = min  ; i<= max ; i++){
        lst.add(String.valueOf(i));
    }
    return lst;
    
}
public static ArrayList<String> mergeLst ( String merge_sep , ArrayList<String> lst1 , ArrayList<String> lst2 ){
    ArrayList<String> keys_adn = null;

    if (lst1.size() == lst2.size()){

        keys_adn = new ArrayList<String>();

    for (int i = 0 ; i<lst1.size() ; i++){
            keys_adn.add(lst1.get(i) + merge_sep + lst2.get(i));
        }
    }

    return keys_adn;
}


public static boolean createIfNotExist(String path){
    
    File file = new File(path);
    if (!file.exists()) {
        if (file.mkdir()) {
            //System.out.println("Directory is created!");
            return true;
        } else {
            //System.out.println("Failed to create directory!");
            return false;
        }
    }
    return true;
}




}