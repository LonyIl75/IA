class Position {

	public int i,j ;


public Position (){
		this.i=-1;
		this.j=-1;
	}


	public Position (int _i , int _j){
		this.i=_i;
		this.j=_j;
	}

	public Position (int _sq){
		this.i=_sq;
		this.j=_sq;
	}


	public geti(){return i;}
	public getj(){return j;}

	public seti(int _i){this.i =_i;}
	public setj(int _j){this.j= _j;}


	@Override 
	public String toString(){
		String str = "<"+this.i+","+this.j+">";
		return str ;
	}
}
