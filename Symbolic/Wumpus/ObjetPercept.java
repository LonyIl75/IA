
// tout est hardcodé car on ne changera pas ces valeurs sinon cela veut dire que l'on modélise un objet dont 
//les propriétés ne sont pas d'avoir BEGIN_CELL , ect ce qui n'a pas de sens  







class ObjetPercept{

	// CVal = taille de l'objet dans le decor
	

	public Map<WrapperT<Integer>,Pair<WrapperT<Integer>,WrapperT<Integer>>> id_map ;
	public Map<WrapperT<Integer>,Pair<WrapperT<Integer>,WrapperT<Integer>>> name_map ;


private final void put_conv(Triplet<Integer> trpl ){
	if(trpl != null /*& */){
		this.id_map.put(trpl.getCVal(),new Pair<String,Integer>(trpl.getCVal(),trpl.getName()));
		this.name_map.put(trpl.getName(),new Pair<Integer,Character>(trpl.getIVal(),trpl.getCVal()));
	}

}
public ObjetPercept(){

	this.put_conv(new Triplet<Integer>(0,1,"WALL"));
	this.put_conv( new Triplet<Integer>(1,1,"CHAIR"));
	this.put_conv( new Triplet<Integer>(2,1,"TABLE"));
	//this.put_conv( new Triplet<Integer>(3,1,"EMPTY")); // a voir 

	
}

public static getIndexPercepts(){
	return 2;
}

public Integer IdtoTaille(int _nb){
	return this.id_map.get(_nb).getKey(); 
}
public String IdtoName(int _nb){
	return this.id_map.get(_nb).getValue(); 
}
public Integer NametoId(String _name){
	return this.name_map.get(_name).getKey(); 
}
public Integer NametoTaille(String _name){
	return this.name_map.get(_name).getValue(); 
}



//static methode  : a voir 
public int defaultCellPercepts (){
		return NULL ; 
	}

public int getSizePerceptsType(){
		return this.id_map.size(); // { see_vide,see_chaise , see_table ,mur  }
	}





/*
public int getTailleObj(int obj_id) throws Exception {
		Object obj_res = NULL;
		obj_res = this.convCtoi_tab.get(obj_id);
		if(obj_res==NULL){ throw new Exception("CharFileToInt : Char file non trouvé ");}//,obj_res.toString()); }
		return (int) obj_res ;

	}
*/


}