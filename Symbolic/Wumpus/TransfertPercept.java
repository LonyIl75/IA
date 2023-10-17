class TransfertPercept{
	private Environment env;

	public TransferPercept(Environment _env) {
		env = _env;
	}
	public boolean getBump() {
		return env.getBump();
	}
	public boolean getChair() {
		return env.getChair();
	}
	public boolean getTable() {
		return env.getTable();
	}
	/*public boolean hasChair(){
		return env.hasChair();
	}*/

/*	public boolean getChairPerp() {
	return env.getChairPerp();
}
public boolean getTablePerp() {
return env.getTablePerp();
}*/
public boolean getObjetDevant()
{
return env.getObjetDevant();
}
public boolean getChaiseDevant()
{
return env.getChaiseDevant()
}
public boolean getTableDevant()
{
return env.getTableDevant() 
}

	public int getDirection(){
		return env.getAgentDirection();
	}

}
