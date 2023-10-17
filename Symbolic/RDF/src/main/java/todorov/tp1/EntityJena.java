package todorov.tp1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract  class EntityJena {
	
	static  String objetURI ; //main object URI 
	HashMap<String , String > proprieties ;
	Set<String> labels ; 
	static final String coRefPattern =Util.URIpattern+Util.delimURI+"[a-zA-Z0-9_]+" ;
	int nbCoRef = 0 ; 
	public EntityJena (String _URI)
	{
		objetURI = _URI ;
		proprieties = null;
		labels = null ;
	}
	public EntityJena ( ArrayList <Pair <String , String >> props , String _URI ) {
		this( _URI) ;
		for (Pair<String,String> e : props ) {
			if (EntityJena.isCoRef(e))add_coRef(e);
			else add_prop(e);
			
		}
		
	}
	public String getURI () {
		return objetURI ; 
	}
	public abstract String getName();
	
	/*
	public void setProps(HashMap<String , String > props ) {
		proprieties = new HashMap<String , String >(props);
		labels = proprieties.keySet();
	}*/
	

	public static boolean isCoRef(Pair<String,String> key_value) {
		Pattern pattern1 = Pattern.compile(coRefPattern );
		Matcher matcher1 = pattern1.matcher(key_value.getValue()) ;
		return matcher1.find();
	}
	public void remove(String key ) {
		if(EntityJena.isCoRef(new Pair<String ,String >(key,proprieties.get(key))))nbCoRef--;
		proprieties.remove(key);
		labels.remove(key);
	}
	public void add_prop(Pair<String,String> key_value ) {
		String key , value ;
		key =objetURI+key_value.getKey(); // File.separator+key_value.getKey();
		value = key_value.getValue(); 
		proprieties.put(key , value);
		labels.add(key);
	}
	public void add_coRef(Pair<String,String> key_value , String otherURI) {
		String key , value ;
		key =key_value.getKey();
		value = otherURI+ Util.delimURI+key_value.getValue();
		add_coRef(new Pair<String,String>(key,value));
		
	}
	
	public void add_coRef(Pair<String,String> key_value ) {
		nbCoRef++;
		add_prop(key_value);
	}
	
	public void clear() {
		proprieties.clear();
		nbCoRef=0;
		labels.clear();
		
	}
	public boolean containsKey( String key ) {
		return labels.contains(objetURI+key);
	}
	public HashMap<String , String > get_proprieties() {
		return proprieties;
	}
	/*public String _getClassName() {
		this.getClass().getSimpleName();
	}*/

}
