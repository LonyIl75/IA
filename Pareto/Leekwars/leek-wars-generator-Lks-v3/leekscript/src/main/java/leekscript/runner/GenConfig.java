package leekscript.runner;

public class GenConfig {
    public int num_generation ;
    public int num_figth; 
    public String  filename ;

    public GenConfig(){
        num_generation = -1;
        num_figth = -1;
        filename = "";
    }

    public GenConfig(int _num_generation , int _num_figth , String _filename){
        num_generation = _num_generation;
        num_figth = _num_figth;
        filename = _filename;
    }


    public int getGenID(){
        return num_generation;
    }
    public int getFigthID(){
        return num_figth;
    }

    public String getSettingFilename(){
        return filename;
    }

    public String toString(){
        return "GenConfig : num_generation = "+Integer.toString(num_generation)+" num_figth = "+Integer.toString(num_figth)+" filename = "+filename;
    }
    
    public String getGenFight(){
        return Integer.toString(num_generation)+"_"+Integer.toString(num_figth);
    }

}
