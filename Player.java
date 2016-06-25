package my.game.logic;



public class Player {
	
	final int RANGE_SPEED = 3;
	final int POS_BY_SPEED_Y = 2;
	final int POS_BY_SPEED_X = 1;
	final int RANGE_TIRED = 2;
	final int RANGE_DEFENSE = 2;
	final int RANGE_PASS = 4;
	final int SHOOT_RANGE = 3;
	
	private String name;
	private int number;
	private int stamina;
	private int power;
	private int speed;
	private boolean possession;
	//private int distance;
	private Team team;
	private Position position;
	private Position enemyGoalPosition;
	
	public Player (String name, int number, int stamina, int power, int speed, /*int distance,*/ Team team, Position position, Position enemyGoalPosition){
		this.name = name;
		this.number = number;
		this.stamina = stamina;
		this.power = power;
		this.speed = speed;
		this.possession = false;
		//this.distance = distance;
		this.team = team;
		this.position = position;
		this.enemyGoalPosition = enemyGoalPosition;
	}
	public int passChance(){
		return 0;
		
	}
	public int shootChance(){
		return 1;
	}
	public boolean runOff()
	{
		return false;
	}
	public String getName(){return name;}
	public Team getTeam(){return team;}
	public int getNumber(){return number;}
	//public int getDistance(){return distance;}
	public int getStamina(){return stamina;}
	public int getPower(){return power;}
	public int getSpeed(){return speed;}
	public boolean hasBall(){return possession;}
	public void gotBall(){
		possession = true;
		team.gotBall();
	}
	public Position getPosition(){return position;}
	public void play(Player nearestTeammate, Player nearestOpponent, Score score, int minute){
		
		RNG rnd = RNG.getInstance();
		boolean possession = hasBall();
		//int range = (hasBall())? 2:3;
		//Hardcoding the range for debugging purposes
		int range = 4;
		int option = rnd.getNumber(range);
		//HardCode the option to test 
		//option = 3;
		System.out.println(option+"\n");
		if(possession) System.out.println("I have the ball\n");
		position.show();
		System.out.println(name);
		if(possession){
			switch(option){
				case 0:
					run(nearestOpponent);
					break;
				case 1:
					pass(nearestTeammate, nearestOpponent);
					break;
				default:
					shoot(minute);
					break;
			}
		}
		else{
			switch(option){
				case 0:
					run(nearestOpponent);
					break;
				default:
					defend(nearestOpponent);
					break;
			}
		}
		position.show();
	}
	
	public int getDistanceToGoal(){return position.distance(enemyGoalPosition);}
	
	public void run(Player nearestOpponent){
		if(tiredOut()) {
			System.out.println("\nIm Tired \n");return;
		}
		System.out.println("Im running \n");
		System.out.println("My nearest Opponent is"+nearestOpponent.getName());
		RNG rnd = RNG.getInstance();
		int rndNumber = rnd.getNumber(RANGE_SPEED);
		int distanceTraveled = position.movePos((POS_BY_SPEED_X*speed*rndNumber)/stamina, (POS_BY_SPEED_Y*speed*rndNumber)/stamina);
		getTired(distanceTraveled);
		if(nearestOpponent.tiredOut()){
			System.out.println(nearestOpponent.getName()+"\nI'cant follow him \n");
			return;
		}
		int rndNumber2 = rnd.getNumber(RANGE_SPEED);
		int opponentSpd = nearestOpponent.getSpeed();
		distanceTraveled = nearestOpponent.getPosition().movePos((POS_BY_SPEED_X*opponentSpd*rndNumber2)/nearestOpponent.getStamina(), (POS_BY_SPEED_Y*opponentSpd*rndNumber2)/nearestOpponent.getStamina());
		nearestOpponent.getTired(distanceTraveled);
	}
	private void getTired(int distanceTraveled){
		System.out.println("StartinG stamina: "+stamina);
		RNG rnd = RNG.getInstance();
		int rndNumber = rnd.getNumber(RANGE_TIRED);
		stamina = stamina - distanceTraveled*rndNumber;
		System.out.println("Stamina last"+stamina+"\n" );
	}
	private boolean tiredOut(){return stamina<=0;}
	
	public void pass(Player nearestTeammate, Player nearestOpponent){
		RNG rnd = RNG.getInstance();
		
		System.out.print("Im passing \n");
		int distanceToPass = getDistanceBetweenPlayers(nearestTeammate);
		int distanceToDefense = getDistanceBetweenPlayers(nearestOpponent);
		
		int defenseLuck = rnd.getNumber(RANGE_DEFENSE );
		int passLuck = rnd.getNumber(RANGE_PASS);
		
		int defenseValue = defenseLuck * ((nearestOpponent.getSpeed()-distanceToDefense));
		int passValue = passLuck * (getPower()-distanceToPass);
	
		if(passValue>= defenseValue){passBall(nearestTeammate); System.out.println("To a teammate\n");}
		else{passBall(nearestOpponent);System.out.println("Intercepted\n");}
	}
	
	private void passBall(Player otherPlayer){
		lostBall();
		otherPlayer.gotBall();
	}
	
	public void lostBall(){
		possession = false;
		team.lostBall();
	}
	public int getDistanceBetweenPlayers(Player otherPlayer){
		return otherPlayer.getPosition().distance(position);
	}
	public void shoot(int minute){
		System.out.println("Im shooting \n");
		int distanceToGoal = getDistanceToGoal();
		RNG rnd = RNG.getInstance();
		int shootLuck = rnd.getNumber(SHOOT_RANGE);
		int shootChance = (power*shootLuck)-distanceToGoal;
		if(shootChance > 0){
			System.out.println("GOOOOL");
			System.out.println("El jugador "+name+" ha metido un golazo");
			scoreGoal(minute);
		}
	}
	private void scoreGoal(int minute) {
		team.scoreGoal(this, minute);
		
	}
	public void defend(Player nearestOpponent){
		System.out.println("Im defending \n");
	}
}





































































