import plotly.figure_factory as ff
import json 
import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import ast
from pathlib import Path

from myutil  import print_pyth



class Leek:
    path_data = ".\\data"
    def __init__(self,dataOutcomeLeek):
        self.stats = dict()
        outcomeKeys = list(dataOutcomeLeek.keys())
        for enumFile in [enumFile for enumFile in os.listdir(Leek.path_data) if enumFile.startswith('enum')]:
            content = os.path.join(Leek.path_data,enumFile)
            f = open(content, "r")
            data=json.loads(f.read())
            filename = os.path.splitext(enumFile[len('enum'):])[0]
            self.stats.update({filename:[dict() for x in range(max(data["enumIndex"]))]})
            for i in range (len(data["enumIndex"])):
                enumName=data["enumName"][i].lower()
                if(enumName in outcomeKeys):
                    self.stats[filename][data["enumIndex"][i]].update({enumName:dataOutcomeLeek[enumName]})
                

def getLeekStats(filename_outcome_fight):          
    f = open(filename_outcome_fight)
    dataOutcome = json.load(f)
    dataOutcome=json.loads(dataOutcome)["fight"]["leeks"]
    leeks = []
    for idx_leek in range(len(dataOutcome)):
        leek_file = Leek(dataOutcome[idx_leek])
        leeks.append(leek_file)
    return leeks



#convertie un df string en dictionnaire dont la clef est titre de la colonne str_col_id et la valeur la serie de valeur de la colonne
def toSeries(df,lst_dct, str_col_id,filter_val="[0]"):
    grp = df[[str_col_id, "ID_ENTITY","ID_TURN"]].query(str_col_id+"!="+filter_val).groupby("ID_ENTITY")[str_col_id,"ID_TURN"]
    
    for key, values in grp:
        tmp_grp = grp.get_group(key)
        lst_tmp = [0 for i in range(tmp_grp[tmp_grp.columns[0]].size)]
        for id_row , val_row in  tmp_grp.iterrows() :
            lst_tmp[val_row["ID_TURN"]]=ast.literal_eval(val_row[str_col_id])
        if (not key in lst_dct):
            lst_dct.update({key:dict()})

        lst_dct[key].update({str_col_id:lst_tmp})
        
    return lst_dct



#
def getEvolutionStats(df , lst_statNegStat =[["1700","LIFE","" ,"LOST_LIFE"]]):

    lst_evolutionStat = []
    #liste de dictionnaire ayant pour clef les titres dans lst_statNegStat 
    dct=dict()
    for lst in lst_statNegStat:
        for str_col_id in lst[1:]:
            if( bool(str_col_id)):
                dct=toSeries(df,dct,str_col_id)
        lst_evolutionStat.append(dct)

    lst_life = dict()
    #pour tous les dictionnaires de lst_evolutionStat calcule la somme des valeurs associé à chaque colonne 
    for dct in lst_evolutionStat:
        for k in dct.keys(): 
            lst_life.update({k:{}})
            for z in range(len(lst_statNegStat)) :
                lst = lst_statNegStat[z]
                lst_life[k].update({lst[1]:[int(lst[0])]})
                lst=lst[1:]
                sum_lst = lst_life[k][lst[0]]
                for i in range(1,len(dct[k][lst[0]])+1):
                    sum_lst.append(sum_lst[i-1])
                    neg=1
                    for str_col_id in lst:
                            if( not(bool(str_col_id))):
                                neg= -1
                                continue
                            dct_val = dct[k][str_col_id]
                            sum_lst[i] += neg*sum(dct_val[i-1])
    return lst_life





#df.drop(columns=["Unnamed: 0"],inplace=True)

def recap_fight(df ,  filter_lst_val = ["MOVE_TO"] , id_startWith ='ID' , seq_endWith='s' ,  n_key = ["fight_gen_idLeek","dimension", "seq_lens","mean","std","min","max"]):


    id_cols = [col for col in df.columns if col.startswith(id_startWith)]
    feat_cols = [col for col in df.columns if not(col in filter_lst_val  ) and not(id_startWith in col)  and not col.lower().endswith(seq_endWith) ] 

    #print_pyth(feat_cols)
    df2 = df.copy()[id_cols+feat_cols]


    for i in range(len(df2[feat_cols[0]])):
        for c in feat_cols :
            df2[c][i] = pd.to_numeric(ast.literal_eval(df2[c][i]))



    to_column_map = df2.set_index(id_cols)

    grouped = df2.groupby(id_cols[:-1])

    dict_gen = dict()

    obj_json_stats = dict. fromkeys(n_key)

    #.append(dictionary, ignore_index=True)
    n_df = pd.DataFrame(columns=n_key)
    for name, group in grouped:


        dict_val = dict()
        for f in feat_cols :
            dict_val.update({f:group[f].apply(pd.Series).stack().values})

        treshold = 0
        stats = dict()

        for f in dict_val.keys() :
            tmp_obj = obj_json_stats.copy()
            seq_lens = [0]
            for e in dict_val[f]:
                if(e > treshold):
                    seq_lens[-1]+=(1/len(dict_val[f]))
                elif(seq_lens[-1]>0):
                    seq_lens.append(0)

            tmp_obj["seq_lens"] = sum(seq_lens)/len(seq_lens)
            tmp_obj["mean"] = df2[f].mean()[0] if len(df2[f]) > 0 and bool(df2[f].mean() ) else 0 
            tmp_obj["std"] = df2[f].std() if  tmp_obj["mean"] > 0 else 0 
            tmp_obj["min"] = df2[f].min()[0] if tmp_obj["mean"] > 0  else 0 
            tmp_obj["max"] = df2[f].max()[0] if tmp_obj["mean"] > 0 else 0 
            
            tmp_obj["dimension"]=f

            tmp_obj["fight_gen_idLeek"]=name
            n_df = n_df.append(tmp_obj, ignore_index=True)

    return n_df 





def m_flatten(l):
    return [item for sublist in l for item in sublist]


def getlst_statNegStat( leek,str_lst = ["LIFE"]):
    lst_statNegStat =[]
    for str_col_id in str_lst:
        kl = leek.stats 
        t_l = str_col_id.lower()
        for k, e in kl.items():
            dct= e[0]
            if(t_l in  dct):
                    t_l2=t_l.upper()
                    lst_statNegStat.append([str(dct[t_l]),t_l2,"","LOST_"+t_l2])
                    break
    return lst_statNegStat


def getlstValAndLstIdx( df, filename_test_json ):
    leeks=getLeekStats( filename_test_json )
    lst_statNegStat = getlst_statNegStat( leeks[0])  
    lst_life = getEvolutionStats(df,lst_statNegStat)
    return lst_life , lst_statNegStat



def mergeNegStat (lst_life ,cols ,id_="ID_LEEK") :

    df2  = pd.DataFrame(columns=[id_]+cols  )

    for k in lst_life.keys():
        tmp_obj=dict({id_:k})
        tmp_obj.update(lst_life[k])
        df2=pd.concat([df2,pd.DataFrame([tmp_obj])], ignore_index=True)

    df3= pd.DataFrame(columns=[id_]+cols  )
    for col in cols : 
        t= df2.set_index([id_])[col].apply(pd.Series).stack()
        df4=t.to_frame()
        df4.rename(columns={0:col},inplace=True)
        if(col == cols[0]):
            df3=df4
        else:
            df3=df3.join(df4)
        
    return df3

def getColsFromLstStatNegStat(lst_statNegStat):
    return m_flatten([ list(filter(lambda x: x , e[1:2] )) for e in lst_statNegStat ])


def print_Axis(df5,_id="ID_LEEK"):
    key_leek = list(set([e[0] for e in df5.index.values]))
    for i,k in enumerate(key_leek):
        df_o = df5.iloc[df5.index.get_level_values(_id) == k].reset_index(drop=True)
        if(i==0):
            ax = df_o.plot()
        else:
            df_o.plot(ax=ax)
        ax.legend(key_leek)

    return ax



def getCumulStat(df,filename_test_json):
    lst_life , lst_statNegStat  = getlstValAndLstIdx(  df,filename_test_json )
    cols = getColsFromLstStatNegStat(lst_statNegStat)
    df5  = mergeNegStat (lst_life ,cols )
    return df5


def convert_XY_toCoord(XY):
    coef_x = XY//18
    coord_x = XY%18
    sign_x =  0 if coord_x < coef_x else -18
    coef_y = XY//17
    coord_y= XY%17
    sign_y = 0 if coord_y < coef_y  else -18
    
    tmp_x = None
    return (coord_y+sign_y,-(coord_x+sign_x))



def getCoords(df):
    dtr = dict()
    dtr = toSeries(df,dtr, "MAPS")
    coords = dict()
    for k in dtr.keys() :
        dtr_map = dtr[k]["MAPS"]
        coords.update({k:dict()})
        for i in range(len(dtr[k]["MAPS"])):
                if( len(dtr_map[i]) > 1 ):
                    coords[k].update({i:[convert_XY_toCoord(t_coord) for t_coord in dtr_map[i]]})
    return coords




def getMapPlot(df,coords):
    dict_tuple = dict()

    for k1 in coords.keys(): #pour chaque leek 
        dict_tuple.update({k1:[[],[]]})
        coords_1 = coords[k1]
        for k2 in coords_1.keys():#pour chaque tour 
            for coord in coords_1[k2]: # pour chaque position 
                for i , e in enumerate(coord): #pour chaque dimension de la position 
                    dict_tuple[k1][i].append(e)
    return dict_tuple

def dfFromCoordDict(dct, str_coord=["x","y"]):
    df_final = pd.DataFrame()
    #print_pyth(dct)
    for k,v in dct.items():
        df = pd.DataFrame(columns=["ID_LEEK"]+str_coord)
        for i , l in enumerate(v) : 
            df[str_coord[i]] = l
        df["ID_LEEK"] = k
        df_final = pd.concat([df_final,df])
    return df_final.reset_index()


def plot2DMap(df,colors = ['red', 'green', 'blue', 'yellow', 'black', 'purple', 'orange', 'pink', 'brown', 'grey', 'olive', 'cyan']):
    grp = df.groupby(["ID_LEEK"])
    for i,k in enumerate(grp.groups.keys()):
        df_o = grp.get_group(k)
        for x1,y1  in  zip(df_o["x"] ,df_o["y"]) :
                plt.scatter( x1, y1,  c=colors[i], alpha=0.5, edgecolors='none', s=30)
    return plt 




import shutil

class SaveStat :
    
    
        
    def __init__(self,_filename_test_csv,_filename_test_json,outcome_filename):
        if(os.path.isfile(outcome_filename)):
            outcome_filename= os.path.dirname(outcome_filename)

      

        #self.filename = os.path.splitext(outcome_filename)[0]
        self.path_out = outcome_filename

        self.position_map_save = os.path.join(self.path_out,"mapPosition.csv")
        self.stat_save = os.path.join(self.path_out,"stats.csv")
        self.recap_save =  os.path.join(self.path_out,"recaps.csv")
        
        self.filename_test_csv=_filename_test_csv
        self.filename_test_json = _filename_test_json


        #self.init_dir()
        
        


        self.init_dataframe()



        
        


    def init_dir (self):
                
        isExist = os.path.exists(self.path_out)

        if isExist:
            shutil.rmtree(self.path_out)

        os.mkdir(self.path_out)    
        os.mkdir(self.position_map_save)
        os.mkdir(self.stat_save)
    


    def init_dataframe(self):
            self.df = pd.read_csv(self.filename_test_csv, sep=";")      
            self.df.drop(columns=["Unnamed: 0"],inplace=True)
            
            
    def saveMap(self):
        coords = getCoords(self.df)
        dict_tuple = getMapPlot(self.df,coords)
        df78 = dfFromCoordDict(dict_tuple)
        #plt2 = plot2DMap(df78)
        #plt2.savefig( os.path.join(self.position_map_save , self.filename+".png"))
        df78.to_csv( self.position_map_save, encoding="utf-8", sep=";")


    def saveStat(self): #marche pas avec chips +weapons  
        df5 = getCumulStat(self.df , self.filename_test_json)
        #ax= print_Axis(df5)
        df5.to_csv(self.stat_save, encoding="utf-8", sep=";")
        #ax.figure.savefig( os.path.join(self.stat_save , self.filename +".png"))



    def saveRecap(self):
        n_df = recap_fight(self.df)
        n_df.to_csv(self.recap_save, encoding="utf-8", sep=";")


    def writeFiles(self):
        self.saveMap()
        self.saveStat()
        self.saveRecap()




def _fucn( filename_test_csv,filename_test_json ):
	

	SaveStat(filename_test_csv,filename_test_json,filename_test_csv).writeFiles()