import re
from enum import Enum
from collections import deque
import gym 
from gym import spaces
from enum import IntEnum
import numpy as np
from scipy.spatial import distance
import math 


class Tile(IntEnum):
    OBSTACLE = 0
    GRASS = 1
    MOWED = 2

class Direction(IntEnum):
    NORTH = 0
    EAST = 1
    SOUTH = 2
    WEST = 3

class Action(IntEnum):
   
    FORWARD = 0
    RIGHT = 1
    LEFT = 2
    BACKWARD = 3
    MOW =4
    EMPTY =  5  
    #PLANT = 6 

def rewardAction(action):
    if action == int(Action.MOW ) :
        return -1 #0.85 sont des cases herbe et 50% sont a  tondre TODO
    elif action == int(Action.LEFT)  :
        return -2
    elif action == int(Action.FORWARD) :
        return -1 
    elif action == int(Action.RIGHT) :
        return -2
    elif action == int(Action.BACKWARD) :
        return -1
    return -10


def openFileAt(deb,path):
    ifstream_obj = open(path)
    ifstream_obj.seek(deb, 0)

    return ifstream_obj






class Position :

    def __init__(self,ls=[0,0]):
        self.pos =ls
    def incr_x(self):
        return 0
    def incr_y(self):
        return 1 
    def toList(self):
        return self.pos
    def getPosX(self):
        return self.pos[self.incr_x()]
    def getPosY(self):
        return self.pos[self.incr_y()]
    def setPosX(self,x):
        self.pos[self.incr_x()]=x
    def setPosY(self,y):
        self.pos[self.incr_y()]=y

    def decPosX(self):
        self.setPosX(self.getPosX()-1)
    def decPosY(self):
        self.setPosY(self.getPosY()-1)

    def incrPosX(self):
        self.setPosX(self.getPosX()+1)
    def incrPosY(self):
        self.setPosY(self.getPosY()+1)

    '''def __sub__(self, other):


    def __add__(self, other):
    

    def __isub__(self, other) :

    def __iadd__(self, other):

    def __eq__(self, other) :'''

    def __str__(self):
        return "("+str(self.getPosX())+","+str(self.getPosY())+")\n"

def notGoodPos():
    return Position([-1,-1]) 

def notGoodIndex():
    return -1

class Grid :
    def cellDefault(self):
        return Tile.OBSTACLE
    def __init__(self,r=0,c=0):
        self.rowCount=r
        self.colCount=c
        self.taille = self.rowCount*self.colCount 
        self.tab=[self.cellDefault()]*self.taille #que obstacle


    def isOutOfBound(self , pos ):
        return (pos.getPosX()<0 or pos.getPosX()>=self.rowCount or pos.getPosY()<0 or pos.getPosY()>=self.colCount)

    def isOutOfBound2(self , index ):
        return (index <0 or index >=self.taille)

    def indexToPos(self , index):
        if(self.isOutOfBound2(index)):
            return notGoodPos()
        return Position([math.floor(index/self.colCount) ,index%self.colCount] ) 

    def posToIndex(self,pos) :
        if(self.isOutOfBound(pos)):
            return notGoodIndex() 
        return pos.getPosX()*self.colCount+pos.getPosY()

    def getPosGrid(self,pos):
        #print(str(pos))
        if(self.isOutOfBound(pos)):
          return Tile.OBSTACLE
        return self.tab[int(self.posToIndex(pos))]


    def setPosGrid(self,pos,obj):
        self.tab[self.posToIndex(pos)]=obj


    def setSize(self,r,c):
        for i in range (c*r-self.taille):
            self.tab.append(self.cellDefault())

        self.colCount=c
        self.rowCount=r
        self.taille = self.colCount*self.rowCount

    def setGrid (self,ifstream_obj):
        deb=0
        cond = True
        taille=0
        while(cond):
            line = ifstream_obj.readline()
            if (";" in line) :
                cond = False 
            X= re.split(',|;',line)#[line];\n
            X.pop()#pop \n
            #print("SetGrid"+str(X))
            '''for i in range (len(X)-self.colCount*self.rowCount):
                self.tab.append(0)

            self.colCount=
            self.rowCount='''
            #print(str(self))
            for i,val in enumerate(X):
                        #print(i,val)
                        if val.isnumeric() :
                            #print("LA"+str(val))
                            self.tab[taille+i]=Tile(int(val))
            taille+=len(X)
                            
            deb+=len(line)+1
        #print("FIN "+str(taille)+"\n\n\n\n\n\n")
        return deb 

    def initGrid(self,deb,path):
        ifstream_obj = openFileAt(deb,path)
        ifstream_obj.seek(deb,0)

        cond = True
        while(cond):
            line = ifstream_obj.readline()
            if (";" in line) :
                cond = False 
            X= re.split(',|;',line)#[line];\n
            X.pop()#pop \n
            j=r=c=0
            #print("X initG" + str(X))
            for word in X:
                if j == 0: # first element
                    r = int(word) # convert to int
                elif j == 1: # second element
                    c = int(word)
                else:
                    break
                j += 1
            deb+=len(line)+1

        self.setSize(r,c)

        deb+=self.setGrid(ifstream_obj)
        ifstream_obj.close()

      
        return deb


    def initGrid2(self,deb, path ):
        ifstream_obj = openFileAt(deb,path)
        deb+=self.setGrid(ifstream_obj)
        ifstream_obj.close()
        return deb



    def outOfBound( self , pos):
        if pos.getPosX()<0 :
            pos.setPosX(0)
        elif pos.getPosX()>=self.rowCount :
            pos.setPosX(self.rowCount-1)

        if pos.getPosY()<0 :
            pos.setPosY(0)

        elif pos.getPosY()>=self.colCount :
             pos.setPosY(self.colCount-1)

        return pos 
         

    def isBorder(self,pos):
        return self.isOutOfBound(pos)

    def isObstacle(self, pos):
            return (self.getPosGrid(pos) == Tile.OBSTACLE)

    def isMowed(self,pos):
            #print("LA"+str(i)+str(j))
        return int(self.getPosGrid(pos)) == int(Tile.MOWED)

    def isWalkable(self,pos) :
        return not self.isBorder(pos) and not self.isObstacle(pos)


    def printDebug(self):
        intToStr = ["X", "□", "⏹"]
        #print(str(self.grid))
        
        for j in range(self.colCount):
            print("____", end="")
        print()
        for i in range(self.rowCount):
            print("| ", end="")
            for j in range(self.colCount):
                    print(intToStr[int(self.getPosGrid(Position([i,j])))] + " | ", end="")
            print()
            for j in range(self.colCount):
                print("____", end="")
            print()
       



    def __str__(self):
        return "ColCount :"+str(self.colCount)+"\n"+"RowCount :"+str(self.rowCount)+"\n \nTab :"+str(self.tab)+"\n"
  

class Environement:
    def __init__(self):
        self.grid = Grid()

        self.agentPos=Position()
        self.agentDir=Direction.NORTH


    def initEnv (self ,path):
        deb=0
        deb+=self.grid.initGrid(deb,path)
        #print("DEB"+str(deb))
        ifstream_obj = openFileAt(deb,path)
        
        j = 0
        cond = True
        while(cond):
            line = ifstream_obj.readline()
            if (";" in line) :
                cond = False 
            X=re.split(',|;',line)
            X.pop()
            #print("initEnv"+str(X))
            for word in X : #[line];\n 
                if j == 0: # first element
                    self.agentPos.setPosX(int(word)) # convert to int
                elif j == 1: # second element
                    self.agentPos.setPosY ( int(word) ) 
                elif j == 2: # thrid element
                    self.agentDir = Direction(int(word))
                else:
                    break
                j += 1

           

        deb+=len(line)+1
        ifstream_obj.close()

        return deb 



    def getAgentPosition(self):
        return self.agentPos

    def getAgentDirection(self):
        return self.agentDir

    def sideCellAgent(self):
        dirA = self.getAgentDirection() 
        posA = self.getAgentPosition().toList()
        forward = posA[:]
        left = posA[:]
        right = posA[:]
        backward=posA[:]

        if  dirA == Direction.NORTH:
            #print("north")
            forward[0] -= 1 # row up
            left[1] -= 1 # row up
            right[1] += 1 # row up
            backward[1]+=1
        elif  dirA == Direction.EAST:
            #print("east")
            forward[1] += 1 # column right
            left[0] -= 1 # row up
            right[0] += 1 # row up
            backward[1]-=1
        elif  dirA == Direction.SOUTH:
            #print("south")
            forward[0] += 1 # row down
            left[1] += 1 # row up
            right[1] -= 1 # row up
            backward[1]-=1
        elif dirA == Direction.WEST: # column left
            #print("west")
            forward[1] -= 1
            left[0] += 1 # row up
            right[0] -= 1 # row up
            backward[1]+=1

        return [int(self.grid.getPosGrid(Position(left))),int(self.grid.getPosGrid(Position(right))),int(self.grid.getPosGrid(Position(forward))),int(self.grid.getPosGrid(Position(backward)))]


    def isBorder(self,pos):
        return self.grid.isBorder(pos)

    def isObstacle(self, pos):
             return self.grid.isObstacle(pos)

    def isWalkable(self,pos) :
        return  self.grid.isWalkable(pos) 

    def isMowed(self,pos):
        return self.grid.isMowed(pos)

    def isOutOfBound(self , pos ):
        return self.grid.isOutOfBound(pos)
         
    def outOfBound( self , pos):
         return self.grid.OutOfBound(pos)


    def forwards(self):
        if self.agentDir == Direction.NORTH:
            self.agentPos.decPosX()
        elif self.agentDir == Direction.EAST:
            self.agentPos.incrPosY()
        elif self.agentDir == Direction.SOUTH:
            self.agentPos.incrPosX()
        elif self.agentDir == Direction.WEST:
            self.agentPos.decPosY()

    def turnRight(self):
        if self.agentDir == Direction.NORTH:
            self.agentDir = Direction.EAST
        elif self.agentDir == Direction.EAST:
            self.agentDir = Direction.SOUTH
        elif self.agentDir == Direction.SOUTH:
            self.agentDir = Direction.WEST
        elif self.agentDir == Direction.WEST:
            self.agentDir = Direction.NORTH

    def turnLeft(self):
        if self.agentDir == Direction.NORTH:
            self.agentDir = Direction.WEST
        elif self.agentDir == Direction.WEST:
            self.agentDir = Direction.SOUTH
        elif self.agentDir == Direction.SOUTH:
            self.agentDir = Direction.EAST
        elif self.agentDir == Direction.EAST:
            self.agentDir = Direction.NORTH

    def backwards(self):
        if self.agentDir == Direction.NORTH:
            self.agentPos.incrPosX()
        elif self.agentDir == Direction.EAST:
            self.agentPos.decPosY()
        elif self.agentDir == Direction.SOUTH:
            self.agentPos.decPosX()
        elif self.agentDir == Direction.WEST:
            self.agentPos.incrPosY()



    def mow(self,pos):
        if(not self.isBorder(pos) or not self.isObstacle(pos) ):
                #print("mow "+str(pos))
            self.grid.setPosGrid(pos,Tile.MOWED)


    def printDebug(self):
        intToStr = ["X", "□", "⏹"]
        #print(str(self.grid))
        
        for j in range(self.grid.colCount):
            print("____", end="")
        print()
        for i in range(self.grid.rowCount):
            print("| ", end="")
            for j in range(self.grid.colCount):
                if i==self.agentPos.getPosX() and j==self.agentPos.getPosY():# agent position
                    if self.agentDir == Direction.NORTH:
                        print(intToStr[int(self.grid.getPosGrid(Position([i,j])))] + "^", end="| ")
                    elif self.agentDir == Direction.EAST:
                        print(intToStr[int(self.grid.getPosGrid(Position([i,j])))] + ">", end="| ")
                    elif self.agentDir == Direction.SOUTH:
                        print(intToStr[int(self.grid.getPosGrid(Position([i,j])))] + "v", end="| ")
                    elif self.agentDir == Direction.WEST:
                        print(intToStr[int(self.grid.getPosGrid(Position([i,j])))] + "<", end="| ")
                else:
                    print(intToStr[int(self.grid.getPosGrid(Position([i,j])))] + " | ", end="")
            print()
            for j in range(self.grid.colCount):
                print("____", end="")
            print()

    def __str__(self):
     return "Env: \n--------  \nGrille: \n"+str(self.grid) +"\n--------\nAGENT : \n"+"Position :"+str(self.agentPos)+"\n"+"Direction :"+str(self.agentDir)+"\n\n--------\n"
  




def vois_getY():
    return 1
def vois_getX():
    return 0

class Agent (object):

    def __init__ (self,env):
        self.SIZE_HISTORY = 100
        self.history=deque(maxlen = self.SIZE_HISTORY)
        for i in range(self.SIZE_HISTORY):
            self.history .appendleft(-1)
        self.env = Environement() 
        self.agentPos = env.getAgentPosition()
        self.agentDir=Direction.NORTH
        self.bumpBorder_cost=rewardAction(Action.FORWARD)*2
        self.historyStat=[0]*len(Action)
        self.voisinage=[6,6] #must be odd #assert
    
    def getPosDir(self):
        return self.agentPos.toList()+[int(self.agentDir)]

    def execAction(self,env,action):

        if action == Action.MOW  :
            env.mow(self.agentPos)#self.agentPos)
        elif action == Action.LEFT  :
            env.turnLeft()#self.agentPos)
        elif action == Action.FORWARD :
            env.forwards()#self.agentPos)
        elif action == Action.RIGHT :
            env.turnRight()#self.agentPos)
        elif action == Action.BACKWARD :
             env.backwards()#self.agentPos)

        #print(str(action))
        return rewardAction(action)


    def unDoAction(self, env ,last_action ):
       
        act= Action.EMPTY 
        #print("undo "+ str(last_action)+" pos "+str(pos))
        if last_action == Action.LEFT :
             act= Action.RIGHT
        elif last_action == Action.RIGHT :
             act= Action.LEFT
        elif last_action == Action.FORWARD  :
             act= Action.BACKWARD
        elif last_action == Action.BACKWARD :
             act= Action.FORWARD

        #print("UNDO",act,"LAST ACTION",last_action)
        return self.execAction(env,act)


    def last_actionH(self):
        for x in self.history :
            if(x!=-1):
             return x 
        return 0

    def step(self, env,envTh,action):

        #print("HISTORY"+str(self.history))
        self.agentPos = env.getAgentPosition()
        reward = 0
        #print("HISTORY :"+str(self.history))
        if env.isBorder(self.agentPos) :
             reward+=self.unDoAction(env,self.last_actionH())
             #reward+=self.bumpBorder_cost

        elif env.isObstacle(self.agentPos) :
            #print("OBSTACLE")
            reward+=self.unDoAction(env,self.last_actionH())
        

        elif not env.isMowed(self.agentPos) and envTh.isMowed(self.agentPos): #TODO a enlever si on veut que le robot apprenne aussi quand effectuer cette action  => self.goodMow
            #print("GOOD")
            reward+=self.execAction(env,Action.MOW)
            #envTh.setPosGrid(pos,Tile.MOWED)

        elif action == Action.MOW: #tond pour rien
            #print("BAD")
            #reward+=self.execAction(env,Action.MOW,pos) # n arrive pas a le comprendre 
            reward+=-1

        if(action != Action.MOW) : #permet de ne pas tondre meme si le sys la decider , enlever pour que sys doit decider 
            reward+=self.execAction(env,action)

       

        #print("ACTION STEP AGENT :"+str(action)+"REWARD"+str(reward))

        self.history.appendleft(int(action))
        self.historyStat[int(action)]+=1

        return reward 


        

class AgentSystem(gym.Env):




    def __init__(self,path ):
        
        
        super(AgentSystem, self).__init__()
    
       
        

        self.initFile = path 

        self.env = Environement() 

        self.setMyEnv(self.initFile)

        self.agent = Agent(self.env )
        

        self.SIZE_PREVIOUSA = int(math.sqrt(self.env.grid.taille))
        self.SIZE_OBS =3 + 2*len(Direction) +self.agent.voisinage[vois_getY()]*self.agent.voisinage[vois_getX()]#+ self.env.grid.taille

        self.delta = self.computeDelta()

        #self.rewardUnit = self.delta/(self.env.grid.taille+1) # div par nb_deplacement_case + mow 

        #print("REWARD UNIT " + str( self.rewardUnit))
        self.prev_delta=self.delta

        self.freq = float(self.delta)/float(self.env.grid.taille)
        
        self.action_space = spaces.Discrete(len(Action)-3)

        self.prev_actions = deque(maxlen = self.SIZE_PREVIOUSA )  
        for i in range(self.SIZE_PREVIOUSA ):
            self.prev_actions.append(int(Action.EMPTY)) # to create history
       
        self.done = False


        self.reward_total = 0
        self.last_reward=0
        self.nochange =0 
        self.coefMowed=self.computeCoefMowed()

        self.caseExplore = Grid(self.env.grid.rowCount,self.env.grid.colCount)
        self.caseExploreCount = 0

        #self.goodMow =0

        info ={}

        self.observation_space = spaces.Box(low=-1,high=500 , shape=(self.SIZE_OBS,),dtype=np.int) #+self.SIZE_PREVIOUSA
    
        

    def initDeltaMowed(self):
        delta=0
        for i,val in enumerate(self.gridEnvTh.tab) :
            #print("i: "+str(i)+"val: "+str(val))
            if val == Tile.MOWED and self.env.grid.tab[i] != Tile.MOWED : #suppose que envTh copie de env 
                delta+=1
        return delta

    def nouvelleCase(self):
        return self.caseExplore.getPosGrid(self.agent.agentPos)==0


    def computeCoefMowed (self):
        coef =0 
        a = self.gridEnvTh.outOfBound(self.agent.agentPos )
        pop=0
        agentX=self.agent.agentPos.getPosX()
        agentY=self.agent.agentPos.getPosY()
        vois_agentX=self.agent.voisinage[vois_getX()]
        vois_agentY=self.agent.voisinage[vois_getY()]
        for x in range(int(agentX-vois_agentX/2),int(agentX+vois_agentX/2)):
            for y in range( int(agentY-vois_agentY/2),int(agentY+vois_agentY/2)):
                b = Position([x,y])
                if (not self.env.isBorder(b) or not self.env.isObstacle(b) ) and self.gridEnvTh.isMowed(b) and not self.env.grid.isMowed(b) :
                    dist= distance.euclidean(a.toList(), b.toList())
                    if(dist !=0) :
                        coef+= 1.0/ dist
                        pop+=1
                    #else :
                    #    coef+=2
        if(pop!=0):
            coef/=pop

        return coef

    def computeDelta(self):
        delta=0
        for i,val in enumerate(self.gridEnvTh.tab) :
            #print(str(self.env.grid.tab))
            #print("i: "+str(i)+" val: "+str(val)+"grid"+str(self.env.grid.tab[i]))
            if val != self.env.grid.tab[i] : #suppose que envTh copie de env 
                delta+=1
        return delta

    def debugPrint(self):
        self.env.printDebug()
        #print(str(self.gridEnvTh))

    def getExploreCount(self):
        return self.caseExploreCount

    def subTH(self):
        a = self.gridEnvTh.outOfBound(self.agent.agentPos )
        pop=0
        agentX=self.agent.agentPos.getPosX()
        agentY=self.agent.agentPos.getPosY()
        vois_agentX=self.agent.voisinage[vois_getX()]
        vois_agentY=self.agent.voisinage[vois_getY()]
        arr =[]
        for x in range(int(agentX-vois_agentX/2),int(agentX+vois_agentX/2)):
            for y in range( int(agentY-vois_agentY/2),int(agentY+vois_agentY/2)):
                b = Position([x,y])
                arr.append(int(self.gridEnvTh.getPosGrid(b)) )
        return arr 

    def step(self,action):

        self.prev_delta=self.delta
        tmp=0
        tmp = self.agent.step(self.env,self.gridEnvTh,action)
        self.subarr= self.subTH() #computeCoefMowed sur SUBTH TODO 
        self.coefMowed=self.computeCoefMowed()
        self.last_reward=tmp
        '''
        if(self.coefMowed !=0):
            self.last_reward=tmp*self.coefMowed
        else:
            self.last_reward=tmp*1
        '''
        #print("ACTION" + str(action)+"LAST " +str(self.last_reward)+"\n\n")
        

        self.prev_actions.append(int(action))

            #if self.done:
                #self.reward = -3*9

        info ={}
        
            #dist nearest grass XOR
        self.delta=self.computeDelta()
        self.freq = float(self.delta)/float(self.env.grid.taille)
        
        if( self.delta-self.prev_delta == 0 ):
            self.nochange+=1
            #self.nochangecost+=self.rewardUnit
        elif ( self.delta-self.prev_delta > 0):
            #self.nochangecost+= 1.0/self.rewardUnit
            self.nochange=0
        else:
            #self.nochangecost =0
            self.nochange=0

        if( not self.env.isBorder(self.agent.agentPos) and self.nouvelleCase()  ):
            self.caseExploreCount+=1
            self.caseExplore.setPosGrid(self.agent.agentPos,1) 
            self.last_reward += self.coefMowed*(self.caseExploreCount+1)#*1.0/self.freq #exploreCount pas sur 
        else :
            tmp=(self.nochange+1)*(self.env.grid.taille/self.caseExploreCount)#*self.rewardUnit#enlever rewardUnit il reste trop souvent sur meme case 
            #if(self.coefMowed !=0):
            self.last_reward-=tmp*(1-self.coefMowed)
            #else:
            #    self.last_reward-=tmp*1
        

        self.last_reward+= (self.prev_delta-self.delta)*2*(self.nochange+1)*(1-self.coefMowed ) #-self.nochange# si il peut mow de lui meme alors : *2 #+int(Math.floor(-self.rewardUnit*self.nochange) ) # 2 car 1 = cout moyen pour mow donc si on a tondu la case on dit qu'on a rentabiliser le trajet (0+1=1) et gagner 1 point bonus (1+1=2) 


        self.reward_total += self.last_reward         
        #print("Delta :"+ str( self.delta) +"\n")

        if(self.delta==0 ):
            self.done=True 

        if(self.nochange >= self.env.grid.taille):
            self.reward_total -= self.delta *(self.env.grid.taille-self.caseExploreCount)+self.similarAction()#*self.nochange
            self.done=True 
       
    
        '''
        if(action == Action.MOW):

            self.gridEnvTh.setPosGrid(self.env.getAgentPosition(),Grid.cellDefault())
            self.deltaMowed-=1
        ''' 
        convert_toTabInt =[]
        for x in self.gridEnvTh.tab :
            convert_toTabInt.append(int(x))

        #if self.nochange ==0:
        #print("voel")
        
       
        observation = list(self.env.sideCellAgent()) +list(self.agent.getPosDir())+ list([self.prev_actions[-i] for i in range(1,len(Direction)+1)])+ self.subarr#+list(convert_toTabInt) #+[ self.delta] +list(posAgent.toPosList()) + list(self.prev_actions) # +list(convert_toTabInt)  #self.goodMow 
      
        observation = np.array(observation)
        
        info = {"caseExploreCount" : self.caseExploreCount , 
        "delta" : self.delta,
        "forward_other":self.agent.historyStat[int(Action.FORWARD)]/sum(self.agent.historyStat[int(Action.FORWARD):]),
        "euclidDist":self.coefMowed,
        "last_reward":self.last_reward,
        "nochange":self.nochange}

        return observation, self.reward_total, self.done, info



    def setMyEnv(self,path):
        self.env = Environement() 
        deb=0
        deb+= self.env.initEnv(path)
        #print(str(self.env)+"\n\n "+str(deb)+"\n")
        
        self.gridEnvTh = Grid(self.env.grid.rowCount,self.env.grid.colCount)
        deb+=  self.gridEnvTh.initGrid2(deb,path) 
        #print("EnvTh:"+str(self.gridEnvTh)+"\n\n "+str(deb)+"\n")
        for i in range(self.env.grid.taille) :
            if self.env.grid.tab[i] == Tile.OBSTACLE or self.gridEnvTh.tab[i] == Tile.OBSTACLE:
                self.gridEnvTh.tab[i]=self.env.grid.tab[i]

    def similarAction(self):
        tab=[0]*len(Action)
        mean=[0]*len(Action)
        size = sum(self.agent.historyStat)-len(self.prev_actions)
        for x in self.prev_actions:
            tab[int(x)]+=1
        for i in range(len(Action)):
            mean[i]=self.agent.historyStat[i]-tab[i]

        mean = [x / size for x in mean]
        result =0
        for i,x in enumerate(tab):
            if x > result:
                result=x*(1-mean[i])
        return result 
        


    def setPath(self,path):
        self.initFile=path 

    def reset(self):
        self.env = Environement() 
                
        #print("EnvTh:"+str(self.gridEnvTh)+"\n\n "+str(deb)+"\n")

        self.setMyEnv(self.initFile)
        self.agent = Agent(self.env )


        self.done = False
        self.nochange =0 
        self.caseExploreCount = 0
        self.delta = self.computeDelta()

        self.prev_delta=self.delta

        self.freq = float(self.delta)/float(self.env.grid.taille)
        
        
        self.action_space = spaces.Discrete(len(Action)-3)#-1 empty et -1 mow 

        self.prev_actions = deque(maxlen = self.SIZE_PREVIOUSA )  # however long we aspire the snake to be
        for i in range(self.SIZE_PREVIOUSA ):
            self.prev_actions.append(int(Action.EMPTY)) # to create history

        
  
    
        self.done = False
        self.caseExplore= Grid(self.env.grid.rowCount,self.env.grid.colCount) #.tab =[0]*self.env.grid.rowCount*self.env.grid.colCount

        self.reward_total = 0
        self.last_reward=0

        #self.goodMow =0

        info ={}

    
        self.subarr= self.subTH() #computeCoefMowed sur SUBTH TODO 
        self.coefMowed=self.computeCoefMowed()
        '''
        if(action == Action.MOW):

            self.gridEnvTh.setPosGrid(self.env.getAgentPosition(),Grid.cellDefault())
            self.deltaMowed-=1
        ''' 
        convert_toTabInt =[]
        for x in self.gridEnvTh.tab :
            convert_toTabInt.append(int(x))
            
        '''
        print(self.agent.getPosDir())
        print(list(self.env.sideCellAgent()))
        print(len(Direction))
        print(list(convert_toTabInt))
        print(list([self.prev_actions[-i] for i in range(1,len(Direction)+1)]))
        print(len(Direction))
        '''
       
        observation = list(self.env.sideCellAgent()) +list(self.agent.getPosDir())+ list([self.prev_actions[-i] for i in range(1,len(Direction)+1)])+ self.subarr#+list(convert_toTabInt)#+[ self.delta]+posAgent.toList()  + list(self.prev_actions) #+list(convert_toTabInt)  #self.goodMow 
        '''
        print(len(convert_toTabInt))
        print(str(self.SIZE_OBS+self.SIZE_PREVIOUSA))
        print(len(observation))
        '''
        observation = np.array(observation)
        '''
        print(observation.shape)
        print(self.observation_space)
        '''
        return observation


        '''
        self.env , end = self.initEnvGrid(0,path)
        #print("Env:"+str(self.env)+"\n\n "+str(end)+"\n")
        self.agent = Agent()
        
        self.gridEnvTh ,end = self.initThEnvGrid(end,path) 
        for i in range(len(self.env.grid)) :
            if self.env.grid.tab[i] == Tile.OBSTACLE or self.gridEnvTh.tab[i] == Tile.OBSTACLE:
                self.gridEnvTh.tab[i]=self.env.grid.tab[i]

        #print("EnvTh:"+str(self.gridEnvTh)+"\n\n "+str(end)+"\n")

        self.deltaMowed = self.initDeltaMowed()
        #print("Delta :"+ str( self.deltaMowed) +"\n")


        self.prev_actions = deque(maxlen = self.SIZE_PREVIOUSA )  # however long we aspire the snake to be
        for i in range(self.SIZE_PREVIOUSA ):
            self.prev_actions.append(int(Action.EMPTY)) # to create history

        self.done = False
        '''



    def __str__(self):
     return str(self.env)+"\n" #agent
