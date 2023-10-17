import numpy as np
import ast
import matplotlib.pyplot as plt
import os
import json 
 
from myutil import print_pyth




#Cacule le rapport entre le buff d'une statistique par le poireau et l'emploi d'une arme dont l'effet dépend de cette statistique 
# = nbUtilisationPendantBuff / nbUtilisationTotale


obj_json_stat = {
            'stat_atm':[0] ,
            'stat_base':[0]
            , 'stat_dot':[0]
        }

affects_json_affect = {
    "affect":obj_json_stat.copy(),
    "affectedBy":obj_json_stat.copy()
    
}
obj_recvOrLancr_df = {
    "receveur":None ,
    "lanceur" : None
}
obj_affects_df={ "affect":None,"affectedBy":None}

obj_recvOrLancr_df = {
    "receveur":affects_json_affect.copy(),
    "lanceur" :affects_json_affect.copy()
}
   
lst_key=[]
row_cols = []
    
def init_lst_key():
    with open(os.path.join(os.path.join(".","data"),"df_enumCaracteristique.json"), 'r') as f:
        dct_enumCara = json.load(f)
        if  isinstance (dct_enumCara,str) :
           dct_enumCara=json.loads(dct_enumCara)

    lst_idx=np.argsort(np.array(dct_enumCara['enumIndex']))
    lst_key=(np.array(dct_enumCara['enumName'])[lst_idx]).tolist()
    return lst_key
    
    
lst_key=init_lst_key()
row_cols = ["ReceveurOrLanceur","AffectOrAffectBy","Stats"]+lst_key
    
    



def stringDf_toDf(df_fight):
            for str_col in df_fight.columns :
                i=0
                for _arr in df_fight[str_col].tolist():
                    if (isinstance(_arr,str)):
                        df_fight.loc[i,str_col]=ast.literal_eval(_arr)
                        i+=1
            return df_fight



def add_affect ( obj_1,obj_2,obj_type =obj_json_stat):
            res = obj_type.copy()
            for k in res.keys():
                if len(obj_2[k]) < len(obj_1[k]):
                    limit_arr = obj_2
                    top_arr = obj_1
                else :
                    limit_arr =  obj_1
                    top_arr =  obj_2

                for i,e in enumerate(top_arr[k]) :
                    if(len(res[k])<i):
                        res[k].append(0)
                    if(i>=len(limit_arr[k])):
                        res[k][i] = top_arr[k][i]
                    else:
                        res[k][i] = obj_1[k][i]|obj_2[k][i]
            return res

def add_obj (obj_1,obj_2,obj_type=obj_affects_df):
            res = obj_type.copy()
            for k in res.keys():
                    res[k] = add_affect (obj_1[k],obj_2[k])

            return res 

def add_l_r (obj_1,obj_2,obj_type=obj_recvOrLancr_df):
            res = obj_type.copy()
            for k in res.keys():
                res[k] = add_obj (obj_1[k],obj_2[k])

            return res 


def getDfWeapons_affect(df_fight):
            df_weapons = pd.DataFrame(columns= row_cols)
            myEffect_weap =  os.path.join(os.path.join(".","data"),"myEffect_weapons.json") 
            with open(myEffect_weap, 'r') as f3:
                effect_weap=json.load(f3)

            for i,w_ids in enumerate(df_fight["WEAPON_ID"].tolist()):
                obj_json_super = obj_recvOrLancr_df.copy()
                obj_json_super["receveur"] = affects_json_affect.copy()
                obj_json_super["lanceur"] = affects_json_affect.copy()
               

                for w_id in w_ids:
                    for effect in effect_weap[str(w_id)] :
                        if str(w_id) in effect_weap.keys() :
                                obj_json_super=add_l_r(obj_json_super,effect)
                if( bool( w_ids) ):
                    for k ,v in obj_json_super.items():
                        rOrL = int(k=="receveur")
                        for k2,v2 in v.items():
                            aOraB = int(k2=="affect")
                            for k3 ,v3 in v2.items():
                                str_stat = k3
                                content = [int(c) for c in "{0:b}".format(v3[0])]
                                for _ in range(len(lst_key)-len(content )):
                                    content = [0]+content
                                df_weapons.loc[len(df_weapons)]=[rOrL,aOraB,str_stat]+content

            return df_weapons



def totalAndAtm (filename_actions):
            with open(filename_actions, 'r') as f:
                data = json.load(f)
                if  isinstance (data,str) :
                    data=json.loads(data)
                used_genes = data["used_genes"]
                history = data["history"]

                lst_key_2=lst_key+["id_entity"]

                df_total_stat = pd.DataFrame(columns= lst_key_2)
                df_atm_stat = pd.DataFrame(columns= lst_key_2)
                for i in range(min(len(history[list(history.keys())[0]]),len(history[list(history.keys())[1]]))):
                    for k in history.keys():
                        df_total_stat.loc[len(df_total_stat)] = history[k][i][1]+[int(k)]
                        df_atm_stat.loc[len(df_atm_stat)]=history[k][i][0]+[int(k)]
            return  df_total_stat,df_atm_stat



def dfToBinary(_df):
            df = _df.copy()
            for col in df.columns[:-1]:
                for i in range(len(df[col])):
                    df[i,col]= int(df[col].loc[i] > 0)
            return df


def writeCorrelation(df_weapons,df_atm_stat,df_total_stat,in_dir ,ids,num_gen,num_fight, ids_col=["ID_GEN","ID_FIGHT","ID_ENTITY"]):
            turns = [i for i in range(len(df_atm_stat[df_atm_stat.columns[0]]))]

            keyss= lst_key[3:]
            dflt_dct_=dict.fromkeys(keyss+ids_col,[0,0])
            correl_path=os.path.join(in_dir , 'correl.csv')
            exist = os.path.isfile(correl_path)
            dfuu = pd.read_csv(correl_path,sep=";",encoding="utf-8")  if exist else pd.DataFrame(columns=list(dflt_dct_.keys()))

            for z in range(2):
                _dct = dflt_dct_.copy()
                df_4 =df_weapons.query("ReceveurOrLanceur=="+str(z)).query('AffectOrAffectBy ==0').query("Stats==\"stat_atm\"")

                for key in keyss :

                    x = df_4[key].tolist()

                    y = list(map(int,df_total_stat[key][:len(x)].tolist()))
                    for i in range(len(x)):
                        if(x[i]==1):
                            _dct[key][1]+=1
                            if(y[i]==1):
                                _dct[key][0]+=1
                _dct["ID_GEN"] =num_gen
                _dct["ID_FIGHT"]=num_fight
                _dct["ID_ENTITY"] = ids["receveur"] if z==1 else ids["lanceur"]
            
                dfuu.loc[len(dfuu)] = list(_dct.values())


            dfuu.to_csv(correl_path,sep=";",index=False)




