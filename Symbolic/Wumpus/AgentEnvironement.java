




class AgentEnvironment {
private EnvironementTab tab_env ;
private EnvironementTab tab_percepts ; 
private int taille_envC , taille_envR;

private Agent agent ;
private Position prevAPos ;

private boolean bump ;



public getRtaille(){
	return taille_envR;
}
public getCtaille(){
	return taille_envC;
}
public final int mysquare(int n){
	return pow((2*n+1),2);
}


public AgentEnvironment  ( int _envC , int _envR , EnvironementTab  _env)
{
	this.taille_envC =_envC;
	this.taille_envR=_envR;

	this.tab_env = new EnvironementTab( _env);//+1 pour direction agent


	//int vsquare_size = pow(2*agent. Rvision()+1,2)
	ArrayList<Integer> tmp =ArrayList<Integer> (){
		{
			add(taille_envR);
			add(taille_envC);
			add(CharFileEnv.getSizePerceptsType());
			add(Direction.tailleDir());
		}
	}
	this.tab_percepts = new EnvironementTab( tmp);//[vsquare_size];
	
	setPerceptArray();


}
public borne_min_row (){
	return 0; 
}

public borne_min_col (){
	return 0; 
}
public int getIndexPreceptsPos(){
	return Direction.getIndexPercepts()+1;
}
public ArrayList<Pair<Integer>>perm_couple(int diag){
	ArrayList<Pair<Integer>> couple = new ArrayList<Pair<Integer>>();
	for(int i =-diag;i<diag;i++){
		for(int j=-diag;j<diag;j++){
			couple.add(new Pair(i,j));
		}
	}
	return couple ; 
}
// r1 c2 CHAISE
//r1 c2 CHAISE 
//

public void setPerceptArray() {
	class ObjetPercept obj_env =new ObjetPercept();
	ArrayList<Integer> tmp ,tmp2;
	int taille_perm =0,rayon;
	for (int row = 0; row < this.taille_envR; row++) {
			tmp=ArrayList<Integer> ();
			tmp.add(row);
		for (int col = 0; col < this.taille_envC; col++) {
				tmp.add(col);
				for (int k = 0; k < CharFileEnv.getSizeObjectsType(); k++) {

					if(this.tab_env.isIn(tmp,CharFileEnv.getIndexEnv(),k)){ // TODO  ajouter direction aux objets
									tmp.add(k);
									percepts.addToRs(tmp) ; //si le robot se trouve sur l'objet il capte le precept
									rayon=agent.getRvision();//*ObjetPercept.IdToTaille(k);
									taille_perm=mysquare(rayon);
									tmp2=perm_couple(rayon);
									for(int i =0;i < taille_perm;i++ ){
										if (i!=pow(rayon,2)*2+rayon & col+tmp2[i].getValue() >= borne_min_col () & 
											col+tmp2[i].getValue()< this.taille_envC & 
											row+tmp2[i].getKey()< this.taille_envR &
											row+tmp2[i].getKey() >= borne_min_row () ) {
											tmp= new ArrayList<Integer>(){{add(row+tmp2[i].getKey());add(col+tmp2[i].getValue());}};
											percepts.setRs(tmp,k) ; // une idee serait non pas de set mais d'append ainsi il y aura n relation identique ,n etant le nombre de chaise vue sur la case mais cela mais en peril la definition de MySet = element distinct 
										}
									}
								/*
								if (row+i < this.taille_envR){
									tmp= new ArrayList<Integer>(){{add(row+i);add(col);}};
									percepts.setRs(tmp,k,i) ;  
								} //percepts[row+i][col][k] = 1; 
								if (col+i < this.taille_envC) {
									tmp= new ArrayList<Integer>(){{add(row);add(col+i);}};
									percepts.setRs(tmp,k,i) ; 
								}//percepts[row][col+i][k] = 1;
								if (row-i >= borne_min_row ()) {
									tmp= new ArrayList<Integer>(){{add(row-i);add(col);}};
									percepts.setRs(tmp,k,i) ; 

								}//percepts[row-i][col][k] = 1;
							*/
							
							}
						}
					
				}

		}
	}
}

public void printPercepts() {
	String separation = new String("-----------------------");
	System.out.println(separation);
	ArrayList<Integer> tmp ;
	for (int row = 0; row <  this.taille_envR ; row--) {
			System.out.print("[|");
			tmp.add(row);
			for (int col = 0; col < this.taille_envC; col++) {
				tmp.add(col);
				for (int k = 0; k < ObjetPercept.getSizePerceptsType(); k++) {
					tmp.add(k);
					if(percepts.isIn(tmp,ObjetPercept.getIndexPercepts(),k)){
					//if(this.percepts[row][col][k]!=ObjetPercept.defaultCellPercepts ()){
						System.out.print( " "+ObjetPercept.IdtoName(k) + " |" );
					}
				}
		}
		System.out.println("]");
	}
	System.out.println(separation);

}

public int getTaille_envC() {
	return this.taille_envC;
}


public int getTaille_envR(){
	return this.taille_envR;
}

public int getAgentDirection() throws Exception {
	//int current_direction = Direction.defaultValueDirectionCell;
	ArrayList<Integer> tmp = new ArrayList<Integer>() ;
	int nb = -1;
	for (int row = 0; row < this.taille_envR; row++) {
		tmp.add(row);
	for (int col = 0; col < this.taille_envC; col++) {
		tmp.add(col);
		//current_direction=tab_env[row][col][CharFileEnv.getSizeObjectsType()];
		if((nb=tab_env.getIndexOf(tmp,Direction.getIndexEnv()))!=-1)return nb;
	//if (current_direction != Direction.defaultValueDirectionCell ) 
	}

}
 throw new Exception("pas direction ");
//return Direction.defaultValueDirectionCell; // err ? 
}


public Position getAgentLocation() {
	Position a_pos  = new Position();
	ArrayList<Integer> tmp = new ArrayList<Integer>() ;
		for (int row = 0; row < this.taille_envR; row++) {
			tmp.add(row);
			for (int col = 0; col < this.taille_envC; col++) {
				tmp.add(col);
					//current_direction=tab_env[row][col][CharFileEnv.getSizeObjectsType()];
					if(tab_env.isIn(tmp,Direction.getIndexEnv()))  {
						a_pos.seti(row);
						a_pos.setj(col);
					}
			}
		}
	return a_pos;
}


public void placeAgent(Agent _a) {
		ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(prevAPos.geti());
			add(prevAPos.getj()) ;
			}
			};

		tab_env.clear(tmp,Direction.getIndexEnv());
		//tab_env[prevAPos.geti()][prevAPos.getj()][CharFileEnv.getSizeObjectsType()] = Direction.defaultCellDir ();
		agent = _a;
		tmp=new ArrayList<Integer>( ) {
			{add(agent.getLocation().geti());
			add(agent.getLocation().getj());
			}
			};
		tab_env.addtoRs(tmp,Direction.getIndexEnv(),agent.getAgentDirection()) ; //changement ici 
		//tab_env[agent.getLocation().geti()][agent.getLocation().getj()][CharFileEnv.getSizeObjectsType()] = agent.getAgentDirection(); //changement ici 
		prevAgentPosition = new Position ( agent.getLocation().geti(),agent.getLocation().getj());
}

public void setBump(boolean bumped) {
	bump = bumped;
}

public boolean getBump() {
	return bump;
}


public boolean getChair() {
	ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(agent.getLocation().geti());
			add(agent.getLocation().getj()) ;
			}
			};
	return (tab_percepts.getIndexOf(tmp,ObjetPercept.getIndexPercepts(),NametoId("CHAIR"))!=-1); // chaise sur le dessus du robot

	//return tab_percepts[agent.getLocation().geti()][agent.getLocation().getj()][NametoId("CHAIR")]==1;
}
public boolean getTable() {
ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(agent.getLocation().geti());
			add(agent.getLocation().getj()) ;
			}
			};
	return (tab_percepts.getIndexOf(tmp,ObjetPercept.getIndexPercepts(),NametoId("TABLE"))!=-1);
}

public Position devantRobot(){
Position location = new Position(agent.getLocation());
	switch(Direction.IdtoName(getAgentDirection())){
			case "NORTH":
			case "SOUTH":
			location.seti(agent.getLocation().geti()+(getAgentDirection()%2?-1:1));
			break;

			case "WEST":
			case "EAST":
			location.setj(agent.getLocation().getj()+(getAgentDirection()%2?-1:1));

			case "NO_DIRECTION": // ca pourrait etre interessant d'assigner pour la premiere fois la direction ici 
			default:
			throw new Exception("Aucune direction connu ");


		}
		return location

}
public boolean getObjetDevant() {

	Position location = new Position(devantRobot());

	ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add( location.geti());
			add( location.getj()) ;
			}
			};

	return (tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv())!=-1); // chaise sur le dessus du robot

}

public boolean getChaiseDevant() {

	Position location = new Position(devantRobot());

	ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add( location.geti());
			add( location.getj()) ;
			}
			};

	return (tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv(),NametoId("CHAIR"))!=-1); // chaise sur le dessus du robot

}
public boolean getTableDevant() {

	Position location = new Position(devantRobot());

	ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add( location.geti());
			add( location.getj()) ;
			}
			};

	return (tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv(),NametoId("TABLE"))!=-1); // chaise sur le dessus du robot

}


public boolean getChairPerp() {
ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(agent.getLocation().geti());
			add(agent.getLocation().getj()) ;
			}
			};
	return (tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv(),NametoId("CHAIR"))!=-1);
}


public boolean getTablePerp() {
ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(agent.getLocation().geti());
			add(agent.getLocation().getj()) ;
			}
			};
	return (tab_env.getIndexOf(tmp,CharFileEnv.getIndexEnv(),NametoId("TABLE"))!=-1);
}


public boolean pickChair() {

	ArrayList<Integer> tmp = new ArrayList<Integer>( ) {
			{add(agent.getLocation().geti());
			add(agent.getLocation().getj()) ;
			}
			};

if (tab_percepts.isIn(tmp,ObjetPercept.getIndexPercepts(),NametoId("CHAIR"))) {
tab_percepts.clear(tmp,ObjetPercept.getIndexPercepts(),NametoId("CHAIR"));
//percepts[agent.getLocation()[0]][agent.getLocation()[1]][2] = ' ';
tab_env.clear(tmp,ObjetPercept.getIndexPercepts(),NametoId("CHAIR"));
return true;
}
return false;
}
