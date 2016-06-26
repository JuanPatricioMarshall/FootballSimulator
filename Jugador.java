package my.game.logic;

public abstract class Jugador {

	private String name;
	private int number;
	private int stamina;
	private int power;
	private int speed;
	private boolean possession;
	private int distance;
	
	public Jugador (String name, int number, int stamina, int power, int speed, int distance){
		this.name = name;
		this.number = number;
		this.stamina = stamina;
		this.power = power;
		this.speed = speed;
		this.possession = false;
		this.distance = distance;
		
		
	}
	abstract public int passChance(int distance);
	abstract public int shootChance(int distance);
	abstract public boolean runOff(int distance, Jugador vsPlayer);
	public String getName(){return name;}
	public int getNumber(){return number;}
	public int getDistance(){return distance;}
	public int getStamina(){return stamina;}
	public int getPower(){return power;}
	public int getSpeed(){return speed;}
	public boolean hasBall(){return possession;}
	abstract public void play();
}
