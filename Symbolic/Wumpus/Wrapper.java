class WrapperT<TypeVal>{
	private final Object key ;

	public Wrapper (int _key)
	{
		this.key=_key;
	}
	public Wrapper (TypeVal _key)
	{
		this.key=_key;
	}
	public Wrapper (String _key)
	{
		this.key=new String(_key);
	}
}
