



class MySet{
	ArrayList<BigInteger> set ;
	int nbobjets ;
	public final int  block_index (int _nb){
		return Math.ceil(_nb/(sizeof(BigInteger)*8));
	}
	public final int column_index (int _nb){
		return _nb%(sizeof(BigInteger)*8);
	}


	public MySet(  ){
		set = new ArrayList<BigInteger>();
	}

	public MySet( int nb_objets  ){
		
		set = new ArrayList<BigInteger>();
		for(int i =0;i<row_index(nb_objets);i++){
			set.add(new BigInteger(0));

		}
		nbobjets=nb_objets;
		
	}

	public MySet( ArrayList<Integer> indexs , int nb_objets  ){
		
		this = new MySet(nb_objets);
		for(int i = 0; i< indexs.size();i++){
			this.AddElement(i);
		}
		nbobjets=nb_objets;
		
	}

	public MySet( MySet s2  ){
		
		set= new ArrayList<BigInteger>(s2.set);
		nbobjets=s2.nbobjets
		
	}
	public int getTaille(){
		return nbobjets;
	}



	public BigInteger getBlock(int _nb) throws Exception  {
		BigInteger row ;
		if((row=set.get(block_index (num_element)))==NULL){ throw new Exception("aps elem");}
		return row ;
	}


	public void AddElement(int num_element){
		int row = block_index(num_element);
		set.set(row,set.get(row).setBit(column_index (num_element))); // if already set change rien 

		
	}

	public boolean isIn(int num_element){
		return  getBlock(num_element).testBit(column_index (num_element)); // if already set change rien 

		
	}

	public eraseBit(int num_element){
		int row = block_index(num_element);
		set.set(row,set.get(row).clearBit(column_index (num_element)));

	}

	@Override 
	public boolean equals(Object o ){


        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof MySet)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        MySet s2 = (MySet) o;
         
        // Compare the data members and return accordingly
 		return this.set.equals(s2.set);


}

public MySet orSet (MySet s2){
	for(int i =0 ; i < s2.set ; i++){
		set[i].or(s2.set[i])
	}

}

public MySet andSet (MySet s2){
	for(int i =0 ; i < s2.set ; i++){
		set[i].and(s2.set[i])
	}

}


public MySet xorSet (MySet s2){
	for(int i =0 ; i < s2.set ; i++){
		set[i].xor(s2.set[i])
	}

}



}

class Relation {
	ArrayList<MySet> sets ;
	//int nb_set ;



	public Relation (){
		sets = new ArrayList<MySet> ();
	}
	public  Relation (Relation r ){
	
		sets=new ArrayList<MySet>(r.sets);
		
		
	}
	public Relation(ArrayList<MySet> tab ){
		sets=new ArrayList<MySet>(tab);

	}

	public  Relation (ArrayList<Integer> tab_taille ){

		for (int i =0 ; i < tab_taille.size();i){
			sets.add(new MySet(tab_taille[i]));
		}
		
		
	}


@Override 
public boolean equals ( Object o ){

	        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof  Relation)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Relation s2 =  (Relation) o;
         
        //for(int i =0 ; i < relation.size();i++) 

        return relation.equals(s2.sets); //peut etre faut specifier le for 


				
}

public boolean isIn(int index, MySet s2){
	return getSet(index).equals(s2);

}
/*

public boolean isIn2(ArrayList<Integer> indexs, ArrayList<Integer> pos){
	
	for( int j =0;j<indexs.size();j++){
	for( int k =0 ; k< pos.size();k++){
					if(!getSet(i).isIn(k)){ 
						return false ;
					}
				}
			}
			return true;

}

public boolean isIn2( ArrayList<Integer> pos){
		ArrayList<Integer> tmp = new ArrayList<integer>();
		for(int i =0;i<pos.size();i++){
			tmp.add(i);
		}
		return isIn2(tmp,pos);

}*/

/*
public boolean isIn(ArrayList<Integer> indexs, ArrayList<ArrayList<Integer>> s2){

	ArrayList<MySet> tmp = new ArrayList<MySet>();
		for(int i =0;i<s2.size();i++){
			tmp.add(new MySet(s2[i]));
		}
		return isIn(indexs,tmp);

}*/

public MySet getSet(int index) throws Exception{

	MySet set ;
	if((set=sets.get(index))==NULL){ throw new Exception("aps elem");}
	return set  ;
	}
public boolean isEmpty(){
	return sets.length==0 ; 
}
public int getTailleSet(int index )throws Exception {
	if(index>= set_taille.size()) {throw new Exception("aps elem");}
	return set_taille[index];
}
public ArrayList<Integer> getTailletab(){
	return this.set_taille ; // a voir comment c'est passé : si besoin new arraylist
}


	public ArrayList<MySet> andSets(ArrayList<MySet> indexs   )
	{
				 ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i < indexs.size();i++){
				 	tmp.add(i);
				 }
				 return andSets(tmp,indexs);

	}



	public ArrayList<MySet> orSets(ArrayList<MySet> indexs   )
	{
				 ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i < indexs.size();i++){
				 	tmp.add(i);
				 }
				 return orSets(tmp,indexs);

	}



public ArrayList<MySet> orSets(ArrayList<Integer> nums_set , ArrayList<MySet> indexs ){
	ArrayList<MySet> tmp = new ArrayList<MySet>();
	for(int i =0 ; i < nums_set.size();i++){
		tmp.add(orSets(nums_set[i],indexs[i]));
	}
	return tmp;
}

public ArrayList<MySet> andSets(ArrayList<Integer> nums_set , ArrayList<MySet> indexs ){
	ArrayList<MySet> tmp = new ArrayList<MySet>();
	for(int i =0 ; i < nums_set.size();i++){
		tmp.add(andSets(nums_set[i],indexs[i]));
	}
	return tmp;
}



public MySet orSets(Integer num_set , MySet indexs ){
		
		return getSet(num_set).orSet( indexs);


}


public void andSets(Integer num_set , MySet indexs ){
		
		getSet(num_set).andSet( indexs);

}


public void setSets(ArrayList<MySet>  _s2){
		sets=new ArrayList<MySet>(_s2);

}

public void setBitInSets(int index , int num ){
		sets.set(index,getSet(index).AddElement(num));

}

public void setBitInSets(ArrayInteger<Integer> index , ArrayInteger<Integer> num ){
		for(int i =0; i< index.size();i++)setBitInSets(index[i],num[i]);

}

public void setBitInSets( ArrayInteger<Integer> num ){
		ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i < indexs.size();i++){
				 	tmp.add(i);
				 }	
		setBitInSets(tmp,num);

}






}




class EnvironementTab{ //relation

	ArrayList<Relation> Relations; //Relations[i]: le i eme element de la relation 
	ArrayList<Integer> relation_taille  ;//= Relations[0].set_taille 



	public EnvironementTab(){
		Relations = new ArrayList<Relation>() ;
		relation_taille = new ArrayList<Integer>() ;
	}
	public EnvironementTab(ArrayList<Integer> tab_taille ){


		this.relation_taille=new ArrayList<Integer>( tab_taille);

	
		//Relations.add(new Relation(tab_taille));
		

		
		}
	public EnvironementTab(int number_elements ,ArrayList<Integer> tab_taille ){


		this.relation_taille=new ArrayList<Integer>( tab_taille);
		for(int i =0 ; i<number_elements ;i++)Relations.add(new Relation(tab_taille));

}
		
	
	public Relation getRelations(int num_relation) throws Exception {
		return Relations  ;
	}
	public boolean isNull( int num_relation ){
		return getRelation(num_relation).equals(new Relation());
	}


	public Relation getRelation(int num_relation) throws Exception {
		Relation relation ;
		if((relation=Relations.get(num_relation))==NULL){ throw new Exception("aps elem");}
		return relation  ;
	}

	public  ArrayList<MySet> convert(ArrayList<Integer> eqsets ){

		ArrayList<MySet> tmp = new ArrayList<MySet>();
		for(int i =0;i<eqsets.size();i++){
			tmp.add(new MySet(eqsets[i]));
		}
		return tmp ; 
	}

	public boolean isIn ( ArrayList<Integer> eqset_index , ArrayList<MySet> eqsets ,int num_set ,int num_element ){
			if(eqset_index.size()==0)return true ;
			for( int i =0;i< Relations.size();i++){
				for(int j =0 ; j<eqset_index.size() ;j++ ){
					if(!(this.getRelation(i).sets.isIn(eqset_index[j],eqsets[j])))break;
				}
				if(num_element<0)return true;
				if(this.getRelation(i).getSet(num_set).isIn(num_element)){//si le num_element eme bit est set return true
						return true ;

			}
			
		}
		return false;
	}

	public boolean isIn (  ArrayList<MySet> eqsets ,int num_set ,int num_element  ){
				ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <  eqsets.size();i++){
				 	tmp.add(i);
				 }
				 return isIn(tmp,eqsets, num_set,num_element);

	}
	public boolean isIn (  ArrayList<Integer> eqsets ,int num_set ,int num_element  ){

		return isIn(convert(eqsets),num_set,num_element);

	} 

		public boolean isIn (  ArrayList<MySet> eqsets ,int num_set   ){
			 return isIn(eqsets, num_set,-1);

	}

	public boolean isIn (  ArrayList<Integer> eqsets ,int num_set   ){
			 return isIn(eqsets, num_set,-1);

	}
/*
	public boolean isIn2( ArrayList<Integer> pos)
	 {
	 	for(int i =0; i < Relations.size();i++){
	 		if(getRelation(i).isIn2(pos))return true;
	 	}
	 	return false ;
	 }*/

	public int getIndexOf( ArrayList<Integer> eqset_index , ArrayList<MySet> eqsets ,int num_set   ){

		for( int i =0;i< Relations.size();i++){
				for(int j =0 ; j<eqset_index.size() ;j++ ){
					if(!(this.getRelation(i).sets.isIn(eqset_index[j],eqsets[j])))break;
				}
				if(num_set<0)return i ; //retourne l'indice de la  premiere relation qui match eqsets
				for( int k =0 ; k< this.getRelation(i).getSet(num_set).size();k++){
					if(this.getRelation(i).getSet(num_set).isIn(k)){ //si un des k eme bit est set le return  
						return k ;
					}

			}
			
		}
		return -1;
	}

	public int getIndexOf (  ArrayList<MySet> eqsets ,int num_set   ){
				ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <  eqsets.size();i++){
				 	tmp.add(i);
				 }
				 return getIndexOf(tmp,eqsets, num_set);

	}

	public int getIndexOf (  ArrayList<Integer> eqsets ,int num_set   ){
		return getIndexOf (convert(eqsets),num_set);
	}

	public int getIndexOf (  ArrayList<MySet> eqsets   ){ //retourne le premier indice qui correspond à eqsets
		return getIndexOf(eqsets,-1);
	}

	public int getIndexOf (  ArrayList<Integer> eqsets   ){
		return getIndexOf(convert(eqsets),-1);
	}


	public Relation removeIndex(ArrayList<MySet> eqsets ) //remove la premier relation qui correspond à eqsets
	{
		return Relations.remove(getIndexOf(eqsets)); // faudrait que ca soit une list d'indexes qui soit retourné par remove mais ce n'est pas le cas dans remove 
	}

	public Relation removeIndex(ArrayList<Integer> eqsets){

		return Relations.remove(getIndexOf(convert(eqsets));
	}

	//getAllIndexOf(ArrayList<Integer> eqsets);
	//removeall (ArrayList<Integer> eqsets)// faudrait que ca soit une list d'indexes qui soit retourné par remove mais ce n'est pas le cas dans remove 
	

	public Relation removeIndex(ArrayList<Integer> eqset_index , ArrayList<MySet> eqsets )
	{
		return Relations.remove(getIndexOf(eqset_index,eqsets));
	}

	public Relation removeIndex(ArrayList<Integer> eqset_index , ArrayList<Integer> eqsets){
		
		return Relations.remove(getIndexOf(eqset_index,convert(eqsets)));
	}

	/*
	public void clear ( ArrayList<MySet> eqsets){ //clear la premiere relation qui correspond à eqsets
			int row =getIndexOf( eqsets );
			Relations.set(row , getRelation(row).getSet(num_set).andSet(new MySet()));
	}

	public void clear ( ArrayList<Integer> eqsets){
			int row =getIndexOf( eqsets );
			Relations.set(row , getRelation(row).getSet(num_set).andSet(new MySet()));
	}*/

	public void clear ( ArrayList<MySet> eqsets, int num_set){
			gint row =getIndexOf( eqsets ,num_set );
			Relations.set(row , getRelation(row).getSet(num_set).andSet(new MySet()));
	}

	public void clear ( ArrayList<Integer> eqsets, int num_set){
			clear(convert(eqsets),num_set);
	}

	public void clear (ArrayList<Integer> eqset_index , ArrayList<Integer> eqsets, int num_set){
			clear(eqset_index,convert(eqsets),num_set);
	}
	public void clear (ArrayList<Integer> eqset_index , ArrayList<MySet> eqsets, int num_set){
			int row =getIndexOf( eqset_index,eqsets ,num_set );
			Relations.set(row , getRelation(row).getSet(num_set).andSet(new MySet()));

	}

	public void clear (ArrayList<Integer> eqset_index , ArrayList<Integer> eqsets, int num_set ,int num_element){
			clear(eqset_index,convert(eqsets),num_set,num_element);

	}

	public void clear (ArrayList<Integer> eqset_index , ArrayList<MySet> eqsets, int num_set ,int num_element){
			int row =getIndexOf( eqset_index,eqsets ,num_set );
			Relations.set(row , getRelation(row).getSet(num_set).eraseBit(num_element);

	}
	public void clear ( ArrayList<Integer> eqsets, int num_set,int num_element){
			clear(convert(eqsets),num_set,num_element);

	}
	public void clear ( ArrayList<MySet> eqsets, int num_set,int num_element){
			int row =getIndexOf( eqsets ,num_set );
			Relations.set(row , getRelation(row).getSet(num_set).eraseBit(num_element);

	}

//TODO : tester si conversion ArrayList<MySet> to ArrayList<Integer> se fait toute seule 
	//MySet peut contenir plusieurs 1 set pas ArrayList<Integer> mais ok pour ArrayList<ArrayList<Integer>> soucis memoire donc couper au plus vite <=> convertir au plustoit en myset

	public boolean isIn (  ArrayList<MySet> eqsets  ,int num_element ){ // TODO : tu fais mal les param optionnel faut les mettres en dernier .A changer PARTOUT aussi en haut  
				ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i < indexs.size();i++){
				 	tmp.add(i);
				 }
				 isIn(tmp,eqsets, tmp.size(),num_element);

	}


	public void setRs( ArrayList<Integer> eqsets , int num_set    )
	{	
			setRs(convert(eqsets),num_set);

	}

	public void setRs(ArrayList<MySet> eqsets , int num_set ){
			ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <sets.size();i++){
				 	tmp.add(i);
				 }
				 setRs(tmp, eqsets ,num_set);

	}	

	public void setRs(ArrayList<Integer> eqset_index , ArrayList<Integer> eqsets , int num_set   )
	{	
			int res =-1;
			if ( (res = getIndexOf(eqset_index,eqsets , num_set ))==-1){
				appToRs(eqset_index.add(num_set),eqsets.add(index));

			}
			else{
				addToRs(res,eqset_index.add(num_set),eqsets.add(index));
			}

	}

	public void setRs( ArrayList<Integer> eqsets , int num_set ,int index   )
	{	
			setRs(convert(eqsets),num_set,index );

	}

	public void setRs(ArrayList<MySet> eqsets , int num_set ,int index){
			ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <sets.size();i++){
				 	tmp.add(i);
				 }
				 setRs(tmp, eqsets ,num_set,index);

	}	

	public void setRs(ArrayList<Integer> eqset_index , ArrayList<MySet> eqsets , int num_set ,int index){
			int res =-1;
			if ( (res = getIndexOf(eqset_index,eqsets , num_set ))==-1){
				appToRs(eqset_index.add(num_set),eqsets.add(index));

			}
			else{
				addToRs(res,eqset_index.add(num_set),eqsets.add(index));
			}
	}

	public void appToRs(ArrayList<Integer> sets   )
	{	
			appToRs(convert(sets));

	}

	public void appToRs(ArrayList<MySet> sets   )
	{
				
				ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <sets.size();i++){
				 	tmp.add(i);
				 }
				appToRs(tmp,sets);

	}
	public void appToRs( ArrayList<Integer> nums_set , ArrayList<MySet> sets )
	{

		Relations.add(new Relation(relation_taille));
		addToRs(Relations.size(),nums_set,sets);

	}




	public void addToRs( int num_relation  , ArrayList<Integer> nums_set , ArrayList<Integer> sets  ) {

		addToRs(num_relation,nums_set,convert(sets));
	}
		public void addToRs( int num_relation  , ArrayList<Integer> nums_set , ArrayList<MySet> sets  ) {

		Relations.set(num_relation,getRelation(num_relation).orSets(nums_set,sets));
	}


	public void addToRs(  int num_relation  ,  ArrayList<Integer> sets ) {
		ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <sets.size();i++){
				 	tmp.add(i);
				 }
		addToRs(num_relation,tmp,sets);
	}

	public void addToRs(  int num_relation  ,  ArrayList<MySet> sets ) {
		ArrayList<Integer> tmp = new ArrayList<Integer>() ;
				 for(int i =0 ; i <sets.size();i++){
				 	tmp.add(i);
				 }
		addToRs(num_relation,tmp,sets);
	}


	public void addToRs( Relation r ) throws Exception {
		ArrayList<Integer> tmp_tab = r.getTailletab(); 
		for( int i =0;i< tmp_tab.size();i++){
				if(tmp_tab[i]!=relation_taille[i]) throw new Exception("aps elem");

		}
		Relations.add(r);
				
	}









}