package com.leekwars.generator.genetics;

import java.io.IOException;

public class JavaPython extends Thread {
    
    private String scriptFile ;
    private String type  ;
    private Object parameters ; 

    public JavaPython (String _scriptFile,String _type ,Object _parameters){
        this.scriptFile = _scriptFile ;
        this.type = _type ; 
        this.parameters = _parameters ; 


    }
    public  void run() {
        System.out.println(this.type);
        String cmd = "";
        switch(this.type){
            case "SERV_WRITER":
                
                ServWriterActionsParameters serv_param = (ServWriterActionsParameters) parameters ; 
                System.out.println("SERV_WRITER " + this.scriptFile + " " + serv_param.toString() );

                cmd = "python "+this.scriptFile +" "+serv_param.toString();
                break;
          

            case "CLIENT_JAVA":
                ClientReaderFilesActions client_param_java = (ClientReaderFilesActions) parameters ;
                System.out.println("CLIENT_JAVA " + this.scriptFile + " " + client_param_java.toString() );

                cmd = "python "+this.scriptFile +" "+client_param_java.toString();
                break;

            case "CLIENT_PYTHON":

                ClientReaderFilesActions client_param_pyth = (ClientReaderFilesActions) parameters ;
                System.out.println("CLIENT_PYTHON " + this.scriptFile + " " + client_param_pyth.toString() );

                cmd = "python "+this.scriptFile +" "+client_param_pyth.toString();
                break;

                
            default :
                return ;

        }

        if( cmd == ""){
            return ;
        }

        ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));//,"--multithread ",true);
        Process p;
        try {
            p = pb.inheritIO().start();
            System.out.println("SERV_WRITER");
            
            p.waitFor();
            System.out.println("Fin " + this.type);
            
            return ;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        
        
    }


}
