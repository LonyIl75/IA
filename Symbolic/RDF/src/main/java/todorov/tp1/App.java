package todorov.tp1;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.shared.ClosedException;
import com.hp.hpl.jena.vocabulary.*;

import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.graph.GraphFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import todorov.tp1.ModelPage; 

public class App 
{
	 	/*static String PersonURI = "http://personne/rdf/";
	    static String SymphonieURI = "http://jupiter/rdf/";*/

		
	    
	    
	    static String _foaf = "";//"foaf"+ Util.delimURI;
	    static String _dbr = "dbr"+ Util.delimURI;
	    static String _dbp = "dbp"+ Util.delimURI;
	    static String _yago = "yago"+ Util.delimURI;
	    static String _dbo ="dbo"+ Util.delimURI;
	    static String _mus = "mus" + Util.delimURI;
	    static String _rdf = "rdf" + Util.delimURI;
	    static String _owl = "owl" + Util.delimURI;
	    
	    
	    static String MozartURI = _dbr + "Wolfgang_Amadeus_Mozart";
	    
	    static String VienneURI = _dbr +"Vienna";
	    static String AutricheURI = _dbr +"Austria";
	    
	    static String MusiqueURI = _dbo +"ClassicalMusicComposition";
	    static String PersonneURI = _dbr +"Person";
	    
	    static String MozartFullName = "Wolfgang Amadeus Mozart";
	    static String MozartFirstName = "Wolfgang Amadeus";
	    static String MozartLastName =	"Mozart";
	    
	    static String MozartBirthDate = "27 janvier 1756";
	    
	    static String MozartDeathDate = "5 decembre 1791";
	    
	    static String CompositeurURI = _dbo +"MusicComposer";
	   
	    
	    //death vienne à ___
	    static String MozartBirthPlace =  _dbr+"Salzbourg";
	    //vienne capitale autriche 
	    static String MozartDeathPlace = VienneURI;
	    
	    
	    //fils de ___ 
	    static String LeopoldMozartURI =  _dbr + "Leopold_Mozart";
	    
	    static String LeopoldMozartFullName = "Leopold Mozart";
	    
	    static String LeopoldMozartFirstName =   "Leopold";
	    static String LeopoldMozartLastName =	"Mozart";
	    
	    

	    //marié à ___
	    static String ConstanceWeberURI = _dbr + "Constance_Weber";
	    
	    static String ConstanceWeberFullName = "Constance Weber";
	    
	    static String ConstanceWeberFirstName =  "Constance";
	    static String ConstanceWeberLastName =	"Weber";

	    
	    
	    static String FluteEnchanteURI = _dbr + "The_Magic_Flute";
	    static String FluteEnchanteTitle =  "The Magic Flute"; // pas sur la page 
	    
	    
	    static String JupiterURI = _dbr + "Jupiter";
	    static String JupiterTitle = "Jupiter";
	    
	    static String JupiterBisURI = _dbr + "Symphony_No._41_(Mozart)"; //same as 
	    static String JupiterBisTitle = "41 Symphonie en ut majeur de Mozart";
	    
	    
	    //est composé ___ * 4
	    
	    
	    static String SymphonyURI = _dbr +"Symphony";
	    //static String JupiterYago = _yago +"WikicatSymphoniesByWolfgangAmadeusMozart";
	    
	    
	    static String Partie4URI = _dbr +"Molto_Allegro"; //? Symphony_No._41_(Mozart)__Sound__1
	    static String Partie4Title = "Molto Allegro";
	    
	    static String Partie3URI = _dbr +"Menuetto";
	    static String Partie3Title = "Menuetto";
	    
	    static String Partie1URI = _dbr +"Allegro_Vivace";
	    static String Partie1Title = "Allegro Vivace";
	    
	    static String Partie2URI = _dbr +"Andante_Cantabile";
	    static String Partie2Title = "Andante Cantabile";

	   
	    static String randomId = "0001" ;
	    static String EnregistrementURI = _dbr +"Records/"+randomId ;
	    //Jupiter record by  __ 
		static String OrchestrePhilLondresURI = _dbr +"London_Philharmonic_Orchestra" ; 
		
		//___
		static String LondresURI = _dbr +"London";
		
		//orchestre par ___
		static String ClaudioAbbadoURI = _dbr + "Claudio_Abbado";
	    static String ClaudioAbbadoFullName = "Claudio Abbado";
	    
	    static String ClaudioAbbadoFirstName =   "Claudio";
	    static String ClaudioAbbadoLastName =	"Abbado";
		
		static String DateYearRecording = "1980";
		
		static       String MusiqueGroupeURI = _dbo + "musicBand";
		
		
        
        static String MusiqueEnregistrementURI = _dbo + "soundRecording";
        static String MusiqueGroupURI = _dbo + "soundRecording";
	    
	    
	    public static ModelPage CreateModel_ex1() {
	    	return new ModelPage(ModelFactory.createDefaultModel());
	    }
	    
	    /*public static void AddEntity_ex1(ModelPage model) {
	    	Symphonie symph = new Symphonie();
	    	Person pers = new Person();
	    	
	    	model.add_entity(symph);
	    	model.add_entity(pers);
	    	
	    	
	    }*/
	    

	    


	    public static void AddProps_ex1(ModelPage model) {
	    	
	    	
	    	//MOZART :
	        model.add_prop(MozartURI, _dbo+"peopleName", MozartFullName);
	        model.add_prop(MozartURI, _foaf+"lastName", MozartLastName);
	        model.add_prop(MozartURI, _foaf+"firstName", MozartFirstName);
	        
	        model.add_prop(MozartURI,  _dbo+"birthDate", MozartBirthDate);
	        model.add_prop(MozartURI,  _dbo+"birthPlace", MozartBirthPlace);
	        
	        model.add_prop(MozartURI,  _dbo+"nationality", AutricheURI);
	        
	        
	        model.add_prop(MozartURI, _dbo+"deathDate", MozartDeathDate); 
	        model.add_prop(MozartURI, _dbo+"deathPlace", MozartDeathPlace);
	        
	        
	        model.add_prop(VienneURI, _dbo+"capital", AutricheURI);
	        model.add_prop(MozartURI, _dbo+"country", AutricheURI);
	        
	        
	        model.add_prop(MozartURI,  _rdf+"type", CompositeurURI);
	        
	        //RELATIVE de MOZART 
	        model.add_prop(LeopoldMozartURI, _dbo+"parent", MozartURI);
	        model.add_prop(MozartURI,  _dbo+"spouse", ConstanceWeberURI);
	        
	        model.add_prop(LeopoldMozartURI, _dbo+"peopleName", LeopoldMozartFullName);
	        model.add_prop(LeopoldMozartURI, _foaf+"lastName", LeopoldMozartLastName);
	        model.add_prop(LeopoldMozartURI, _foaf+"firstName", LeopoldMozartFirstName);
	        
	        model.add_prop(ConstanceWeberURI, _dbo+"peopleName", ConstanceWeberFullName);
	        model.add_prop(ConstanceWeberURI, _foaf+"lastName", ConstanceWeberLastName);
	        model.add_prop(ConstanceWeberURI, _foaf+"firstName", ConstanceWeberFirstName);
	        
	        
	        //COMPOSITION de MOZART
	        model.add_prop(MozartURI,  _dbo+"notableWork", JupiterURI);
	        model.add_prop(MozartURI,  _dbo+"notableWork", FluteEnchanteURI);
	        
	        
	        //JUPITER :
	        model.add_prop(JupiterURI,  _rdf+"type", SymphonyURI);
	        model.add_prop(JupiterURI,  _dbo+"title", JupiterTitle);
	        model.add_prop(JupiterURI,  _owl+"sameAs", JupiterBisURI);
	        
	        
	        //PARTIE JUPITER :
	        //model.add_prop(Partie1URI,  "Title", Partie1Title);
	        model.add_prop(JupiterURI,  _mus+"#U14_has_tempo", Partie1URI);
	        
	        //model.add_prop(Partie4URI,  "Title", Partie4Title);
	        model.add_prop(JupiterURI,  _mus+"#U14_has_tempo", Partie4URI);
	        
	        //model.add_prop(Partie2URI,  "Title", Partie2Title);
	        model.add_prop(JupiterURI,  _mus+"#U14_has_tempo", Partie2URI);
	        
	        //model.add_prop(Partie3URI,  "Title", Partie3Title);
	        model.add_prop(JupiterURI,  _mus+":#U14_has_tempo", Partie3URI);
	        
	        model.add_prop(JupiterURI,  _dbo+"musicalKey", "C major");
	        
	        
	        
	        
	 
	
	       //ENREGISTREMENT
	        model.add_prop(EnregistrementURI,  _mus+"#U51_is_partial_or_full_recording_of", JupiterURI);

	        model.add_prop(EnregistrementURI,  _dbo+"Producer", ClaudioAbbadoURI);
	        model.add_prop(EnregistrementURI,  _mus+":#U54_performed_by", OrchestrePhilLondresURI);
	        model.add_prop(EnregistrementURI,  _dbo+"recordedIn", LondresURI);
	        model.add_prop(EnregistrementURI,  _dbo+"recordDate", DateYearRecording);
	        model.add_prop(OrchestrePhilLondresURI,  _dbo+"city", LondresURI);
	        model.add_prop(OrchestrePhilLondresURI,  _rdf+"type", MusiqueGroupeURI);
	        model.add_prop(ClaudioAbbadoURI,  _dbo+"leader", OrchestrePhilLondresURI);
	        model.add_prop(LondresURI,  _rdf+"type", _dbo+"Place");
	        
	       
	        
	        
	        model.add_prop(ClaudioAbbadoURI, _dbo+"peopleName", ClaudioAbbadoFullName);
	        model.add_prop(ClaudioAbbadoURI, _foaf+"lastName", ClaudioAbbadoLastName);
	        model.add_prop(ClaudioAbbadoURI, _foaf+"firstName", ClaudioAbbadoFirstName);
	        
	        
	        
	        //ORCHESTRE (PAS SUR) 
	        model.add_prop(OrchestrePhilLondresURI,  _mus+":U20i_is_music_group_formation_of", LondresURI);

	        //TYPAGE :
	        model.add_prop(JupiterURI,  _rdf+"type", MusiqueURI);
	        
	        
	        
	        model.add_prop(FluteEnchanteURI,  _rdf+"type", MusiqueURI);
	        model.add_prop(Partie2URI,  _rdf+"type", MusiqueURI);
	        model.add_prop(Partie3URI,  _rdf+"type", MusiqueURI);
	        model.add_prop(Partie4URI,  _rdf+"type", MusiqueURI);
	        model.add_prop(Partie1URI,  _rdf+"type", MusiqueURI);
	        
	        
	  
	        
	        
	        model.add_prop(Partie2URI,  _dbo+"isPartOf", JupiterURI);
	        model.add_prop(Partie3URI,  _dbo+"isPartOf", JupiterURI);
	        model.add_prop(Partie4URI,  _dbo+"isPartOf", JupiterURI);
	        model.add_prop(Partie1URI,  _dbo+"isPartOf", JupiterURI);
	       
	        
	        model.add_prop(Partie2URI,  _mus+"#U10_has_order_number", "2");
	        model.add_prop(Partie3URI,  _mus+"#U10_has_order_number", "3");
	        model.add_prop(Partie4URI,  _mus+"#U10_has_order_number", "4");
	        model.add_prop(Partie1URI,  _mus+"#U10_has_order_number", "1");
	        model.add_prop(JupiterURI,  _mus+"#U42_has_opus_number", "41");
	        
	        
	
	        
	        model.add_prop(OrchestrePhilLondresURI,  _mus+"#M20_Music_Group_Formation", MusiqueGroupURI);
	        model.add_prop(EnregistrementURI,  _rdf+"type", MusiqueEnregistrementURI);
	        
	        
	        model.add_prop(MozartURI,  _rdf+"type", PersonneURI);
	        model.add_prop(LeopoldMozartURI,  _rdf+"type", PersonneURI);
	        model.add_prop(ConstanceWeberURI,  _rdf+"type", PersonneURI);
	        model.add_prop(ClaudioAbbadoURI, _rdf+"type", PersonneURI);
	        
	        
	     
	        

	    }

	
	    

	    public static void main(String[] args) throws IOException {
	    	
	    	ModelPage model_ex1  = CreateModel_ex1();
		
	    	//AddEntity_ex1(model_ex1);
	    	AddProps_ex1(model_ex1);
	    	
	       
	        model_ex1.writeModel("test");
	        model_ex1.writeModel("test", "N-TRIPLE");
	        model_ex1.writeModel("test", "TURTLE");
	    }
}