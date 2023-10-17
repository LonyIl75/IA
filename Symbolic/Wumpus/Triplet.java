class Triplet<TypeVal>{

	public int i_valeur; //key1
	public TypeVal c_valeur; //value
	public String name;//key2

	public Triplet(int _i , TypeVal _c , String _name){
		this.i_valeur=_i;
		this.c_valeur=_c;
		this.name=_name;
	}

	public TypeVal getCVal(){
		return this.c_valeur;
	}

	public int getIVal(){
		return this.i_valeur;
	}

	public String getName(){
		return this.name;
	}

	@Override 
	public String toString(){
		String str = ""+i_valeur+c_valeur.toString()+name;
		return str ;
	}

	@Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
 
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
 
        Triplet<?> _trpl = (Triplet<?>) o;
        if (this.c_valeur != null ? !this.c_valeur.equals(_trpl.getCVal()) : _trpl.getCVal() != null) {
            return false;
        }
        if(!this.i_valeur.equals(_trpl.getIVal())){
        	return false 
        }
        if(!this.name.equals(_trpl.getName())){
        	return false 
        }
 
        return true;
    }

      @Override
    public int hashCode()
    {
        int result = c_valeur != null ? c_valeur.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0) + i_valeur ;//a verifier
        return result;
    }


 

}
