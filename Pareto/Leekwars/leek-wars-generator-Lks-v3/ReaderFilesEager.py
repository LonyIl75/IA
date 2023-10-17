import socket , os 
import os.path
import time
from DistantUtil import DistantConfig , IRWFilesActions
from filenameVerif import _emptyTuple,filename_isvalid,splitIds,getIdsPos,getGenPos,getFightPos
import re 
from utilFight import * 



#Retourne les nouveaux fichiers écris dans input_path  

class ReaderFilesEager (IRWFilesActions):
    def __init__(self,path_input_files ):
        self.input_path = path_input_files
        self.debug_mode = False 

        self.init_cst()

    def init_cst(self,port=None):
        self.updateFiles()
        self.prev_files = []

    def needToRead(self):
        return  list(set(self.files) - set(self.prev_files))
    
    def getFilesInPath(self):
        #print_pyth("LIST " + str(os.listdir(self.input_path)))
        if( os.path.exists(self.input_path )) :
            return [pos_json for pos_json in os.listdir(self.input_path) if pos_json.endswith('.json')]
    
    def updateFiles(self,regex=None):
        if(not(regex is None)):
            n_files = self.getFilesInPath()
            pattern = re.compile(regex)
            self.files = [f for f in n_files if bool(pattern.match( f))]
        else:
            self.files = self.getFilesInPath()
    
    def setPrev(self):
        self.prev_files = self.files

    def update_for_newFiles(self,regex=None):
        self.updateFiles(regex)  
        json_files = self.needToRead()
        self.setPrev()
        return json_files


    