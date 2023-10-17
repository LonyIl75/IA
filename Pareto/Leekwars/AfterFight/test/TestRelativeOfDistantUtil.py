import random 
import threading
import sys

sys.path.append("../src")

from DistantUtil import *
from myutil import *
from ServWriterFilesActions import *
from ClientReaderFilesActions import *





class TestWriterDistantConfig :
    port_pos = 5010
    no_port = None
    port_underLimit = 5000-10
    port_neg = -1
    
    dir_input = "..\data"
    invalid_filename = "jeSuisInvalid.json"
    valid_filename = "1_1_4-4_45655.json" #"int_int_(int-int)+_int+"  ".json"
    def __init__(self):
        self.test_port()
        self.test_writeFile()
        self.test_serv()
        
    def test_port(self):
        config_pos_port = DistantConfig(TestWriterDistantConfig.port_pos)
        assert(config_pos_port.config["port"]==TestWriterDistantConfig.port_pos)
        
        config_no_port = DistantConfig(TestWriterDistantConfig.no_port)
        assert(config_no_port.config["port"]==DistantConfig.df_port)
        
        config_neg_port = DistantConfig(TestWriterDistantConfig.port_neg)
        assert(config_neg_port.config["port"]==DistantConfig.df_port)
        
        config_underLimit_port = DistantConfig(TestWriterDistantConfig.port_underLimit)
        assert(config_underLimit_port.config["port"]==DistantConfig.df_port)
        
    def test_writeFile(self):
        
        wrdc = WriterFilesActions(TestWriterDistantConfig.dir_input )
        
        content_1 = "On est les Masters ICO"
        assert(wrdc.write_file(TestWriterDistantConfig.invalid_filename,content_1)==WriterFilesActions.getInvalidWriteCode())
        
        
        assert(wrdc.write_file(TestWriterDistantConfig.valid_filename,content_1)==WriterFilesActions.getValidWriteCode())
        #TODO : test readFile == content + file create ?overwrite 
        
        
    def test_serv(self):
        
        wrdc = WriterFilesActions(TestWriterDistantConfig.dir_input, port=11111 )
        
        rdc=  ReaderFilesActions(TestWriterDistantConfig.dir_input , port=wrdc.get_myPort()  , host=wrdc.get_myHostname() )
        
        def writeNFile(wfa , nb_file):
            wfa.init_serv()
            str_ids = "4-4"
            test_content = "Bonjour "
            for i in range (nb_file):
                filename = '_'.join(map(str,[i,i*10,str_ids,random.randrange(100, 20000)]))+".json"
                print(filename)
                wfa.write_file(filename , test_content+str(i))
          
        t1 = threading.Thread(target=writeNFile, args=(wrdc,10) )
        t1.start()
        
        #time.sleep(2)
        t2 = threading.Thread(target =rdc.init_client )
        t2.start()
        
        
                              
        t1.join()
        t2.join()



class TestIRWFilesActions:
    
    def f_hello():
        print("hello")
    
    def f_bye():
        print("bye")
    
    lmb_add2 = (lambda x : 2+x)
    lmb_sub2 = (lambda x : x-2)
    
    def __init__(self):
        self.hello_bye = IRWFilesActions(TestIRWFilesActions.f_hello , TestIRWFilesActions.f_bye)
        self.addSub = IRWFilesActions(TestIRWFilesActions.lmb_add2 , TestIRWFilesActions.lmb_sub2)
        
        self.test_getFuncts()
        self.test_callFuncts()
        
    def test_getFuncts(self):
        self.hello_bye.getFuncts(IRWFilesActions.Token.INIT)()
        self.hello_bye.getFuncts(IRWFilesActions.Token.END)()
        
        assert(self.addSub.getFuncts(IRWFilesActions.Token.INIT)(0) == self.addSub.getFuncts(IRWFilesActions.Token.END)(4))
        
        
        
        #config_pos_port = DistantConfig(TestWriterDistantConfig.port_pos)
        #assert(config_pos_port.config["port"]==TestWriterDistantConfig.port_pos)
        
    def test_callFuncts(self):
        assert(self.addSub.callFuncts(IRWFilesActions.Token.INIT,0) == self.addSub.callFuncts(IRWFilesActions.Token.END,4))



if __name__ == '__main__':
    #TODO function setup 
    #init
    #test switch
    tIRW = TestIRWFilesActions()
    tWDC = TestWriterDistantConfig()