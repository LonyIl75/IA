import re 
import os 

from myutil import print_pyth

def getGenPos():
    return 0
def getFightPos():
    return 1
def getIdsPos():
    return 2

def getDelim():
    return '_'
def getDelimIds():
    return '-'
def get_restr_ids():
    return "((?:\d+-\d+)+)"

def get_restr_idFigth(nb):
    return getDelim().join(["(\d+)"]*nb)
    
def splitIds(str_ids):
    return list(map(int , str_ids.split(getDelimIds())))


def _emptyTuple():
    return ()

def get_restr_filename_isvalid(nb=None):
    if(nb is None ):
        nb=getIdsPos()
    return getDelim().join([get_restr_idFigth(nb),get_restr_ids()])+"(?:_\d+)?"

def get_re_filename_isvalid(nb=None):
    return re.compile(get_restr_filename_isvalid(nb))

def b_filename_isvalid(filename):
    return filename_isvalid(filename)!=_emptyTuple()

def filename_isvalid(filename,nb = None ):
    f_str = os.path.splitext(filename)[0]
    res = re.fullmatch("^"+get_restr_filename_isvalid(nb)+"$",f_str )
    if(bool(res)):
        return (res.group(getGenPos()+1),res.group(getFightPos()+1),res.group(getIdsPos()+1),res.group(0))
    return ()
        
if __name__ == '__main__':
    print_pyth(filename_isvalid("145_256_8-8_14541.json"))