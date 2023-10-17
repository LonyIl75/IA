class Direction{
	



public Map<WrapperT<Character>,Pair<WrapperT<Character>,WrapperT<Character>>> id_map ;
public Map<WrapperT<Character>,Pair<WrapperT<Character>,WrapperT<Character>>> cara_map ;
public Map<WrapperT<Character>,Pair<WrapperT<Character>,WrapperT<Character>>> name_map ;




private final void put_conv(Triplet<Character> trpl ){
	if(trpl != null /*& */){
		this.id_map.put(trpl.getCVal(),new Pair<Character,String>(trpl.getCVal(),trpl.getName()));
		this.cara_map.put(trpl.getCVal(),new Pair<Integer,String>(trpl.getIVal(),trpl.getName()));
		this.name_map.put(trpl.getName(),new Pair<Integer,Character>(trpl.getIVal(),trpl.getCVal()));
	}
}



public Direction(){
	this.put_conv(new Triplet<Character>(0,'S',"SOUTH"));
	this.put_conv(new Triplet<Character>(1,'N',"NORTH"));

	this.put_conv(new Triplet<Character>(2,'W',"WEST"));
	this.put_conv(new Triplet<Character>(3,'E',"EAST"));
	
	this.put_conv(new Triplet<Character>(4,'O',"NO_DIRECTION"));
	
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


	
	//static methode  : a voir 
	public int defaultCellDir (){
		return this.id_map.get('O');
	}
	public String defaultCellDirStr (){
		return IdToName(defaultCellDir());
	}
	public char defaultCellDirCara (){
		return 'O';
	}

	public int getSizeDir (){
		return this.id_map.size();
	}

	public int getIndexEnv(){
		return CharFileEnv.getIndexEnv()+1;
	}
	public int getIndexPercepts()
	{
	return ObjetPercept.getIndexPercepts()+1;
	}
}
