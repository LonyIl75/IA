package com.leekwars.generator.fight.julienStats.util;



import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.leekwars.generator.fight.julienBdd.weaponPattern;

public class MySet{

	public static int sizeof(String s) { 
		int res = 0;
		if(s.equals("int") || s.equals("Integer")){
			res=4;
		}
		else if(s.equals("long") ){
			res= 8;
		}
		return res*8;
	 } 

	public int[] set ;

	public String toString(){
			return weaponPattern.toStringBinArr(set);
	}
	public JSONArray toJson() {
		JSONArray json = new JSONArray();
		for (int i = 0; i < set.length; i++) {
			json.add(set[i]);
		}
		return json;
	}
	

	public static int  block_index (int _nb){
		return (int) (_nb/sizeof("int"));
	}
	public  static int column_index (int _id){	
		//if(_id==0)return -1;
		return _id%(sizeof("int"));
	}


	public MySet(  ){
		set = new int[0];
	}

	private int getLen(int nb_objets){
		return block_index(nb_objets) +(nb_objets%sizeof("int")>0?1:0 ) ;
	}

	public MySet( int nb_objets  ){
		this();
		if( nb_objets != 0){
		int len = getLen(nb_objets)  ;
		set =  new int[len];
		for(int i =0;i<len;i++){
			set[i]=0;

		}
	}
		
	}
	public static MySet getOne(int nb_objets){
		MySet s = new MySet(nb_objets);
		int len = s.getLen(nb_objets)  ;
		for(int i =0;i<len ;i++){
			s.set[i]=UtilBin.not(s.set[i]);

		}
		return s;

		
	}

	public MySet( int[] _set  ){
		
		this.set =Miscellaneous.cpyArr( this.set,_set);
		
	}

	public MySet( ArrayList<Integer> indexs , int nb_objets  ){
		
		this(nb_objets);
		for(int i = 0; i< indexs.size();i++){
			this.AddElement(indexs.get(i));
		}
		
	}

	public MySet( MySet s2  ){
		
		set= Miscellaneous.cpyArr(set , s2.set);
		
	}

	public static int getInvalidBlock(){
		return -2;
	}
	public int  getBlock(int _nb)   {
		int block_indx = block_index (_nb);
		if(block_indx < set.length)return set[block_indx];
		return getInvalidBlock();
	}

	
	public  ArrayList<Integer> getBitsSet( ){
		ArrayList<Integer> res = new  ArrayList<Integer>();
		ArrayList<Integer> tmp;
		for(int i =0 ; i < set.length ; i++){
			tmp = UtilBin.getBitsSet(set[i]);
			for(int j =0 ; j < tmp.size() ; j++){
				tmp.set(j, tmp.get(j)+i*sizeof("int"));
			}
			res.addAll(tmp);
		}
		return Miscellaneous.reverseArrayList(res);
	}

	public int AddElement(int num_element){
		int row = block_index(num_element);
		if(row+1 > set.length)return 1;
		set[row] = UtilBin.setBit(set[row],column_index (num_element)); // if already set change rien 
		return 0;

		
	}

	public boolean isIn(int num_element){
		int row = getBlock(num_element);
		int column = column_index (num_element);
		if(row == getInvalidBlock() )return false;
		return  UtilBin.testBit(row,column ); // if already set change rien 

		
	}

	public int eraseBit(int num_element){
		int row = block_index(num_element);
		if(row+1 > set.length)return 1;
		set[row] = UtilBin.clearBit(set[row],column_index (num_element));
		return 0;

	}

	@Override 
	public boolean equals(Object o ){

        if (o == this) {
            return true;
        }
        if (!(o instanceof MySet)) {
            return false;
        }
        
        MySet s2 = (MySet) o;
         
		if(s2.set.length != this.set.length)return false;

		for(int i =0 ; i < s2.set.length ; i++){
			if(s2.set[i] != this.set[i])return false;
		}
		return true;
 		//return this.set.equals(s2.set);


}

public static MySet orSet (MySet s1 , MySet s2){
	int[] tmp_set = new int[Math.max(s2.set.length,s1.set.length)];
	for(int i =0 ; i < s2.set.length ; i++){
		tmp_set[i] = UtilBin.orBit( s1.set[i] , s2.set[i] );
	}
	return new MySet(tmp_set);

}
public void orSet ( MySet s2){
	this.set = MySet.orSet (this,s2).set;
}

public static MySet andSet (MySet s1 ,MySet s2){
	int[] tmp_set = new int[Math.max(s2.set.length,s1.set.length)];
	for(int i =0 ; i < s2.set.length ; i++){
		tmp_set[i] = UtilBin.andBit(s1.set[i] , s2.set[i] ) ; 
	}

	return new MySet(tmp_set);
}

public void andSet ( MySet s2){
	this.set = MySet.andSet (this,s2).set;
}

public static MySet xorSet (MySet s1 ,MySet s2){
	int[] tmp_set = new int[Math.max(s2.set.length,s1.set.length)];
	for(int i =0 ; i < s2.set.length ; i++){
		tmp_set[i]  = UtilBin.xorBit( s1.set[i], s2.set[i] );
	}
	return new MySet(tmp_set);

}

public void xorSet ( MySet s2){
	this.set = MySet.xorSet(this,s2).set;
}

}