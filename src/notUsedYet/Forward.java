package my.game.logic;

public class Forward extends Jugador {
	
	final int EXTRA_CURVE = 5;
	final int EXTRA_SPEED = 3;
	
	public Forward(String name, int number, int stamina, int power, int speed, int distance) {
		super(name, number, stamina, power, speed, distance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int passChance(int distance) {
		RNG rnd = RNG.getInstance();
		return rnd.getNumber(getStamina())+rnd.getNumber(getPower())-distance;
	}

	@Override
	public int shootChance(int distance) {
		return passChance(distance)+distance+EXTRA_CURVE;
	}

	@Override
	public boolean runOff(int distance, Jugador vsPlayer) {
		RNG rnd = RNG.getInstance();
		int ownRes = rnd.getNumber(getStamina()); 
		int ownSpd = rnd.getNumber(getSpeed());
		int vsRes = rnd.getNumber(vsPlayer.getStamina());
		int vsSpd = rnd.getNumber(vsPlayer.getSpeed());
		return((ownRes-vsRes+((ownSpd-vsSpd)*distance)+EXTRA_SPEED)>0);
	}
	public void play(){
		
	}
}
