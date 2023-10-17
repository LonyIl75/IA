package com.leekwars.generator.genetics;

public class modifyFieldsAI {
   
    public static modifierField[] getFields(){
        return modifierField.values();
    }
    public static int getIndxField(modifierField modifField){
        return modifField.ordinal();
    }
    public static int getLenghtFields(){
        return modifierField.values().length;
    }

    public static String getToken(modifierField modifField){
        switch(modifField){
            case WEAPONS : return "W";
            case CHIPS : return "C";
            default : return "";
        }
    }
    public static String getLabel(modifierField modifField){
        switch(modifField){
            case WEAPONS : return "weapons";
            case CHIPS : return "chips";
            default : return "";
        }
    }
    
}
