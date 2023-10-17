class Triplet<TypeVal>{

	public int i_valeur;
	public TypeVal c_valeur;
	public String name;

	public Triplet(int _i , TypeVal _c , String _name){
		this.i_valeur=_i;
		this.c_valeur=_c;
		this.name=_name;
	}

	TypeVal getCVal(){
		return this.c_valeur;
	}

	int getIVal(){
		return this.i_valeur;
	}
}
