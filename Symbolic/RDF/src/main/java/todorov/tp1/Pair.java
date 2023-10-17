package todorov.tp1;



	public class Pair<T,U> {
	    private final T key;
	    private final U value;

	    public Pair(T key, U value) {
	        this.key = key;
	        this.value = value;
	    }

	    public T getKey() {
	        return this.key;
	    }

	    public U getValue() {
	        return this.value;
	    }
	    
	    public boolean isNotBlankK() {
	    	if (key instanceof String ) {
	    		String key_str = (String)key;
	    		return key_str.isEmpty()&& !key_str.isBlank();
	    	}
	    	return false ;
	    }
	    
	    public boolean isNotBlankV() {
	    	if (value instanceof String ) {
	    		String value_str = (String)value;
	    		return value_str.isEmpty()&& !value_str.isBlank();
	    	}
	    	return false ;
	    }
	    
	}

