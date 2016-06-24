package my.game.logic;



public class Player {
	
	final int RANGE_SPEED = 3;
	final int POS_BY_SPEED_Y = 2;
	final int POS_BY_SPEED_X = 1;
	final int RANGE_TIRED = 2;
	
	private String name;
	private int number;
	private int stamina;
	private int power;
	private int speed;
	private boolean possession;
	private int distance;
	private Team team;
	private Position position;
	
	public Player (String name, int number, int stamina, int power, int speed, int distance, Team team, Position position){
		this.name = name;
		this.number = number;
		this.stamina = stamina;
		this.power = power;
		this.speed = speed;
		this.possession = false;
		this.distance = distance;
		this.team = team;
		this.position = position;
		
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
	public int getDistance(){return distance;}
	public int getStamina(){return stamina;}
	public int getPower(){return power;}
	public int getSpeed(){return speed;}
	public boolean hasBall(){return possession;}
	public void gotBall(){
		possession = true;
		team.gotBall();
	}
	public Position getPosition(){return position;}
	public void play(Player nearestTeammate, Player nearestopponent, Score score){
		
		RNG rnd = RNG.getInstance();
		boolean possession = hasBall();
		int range = (hasBall())? 2:3;
		int option = rnd.getNumber(range);
		option = 0;
		System.out.println(option+"\n");
		if(possession) System.out.println("I have the ball\n");
		position.show();
		System.out.println(name);
		switch(option){
			case 0:
				run(nearestopponent);
				break;
			case 1:
				pass();
				break;
			case 2:
				shoot();
				break;
			case 3: 
				defend();
				break;
		}
		position.show();
	}
	public void run(Player nearestOpponent){
		if(tiredOut()) {
			System.out.println(getName()+"\nIm Tired \n");return;
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
	
	public void pass(){
		System.out.print("Im passing \n");
		
	}
	public void shoot(){
		System.out.println("Im shooting \n");
	}
	public void defend(){
		System.out.println("Im defending \n");
	}
}





































































