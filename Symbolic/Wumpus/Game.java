class Game{
	private int curr_distGoal=-1 ;
	private int num_step =0;

	private Action act ; 
	private Integer lastAction ; // begin 

	private boolean is_running = false;
	private Agent agent ;
	private Environement env;
	private TransPercept transP;
	private ArrayList<Integer> sequence_state = new ArrayList<Integer>();


	public Game(Environment _env, int maxSteps) {

	this.is_running=true;
	
	transferPercept = new TransferPercept(wumpusEnvironment);
	environment = wumpusEnvironment;
	agent = new Agent(environment, transferPercept, nonDeterministic);
	environment.placeAgent(agent);
	environment.printEnvironment();

}

}