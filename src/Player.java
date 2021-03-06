package my.game.logic;

import my.game.logic.utils.RNG;

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
	private int technique;
	private boolean possession;
	private Team team;
	private Position position;
	private Position initialPosition;
	
	public Player (String name, int number,PlayerStats stats, Team team, Position position){
		this.name = name;
		this.number = number;
		this.stamina = stats.getStamina();
		this.power = stats.getPower();
		this.speed = stats.getSpeed();
		this.technique = stats.getTechnique();
		this.possession = false;
		this.team = team;
		this.initialPosition = position;
		this.position = new Position(position.getPosx(),position.getPosY());
	}
	
	//not implemented yet, not even used, probably deprecated
	public int passChance(){
		return 0;
		
	}
	//not implemented yet, not even used, probably deprecated
	public int shootChance(){
		return 1;
	}
	//not implemented yet, not even used, probably deprecated
	public boolean runOff()
	{
		return false;
	}
	
	public String getName(){return name;}
	public Team getTeam(){return team;}
	public int getNumber(){return number;}
	public int getStamina(){return stamina;}
	public int getPower(){return power;}
	public int getSpeed(){return speed;}
	public boolean hasBall(){return possession;}
	
	public void gotBall(){
		possession = true;
		team.gotBall();
	}
	
	public Position getPosition(){return position;}
	
	//method play: a player decides what to do given nearestTeammate, nearestOpponent and a 
	//Random number that choose the action
	//without the possession the player can defend or run
	//with the possession the player choose to run, shoot or pass
	//if the player scores a goal, returns true, else false
	public boolean play(Player nearestTeammate, Player nearestOpponent, Score score, int minute){
		
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
		//position.show();
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
					return shoot(minute);
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
		return false;
		//position.show();
	}
	
	public int getDistanceToGoal(){return team.getField().distanceToGoal(team.getHomeBoolean(), position);}
	
	public void run(Player nearestOpponent){
		if(tiredOut()) {
			System.out.println("\nIm Tired \n");return;
		}
		System.out.println("Im running \n");
		System.out.println("My nearest Opponent is"+nearestOpponent.getName());
		//Not using any logic at the moment, for debuging purposes
		/*RNG rnd = RNG.getInstance();
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
		*/
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
	public boolean shoot(int minute){
		System.out.println("Im shooting \n");
		int distanceToGoal = getDistanceToGoal();
		RNG rnd = RNG.getInstance();
		int shootLuck = rnd.getNumber(SHOOT_RANGE);
		int shootChance = (power*shootLuck)-distanceToGoal;
		if(shootChance > 0){
			System.out.println("GOOOOL");
			System.out.println("El jugador "+name+" ha metido un golazo");
			scoreGoal(minute);
			return true;
		}
		else{
			missedShot();
			System.out.println("Y se va por al lado del palo");
			return false;
		}
	}
	private void missedShot() {
		team.missedShot();
		
	}
	private void scoreGoal(int minute) {
		team.scoreGoal(this, minute);
		
	}
	public void defend(Player nearestOpponent){
		System.out.println("Im defending \n");
		if(nearestOpponent.hasBall()){
			int oppponentSpd = nearestOpponent.getSpeed();
			RNG rnd = RNG.getInstance();
			int opponentChance = rnd.getNumber(oppponentSpd);
			int ownChance = rnd.getNumber(speed);
			if(ownChance > opponentChance){
				nearestOpponent.lostBall();
				gotBall();
				System.out.println("Im stealing the ball");
			}
			else{System.out.println("Im not stealing the ball");}
		}
	}
	
	public void looseBall() {
		possession = false;
	}
	
	
	public void resetPositions() {
		Position updatedPosition = new Position(initialPosition.getPosx(), initialPosition.getPosY());
		this.position = updatedPosition;
	}
}





































































