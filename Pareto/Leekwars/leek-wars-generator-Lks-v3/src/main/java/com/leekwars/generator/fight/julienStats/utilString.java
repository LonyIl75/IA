package com.leekwars.generator.fight.julienStats;

import java.util.List;

public class utilString {


    public static String getStringAtPosDelimiter(String str , int pos,String delimiter){
        if(pos<0){
            for (int i = str.length()-1; i > 0 ;i-- ) {
                  if(str.charAt(i) == delimiter.charAt(0))
                    {pos++;}
                if(pos == 0){
                    int index = str.indexOf(delimiter, i+1);
                    if(index ==-1)return str.substring(i+1,  str.length());
                    else return str.substring(i+1, index);
                }
            }
        }
        else{
        if(pos==0){
            if (str.isEmpty() || str.indexOf(delimiter) == -1)return str;
            return str.substring(0, str.indexOf(delimiter));
        }
        for (int i = 0; i < str.length(); i= i+1) {
            if(str.charAt(i) == delimiter.charAt(0))
                pos--;
            if(pos == 0){
                int index = str.indexOf(delimiter, i+1);
                if(index ==-1)return str.substring(i+1,  str.length());
                return str.substring(i+1, index);
                }
        }
        }
        return null ; 

     }
    
    public static boolean _isNotEmptyOrBlank(String val ) {
		return !_isEmptyOrBlank(val )  ;
        
	}
    public static boolean _isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
    public static boolean _isNotEmpty(String val ) {
        return !_isEmpty(val )  ;
    }
    public static boolean _isEmpty(String val) {
        return val==null || val.isEmpty() ;
        
    }

	public static boolean _isEmptyOrBlank(String val) {
		return _isEmpty(val) || _isBlankString(val)  ;
		
	}

        /*
      * return indexBis > 0 from index \in Z  
      */
    public static int getIndexString( String str , int index) {
        if(Math.abs(index) > str.length())return -1;
        if(index >= 0 ){    
            return index;
        }
        else{
            return str.length() + index;
        }

    }
    /*
     * return indexBis > 0 from index \in Z 
     */
    public static int getIndexString(List<String> str, int index) {

        if(Math.abs(index) > str.size())return -1;
        if(index >= 0 ){    
            return index;
        }
        else{
            return str.size() + index;
        }

    }

 

    public static String concatString(String separator,String ... str_args){
        String concat =	new String(str_args[0]);
        for (int i = 1  ; i < str_args.length ; i++ ) {
            concat = concat+separator+str_args[i];
           }
        return concat;
    }

    
}
