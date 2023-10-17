package com.leekwars.generator.fight.julienStats.old;

import java.util.Arrays;
import java.util.List;
import com.leekwars.generator.fight.julienStats.utilString;
public class statString {

    public String storageName ;
    public String fullName ;

    private final String delimiterFunctionName ;
    private final String delimiterStorageName ;

    public final static int positionOfEntityType = 0 ;
    public final static int positionOfDimensionType = -1 ;
    public final static int positionOfFunctionName = -2 ;
    public final static int getminSizeOfFunction() {return  positionOfEntityType - positionOfFunctionName +1 ; }


    enum type_of_entity {
        E, //enemies
        A, //Allies
        ME // my leek
      };
      enum type_of_dimension {
        D, //parametered by damage
        M ,//parametered by MP
        C ,//parametered by cell
        H ,//parametred by sustain_stat leek 
        Q  // parametred by quantity of ...
      };


         
    private boolean debug_mode = false;
    public void set_debug_mode(boolean b) {
        debug_mode = b;
    }

    public statString(){
        delimiterFunctionName = "_";
        delimiterStorageName  = ":";
    }

    public statString(String _delimiterFunctionName, String _delimiterStorageName){
        delimiterFunctionName = _delimiterFunctionName;
        delimiterStorageName  = _delimiterStorageName;
    }
    

    private statString(String _storageName , String _delimiterFunctionName, String _delimiterStorageName){
        this(_delimiterFunctionName,_delimiterStorageName);
        this.storageName = _storageName;
        fullName = this.getFullName();
    }

    public statString(List<String> storageName , String _delimiterFunctionName, String _delimiterStorageName){
        this(_delimiterFunctionName,_delimiterStorageName);
        this.storageName = getStorageName(storageName, _delimiterStorageName);
        fullName = this.getFullName();
    }

    public statString(List<String> strs){
        this();
        this.storageName = getStorageName(strs,delimiterStorageName);
        fullName = this.getFullName();
    }

    public statString(String _storageName){
        this();
        if(isCorrectStorageName (_storageName ,delimiterStorageName)){
            this.storageName =_storageName;
            fullName = this.getFullName();  
        }else{
            System.out.println("Error : storageName is not correct");
        }
    }

    public String getStorageName() {
        return storageName;
    }

    public static boolean isCorrectStorageName (String storage_name ,String _delimiterStorageName){//, Runnable function ) {
        List<String>  list_function = Arrays.asList(storage_name.split(_delimiterStorageName));
        return isCorrectList(list_function);
    }

    public static boolean isCorrectFunctionName (String storage_name ,String _delimiterFunctionName){//, Runnable function ) {
        List<String>  list_function = Arrays.asList(storage_name.split(_delimiterFunctionName));
        return isCorrectList(list_function);
    }
    

    public static boolean isCorrectList (List<String>  list_function){//, Runnable function ) {
        if (isToShortList(list_function)) {
            System.out.println("Error : function is too short");
            return false;
        }
        if (!isToShortList(list_function)) {
            if(!isValidEntityType(list_function)){
                System.out.println("Error : type of entity is not valid");
                return false;
            }
            List<String>  options =  getOptions(list_function);

            String function_name = getFunctionName(list_function) ;
            if(utilString._isEmptyOrBlank(function_name)){
                System.out.println("Error : Function name is empty");
                return false;
            }

            if(!isValidDimensionType(list_function)){
                System.out.println("Error : dimension type is not valid");
                return false;
            }
            
        }
        return true;
    }


    public  String getStringAtPosDelimiterStorage( int pos){
        return utilString.getStringAtPosDelimiter(this.storageName, pos , delimiterStorageName );

     }

     public  String getStringAtPosDelimiterFunction( int pos){
        return utilString.getStringAtPosDelimiter(this.fullName, pos , delimiterFunctionName );

     }



 

    public static int getPosEntityType() {
        return positionOfEntityType;
    }

     //PAS TESTER 

    public Integer getIndexString( int index){
        return utilString.getIndexString(this.storageName, index );

     }



    public Integer getIndexOfFunctionName( ){
        return getIndexString(  positionOfFunctionName ) ;
    }

    public  Integer getIndexOfEntityType(){
        return getIndexString( positionOfEntityType ) ;
    }

    public  Integer getIndexOfDimensionType( ){
        return getIndexString( positionOfDimensionType ) ;
    }

    public  Integer getIndexOfOptions() {
        String test_str = getStringAtPosDelimiterStorage(getminSizeOfFunction());
        if(test_str != null)
            return positionOfEntityType + 1 ;
        else
            return  null ; //pas sur
        
     }


    public static Integer getIndexOfFunctionName(List<String> strs ){
        return utilString.getIndexString( strs , positionOfFunctionName ) ;
    }

    public static Integer getIndexOfEntityType(List<String> strs ){
        return utilString.getIndexString( strs , positionOfEntityType ) ;
    }

    public static Integer getIndexOfDimensionType(List<String> strs ){
        return utilString.getIndexString( strs , positionOfDimensionType ) ;
    }

    public static boolean isToShortList(List<String> list) {
        return list.size()<getminSizeOfFunction();
    }

    public static boolean containOptionList(List<String> list) {
        return list.size()> getminSizeOfFunction();
    }

    public static Integer getIndexOfOptions(List<String> list) {
        if(containOptionList(list))
            return positionOfEntityType + 1  ;
        else
            return  null ; //pas sur 
        
     }



     //PAS TESTER 
    public  String getDimensionType() {
       
       return  this.getStringAtPosDelimiterStorage( getIndexOfDimensionType() );
    }
  
    public   String getFunctionName() {
        return  this.getStringAtPosDelimiterStorage( getIndexOfFunctionName() );
    }
    public  String getEntityType() {
        return  this.getStringAtPosDelimiterStorage( getIndexOfEntityType() );
    }
    public  String getOptions() {
        Integer pos = getIndexOfOptions();
        if(pos != null)
            return this.getStringAtPosDelimiterStorage( pos);
        else
            return null ;
        
    }





    public  static String getDimensionType(List<String> strs) {
        String result = null;
        String tmp_result ;
        if(!isToShortList(strs)) {
        tmp_result = strs.get(getIndexOfDimensionType(strs));
        if(isValidDimensionType( tmp_result))result=tmp_result;
        }
        return result;
     }
   
     public static  String getFunctionName(List<String> strs) {
        String result = null;
        String tmp_result ;
        if(!isToShortList(strs)) {
            tmp_result =strs.get(getIndexOfFunctionName(strs) );
            if(utilString._isNotEmptyOrBlank(tmp_result))result=tmp_result;
        }
        return result;

     }
     public  static String getEntityType(List<String> strs) {
        String result = null;
        String tmp_result ;
        if(!isToShortList(strs)) {
            tmp_result = strs.get(getIndexOfEntityType(strs) );
            if(isValidEntityType( tmp_result))result=tmp_result;
        }
        return result;

     }
     public   static List<String> getOptions(List<String> strs) {
        List<String> result = null;
        List<String> tmp_result = null;
        Integer pos = getIndexOfOptions( strs) ;
        if(pos != null){
            tmp_result= strs.subList(pos , utilString.getIndexString( strs , positionOfFunctionName )   );
            if(tmp_result!=null && utilString._isNotEmptyOrBlank(tmp_result.get(0))) result= tmp_result;
        }
        return  result;
         
         
     }

    public static boolean isValidEntityType(String str) {
        boolean result = false;
        for (type_of_entity t_entity : type_of_entity.values()) {
            System.out.println("t_entity.name() : " + t_entity.name() + " str : " + str );
            if (t_entity.name().equalsIgnoreCase(str)) {
                result = true;
                break;
            }
        }
        return result;
    }
    public  static  boolean isValidDimensionType(String str) {
        boolean result = false;
        for (type_of_dimension t_dimension : type_of_dimension.values()) {
            System.out.println("t_dimension.name() : " + t_dimension.name() + " str : " + str );
           
            if (t_dimension.name().equalsIgnoreCase(str)) {
                result = true;
                break;
            }
        }
        return result;
    }

  
    public static boolean isValidEntityType(List<String> strs) {
        return isValidEntityType((getEntityType(strs)));
    }
    public static boolean isValidDimensionType(List<String> strs) {
        return isValidDimensionType((getDimensionType(strs)));
    }

    public  String getFullName(){
        return this.storageName.replace(delimiterStorageName,delimiterFunctionName);
    }
    public static  String getFullName(List<String> strs, String _delimiterFunctionName){
        return (strs!=null?String.join(_delimiterFunctionName,strs):null);
    }


    public static String getStorageName(List<String> strs, String _delimiterStorageName){
        return (strs!=null?String.join(_delimiterStorageName,strs):null);
    }


    



    
}
