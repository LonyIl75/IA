from collections import Counter
import pandas as pd

from myutil import print_pyth

def recur (lst):
    res = []
    for x in lst :
        if( isinstance(x, list)):
            res += recur(x)
        else :
            res.append(x)
    return res 
def notSetCol():
    return [] 
def mergeCol (df,idx,df_res=None):
    if df_res is None :
        df_res = pd.DataFrame(columns = df.columns )
    else :
        assert(df_res.columns == df.columns)

    df_res.loc[idx]=[[]]*len(df_res.columns)
    for c in df_res.columns :
        if(not('ID' in c)):
           df_res.loc[idx][c]=recur (list(df[c]))
        else:
            df_res.loc[idx][c]=notSetCol()
    return df_res



def reduceCol (df,idx , res_dict=None ):
    if res_dict is None :
        res_dict = dict.fromkeys(df.columns)
        for c in df.columns :
            res_dict[c]= list()
        
    else : 
        assert(list(res_dict.keys()) == df.columns)
        assert(isinstance(list(res_dict.values())[0],list))
    for c in df.columns :
        res_dict[c].append(dict(Counter(df[c].tolist()[0])))
    return res_dict
