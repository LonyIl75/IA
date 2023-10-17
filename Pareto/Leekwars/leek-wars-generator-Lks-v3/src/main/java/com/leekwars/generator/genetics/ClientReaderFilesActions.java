package com.leekwars.generator.genetics;

import com.leekwars.generator.genetics.ConfigServ;

public class ClientReaderFilesActions extends ConfigServ{
    
    String path_output_files;
    String path_input_files ; 

    
    enumDistant.TypeClient typeClient ;
       



    public ClientReaderFilesActions(String _pathOutput ,String _pathInput ,  String hostname , int port , enumDistant.TypeClient _typeClient){

        super(hostname ,port );
        this.path_output_files = _pathOutput ;
        this.path_input_files = _pathInput ;
        this.typeClient = _typeClient ;


    }

    public ClientReaderFilesActions clone(){
        return new ClientReaderFilesActions(this.path_output_files,this.path_input_files,this.hostname,this.port,this.typeClient);
    }

    public void setPathOutput(String _pathOutput){
        this.path_output_files = _pathOutput ;
    }

    public void setPathInput(String _pathInput){
        this.path_input_files = _pathInput ;
    }

    public void setTypeClient(enumDistant.TypeClient _typeClient){
        this.typeClient =  _typeClient ;
    }



    @Override
    public String toString(){
        return "--dir_output "+this.path_output_files+" --hostname "+this.hostname +" --port "+this.port +" --dir_input "+this.path_input_files+" --gtype "+enumDistant.typeClientToString(this.typeClient) ; 
    }


}