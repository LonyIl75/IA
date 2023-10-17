
// tout est hardcodé car on ne changera pas ces valeurs sinon cela veut dire que l'on modélise un objet dont 
//les propriétés ne sont pas d'avoir BEGIN_CELL , ect ce qui n'a pas de sens  
class CharFileEnv{
	

public final  int nombre_delim =2 ;

public Map<WrapperT<Character>,Pair<WrapperT<Character>,WrapperT<Character>>> id_map ;
public Map<WrapperT<Character>,Pair<WrapperT<Character>,WrapperT<Character>>> cara_map ;
public Map<WrapperT<Character>,Pair<WrapperT<Character>,WrapperT<Character>>> name_map ;


private final void put_conv(Triplet<Character> trpl ){
	if(trpl != null /*& */){
		this.id_map.put(trpl.getIVal(),new Pair<Character,String>(trpl.getCVal(),trpl.getName()));
		this.cara_map.put(trpl.getCVal(),new Pair<Integer,String>(trpl.getIVal(),trpl.getName()));
		this.name_map.put(trpl.getName(),new Pair<Integer,Character>(trpl.getIVal(),trpl.getCVal()));
	}

}


public CharFileEnv(){

	this.put_conv(new Triplet<Character>(0,' ',"BEGIN_CELL"));
	this.put_conv(new Triplet<Character>(1,'*',"WALL"));
	this.put_conv(new Triplet<Character>(2,'C',"CHAIR"));
	this.put_conv(new Triplet<Character>(3,'T',"TABLE"));
	this.put_conv(new Triplet<Character>(4,'-',"EMPTY"));
	this.put_conv(new Triplet<Character>(5,';',"END_CELL"));

}



public Character IdtoCara(int _nb){
	return this.id_map.get(_nb).getKey(); 
}
public String IdtoName(int _nb){
	return this.id_map.get(_nb).getValue(); 
}
public Integer CaratoId(Character _cara ){
	return this.cara_map.get(_cara).getKey(); 
}
public String CaratoName(Character _cara){
	return this.cara_map.get(_nb).getValue(); 
}
public Integer NametoId(String _name){
	return this.name_map.get(_name).getKey(); 
}
public Character NametoCara(String _name){
	return this.name_map.get(_name).getValue(); 
}


public char defaultCellCara (){
		return NametoCara("EMPTY") ; 
	} 

public int getNombreObjets(){
	return this.id_map.size()-this.nombre_delim-1;
}
public int getNombreDelim(){
	return this.nombre_delim;
}
public int getSizeObjectsType(){
		return getNombreDelim()+getNombreObjets(); // = this.nombre_objets+ this.nombre_delim     { vide,chaise , table ,mur  }
	}

public int getIndexEnv(){
		return 2;
	}




}