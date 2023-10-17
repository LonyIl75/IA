class Duo<TypeVal1 , TypeVal2>{

	public TypeVal1 i_valeur;
	public TypeVal2 c_valeur;

	public Duo(TypeVal1 _i , TypeVal2 _c ){
		this.i_valeur=_i;
		this.c_valeur=_c;
	}

	public TypeVal1 getCVal(){
		return this.c_valeur;
	}

	public TypeVal2 getIVal(){
		return this.i_valeur;
	}



	@Override 
	public String toString(){
		String str = ""+i_valeur.toString()+c_valeur.toString();
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
 
        Duo<?,?> _trpl = (Duo<?,?>) o;
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
