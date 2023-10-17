import json
import argparse
import socket , os
import sys
import threading
from filenameVerif import *
from DistantUtil import IRWFilesActions , DistantConfig , recvall
from waiting import wait
from myutil import print_pyth
from pathlib import Path

class WriterFilesActions :
        

    

    
    def __init__(self,path_output_files, port = None  , host=None ):
        self.output_path = path_output_files
        
        self.config_serv = DistantConfig(port=port,host=host)
        self.connections = {"JAVA":None , "PYTH":None}
        self.filenames = []
        self.address = None
        self.cv = threading.Condition()
        self.debug_mode = True
        self.end=False
        self.init_serv()
        #self.command =IRWFilesActions( self.init_serv , self.finish_serv)
        
    def checkValidJson(content):
        return True 
    
    def getInvalidWriteCode():
        return -1
    
    def getValidWriteCode():
        return 0 
    
    def write_file(self,filename_output , content):
        beg_json = content.index("{")
        end_json = content.rindex("}")
        content = content[beg_json:end_json+1]
        
        if(not(bool(b_filename_isvalid(filename_output)))):
            return WriterFilesActions.getInvalidWriteCode()
        


        n_filename_out = os.path.join(self.output_path, filename_output+".json")
        out_file = open(n_filename_out, "w")

        assert(WriterFilesActions.checkValidJson(content))

        json.dump(content, out_file, indent = 6)
        out_file.close()

        '''
        if(self.debug_mode):
            print_pyth("WRITE")
        if( not( self.conn is None  )):
            message="READY"
            self.conn.send(message.encode())
        self.finish_serv()
        '''
        return WriterFilesActions.getValidWriteCode()
    
    def listen_javaClient(self,address):
        
        data =  str(recvall(self.connections["JAVA"]).decode())  # receive response

        if(self.debug_mode):
            print_pyth("OK message recu : " + data)
        
        ok = False

        while not ok :
            try:
                end_filename = data.index(WriterFilesActions.getDelimFileName())
        
            except ValueError:
                if("END" in data ):
                    ok = True
                    feee = open("EndFile.txt", "a")
                    feee.write("Now the file has more content!")
                    feee.close()
                    continue

                res_recv=str(recvall(self.connections["JAVA"]))

                if(res_recv == ""):
                    ok = True
                    continue
                data+=res_recv
                continue

            first_part = data[:end_filename]
            filename_send = WriterFilesActions.getFileNameFromStr(first_part )
            if filename_send == WriterFilesActions.getEmptyFilename():
                ignore = True 
            else:
                ignore = False

            data = data[end_filename+1:]
            

            ok2 = False
            while not ok2:
                try:
                    end_second_part = data.index(WriterFilesActions.getDelimEnd())
                    second_part=data[:end_second_part]
                    data = data[end_second_part:]
                    
                 
                    if not ignore:
                       self.write_file(filename_send,second_part.replace("\\",""))
                       self.cv.acquire()
                       self.filenames.append(filename_send)
                       self.cv.notify_all()
                       self.cv.release()
                       


                
                    ok2 = True
                except ValueError:
                    res_recv=str(recvall(self.connections["JAVA"]))
                    if(res_recv == ""):
                        ok = True
                        break
                    data+=res_recv
                    ok2 = True
                    continue
                    
            
            #ok2 = False
            #ok=True
        feee = open("EndFile.txt", "a")
        feee.write("Sortie Boucle")
        feee.close()
        self.cv.acquire()
        self.end=True 
        self.connections["JAVA"].close()
        self.cv.notify_all()
        self.cv.release()
        
        return 


    def listen_pythonClient(self,address):
        self.cv.acquire()
        numbytes = 1

        while numbytes > 0 and not(self.end):
            self.cv.wait()
            self.filenames=[]
            content_message= "#" if(self.end) else "READY"

            numbytes = self.connections["PYTH"].send(content_message.encode())
            
        self.connections["PYTH"].close()  
        self.cv.release()
        return 

  
    def init_servOK():
        return 0

    def init_serv(self):

        host , port , nb_client = list(self.config_serv.getValues())[:]
        self.server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        
        self.server_socket.bind((host, port)) 
        if(self.debug_mode):
            print_pyth(self.server_socket.getsockname())

        self.server_socket.listen(nb_client)
        
        if(self.debug_mode):
            print_pyth("PRET A LIRE "+str(nb_client))
        
        
        #DEBUG AVEC FICHIER 
        
        
        #message="OK"
        #self.conn.send(message.encode())
        feee = open("PretAlire.txt", "w")
        feee.write("PretAlire")
        feee.close()

        feee = open("EndFile.txt", "w")
        feee.write("Debut")
        feee.close()
        
        
        t_jv = None 
        t_py = None 
        
        while( self.lenConnections() < nb_client):
            conn, address =  self.server_socket.accept() 
            feee = open("PretAlire.txt", "a")
            feee.write(str(address))
            feee.close()

            if(self.debug_mode):
                print_pyth("Connection from: " + str(address))
                
            data =  conn.recv(4).decode()  # receive response
            if(self.debug_mode):
                print_pyth(str(data))

            if(str(data)=="JAVA"):
                self.connections["JAVA"] = conn
                t_jv = threading.Thread(target = self.listen_javaClient,args=(address,)).start()
            elif (str(data)=="PYTH"):
                self.connections["PYTH"] = conn
                t_py = threading.Thread(target = self.listen_pythonClient,args=(address,)).start()
                
            else:
                print_pyth("Not accepted ")
                conn.close()
            

        feee = open("EndFile.txt", "a")
        feee.write("Attends")
        feee.close()

        if( not(t_jv is None )):
            t_jv.join()
        if( not(t_py is None )):
            t_py.join()

        feee = open("EndFile.txt", "a")
        feee.write("FinServ")
        feee.close()
     
        
        #self.write_file("145_256_8-8_14541.json" , str(data))
        
        #self.finish_serv()
        return WriterFilesActions.init_servOK()
    
  
    def getDelimFileName():
        return "|"
    
    def lenConnections(self):
        i=0
        self.cv.acquire()
        for c in self.connections.values() :
            if not( c is None) :
                i+=1
        self.cv.release()
        return i

    
    def getDelimEnd():
        return "#"
    
    def getEmptyFilename():
        return ""
    
    def getIndexOfFileNameFromStr(stre,nb=None):
        l = re.search(get_restr_filename_isvalid(nb),stre)
        if l is None:
            return None
        return [l.start(),l.end()]
    
    def getFileNameFromStr(stre,nb=None):
        coord = WriterFilesActions.getIndexOfFileNameFromStr(stre,nb)
        if coord is None:
            return WriterFilesActions.getEmptyFilename()
        return stre[coord[0]:coord[1]]

        
    def get_myHostname(self):
        return self.config_serv.getValue("host")
    
    def get_myPort(self):
        return self.config_serv.getValue("port")
    
    
    def finish_serv(self) :

        if(self.debug_mode):
            print_pyth("Serv close ")
        self.conn.close()


if __name__ == '__main__':
        writer=None 
        try:
            parser = argparse.ArgumentParser(description='Ecris un fichier correpondant au contenu fourni')
            parser.add_argument("-t","--hostname",type=str,help="address du serveur")
            parser.add_argument("-p","--port",type=str , help="port du serveur ")
            parser.add_argument("-d","--dir_output", type=str,  help="Chemin vers le repertoire d'output ")
            #parser.add_argument("-f" , "--filename_output", nargs='?' , type=str, default="", help="[optionnel] Nom du fichier a ecrire ")

            #parser.add_argument("-c","--content", type=str, help="Contenu du fichier a ecrire")
            
            parser.add_argument("-m","--multithread", nargs='?' , type=bool, default=False, help="Mode multithread : while there is the java parent program continue running  ")


            args = parser.parse_args()

            def check_content(content):
                if(content is None ) :
                    raise ValueError("Content is None")
                try :
                    json.loads(content)
                except ValueError as e:
                    raise ValueError("Content is not a valid json")
                return True
            

            
            def check_filename_output(filename_output):
                if(filename_output is None ):
                    raise ValueError("filename_output is None")
                
                '''
                if(not(bool(b_filename_isvalid(filename_output)))):
                    raise ValueError("filename not validt format")
                '''
                return True
            
            def check_dir_output(dir_output):
                if(dir_output is None ):
                    raise ValueError("dir_output is None")
                '''if(not(os.path.isdir(dir_output))):
                    print_pyth(dir_output)
                    raise ValueError("dir_output is not a valid directory")'''
                return True

            check_dir_output(args.dir_output ) 
            '''if(not(args.multithread)):
                    check_content(args.content)
                    check_filename_output(args.filename_output)
            '''

            writer = WriterFilesActions(str(args.dir_output),port=int(args.port), host=str(args.hostname))
            
            
        except KeyboardInterrupt:
            print_pyth('User has exited the program') 
            if(not(writer is None)):
                writer.finish_serv()
                sys.exit(0)
