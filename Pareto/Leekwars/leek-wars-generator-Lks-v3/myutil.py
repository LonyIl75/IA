

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
    print("[PYTH]" + _str)