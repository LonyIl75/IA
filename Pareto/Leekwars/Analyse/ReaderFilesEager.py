import socket , os 
import os.path
import time
from DistantUtil import DistantConfig , IRWFilesActions
from filenameVerif import _emptyTuple,filename_isvalid,splitIds,getIdsPos,getGenPos,getFightPos

from utilFight import * 




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
    
    def updateFiles(self):
         self.files = self.getFilesInPath()
    
    def setPrev(self):
        self.prev_files = self.files

    def update_for_newFiles(self):
        self.updateFiles()  
        json_files = self.needToRead()
        self.setPrev()
        return json_files


    