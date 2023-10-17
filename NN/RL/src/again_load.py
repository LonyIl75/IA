import random
import math
import sys
from AgentSystem import AgentSystem ,Action ,Direction , Tile
import gym 
import time
from stable_baselines3 import PPO
import os
from stable_baselines3.common.env_checker import check_env

from stable_baselines3.common.callbacks import BaseCallback
from stable_baselines3 import PPO



env = AgentSystem("../data/grid0")
env.debugPrint()
env.reset()

models_dir = "models/PPO"

model_path = f"../models/1670172111/8000.zip"




model = PPO.load(model_path,env=env)
mean_etape =0 
mean_delta=0
episodes=9
pourcentage_delta =0
pourcentage_explorer =0 
detail = True 
print("\n")
print("TAILLE_ENV : " + str(env.env.grid.rowCount*env.env.grid.colCount)+"\n\n")
for ep in range(episodes):
	env = AgentSystem("../data/grid"+str(ep))
	obs=env.reset()
	delta_init= env.delta
	done = False
	x=0 
	while not done :  #obs[0] > 1: #il faut lancer plusieurs robot dans l'env les uns a la suite des autres pour completer la recherche 
		x+=1
		#env.debugPrint()
		action , _ = model.predict(obs)
		obs,reward,done,info   = env.step(Action(action))
		if(detail):
			env.debugPrint()
			print("\nSTEP : "+ str(x)+"\n")
			print("Position Agent : "+str(env.agent.agentPos) )
			print("Delta : "+ str(env.delta))
			print("Total Case Explorer : "+ str(env.caseExploreCount) )
			print("Last_reward : "+ str(env.last_reward)  )
			print("Euclidean distance : "+ str(env.coefMowed)  )
			print("Step without change : "+str(env.nochange))
			print("Previous actions : "+str(list(env.prev_actions)))
			print("Theorical local env : "+str(list(env.subarr)) )
			print("ToMowed case in th_env : " + str(len(list(filter (lambda x : x== int(Tile.MOWED), env.subarr)))))	
			key=input("Press Enter to continue , s to stop ")
			if(key=="s"):
				detail=False
			print("-------------------------------------------------------")


			#env.gridEnvTh.printDebug()

		#print(reward)
		#print("Delta "+ str(obs[0]))
	mean_etape +=x
	mean_delta += env.delta
	pourcentage_delta += env.delta/delta_init
	pourcentage_explorer += env.caseExploreCount/(env.env.grid.rowCount*env.env.grid.colCount)
	print("Instance : "+ str(ep))
	print("NB_ETAPE : " +str(x))
	#env.debugPrint()
	print("Delta : "+ str(env.delta))
	print("Case Explorer : "+ str(env.caseExploreCount) )
	print("TAILLE Instance : " +str(env.env.grid.rowCount*env.env.grid.colCount) )
	print("-------------------------------------------------------")
	'''
	print(env.agent.historyStat)
	#print("Delta "+ str(obs[0]))
	env.gridEnvTh.printDebug()

	'''
print(" **************** RESULTATS ****************")
print("-------------------------------------------------------")
print("MOYENNE NB_ETAPE : " + str(round(mean_etape/episodes,2)))
print("MOYENNE DELTA : " + str(round(mean_delta/episodes,2)))
print("Pourcentage planter : "+str(round(pourcentage_delta/episodes,2)*100)+"%")
print("Pourcentage explorer : "+str(round(pourcentage_explorer/episodes,2)*100)+"%")
print("\n\n ")
env.close()