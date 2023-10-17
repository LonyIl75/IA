package todorov.tp1;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	
	public static String concatNameFile(String ... folders){
        String concat =	new String(folders[0]);
        for (int i = 1  ; i < folders.length ; i++ ) {
            concat = concat+File.separator+folders[i];
           }
        return concat;
    }

	public static boolean isNotBlank(String val ) {
		return !isBlank(val )  ;
	}
	public static boolean isBlank(String val) {
		return val==null || val.isEmpty() || val.isBlank( )  ;
		
	}
	static final String URIpattern ="https?:\\/\\/.+";
	static final String pathURIpattern = URIpattern +"\\/";
	
	static final String delimURI =":";
	
	public static boolean isURI(String str) {
		Pattern pattern1 = Pattern.compile(URIpattern);
		Matcher matcher1 = pattern1.matcher(str) ;
		boolean res=matcher1.find();
		return res;
	}
	
	
	public static boolean isPathURI(String str) {
		Pattern pattern1 = Pattern.compile(pathURIpattern);
		Matcher matcher1 = pattern1.matcher(str) ;
		boolean res=matcher1.find();
		return res;
	}
	
}
