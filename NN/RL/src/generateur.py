import random
import math
import sys
from AgentSystem import AgentSystem ,Action
import gym 
import time
from stable_baselines3 import PPO
import os
from stable_baselines3.common.env_checker import check_env

from stable_baselines3.common.callbacks import BaseCallback

def openFileAt(deb,path,perm):
    ifstream_obj = open(path, perm)
    if ifstream_obj:
        print("FILE EXIST")
    else:
        print("FILE DOESN'T EXIST")
    ifstream_obj.seek(deb, 0)

    return ifstream_obj


def generateur(path):
	PATH = path
	MIN_ROW_SIZE = 20
	MIN_COL_SIZE = 20

	MAX_ROW_SIZE = 20
	MAX_COL_SIZE = 20

	INDEX_MOWED = 2 #valeur filler dans le second tab 
	INDEX_NOTMOWED=0
	INDEX_TAB2=INDEX_NOTMOWED

	MIN_INDEX_DIR = 0
	MAX_INDEX_DIR = 3

	ID_OBSTACLE=0 #int(Tile.OBSTACLE)

	FIRST_NOTNULL_OBJ =0
	LAST_NOTNULL_OBJ = 1 # GRASS , OBSTACLE ,MOWED 

	ifstream=openFileAt(0,PATH,"w")

	rowCount = random.randint(MIN_ROW_SIZE, MAX_ROW_SIZE)

	colCount = random.randint(MIN_COL_SIZE, MAX_COL_SIZE)

	print("ROWCOUNT : "+str(rowCount)+"COLCOUNT :"+str(colCount)+"\n" )
	beg_delimiter=""
	med_delimiter =","
	end_delimiter=";"

	size_line = beg_delimiter+str(rowCount) +med_delimiter+str(colCount)+end_delimiter+"\n"
	ifstream.write(size_line)

	MAX_SIZE_LIST = sys.maxsize
	allow =[]
	env_line = []
	buffer=""
	i=0
	a=0
	objPerLine = int(101/(2*math.ceil(float(LAST_NOTNULL_OBJ)/10.0))-1)


	for r in range(rowCount):
		for c in range(colCount):
			
			if(random.uniform(0, 1)>0.85):
					env_line.append(0)
			else:
				env_line.append(1)

			#env_line.append(random.randint(FIRST_NOTNULL_OBJ, LAST_NOTNULL_OBJ))
			
			if(env_line[-1]!=ID_OBSTACLE and a < MAX_SIZE_LIST ):
				allow.append([r,c])
				a+=1
			i+=1
			if(i%objPerLine==0):
				buffer=beg_delimiter+med_delimiter.join(map(str,env_line))+"\n"
				env_line.clear()
				ifstream.write(buffer)
				buffer=""
	if(len(env_line)!=0):
		buffer=beg_delimiter+med_delimiter.join(map(str,env_line))+end_delimiter+"\n"
		env_line.clear()
		ifstream.write(buffer)
		buffer=""

	sample_selected=random.randint(0,a-1)
	buffer=beg_delimiter+str(allow[sample_selected][0])+med_delimiter+str(allow[sample_selected][1])+med_delimiter+str(random.randint(MIN_INDEX_DIR , MAX_INDEX_DIR))+end_delimiter+"\n"
	ifstream.write(buffer)
	buffer=""

	env_line = [0]*objPerLine


	i=0
	for r in range(rowCount*colCount):
		if(random.randint(0, 1)) :
			INDEX_TAB2 = INDEX_MOWED
		else:
			INDEX_TAB2 =INDEX_NOTMOWED 
		env_line[r%objPerLine]=INDEX_TAB2
		i+=1
		
		if(i%objPerLine==0):
			buffer=beg_delimiter+med_delimiter.join(map(str,env_line))+"\n"
			env_line= [0]*objPerLine #clear marche aps
			ifstream.write(buffer)
			buffer=""
		

	if(len(env_line)!=0):
		buffer=beg_delimiter+med_delimiter.join(map(str,env_line[0:(rowCount*colCount)%objPerLine]))+end_delimiter+"\n"
		env_line.clear()
		ifstream.write(buffer)
		buffer=""
	ifstream.close()

class TensorboardCallback(BaseCallback):
    def __init__(self, verbose=0):
        super(TensorboardCallback, self).__init__(verbose)

    def _on_step(self)->bool:
        self.logger.record("forward_other", self.training_env.buf_infos[0]["forward_other"])
        self.logger.record("euclidDist", self.training_env.buf_infos[0]["euclidDist"])
        self.logger.record("last_reward", self.training_env.buf_infos[0]["last_reward"])
        self.logger.record("nochange", self.training_env.buf_infos[0]["nochange"])
        self.logger.record("delta", self.training_env.buf_infos[0]["delta"])
        self.logger.record("caseExploreCount", self.training_env.buf_infos[0]["caseExploreCount"])
		
		
		
        return True


def trainer(model,models_dir,nbIterations,algoPolicy):
	


	'''done = False
	obs = env.reset()
	for i in range(nbIterations): #while true nb step max
		print("Iteration", i)
		#s.env.printDebug()
		random_action = s.action_space.sample()
		#print(str(Action(random_action)))
		obs, reward, done, info = s.step(Action(random_action) )
		print("reward",reward)
	s.debugPrint()'''
	TIMESTEPS = nbIterations*10
	for iters in range(int(math.ceil(math.sqrt(nbIterations)))): #fichier par models
		print("LA5")
		model.learn(total_timesteps=TIMESTEPS, reset_num_timesteps=False, tb_log_name=f"PPO",callback=TensorboardCallback()) #algoPolicy
		print("LA5")
		model.save(f"{models_dir}/{TIMESTEPS*iters}")


if __name__ == '__main__':
	nb_sample =10
	PATH_DIR = "../data/grid"
	NB_STEP_MAX = 100

	for i in range(nb_sample): #nb_episode/sample
		completpath=PATH_DIR+str(i)
		
		generateur(completpath)

		models_dir = f"models/{int(time.time())}/"
		logdir = f"logs/{int(time.time())}/"

		if not os.path.exists(models_dir):
			os.makedirs(models_dir)

		if not os.path.exists(logdir):
			os.makedirs(logdir)

		if(i==0):
			s = AgentSystem(completpath)
			check_env(s)

		else:
			s.setPath(completpath)
			s.reset()

		NB_STEP = s.env.grid.rowCount*s.env.grid.rowCount 

		model = PPO('MlpPolicy', s, verbose=1, tensorboard_log=logdir)

	
		trainer(model,models_dir,NB_STEP,"PPO")
		print(completpath)

