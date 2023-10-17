

import threading

class testConnectionThread (threading.Thread):
    def __init__(self,start_function):
        threading.Thread.__init__(self)
        self.mstart = start_function

    def run(self):
        try:
            self.mstart() # Or startClient() for your client thread
        except: Exception 
          # Print your exception here, so you can debug

          
def isInInterval ( e, interval ):
    e = int(e)
    return e >= interval[0] and e< interval[1] 


def reversedict( in_dict ):
        res_dict = dict()
        for k ,v in in_dict.items() :
            res_dict.update({v : k})
        return  res_dict

def print_pyth(_str):
    print("[PYTH]" + str(_str))

def isJsonFile( filename ):
    return filename.endswith(".json")

def errorMessage( _class : object, _method :str ,  _str: str  ):
    print("[ERROR] " + (" from :" + type(_class ) +",") if _class !=""  else "" + ("method :" +_method +",") if _method !="" else "" +   " description : " + _str)

def errorMessage( _str: str ):
    errorMessage( "" , "", _str)

def errorMessage( _method :str, _str: str ):
    errorMessage( "" , _method, _str)


def errorMessagePyth( _str: str ):
    print_pyth(errorMessage( _str ))

def errorMessagePyth( _method:str , _str: str ):
    print_pyth(errorMessage(_method , _str ))

def errorMessagePyth( _class:str , _method:str , _str: str ):
    print_pyth(errorMessage(_class , _method , _str ))


def errorMessageFromClass(obj : object, _str: str ):
    errorMessage( obj ,"", _str)

def errorMessageFromClassPyth(obj:object , _str: str ):
   print_pyth(errorMessage( obj , "" , _str ))


def notFound():
    return -1

def notSetId():
    return -1

def badInsert():
    return -1

def outOfRange():
    return -1

def strColInCols(str_col:str , df ):
    return str_col in df.columns
