package todorov.tp1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.RDFNode;
public class ModelPage {

    private Model model;
    static final private ArrayList<String> df_prefixes = new ArrayList<String>(Arrays.asList("dbt", "dbr" , "dct","dc","rdf","rdfs","dbp","dbo","xsd","dbc","prov","foaf","owl")); //on deja leur url associé dans json
    static final private ArrayList<String> df_ext_kbase = new ArrayList<String>(Arrays.asList("freebase","wikidata","yago-res","yago","mus"));

    private HashMap<String , String > ext_kbase ; //stocke les n et leur url 
    
    
    // verifie si le prefix est dans la base interne ou externe de prefixe DEFAUT 
    public static String df_prefix_toUrl (String _prefix) {
    	String res = null;
    	res=df_prefix1_toUrl (_prefix);
    	if(res == null )res=df_prefix2_toUrl(_prefix);
    	return res ; 
}
    
    
    //resolve un prefixe DEFAUT qui est dans la base interne de prefixe : donne l'url correspondant au prefix 
    public static String df_prefix1_toUrl (String _prefix) { //TODO paratger entre value et entity 
    	
    	String res_url = null ;
    	
    	switch(_prefix) {
    	
    	//DBPEDIA
    	case "dbt" :
    		 res_url = "http://dbpedia.org/resource/Template:";
    		 break;
    	case "dbr" :
    		res_url = "http://dbpedia.org/resource/";
    		break;
    	case "dbp" :
    		res_url = "http://dbpedia.org/property/";
    		break;
    	case "dbo" :
    		res_url = "http://dbpedia.org/ontology/";
    		break;
    	case "dbc" :
    		res_url =	"http://dbpedia.org/resource/Category:";
    		break;
    		
    	case "dct" :
    	case "dc" :
    		res_url = 	"http://purl.org/dc/terms/";
    		break;
    		
    	//W3C
    	case "rdf" :
    		res_url =	"http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    		break;
    	case "rdfs":
    		res_url = "http://www.w3.org/2000/01/rdf-schema#";
    		break;
    	case "xsd" :
    		res_url = 	"http://www.w3.org/2001/XMLSchema#";
    		break;
    	case "owl" :
    		res_url = "http://www.w3.org/2002/07/owl#";
    		break;
    		
    	case "prov" :
    		res_url =	"http://www.w3.org/ns/prov#";
    		break;
    		
    	//XMLNS
    	case "foaf" :
    		res_url = 	"http://xmlns.com/foaf/0.1/";
    		break;
    	
    	}
    	return res_url ; 

    	
    }
    
  //resolve un prefixe DEFAUT qui est dans la base externe de prefixe : donne l'url correspondant au prefix 
    public static String df_prefix2_toUrl (String _prefix) {
    	
    	String res_url = null ;
    	
	switch(_prefix) {
	    	
	    	//DBPEDIA
	    	case "freebase" :
	    		res_url = 	"http://rdf.freebase.com/ns/";
	    		break;
	    	case "wikidata" :
	    		res_url =	"http://www.wikidata.org/entity/";
	    		break;
	    	case "yago-res" :
	    		res_url =	"http://yago-knowledge.org/resource/";
	    		break;
	    	case "yago":
	    		res_url="http://dbpedia.org/class/yago/";
	    		break;
	    	case "mus":
	    		res_url="https://data.doremus.org/ontology/";
	    		break;
	       	
	       	}	
	
		return res_url;
    	
    }
    
    // associe les prefixes DEFAUT de la base interne et externe à leur url 
    private void create_df_prefixs() {
    	
    	String tmp_uri = new String();
    	for (String prf :df_prefixes ) {
    		tmp_uri = df_prefix1_toUrl (prf);
    		setPrefix(prf, tmp_uri);       	
    	}
    	
    	for (String prf :df_ext_kbase ) {
    		tmp_uri = df_prefix2_toUrl (prf);
    		setPrefix(prf, tmp_uri);    
    		
    	}

    }
    
    
    public void add_cst_prefixs(Pair<String,String> elem) {
    	
    	 add_cst_prefixs(elem.getKey(),elem.getValue());
    }
    
    //ajoute un prefixe ( Prefix : <alias , url > ) 
    public void add_cst_prefixs(String key ,String val) {
    	
    	if(model.getNsPrefixURI(key) == null ) {
    		if(Util.isNotBlank(key) &&  Util.isNotBlank(val)  ) {
	    	setPrefix(key,val);
	    	add_to_prefixHashMap(key , val);
    		}
    	}
    	
   }
    
    private void add_to_prefixHashMap(Pair<String,String> elem) {

    	add_to_prefixHashMap(elem.getKey(), elem.getValue());
    	
    }
    
    //ajoute le prefixe aux dictionnaires des prefixes CUSTOM
    private void add_to_prefixHashMap(String key , String val ) {

    	if(Util.isNotBlank(key) &&  Util.isNotBlank(val)  )ext_kbase.put(key, val);
    	
    }
    
    
    
    public void setPrefix(Pair<String , String > name_uri) {
    	setPrefix(name_uri.getKey(),name_uri.getValue());
    }
    
    public void setPrefix(EntityJena _obj) {
    	setPrefix(_obj.getName(),_obj.getURI());
    }
    
    //creer un prefix associer a un url si cette url n'est pas connu le crée 
    public void setPrefix(String name , String _uri) {
    	if(Util.isNotBlank(name) &&  Util.isNotBlank(_uri)  ) {
    	//if() {
    	 if (!containsRessource(_uri)) {
	           model.createResource(_uri);
	        }
    	 
    	 model.setNsPrefix(name, _uri);     // TODO je ne sais pas   
    	}
    	//}
    	
    }
    
    
    public String prefix_toUrl (String _prefix) {
    	
    	return model.getNsPrefixURI(_prefix);
    	
    }
    
    
    //creer un modele avec les prefixes DEFAUTS  set 
    public ModelPage(Model _model) {
    	this.model=_model;
    	create_df_prefixs();
    }
    
    //creer un modele en ajoutant une liste de prefix CUSTOM 
    public ModelPage(Model _model, List<Pair<String, String>> prefixs) {
    	this(_model);
    	String key , value ;
        for (Pair<String, String> s : prefixs) {
        	add_cst_prefixs(s);
        }
        
    }
    
    public boolean containsRessource(EntityJena _obj) {
    	return containsRessource(_obj.getURI());
    }
    public boolean containsRessource(String _URI) {
    	Resource res = model.getResource(_URI) ;
    	boolean b =model.containsResource(res);//contains(,null,(RDFNode) null );
    	return b ;
    }
    
    

    public HashMap<String, String>  objects_by_prefixs ; //< "Person" : "dbr" , ... >  
    //public HashMap<String, String>  props_by_prefixs ; //< "P102" : "dbr" , ... >  
    
    public void add_parent_prefix(String parent , String pref ) {
    	if(objects_by_prefixs!=null) {
    	if(Util.isNotBlank( parent) && Util.isNotBlank( pref))add_ObjectsPrefix(parent , pref);
    	
    	}
    }
    public void add_ObjectsPrefix(Pair<String , String > p ) {
    	add_ObjectsPrefix(p.getKey(),p.getValue());
    }
    
    
    public void add_ObjectsPrefix(String key , String value ) {
    	
    	if(objects_by_prefixs!=null) {
    		 objects_by_prefixs.put(key,value);
    	}
  
    }
    
    
    
    public String getObjectsPrefix(String key) {
    	String res = null ; 
    	if(objects_by_prefixs!=null) {
    		 res = objects_by_prefixs.get(key);
    	}
    	return res ;
    }
    
    public Resource create_entity_bytype(String pref , String obj) {
    	return create_entity2(df_prefix_toUrl(pref),obj);
    }
    
    
    
    //si la clef est vide alors verifie que la value represente un URI complet
    //sinon verifie si la clef est soit dans les prefixes CUSTOM soit DEFAUT 
   // return l'entité créer 
    public Resource create_entity(String key , String value,boolean b ) {
    	
    	String pref = null; 
    	if(Util.isNotBlank(value)){
    	if(Util.isNotBlank(key)) {
    		pref = model.getNsPrefixURI(key);
    		if(pref != null ) {
    			return create_entity2(pref,value);
    		}
    		pref = getObjectsPrefix(key);
    		if (pref!=null) {
    			return create_entity_bytype(pref,value);
    			
    		}
    	}else if(Util.isURI(value)){
    			return create_entity2(key,value);
    		}
    	else if(b){
			return create_entity_value(value);
		}
    	else {
    		System.out.println("ERROR!"+"key: "+key+" value: "+value);
    	}
    	/*else if(Util.isURI(value)){
			add_entity2(key,value);
			return;
		}*/
    	
    	}
    	return null ; 
    	
    	
    	
    }
    public static boolean getIsPossibleValueEnt() {
    	return true;
    }
    public static boolean getIsNotPossibleValueEnt() {
    	return !getIsPossibleValueEnt();
    }
    
    public static boolean df_getPossibleValueEnt() {
    	return getIsPossibleValueEnt();
    }
    
    public Resource create_entity(String key , String value ) {
    	return create_entity( key , value ,ModelPage.df_getPossibleValueEnt() );
    }
    
    public Resource create_entity (Pair<String,String>  p ) {
    	return create_entity( p ,ModelPage.df_getPossibleValueEnt() );
    }
    public Resource create_entity (Pair<String,String>  p ,boolean b) {
    	return  create_entity(p.getKey() ,p.getValue(),b);
    }
   
    public boolean debi = false;
    
    public Property create_prop (Pair<String,String> str_obj) {
    	String key , value ;
    	key = str_obj.getKey();
    	value = str_obj.getValue();
    	return create_prop(key,value);
    }
    
    //si la clef est vide alors verifie que la value represente un URI complet
    //sinon verifie si la clef est soit dans les prefixes CUSTOM soit DEFAUT 
   // return la propriété créer
    public Property create_prop(String key , String value ) {
    	String pref = null; 
    	if(Util.isNotBlank(value)){
    	if(Util.isNotBlank(key)) {
    		pref= model.getNsPrefixURI(key);
    		if(pref !=null) {
    			return create_prop2(pref,value);
    			
    		}
    		
    	}else if(Util.isURI(value)){
			return create_prop2("",value);
		}
    	else if(debi){
			return create_prop2("",value);
		}
    	}
    	return null;
    	
    }
    //creer l'entite si elle n'est pas deja creer
    private Resource create_entity2(String _uri , String value) {
    	Resource res =null ; 
    	String f_uri = new String(_uri + value);
    	if (!containsRessource(f_uri)) {
    	res = model.createResource(f_uri);//erase si il y en avait un avant 
    	}else {
    		res=model.getResource(f_uri);
    	}
    	return res ; 
    }
  //creer la propriete si elle n'est pas deja creer  
  private Property create_prop2(String _uri , String value) {
	  	Property prop =null ; 
    	String f_uri = new String(_uri + value);
    	if (!containsProp(f_uri)) {
    		prop= model.createProperty(f_uri);//erase si il y en avait un avant 
    	}else {
    		prop=model.getProperty(f_uri);
    	}
    	return prop ;
    }
  private Resource create_entity_value(String value ) {
	  Resource res =null ; 
  	//if (!containsProp(f_uri)) {
  	//res= model.createResource(value);//erase si il y en avait un avant 
  	//}
  	return res ;
  }
    
    

    

    public boolean containsProp(Pair<String , String >prop) {
    	return containsProp(prop.getKey());
    }
    public boolean containsProp(String label ) {
    	return  false ; //model.getProperty(label) != null;
    }
    
    public void add_prop( Triplet< String ,String > t_prop) {
    	add_prop(t_prop,false);
    }
    public void add_prop( Triplet< String ,String > t_prop,boolean isValue) {
    	//model.setNsPrefix(_obj.getURI(),_obj.getName());
    	
    
    	String key1  , key2, value ; 
    	key1 = t_prop.getKey1();
    	key2 = t_prop.getKey2();
    	value=t_prop.getValue();
 

    	add_prop(key1,key2,value,isValue);
		
	
       
    	
    }
    public void add_prop(String key1 , String key2 , String value ) {
    	add_prop(key1,key2,value,false);
    }
    //key1 : objet ressource 1 
    //key2 : objet ressource 2 ou value
    //value : label de la prop
    // key1&2 format : (prefix) : (suite uri ) 
    //value format : (prefix) : (suite uri )  ou (uri) 
    //				ou (prefix) : (value_prop) 
    
    public void add_prop(String key1 , String value , String key2 ,boolean isValue) {
    	
    	Resource res1 ,res2 ;
    	res1=res2=null;
    	Property prop =null; 

    	
    	Pattern pattern = Pattern.compile("^(?:(\\w+)\\:)(.+)");
		Matcher matcher ;
		matcher=pattern.matcher(key1) ;
		if(!Util.isURI(key1) && matcher.find()) {
			res1 = create_entity( new Pair<>(matcher.group(1),matcher.group(2)));
		}else  { 
			res1 = create_entity(new Pair<>("",key1),getIsNotPossibleValueEnt());
        }
		if (res1 !=null) {
			//if(!isValue) { //TODO a voir : addprop avec resource 2  == rvalue et non lvalue 
		matcher=pattern.matcher(key2) ;
		if(!Util.isURI(key2) && matcher.find()) {
			res2 = create_entity( new Pair<>(matcher.group(1),matcher.group(2)));
		}else  { 
			res2 = create_entity(new Pair<>("",key2));
        }
		
		matcher=pattern.matcher(value) ;
		if( !Util.isURI(value) && matcher.find()) {
			prop=create_prop( new Pair<>(matcher.group(1),matcher.group(2)));
		}else  { 
			prop=create_prop(new Pair<>("dbp",value));
        }
		
    	 
		if(prop!=null) {
		if(res2==null)res1.addProperty(prop,key2 );
		else res1.addProperty(prop,res2 );
		}
		
		}
		//en cas d'echec :
		if(prop ==null) {
			if(res1!=null)remove_entity(res1);
			if(res2!=null)remove_entity(res2);
		}
		
		
    }
    
    void remove_entity(Resource _res) {
    	model.removeAll(_res, (Property)null, (RDFNode) null);
        model.removeAll(null, null, _res);
    }
    

    
    public void writeModel(String output) throws IOException {
        writeModel(output, "");
    }
    
    
    
    public void writeModel(String output, String format) throws IOException {
    	final String path = new String (Util.concatNameFile("src","main","resources"));
    	String extension = new String();
        switch (format) {
            case "":
            	extension = new String(".xml");
                break;
            case "N-TRIPLE":
            	extension = new String(".nt");
                break;
            case "TURTLE":
            	extension = new String(".ttl");
                break;
            default:
                System.err.println("Voici les seuls formats disponible : N-TRIPLE, TURTLE, méthode sans argument pour RDF/XML");
        }
        File dirs = new File(path);
        if (!dirs.exists()) {
        	 dirs.mkdirs();
        }
        File file = new File(Util.concatNameFile(path,output+extension));
        FileWriter fw = new FileWriter( file );
        try {
            model.write(fw, format);
        } finally {
            try {
            	fw.close();
            } catch (IOException e) {
                //ignore
            }
        
    }


}
    
    
    public void add_entity(EntityJena _obj) {
    	//model.setNsPrefix(_obj.getURI(),_obj.getName());
    	String _uri = _obj.getURI();
    	String _name = _obj.getName();
    	
        if (!containsRessource(_obj)) {
           model.createResource(_uri);
           model.setNsPrefix(_name, _uri);     // TODO je ne sais pas   
        }
    	
    }
    
}