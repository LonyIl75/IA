import socket
from enum import IntEnum
from myutil import print_pyth

def getNoClient():
    return -1
def isServ(config):
    return config['nb_connection']!=getNoClient()

class DistantConfig :
    df_port = 5000 
    MIN_PORT = 5000
    NB_CLIENT = 2 
    config_model = ["host","port","nb_connection"]
        
    def __init__(self,port = None ,host =None ):
        self.config = dict.fromkeys(DistantConfig.config_model ) 
        for i ,v in enumerate([socket.gethostname() ,  DistantConfig.df_port , DistantConfig.NB_CLIENT]):
                self.config[DistantConfig.config_model[i]]=v
        if (not(port is None) and int(port) >= DistantConfig.MIN_PORT) :
            self.config["port"] = port 
        if( not(host is None)):
             self.config["host"] = host
            
    def getValues(self):
        return self.config.values()
    
    
    def invalidValue() :
        return -1
    def getValue(self, str_id):
        if(str_id in self.config.keys()):
            return self.config[str_id]
        else :
            return DistantConfig.invalidValue()
    
class IRWFilesActions:
    SIZE_TOKEN = 100
    NB_MIN_FUNCTS = 2
    class Token(IntEnum):
       INIT = 0
       END = 1
        
       ERROR = -1 #Attention 
    
       READY = 2
       WAIT = 3
    def __init__(self,*args):
        assert(len(args) >= IRWFilesActions.NB_MIN_FUNCTS)
        self.functs =[]
        for f in args :
            self.functs.append(f)
    def callFuncts(self , c_cmd ,*args):
        fct =self.getFuncts(c_cmd  )
        if(bool(fct)):
            return fct(*args)
        
    def getFuncts(self,g_cmd):
        if(int(g_cmd.value) < len(self.functs)):
            return self.functs[int(g_cmd.value)]
        else:
            return None 
        
def recvall(sock):
        BUFF_SIZE = 4096 # 4 KiB
        data = b''
        while True:
            part = sock.recv(BUFF_SIZE)
            data += part
            if len(part) < BUFF_SIZE:
                # either 0 or end of data
                break
        return data
