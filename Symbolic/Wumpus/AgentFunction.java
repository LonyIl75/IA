class AgentFunction {


private int[] actionTable; //l'indice des actions on peut abstraire car le robot sait ce que c'est 
private  EnvironementTab worldMemory;  //aucune relation = empty  , relation avec mur = getbump 
private short[][] weights ;
private Position [] boundary;// on suppose que c'est un quadrilatère chacune des 4 bounds donnera la fonction constante représentant un des cotés 
private int [] location;
private boolean turning;
private boolean hasChair ;
private boolean travel_check ;//optionnel
private boolean check ;
private boolean remplir ;

private  EnvironementTab plan_th;
private String targetDirection; //"NORTH","SOUTH","EAST","WEST"
private ArrayList<Integer> aVisiter;
// private distGoal; c'est le score de l'agent : player dans le game donc c'est le game qui l'a 
//a chaque handle action si seechair et estBonPlace alors score -- sinon si seechair et non(BonPlace) alors cherchePlace()
private final short cardCoord  ; //cardinalité des  espaces  de coordonnée  
private Random rand;


private int initPresence(){return 0 ;}

private int malPlacePlusProche(String objName){
	switch (objName){
		CASE "CHAIR":
		ObjetPercept.NameToId("CHAIR");
		for(int i =0 ; i< cardCoord ; i++){
			for(j =0 ; j<) // je n'arrive pas a trouver les permutations dans le bon ordre , il faudrait obtenir milieu carré puis 1 voisinage puis 2 voisinage ect
		}


	}
}
public AgentFunction(int rayon_vision , ArrayList<Integer> sz_coords, Position _pos,EnvironementTab _env) //,int nb_objets )
{

	cardCoord = rayon_vision;

	hasChair=false;
	paln_th = new EnvironementTab(_env);
	actionTable = new int[Action.getSizeActionType()];
	actionTable[0] = Action.NameToId("GO_FORWARD");
	actionTable[1] = Action.NameToId("TURN_LEFT");
	actionTable[2] = Action.NameToId("TURN_RIGHT");
	actionTable[3] = Action.NameToId("PICK");
	actionTable[4] = Action.NameToId("DROP");
	actionTable[5] = Action.NameToId("PUT");
	actionTable[6]=Action.NameToId("REPLACE");
	//actionTable[5] = Action.NameToId("NOTHING");


	int nb_objets=ObjetPercept.getSizePerceptsType();
	int volume = pow(cardCoord,sz_coords.size()) ;
	/*worldMemory = new int[volume][nb_objets][nb_objets];
	for (int ijk = 0; ijk < volume; ijk++) {
	for (int z = 0; z < nb_objets; z++) {
		for (int w = 0; w < nb_objets; w++) {
			worldMemory[ijk][z][w] = initPresence();
	}
	}*/
	ArrayList<Integer> tmp3 = new ArrayList<Integer> ();
	for (int i =0 ; i< sz_coords.size() ;i++){
		tmp3.add(cardCoord);
	}
	tmp.add(nb_objets);
	worldMemory = new  EnvironementTab(volume,tmp3);
	weights = new short [nb_objets][nb_objets] ; 
	for (int i =0;i<nb_objets;i++)for (int j =0;j<nb_objets;j++)weights=0;

	aVisiter = new ArrayList<Integer>();

	boundary = new Position[sz_coords.size()];
	for(int i =0 ;i < sz_coords.size();i++){
		boundary[i].seti(-1);//un avant debut 
		boundary[i].setj(cardCoord); // un apres fin
		}



	location = new int[sz_coords.size()]; //milieu du carré 
	for(int i =0 ;i < sz_coords.size();i++){
		location[i]= cardCoord/2;
		}

	turning = false ;

	targetDirection=Direction.defaultCellDirStr();


	visited = new Relation(sz_coords);

	rand = new Random();

}

public int getIndexBound( int id_dir ) throws Exception{
	switch(id_dir){
		case Direction.NameToId("NORTH"):
		case Direction.NameToId("SOUTH"):
			return 0;
		case Direction.NameToId("WEST"):
		case Direction.NameToId("EAST"):
			return 1;
		default :
			throw new Exception("pas implé ");
	}

}
public udeplacement(){return 1;}

public int process ( TransferPercept _tp , AgentEnvironement _envth ){ //enelver env voir todo dans chaiseDevant

		ArrayList<Integer> tmp;
		int direction = _tp.getDirection();
		int indexBound = getIndexBound(direction);
		int sign = (direction%2?-1:1)*udeplacement();

	if(!turning){
		 tmp = new ArrayList<Integer>();
		 for(int i=0;i<location.size();i++)tmp.add(location[i]);
		if(!(worldMemory.getIndexOf(tmp)!=-1) ){
				worldMemory.appToRs(tmp); //todo mettre apres pour ajouter a tmp les percepts 

				if(_tp.getBump() ){
					if(!_tp.getObjetDevant() ){
						boundary[indexBound].seti(location[indexBound]+(sign>0?cardCoord:1);
						boundary[indexBound].setj(location[indexBound]+(sign>0?-1:-cardCoord));
						}
						//objet devant moi TODO updateMemory ? si haschair et table devant => drop 
						location[indexBound]-=sign;
						if(hasChair){
							if(_tp.getTableDevant()) return actionTable[5] ; //PUT TODO GAME decrement counter chaise mal placé
							if(_tp.getChaiseDevant()) return actionTable[6] ;//REPLACE
						}


					}

				if(_tp.getChaiseDevant() ){
					if(!_envth.isIn(tmp,CharFileEnv.NameToId("CHAIR")) return actionTable[3]; //PICK
					updateMemoryPerp(ObjetPercept.NameToId("CHAIR"),true);//decrement counter chaise mal placé : TODO 

				}else{
					updateMemoryPerp(ObjetPercept.NameToId("CHAIR"),false);

				}

				if(_tp.getChair()){
					updateMemory(ObjetPercept.NameToId("CHAIR"),true);

				}else{
					updateMemory(ObjetPercept.NameToId("CHAIR"),false);

				}
				if(_tp.getTable()){
					updateMemory(ObjetPercept.NameToId("TABLE"),true);

				}else{
					updateMemory(ObjetPercept.NameToId("TABLE"),false);

				}
				
		}//si deja visité
		if (aVisiter.isEmpty() ){
			if()
		}
		targetDirection = chooseDirection();
		turning = true;


	}// si turning vrai 

	
	


if(direction == targetDirection)
{
	turning = false;

	location[indexBound]+=sign>0;

	return actionTable[0];
}

else
{
	if(isLeft(direction,targetDirection))
	return actionTable[1];
	else
	return actionTable[2];
}



private void updateMemory(int type, boolean check)
{
	Position location = new Position( )
	if(check == true)//here has that percept
	{
	worldMemory[x][y][type] = 0;
	if (x-1 >= 0) checkAndUpdateRisk(x-1,y,type,check);
	if (x+1 < 7) checkAndUpdateRisk(x+1,y,type,check);
	if (y-1 >= 0) checkAndUpdateRisk(x,y-1,type,check);
	if (y+1 < 7) checkAndUpdateRisk(x,y+1,type,check);
	}
	else//no percept here.. now update safe places..
	{
	worldMemory[x][y][type] = 0;
	if (x-1 >= 0) checkAndUpdateRisk(x-1,y,type,check);
	if (x+1 < 7) checkAndUpdateRisk(x+1,y,type,check);
	if (y-1 >= 0) checkAndUpdateRisk(x,y-1,type,check);
	if (y+1 < 7) checkAndUpdateRisk(x,y+1,type,check);
	}

}


}

