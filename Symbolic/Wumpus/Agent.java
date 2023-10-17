class Agent{

	private Position location;
	private int direction;
	//private char agentIcon;
	private boolean hasChair ;
	//private int taille_envC , taille_envR;
	//private boolean isRanger;
	private Environment tab_env;
	private TransfertPercept in_percept;
	private AgentFunction agentFunction;
	private short vision ;

	public int getRvision(){
		return vision ;
	}
	public Agent( Environement _env , TransfertPercept _tpercept ){
			//isRanger=0;
			//nbChair=0;
			//agentIcon
			
			tab_env = new Environement(_env);//theorique 
			in_percept = new TransfertPercept(_tpercept); //realite


			location = tab_env.getAgentLocation();
			direction = tab_env.getAgentDirection();

			agentFunction = new AgentFunction(tab_env.getRtaille(),tab_env.getCtaille(),location,direction);

	}
	/*
	public void setIsRanger(boolean _isRanger) {
		isRanger = _isRanger;
	}
	public boolean getIsRanger() {
		return isRanger;
	}*/

	public Position getLocation(){
		return location;

	}
	/*
	public void setLocation(int i ,int j ){
		location.seti(i);
		location.setj(j)
	}*/

	public int getDirection(){
		return direction;
	}
	public void setDirection(int _d){
		direction=_d;
	}
	public void setHasChair(boolean _b){
		hasChair=_b;
	}
	public boolean getHasChair(){
		return hasChair;
	}
	public String getName(){
		return agentFunction.getAgentName();
	}
	public int chooseAction(){
		return agentFunction.perceptToAction(in_percept);

	}
	public void go() throws Exception{
		Position n_pos = new Position(location);
		switch(Direction.IdtoName(getDirection())){

			case "NORTH":
			case "SOUTH":
			n_pos.seti(n_pos.geti()+(getDirection()%2?-1:1));
			if(n_pos.geti() >= tab_env.getRtaille() ||isObstacle(n_pos) || (getHasChair() & in_percept.getObjetDevant() )) tab_env.setBump(true); //orientation salle : NORTH = vers le bas (avance vertical), EST :droite (avance horizontal), on commence a max West et max Sud
			else location.seti(n_pos);
			break;

			case "WEST":
			case "EAST":
			n_pos.setj(n_pos.getj()+(getDirection()%2?-1:1));
			if(n_pos.getj() >= tab_env.getCtaille() || isObstacle(n_pos) || (getHasChair() & in_percept.getObjetDevant())) tab_env.setBump(true); 
			else location.setj(n_pos);
			break;

			case "NO_DIRECTION": // ca pourrait etre interessant d'assigner pour la premiere fois la direction ici 
			default:
			throw new Exception("Aucune direction connu ");


		}
		
	}

	public boolean isObstacle(Position pos ){ //on suppose que les table est les murs sont fixes dans la salle donc on peut demander au modèle parfait  si y a un mur devant ou une table 
	ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(pos.geti());
			add(pos.getj()) ;
			}
			};
	return (tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv(),NametoId("TABLE"))!=-1)||(tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv(),NametoId("WALL"))!=-1) 

}

public void dropChair(){
	//if(getHasChair()){
		setHasChair(false);
		//return true;
	
	//}else return false;

}

public void turnRight()throws Exception{

	switch(Direction.IdtoName(getDirection())){
		case "NORTH":
			setDirection(NametoId("WEST"));
			break;

			case "EAST":
			setDirection(NametoId("SOUTH"));
			break;

			case "WEST":
			setDirection(NametoId("NORTH"));
			break;

			case "SOUTH":
			setDirection(NametoId("EAST"));	
			break;

			case "NO_DIRECTION": // ca pourrait etre interessant d'assigner pour la premiere fois la direction ici 
			default:
			throw new Exception("Aucune direction connu ");



	}
}

public void turnRight()throws Exception{

	switch(Direction.IdtoName(getDirection())){
		case "NORTH":
			setDirection(NametoId("EAST"));
			break;

			case "EAST":
			setDirection(NametoId("NORTH"));
			break;

			case "WEST":
			setDirection(NametoId("SOUTH"));
			break;

			case "SOUTH":
			setDirection(NametoId("WEST"));	
			break;

			case "NO_DIRECTION": // ca pourrait etre interessant d'assigner pour la premiere fois la direction ici 
			default:
			throw new Exception("Aucune direction connu ");



	}
}









	
}