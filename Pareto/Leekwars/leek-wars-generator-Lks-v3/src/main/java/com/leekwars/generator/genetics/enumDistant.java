package com.leekwars.generator.genetics;

public class enumDistant{

    public enum TypeClient{
        CLIENT_JAVA,
        CLIENT_PYTHON,
        CLIENT_NONE
    }

    public static String typeClientToString(TypeClient type){
        String res = "";
        switch(type){
            case CLIENT_JAVA:
                res= "JAVA";
                break;
            case CLIENT_PYTHON:
                res =  "PYTH";
                break;
            case CLIENT_NONE :
                res="";
                break;

        }
        assert(res.length() == 4);
        return res ;

    }

}
