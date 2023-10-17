import pandas as pd 
import json
from myutil  import *
import os 
import numpy as np


'''
ReadActions :
    - STATIC :
        - MAP_SIZE : nombre de cases sur la map
        - intervals_ends : fin des intervalles des types d'actions ( e.g Times : 100 ,  Buffs: 200 , ... )
        - intervals_dict : dictionnaire inversé de intervals_dict ( e.g { {START_FIGHT: 0 ,...,USE_WEAPON: 16  , LOST_PT : 100 , ... , NOVA_VITALITY :  112 , ... }  }# le fichier filename_dfActions loader 
        - intervals_dictRev : dictionnaire des types d'actions ( e.g {0:START_FIGHT ,...,16: USE_WEAPON , ... } # le fichier filename_dfActions loader reversed
        - filename_dfActions : nom du fichier json contenant les actions de chaque types d'actions ainsi que la taille des intervalles 
        - ex_idx : liste des index des membres du json  a exclure (e.g [0,2,3]<=> interval_size , Funs , Others )
        - lst_col : liste des colonnes non calculer (directement accessible via l'outcome ) à stocker dans le dataframe
        - id_col : liste des colonnes contenant les ids identifiants les lignes du dataframe
        - deg_col : liste des colonnes contenant les degats infligés ou subis par les entités (parmis les colonnes décritent dans lst_col)
        - audit_col : liste des types d'actions performer pendant le tour (e.g [0,0,1,2,1,1] <=> [df.cols[0] , df.cols[0], df.cols[1] , ...] <=> [Times , Times , Buffs , ...])

    - NON STATIC :
        - numGen : numéro de la génération
        - numFight : numéro du combat
        - beg_notCodeAct_col : index indiquant le début des colonnes qui ne sont pas des liste de codes d'actions
        - entities_ids : les ids réelle des entités (telle que spécifié dans le scénario["entities"][i]["id"] ) impliquées dans le fight du dataframe 
        - int_col_idx_in_lst_col : liste des index des colonnes contenant des ints dans lst_col 

'''
class ReadActions :
    MAP_SIZE = 612 
    intervals_ends = dict()
    intervals_dict = dict()
    intervals_dictRev = dict()  
    filename_dfActions = os.path.join(".","data","df-Actions.json")
    ex_idx = [0,2,3]
    lst_col = ['LOST_LIFE','LIFE','MOVE_TO','WEAPON_DEG','CHIP_DEG','MAPS','WEAPON_ID','CHIP_ID']
    id_col = ['ID_GEN','ID_FIGHT','ID_ENTITY','ID_TURN']
    deg_col = ['LOST_LIFE','LIFE','WEAPON_DEG','CHIP_DEG']
    audit_col = ["ACTIONS"]
    
    #maps = [ [0]*MAP_SIZE ,[0]*MAP_SIZE]
    
    def __init__(self,ids :list ,numGen :int , numFight:int ,path_atm :str ):
        self.numGen = numGen
        self. numFight = numFight
        self.numTurn = 0
        ReadActions.init_intervals(ReadActions.filename_dfActions)#,ReadActions.ex_idx)
        self.nb_type = len(ReadActions.intervals_ends)
        self.lst_col_code = [list(ReadActions.intervals_ends.keys())[i] for i in range(1,len(ReadActions.intervals_ends)) ] 
        self.df = pd.DataFrame(columns=self.lst_col_code+ReadActions.lst_col+ReadActions.id_col+ReadActions.audit_col)
        self.beg_notCodeAct_col = len(self.df.columns) - len(ReadActions.lst_col)-len(ReadActions.id_col)-1
        self.entities_ids = ids 
        self.setIntCol()
        
    def setDF(self,df2):
        if(df2.columns == self.df.columns):
            self.df = pd.concat([self.df , df2])
            
        
    def setIntCol(self):
        self.int_col_idx_in_lst_col = []
        for i,d in enumerate(self.df.columns.tolist()) :
            if d in ReadActions.deg_col :
                self.int_col_idx_in_lst_col+=[i]
        self.int_col_idx_in_lst_col.sort()


    @property
    def getNumGen(self):
        return self.numGen
    
    @property
    def getNumFight(self):
        return self.numFight
    
    @property
    def getNumTurn(self):
        return self.numTurn
    
    @property
    def getDF(self):
        return self.df
    
    @property
    def getEntitiesIds(self):
        return self.entities_ids
    



    @staticmethod
    def emptyIntervalsEnd():
            ReadActions.intervals_ends.update({'BEGIN':0})


    
    @staticmethod
    def init_intervals(filename_json_dfA :str ,ex_idx=[]):
            """
            filename_json_dfA : fichier json contenant les actions de chaque types d'actions ainsi que la taille des intervalles
            ex_idx : liste des index des membres du json  a exclure (e.g [0,2,3]<=> interval_size , Funs , Others )

            Description : initialise les attributs intervals_dict , intervals_dictRev , intervals_ends de la classe ReadActions grâce au fichier json passer en paramètre
            """
            if( isJsonFile( filename_json_dfA ) ):
                ReadActions.emptyIntervalsEnd()
                #Load json file
                with open(filename_json_dfA, 'r') as f:
                    df_actions_json = json.load(f)
                interval_size = df_actions_json["interval_size"]

                #ignore interval_size from dictionnary
                del df_actions_json["interval_size"]

                #ignore ex_idx from dictionnary
                for to_ignore in ex_idx :
                    del df_actions_json[to_ignore]

                #extract info from dict and load it in class attributes 

                idx_keyOf_action = 0
                idx_intcodeOf_action = 1
                for i,group_of_actions in enumerate(df_actions_json.items())  :
                    id_group,lst_actions=group_of_actions
                    
                    for action in lst_actions.items():
                        ReadActions.intervals_dict.update({action[idx_keyOf_action]:action[idx_intcodeOf_action]})

                    ReadActions.intervals_ends.update({id_group:(interval_size+1)*(i+1)})
                ReadActions.intervals_dictRev = reversedict(ReadActions.intervals_dict)
            else :
                errorMessagePyth("file"+filename_json_dfA+" must be a json file")
        
     
    
    @staticmethod
    def whileToken(str_token : str , lst_actions :list ):
        """
        str_token : le string correspondant au token a chercher dans lst_actions ( un token est exprimer sous la forme d'un int dans lst_actions)
        lst_actions : liste d'actions  a parcourir   ( lst_action[i][0] == code_token ) 

        Description : parcours lst_actions jusqu'a ce que le token soit trouvé ou que la fin de la liste soit atteinte 
        Return : l'index de l'action token dans lst_actions tel que ( lst_action[index][0] == code_token )  ou -1 si le token n'a pas été trouvé
        """
        for i in range(len(lst_actions)) :
            action = lst_actions[i]
            if(action[0] == ReadActions.intervals_dict.get(str_token)):
                i+=1
                return i
        return notFound()
    

    @staticmethod        
    def isEndFight(act_token: int):
        return act_token == ReadActions.intervals_dict.get("END_FIGHT")
    
    @staticmethod 
    def isBeginFight(act_token: int):
        return act_token == ReadActions.intervals_dict.get("START_FIGHT")
    
    @staticmethod 
    def isBeginTurn(act_token: int):
        return act_token == ReadActions.intervals_dict.get("NEW_TURN")
    
    @staticmethod 
    def isEndTurn(act_token: int):
        return ReadActions.isEndFight(act_token) or ReadActions.isBeginTurn(act_token ) or act_token == ReadActions.intervals_dict.get("END_TURN")
    

    @staticmethod
    def getDfLstTypeActions():
        return list([[]])
    
    @staticmethod
    def getDfLstAudit():
        return list([[]])
    
    @staticmethod
    def getDfLstInLstCol():
        return [0]
    
    @staticmethod
    def getDfLstNotInLstCol():
        return []
    

    def getNewRow(self):
        lst =[]

        #-----------------Type Actions [Times,Buffs , ... ] -----------------
        idx = 0 
        idx_end = self.beg_notCodeAct_col 
        #Type of actions , e.g Times,Buffs,Funs,Effects , Others 
        for i in range (idx,idx_end):
            lst.append(ReadActions.getDfLstTypeActions())

        t=0
        #----------------- Lst Col -----------------
        idx = idx_end
        idx_end +=len(ReadActions.lst_col)
        for i in range (idx , idx_end):
            if i in self.int_col_idx_in_lst_col[t:] :
                lst.append(ReadActions.getDfLstInLstCol())
                t+=1
            #elif ReadActions.lst_col[i-idx] in ["WEAPON_ID","CHIP_ID"]:
            #    lst.append(notSetId())
            else :
                lst.append(ReadActions.getDfLstNotInLstCol())

        #----------------- Not in Lst Col -----------------
        idx = idx_end
        idx_end += len(ReadActions.id_col)
        for i in range (idx ,  idx_end):
            lst.append(notSetId())

        #----------------- Audit -----------------
        idx = idx_end
        idx_end += len(ReadActions.audit_col)
        for i in range (idx,idx_end):
            lst.append(ReadActions.getDfLstAudit())#actions



        return lst
    
    def idxNextRow(self):
        return self.getCurrentIDX()+1
    
    def addNewRow(self):
        self.df.loc[self.idxNextRow()]=self.getNewRow()
    
        
    def addNewTurn(self,idx_turn: int):
        """
        idx_turn : index du tour a ajouter
        Description : ajoute une nouvelle ligne et set sont indexe de tour à idx_turn
        """
        self.addNewRow()
        #self.numTurn = idx_turn
        self.setColumn ('ID_TURN', idx_turn )
        
    def getCurrentIDX (self):
        return len(self.df)-1

    def setColumn ( self , str_col:str ,val):
        """
        Description : set la colonne str_col de la ligne courante à val
        """
        if ( strColInCols(str_col ,self.df)):
            self.df.loc[self.getCurrentIDX(),str_col]= val 
    

    def addToColumn(self,str_col:str,val,pos:int=0):
        """
        val : valeur a ajouter a la liste de la colonne str_col de la ligne courante
        pos : position de la valeur a ajouter dans la liste de la colonne str_col de la ligne courante (par defaut 0)

        Description : ajoute une valeur a la liste de la colonne str_col de la ligne courante
        """
        if (strColInCols(str_col ,self.df)):
            lst=self.df.loc[self.getCurrentIDX(),str_col]

            if( len(lst )< pos):
                errorMessagePyth(self,"addToColumn" , "index out of range")

            if( not(bool(lst)) or len(lst )== pos):
                lst+=[0]
            
            lst[pos]+= val  

        return pos 
            
            
    def addToColumnList(self,str_col:str,val,pos:int=0):
        """
        Attention : on embedde la valeur dans une liste car la colonne str_col est de type liste 
        """
        if(len(self.df[str_col]) == 0  or not(isinstance(self.df[str_col][0],list))):
            errorMessagePyth(self,"addToColumnList" , "column "+str_col+" must be a list")
            return badInsert()
        else :
            return self.addToColumn(str_col , [val],pos) 

    def extendColum(self,str_col:str,val:list):
        """
        Description : on ajoute une liste a la liste de la colonne str_col de la ligne courante
        """
        if (strColInCols(str_col ,self.df)):
            self.df.loc[self.getCurrentIDX(),str_col].extend(val)
       

    def addToAudit(self , idx:int , idx_of_actionType :int ,   act_token:int  ):
        """
        Description : on ajoute l'index du type de l'action courante dans la colonne audit correspondante
        """
        err_code=self.addToColumnList (  ReadActions.audit_col[idx],idx_of_actionType)
        if(err_code == badInsert()):
            errorMessagePyth(self, "addToCategory" , "bad insert")
            
    def addToGroupAction(self,idx_of_actionType:int , act_token:int):
        err_code = self.addToColumnList (list(ReadActions.intervals_ends.keys())[idx_of_actionType],act_token)
        if(err_code == badInsert()):
            errorMessagePyth(self, "addToCategory" , "bad insert")




    def setIDs(self,entity_id:int):
        """
        Description : set les colonnes IDs de la ligne courante sauf ID_TURN 
        """
        self.setColumn ('ID_GEN',self.numGen)
        self.setColumn ('ID_FIGHT',self. numFight)
        self.setColumn ('ID_ENTITY',self.getEntityID(entity_id))
        #self.setColumn ('ID_TURN',self.idx_turn)
        
    def getEntityID(self ,entity_id:int ):
        """
        Description : retourne l'ID de l'entité entity_id si elle existe sinon retourne outOfRange()
        """
        if len(self.entities_ids) > entity_id :
            return self.entities_ids[entity_id]
        
        else :
            errorMessagePyth(self,"getEntityID" , "index out of range")
            return outOfRange()
        
    @staticmethod
    def getIdxCodeToken():
        return 0
    @staticmethod
    def getIdxEntityToken():
        return 1
    
    @staticmethod
    def getCodeToken(_lst :list):
        return _lst[ReadActions.getIdxCodeToken()]
    @staticmethod
    def getEntityToken(_lst :list):
        return _lst[ReadActions.getIdxEntityToken()]
    
    def getTurn( self,lst_actions :list ,idx_turn :int ):
        entity_id = -1 
        
        # continue jusqu'au token LEEK_TURN
        nbActionRead=ReadActions.whileToken("LEEK_TURN",lst_actions)

        k = nbActionRead

        if(k==notFound()):
            return k , None 
        
        act_token = ReadActions.getCodeToken(lst_actions[k])
        entity_id =  ReadActions.getEntityToken(lst_actions[k-1])
        
        self.addNewTurn(idx_turn)
        self.setIDs(entity_id)

        #si le tour est vide : 
        if(self.isEndTurn(act_token)):
            return k , entity_id 
        
        idx=0
        while k  < len(lst_actions):

            act_token = ReadActions.getCodeToken(lst_actions[k])

            #si le tour est fini : 
            if(self.isEndTurn(act_token) ):
                return k , entity_id
            

            for i in range(1,len(ReadActions.intervals_ends)):
                #idx_group_actions=i
                interval = [list(ReadActions.intervals_ends.values())[i-1],list(ReadActions.intervals_ends.values())[i]]
                if(act_token  in ReadActions.intervals_dictRev and isInInterval(act_token , interval )) :
                    
                    _id = ReadActions.getEntityToken(lst_actions[k])
                    self.addToAudit(ReadActions.audit_col.index("ACTIONS"),i,act_token)
                    self.addToGroupAction(i , act_token)

        

                    if(list(ReadActions.intervals_ends.keys())[i] == "Times"):

                        if(ReadActions.intervals_dictRev.get(act_token) == "MOVE_TO"):
                            lst_mv = lst_actions[k][3]
                            self.extendColum('MOVE_TO',[lst_mv[0],len(lst_mv),lst_mv[-1]])
                            self.extendColum('MAPS',lst_mv)
                            
  
                            
                    elif(list(ReadActions.intervals_ends.keys())[i] == "Buffs"):
                        if(ReadActions.intervals_dictRev.get(act_token) == 'HEAL'):
                            self.addToColumn('LIFE',lst_actions[k][2])
                        elif(ReadActions.intervals_dictRev.get(act_token) in ['LOST_LIFE','NOVA_DAMAGE','DAMAGE_RETURN','LIFE_DAMAGE','POISON_DAMAGE',' AFTEREFFECT']):
                            self.addToColumn('LOST_LIFE',lst_actions[k][2])
                    elif(list(ReadActions.intervals_ends.keys())[i] == "Effects"):
                        if(ReadActions.intervals_dictRev.get(act_token) == 'ADD_WEAPON_EFFECT'):
                            self.addToColumn('WEAPON_DEG',lst_actions[k][5])
                            self.extendColum('WEAPON_ID',[_id])
                        elif(ReadActions.intervals_dictRev.get(act_token) == 'ADD_CHIP_EFFECT'):
                                self.addToColumn('CHIP_DEG',lst_actions[k][5])
                                self.extendColum('CHIP_ID',[_id])
                                
                    
                    
            k+=1
            idx+=1
        return k , entity_id

                            
                    
    def getFight ( self,lst_actions:list ):
        nbReads=ReadActions.whileToken("START_FIGHT",lst_actions)

        k=nbReads

        idx_turn = 0 
        while k < len(lst_actions):
            act_token =ReadActions.getCodeToken(lst_actions[k])
            if(self.isEndFight(act_token )):
                return k 
            nbReads , ent_id = self.getTurn(lst_actions[k:],idx_turn)
            if(nbReads==-1):
                break
            k+=(nbReads+1)
            if(k < len(lst_actions)):
                act_token =ReadActions.getCodeToken(lst_actions[k])
                if(self.isBeginTurn(act_token)):
                    idx_turn +=1
