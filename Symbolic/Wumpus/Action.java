class Action{
	
public final  int nombre_delim =2;

	public Map<WrapperT<Integer>,Pair<WrapperT<Integer>,WrapperT<Integer>>> id_map ;
	public Map<WrapperT<Integer>,Pair<WrapperT<Integer>,WrapperT<Integer>>> name_map ;


private final void put_conv(Triplet<Integer> trpl ){
	if(trpl != null /*& */){
		this.id_map.put(trpl.getCVal(),new Pair<String,Integer>(trpl.getCVal(),trpl.getName()));
		this.name_map.put(trpl.getName(),new Pair<Integer,Character>(trpl.getIVal(),trpl.getCVal()));
	}

}

	public Action(){

	this.put_conv(new Triplet<Character>(0,0,"BEGIN"));
	this.put_conv(new Triplet<Character>(1,1,"GO_FORWARD"));
	this.put_conv(new Triplet<Character>(3,1,"TURN_LEFT"));
	this.put_conv(new Triplet<Character>(2,1,"TURN_RIGHT"));
		//this.put_conv(new Triplet<Character>(2,2,"GO_RIGHT"));
	//this.put_conv(new Triplet<Character>(3,2,"GO_LEFT"));
	//this.put_conv(new Triplet<Character>(4,1,"GO_BACKWARD"));
	this.put_conv(new Triplet<Character>(3,NametoCost("GO_FORWARD")+1,"PICK"));
	this.put_conv(new Triplet<Character>(4,NametoCost("GO_FORWARD")+1,"DROP"));
	this.put_conv(new Triplet<Character>(5,NametoCost("DROP"),"PUT"));
	this.put_conv(new Triplet<Character>(6,NametoCost("DROP")+NametoCost("PICK"),"REPLACE"));
	//this.put_conv(new Triplet<Character>(5,0,"NOTHING"));
	this.put_conv(new Triplet<Character>(7,0',"END"));
	


	
}




public Integer IdtoCost(int _nb){
	return this.id_map.get(_nb).getKey(); 
}
public String IdtoName(int _nb){
	return this.id_map.get(_nb).getValue(); 
}
public Integer NametoId(String _name){
	return this.name_map.get(_name).getKey(); 
}
public Integer NametoCost(String _name){
	return this.name_map.get(_name).getValue(); 
}




public int defaultCellAction (){
		return NametoId("NOTHING") ; 
} 

public int getSizeActionType(){
	return this.id_map.size()-this.nombre_delim-1;
}
public int getNombreDelim(){
	return this.nombre_delim;
}



}


