# Scenario 

Un scenario se composent d'un tableau de farmers qui sont les comptes impliqués dans le combat , un tableau de teams qui sont les différentes équipes de farmer qui s'affrontent , un tableau d'entities qui décrit la composition d'IAs de chaque équipe  .
Une map qui décrit la configuration de la carte et les propriétés random_seed , max_turns et max_operation_per_entity 

# Autres proriétés : 
- random_seed : initialise la carte/obstacle avec cette graine , complète le tableau d'obstacle jusqu'au nombre d'obstacle choisi par leekwars (cf.rand(30,80))
- max_turns : nombre de tour maximum 64 par défaut
- max_operation_per_entity : cout maximum d'un tour d'une IA en op 
  
# Farmer":
{ 
	"id" : {type : "Integer" , "description" : "représente l'id du profil de l'utilisateur leekwars "},
	"name":{type : "String" , "description" : " nom du profil de l'utilisateur leekwars "} ,
	"country" : {type : "String" , "description" : " abréviation du pays ou vie l'utilisateur"} 
}
Méthodes [Entity](https://github.com/leek-wars/leek-wars-generator/blob/b962d31ff8c20146672eaf20ec6a3949ff019c99/src/main/java/com/leekwars/generator/fight/entity/Entity.java#L829 ) associées : 

- getFarmerId();
- getFarmerCountry();
- getFarmerName();
- getFarmer();

src: [code-FarmerInfo](https://github.com/leek-wars/leek-wars-generator/blob/ff84374e901f911652ea8d17b10e34e49d9a96fc/src/main/java/com/leekwars/generator/scenario/FarmerInfo.java)


# Team :
{
	"id" :{type : "Integer" , "description" : "représente l'id de la team sur leekwars"},
	"name" : {type : "String" , "description" : "nom de l'équipe" },
	"level" : null
}

Méthodes [Entity](https://github.com/leek-wars/leek-wars-generator/blob/b962d31ff8c20146672eaf20ec6a3949ff019c99/src/main/java/com/leekwars/generator/fight/entity/Entity.java#L829)  associées : 

- getTeamId();
- getTeamName();
- getTeam();

voir : 
src: [code-Team](https://github.com/leek-wars/leek-wars-generator/blob/e880088fdfce70a758c14fdee2ca2c0fa7543c50/src/main/java/com/leekwars/generator/fight/Team.java) et  [code-TeamInfo](https://github.com/leek-wars/leek-wars-generator/blob/ff84374e901f911652ea8d17b10e34e49d9a96fc/src/main/java/com/leekwars/generator/scenario/TeamInfo.java)


# Entity :

"Entity" :
{
	"id": {type : "Integer" , "description" : "représente l'id du poireau sur leekwars "},
	"ai": "{type : "String" , "description" : "fichier leek contenant l'ai du poireau  "},
	"name": "{type : "String" , "description" : " nom du poireau sur leekwars "} ,
	"type": 1,
	"farmer": {type : "Integer" , "description" : "Farmer.id du farmer associé au poireau "},
	"team":{type : "Integer" , "description" : "Team.id de la team du farmer associé au poireau "},
	"level" : {type : "Integer" , "description" : "level du poireau "},
	"life": {type : "Integer" , "description" : "point de vie du poireau "},
	"strength":  {type : "Integer" , "description" : "point de force du poireau "}
	"tp":  {type : "Integer" , "description" : "point de tour du poireau "},
	"mp":  {type : "Integer" , "description" : "point de magie du poireau "},
	"wisdom":  {type : "Integer" , "description" : "point de sagesse du poireau "},
	"resistance":  {type : "Integer" , "description" : "point de résistance du poireau "},
	"weapons":  {type : "Array_of_Integer" , "description" : "id des armes du poireau "},
	"chips": {type : "Array_of_Integer" , "description" : "id des puces du poireau "}
}

Méthodes [Entity](https://github.com/leek-wars/leek-wars-generator/blob/b962d31ff8c20146672eaf20ec6a3949ff019c99/src/main/java/com/leekwars/generator/fight/entity/Entity.java#L829)  associées : 

getLevel()
getStat(int id)
ou [ getStrength() , getAgility() , ... ,  getRelativeShield(), ... getDamageReturn() , getTotalLife() , getFrequency() , getTotalTP() , getTotalMP()  , getPower() ,getTeam()  , getWeapons() ,... ]
....
( Remarque :
	Tous ce qui est noté m[NomAttribut] est issu du json ,
	 Attention : à ne pas confondre avec les [NomAttribut] qui sont je suppose un état de la partie ,exemple :
				getTotalMP() {
				return getStat(Entity.CHARAC_MP) ; #<=> "mTotalMP" 
				}
				vs 
				getMP() {  
				return getTotalMP() - usedMP ;
				} )
src: [code-Entity](https://github.com/leek-wars/leek-wars-generator/blob/b962d31ff8c20146672eaf20ec6a3949ff019c99/src/main/java/com/leekwars/generator/fight/entity/Entity.java) et [code-EntityInfo](https://github.com/leek-wars/leek-wars-generator/blob/b962d31ff8c20146672eaf20ec6a3949ff019c99/src/main/java/com/leekwars/generator/scenario/EntityInfo.java)


# Map 

"Map" : 
{
"width":  {type : "Integer" , "description" : "moitié de la diagonale x de la map  , par défaut 17 "} ,
"height":  {type : "Integer" , "description" : "moitié de la diagonale y de la map  , par défaut 17 "} ,
"type": 3,
"obstacles": {type : "Array_of_Integer" , "description" : "position 1D des obstacles sur la map "} ,
"random_seed": {type : "Integer" , "description" : "seed utiliser pour la génération de la map"},
"max_turns": {type : "Integer" , "description" : "nombre maximum de tour du combat , par défaut 64"},
"max_operations_per_entity": {type : "Integer" , "description" : "nombre maximum d'opérations pour chaque poireau ,par défaut 20M "}
}
voir : [explication-map] (https://leekwars.com/encyclopedia/fr/Tutoriel%20sur%20la%20carte)
src : [code-Map](https://github.com/leek-wars/leek-wars-generator/blob/b962d31ff8c20146672eaf20ec6a3949ff019c99/src/main/java/com/leekwars/generator/maps/Map.java)


