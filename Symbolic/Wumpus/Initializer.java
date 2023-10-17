class Initializer{

	public int taille_envC , taille_envR;
	public String fichier_inEnv;//l'environement tel qu'il doit être 
	//public String fichier_inEnv2; //a mettre dans game : l'environement tel qu'il est atm 
	public String fichier_outEnv ;

	public void init_use (){
		System.out.println("Utilisation:\n");
		System.out.println("-i1 taille_env (int) ");
		System.out.println("-i2 \"input_env.txt\" (string) ");
		System.out.println("-o1 \"output_env.txt\" (string) ");
	}

	public int parseMainArgs(String args[])
	{
		int arg_read =0;
		for(int i=0;i<args.length;)
			{
				if(args[i].equals("-i1")){
				this.taille_envR=args[i++]; 
				this.taille_envC = args[i++];
				n++;
				}
				else if(args[i].equals("-i2")){ 
						this.fichier_inEnv=args[i++]; n++;
				}
				else if(args[i].equals("-o1")){
					this.fichier_outEnv=args[i++]; n++;
				}
				else{
					System.out.println("argument non reconnu et donc ignoré  ");
					++i;
				}
			}
			return n;
	}


//tableau de 0 et de 1  en fonction de si l'element est présent ou non 
public nb_robot(){
	return 1;
}
	public static EnvironementTab readEnvironement (int taille_row , int taille_col , String fichier_inEnv) throws Exception {
			
			File file_env = new File(fichier_inEnv);
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String fileEv_line = new String();

			
			EnvironementTab resEnv = new EnvironementTab (new ArrayList<Integer>( ) {
			{add(taille_row);
			add(taille_col) ;
			add(CharFileEnv.getSizeObjectsType());
			add(Direction.tailleDir());
			//add(nb_robot()); //si on l'ajoute il faudra faire 1 direction pour chaque objet et chaque robot potentiel
		}
			}
			); 
		/*	
			for (int row = 0; i <  taille_row ; row++) {
			for (int col = 0; col < taille_col  ; col++) {
			for (int k= CharFileEnv.getIndex() = 0; k < CharFileEnv.getSizeObjectsType()  ; k++) { // PAS FAIT : +1 pour la direction
				for (int j= CharFileEnv.getIndex() = 0; j < Direction.tailleDir () ; j++){
					for (int i= CharFileEnv.getIndex() = 0; i < nb_robot() ; i++){
						resEnv[row][col][k][j][i] = false;
				}
			}
			}
		}
		}*/
	
			Character cara = new Character (CharFileEnv.defaultCellId ());
			boolean empty=false;
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for(int row = 0 ;row < this.taille_envR & (fileEv_line  = br.readLine())!= null ; row++)
			{
				tmp.add(row);

				for( int col =0 ; col < this.taille_envC;i++){
					tmp.add(col);
					for( int i =0 ; i < fileEv_line.length();i++){

						cara = fileEv_line[i] ; 

						if(  !(CharFileEnv.CaratoName(cara).equals("BEGIN_CELL")) ){
							System.out.println("Probleme ne commence pas par BEGIN_CELL");
							throw new Exception("readEnvironement : BEGIN_CELL "/*,obj_res.toString()*/);	
							}
							empty=false;
							++i;
							cara = fileEv_line[i];
							for(int k=0 ; CharFileEnv.CaratoName(cara).equals("END_CELL") ;k++)
							{
								if(!(empty=CharFileEnv.CaratoName(cara).equals("EMPTY"))){
								tmp.add(CharFileEnv.CaratoId(cara));
								tmp.add(Direction.defaultCellDir ());
								//resEnv[row][col][CharFileEnv.CaratoId(cara)][Direction.defaultCellDir ()]=true;
								resEnv.appToRs(tmp);
								cara = fileEv_line[++i];
								if((empty=CharFileEnv.CaratoName(cara).equals("EMPTY"))& k>0)System.out.println("Probleme empty et pas vide");
								}
								else{cara = fileEv_line[++i]}
							
							}
							tmp.remove(tmp.size());
							tmp.remove(tmp.size());
						
					}
					tmp.remove(tmp.size());
				}
				tmp.remove(tmp.size());
				}
			
			buff.close();
			return resEnv;
		}
//TODO faire un vrai string qui puisse être utilisé pour re-ecrire le file d'output 
public printEnvironement (int taille_envR , int  taille_envC,EnvironementTab _env ){
	ArrayList<Integer> tmp = new ArrayList<Integer>();
	for(int row = 0 ;row < taille_envR ; row++)
			{
				tmp.add(row);
				System.out.println("********* Ligne : "+row+" **********");
				for( int col =0 ; col < taille_envC;i++){
					tmp.add(col);
					System.out.println("Case :"+col);
					System.out.print("[|");
					for( int i =0 ; i < CharFileEnv.getSizeObjectsType();i++){
						if(resEnv.isIn(tmp,CharFileEnv.getIndexEnv(),i))System.out.print(" "+ CharFileEnv.IdToName(i)+" |");

					}
					tmp.remove(tmp.size());
					System.out.println("]");

}
tmp.remove(tmp.size());
}

}
	





	public static void main(String args[]) throws Exception{

		class Initializer  _init = new Initializer();

		if( _init.parseMainArgs(args) <3 ) {
			init_use();
			return ;
		} 
		EnvironementTab tab_env = readEnvironement(_init.taille_envR,_init.taille_envC,_init.fichier_inEnv);
		printEnvironement(_init.taille_envR,_init.taille_envC,tab_env);


	}
}