# Reinforcement Learning Robot Tondeuse :


## Presentation :
Re-implementation python du robot tondeuse conçu par @thibaultodor , Montpellier .


## Applications Utilisées :
	-	Discord
	-	Trello

## Technologie Utilisées:
	-	Github Copilot
	-	Stable-baseline3 (proposé par @araffin , Munich )
	-	MiniConda

## Setup:
		conda env create --name testEnvName --RL_baseline_configConda.yml

## Utilisation :

### Lancer un entrainement  :

		./src/generateur.py

### Visualiser les statistique d'entrainement  :

		tensorboard --logdir=.\python\logs   

###  Lancer une simulation   :

		./src/again_load.py

                                                           
## Modeles pre-entrainer :

### Version 0  : (Visualisation uniquement)

#### Observation_space  :
	-	SideCell : Case attenante à l'agent 
	-	Pos_agent : Position de l'agent dans l'env courant
	-	Dir_agent : Direction de l'agent 
	-	EnvTh : L'environement théorique 

#### Features  :
	-	Delta : Difference entre l'environement courant et l'environement théorique
	-	ExploreCount : Nombre de case explorer par le robot 
	-	NoChange : Nombre d'étape passer sans changement d'état

### Version 1 : 

#### Observation_space :
	-	SideCell : Case attenante à l'agent 
	-	Pos_agent : Position de l'agent dans l'env courant
	-	Dir_agent : Direction de l'agent 
	-	SubEnvTh : sous-tableau de EnvTh (6x6) centré sur la position de l'agent 
	-	Prev_actions : Les 4 actions précédemments effectuées par l'agent 
      
#### Features  :
	- ... Features v0
	- EuclidDist : Distance euclidienne moyenne entre l'agent et les cases d'interets de subTh (objectifs locaux )






