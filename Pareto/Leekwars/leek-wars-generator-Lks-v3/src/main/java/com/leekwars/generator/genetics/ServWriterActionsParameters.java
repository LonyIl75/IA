
    package com.leekwars.generator.genetics;


    public class ServWriterActionsParameters extends ConfigServ{

        String path_output_files;
       

      
        public ConfigServ config ;

        public ServWriterActionsParameters(String _pathOutput ,  String hostname , int port ){

            super(hostname ,port );
            this.path_output_files = _pathOutput ;
            

        }
        @Override
        public String toString(){
            return "--dir_output "+this.path_output_files+" --hostname "+this.hostname +" --port "+this.port ; 
        }


    }