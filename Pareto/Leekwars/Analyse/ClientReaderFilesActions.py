import socket , os 
import os.path
import sys
import time
from DistantUtil import DistantConfig , IRWFilesActions
from filenameVerif import _emptyTuple,filename_isvalid,splitIds,getIdsPos,getGenPos,getFightPos
import argparse
import numpy as np 
from  ReadActionsFromRunScenario import *
from leek_resume import _fucn 
from ReaderFilesEager import *
from utilFight import * 


from myutil import print_pyth


    

class ReaderFilesActions (IRWFilesActions):

    TOKEN_READY_ = "READY"
    TOKEN_END_ ="#"

    def __init__(self,path_input_files ,port,host,_type,output_dir ,todo_num=1):
        self.read_f_eager = ReaderFilesEager (path_input_files)
        
        self.output_dir = output_dir 

        self.debug_mode = True

        self.config_serv = DistantConfig(port,host)
        self.command = IRWFilesActions( self.init_client , self.finish_client)
        
        if( _type != "PYTH" and _type != "JAVA"):
            raise Exception("type must be PYTH or JAVA")
        self.my_type =_type
        
        self.todo_num  = todo_num
        

        self.init_client()

    def init_client(self):
        

        host , port ,_ = list(self.config_serv.getValues())[:]
        if(self.debug_mode):
            print_pyth([host,port])

        self.client_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM) 
        self.client_socket.connect((host, port))  

        if(self.debug_mode):
            print_pyth(self.my_type)
        self.client_socket.send(self.my_type.encode())  # receive response

 

        if (self.my_type== "PYTH"):
         
            while True :
                if( self.todo_num == 1 ):
                    isReady , isTerminate = self.is_ready_Terminate() 
                    if(isReady):
                        self.readFiles()

                    if(isTerminate):
                            break
                else:
                    isReady , isTerminate = self.is_ready_Terminate() 
                    if(isReady):
                        self.readFiles()

                    if(isTerminate):
                            break


        
        elif (self.my_type == "JAVA") :

            json_contents = ["{ \"actions\" : [12,12,14] , \"ids\" : [145,256] , \"gen\" : 8 , \"fight\" : 8 , \"id\" : 14541}",
                            "{ \"actions\" : [12,12,14] , \"ids\" : [145,256] , \"gen\" : 9 , \"fight\" : 8 , \"id\" : 14541}",
                            "{ \"actions\" : [12,12,14] , \"ids\" : [145,256] , \"gen\" : 10 , \"fight\" : 8 , \"id\" : 14541}",
                            "{ \"actions\" : [12,12,14] , \"ids\" : [145,256] , \"gen\" : 11 , \"fight\" : 8 , \"id\" : 14541}"
            ]
            filenames = ["145_256_8-8_14541.json","245_256_8-8_14541.json","345_256_8-8_14541.json","445_256_8-8_14541.json"]

            assert(len(json_contents) == len(filenames))
            for i in range(len(json_contents)) :
                data =  self.client_socket.send((filenames[i] +"|"+json_contents[i]+"#").encode())
            
        else:
            print_pyth("TYPE INCONNU ")
                    
        self.finish_client()
        return 
        

    def finish_client(self):
        print("ON FERME LE SOCKET CLIENT READER")
        self.client_socket.close()  # close the connection    




    def is_ready_Terminate(self):
        datas =  str(self.client_socket.recv(1024).decode())  # receive response
                    
        datas2 = datas.replace(ReaderFilesActions.TOKEN_READY_,"")
        if(len(datas)> len(datas2)):
            if(self.debug_mode ) :
                print_pyth("READY ICI ") 
            return True,ReaderFilesActions.is_terminate(datas2)
        else :
            return  False ,ReaderFilesActions.is_terminate(datas2)

    def is_terminate(datas):
        return ReaderFilesActions.TOKEN_END_ in datas 

    
    
    def readFiles (self ):

        json_files = self.read_f_eager.update_for_newFiles()
        for file in json_files :
                tuples = filename_isvalid( file ) 
                if(tuples!=_emptyTuple()):
                    n_file = os.path.join(self.read_f_eager.input_path,file)
                    if(self.debug_mode):
                        print_pyth([n_file ,self.output_dir , splitIds(tuples[ getIdsPos()]) , tuples[getGenPos()] , tuples[getFightPos()]] )
                    str_time = n_file[n_file.rindex('_')+1:n_file.rindex('.')]
                    filename_csv = ReaderFilesActions.readThenWriteActions(n_file ,self.output_dir , tuples[ getIdsPos()] , tuples[getGenPos()] , tuples[getFightPos()] ,str_time)
                    ReaderFilesActions.readThenWriteStat(n_file , self.output_dir , tuples[ getIdsPos()] , tuples[getGenPos()] , tuples[getFightPos()] ,str_time)
                    ReaderFilesActions.readThenWriteAffects(n_file,filename_csv, self.output_dir , tuples[ getIdsPos()] , tuples[getGenPos()] , tuples[getFightPos()] ,str_time)
                    #_fucn(filename_csv , n_file )#stat avancé mais récupérable avec stat base 




    


    def readThenWriteActions(filename_actions , path_dir_output ,entities_ids, nbGeneration , nbFight , time_str=None  , debug_mode = False ):
    
        data = dict()
        with open(filename_actions, 'r') as f:
            data = json.load(f)
        if  isinstance (data,str) :
            data=json.loads(data)


        rdA = ReadActions(splitIds(entities_ids),nbGeneration , nbFight ,path_atm=os.path.dirname(path_dir_output) )


        rdA.getFight(data['fight']['actions'])
        if time_str is None :
            time_str = str(round(time.time()))
        filename_csv = os.path.join(path_dir_output, nbGeneration , nbFight, '_'.join(map(str,[ entities_ids, time_str ]))+".csv")
        isExist = os.path.exists(path_dir_output)
        if(True ):
            print_pyth([filename_csv,rdA.df["ID_TURN"].values])
        if not isExist:
            os.makedirs(path_dir_output)
        rdA.df.to_csv(filename_csv, sep=';', encoding='utf-8')


        return filename_csv


    def readThenWriteStat(filename_stat , path_dir_output ,entities_ids, nbGeneration , nbFight , time_str=None , debug_mode = False ):
        with open(os.path.join(os.path.join(".","data"),"df_enumCaracteristique.json"), 'r') as f:
            enumCara = json.load(f)
            if  isinstance (enumCara,str) :
                enumCara=json.loads(enumCara)
            
        with open(filename_stat, 'r') as f:
            data = json.load(f)
            if  isinstance (data,str) :
                data=json.loads(data)
            used_genes = data["used_genes"]
            history = data["history"]
            lst_key = []
            lst_idx = np.array(enumCara['enumIndex'])
            lst_idx=np.argsort(lst_idx)
            lst_key=(np.array(enumCara['enumName'])[lst_idx]).tolist()
            lst_key+=["id_entity"]

            df_total_stat = pd.DataFrame(columns= lst_key)
            df_buff_stat = pd.DataFrame(columns= lst_key)
            for i in range(min(len(history[list(history.keys())[1]]),len(history[list(history.keys())[0]]))):
                for k in history.keys():
                    df_total_stat.loc[len(df_total_stat)] = history[k][i][1]+[int(k)]
                    df_buff_stat.loc[len(df_buff_stat)]=history[k][i][0]+[int(k)]

        if(True):
            print_pyth(path_dir_output)
        if time_str is None :
            time_str = str(round(time.time()))
        filename_csv = os.path.join(path_dir_output, nbGeneration , nbFight,'_'.join(map(str,[ entities_ids, time_str ])))

        df_buff_stat.to_csv(filename_csv+"_buff_stat"+".csv", sep=';', encoding='utf-8')
        df_total_stat.to_csv(filename_csv+"_total_stat"+".csv", sep=';', encoding='utf-8')

        return 


    def readThenWriteAffects(filename_actions,filename_csv , path_dir_output ,entities_ids, nbGeneration , nbFight , time_str=None ):

        path_generator_data = os.path.join(".","data")
        myEffect_weap =  os.path.join(path_generator_data,"myEffect_weapons.json") 

        with open(myEffect_weap, 'r') as f:
            effect_weap=json.load(f)
            df_fight = pd.read_csv( filename_csv  , encoding="utf-8",sep=";")

        tmp_ids = splitIds(entities_ids)
        ids ={"receveur":tmp_ids[0],"lanceur":tmp_ids[1]}

        df_total_stat,df_atm_stat = totalAndAtm (filename_actions)
        df_atm_stat = dfToBinary(df_atm_stat)
        df_total_stat = dfToBinary(df_total_stat)
        df_weapons=getDfWeapons_affect(stringDf_toDf(df_fight))
        writeCorrelation(df_weapons,df_atm_stat,df_total_stat,path_dir_output,ids, int(nbGeneration) , int(nbFight))

        return 





    
        
if __name__ == '__main__':
    rfa= None
    try:
            parser = argparse.ArgumentParser(description='Ecris un fichier correpondant au contenu fourni')
            parser.add_argument("-t","--hostname",type=str,help="address du serveur")
            parser.add_argument("-p","--port",type=str , help="port du serveur ")
            parser.add_argument("-d","--dir_output", type=str,  help="Chemin vers le repertoire d'output ")
            parser.add_argument("-i" , "--dir_input", type=str, default="", help=" path vers l'input  ")

            parser.add_argument("-y","--gtype", type=str, help="type du client JAVA ou PYTH ")
            
            parser.add_argument("-m","--multithread", nargs='?' , type=bool, default=False, help="Mode multithread : while there is the java parent program continue running  ")


            args = parser.parse_args()

           
        
            rfa = ReaderFilesActions(path_input_files=str(args.dir_input) , port=int(args.port),host=str(args.hostname),_type=str(args.gtype),output_dir=str(args.dir_output))
            
            
    except KeyboardInterrupt:
            print_pyth('User has exited the program') 
            if(rfa is not None):
                rfa.finish_client()
                sys.exit(0)
