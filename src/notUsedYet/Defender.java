package my.game.logic;

public class Defender extends Jugador {

	public Defender(String name, int number, int stamina, int power, int speed, int distance) {
		super(name, number, stamina, power, speed, distance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int passChance(int distance) {
		return shootChance(distance)-distance;
	}

	@Override
	public int shootChance(int distance) {
		RNG rnd = RNG.getInstance();
		return rnd.getNumber(getStamina())+rnd.getNumber(getPower())-distance;
	}

	@Override
	public boolean runOff(int distance, Jugador vsPlayer) {
		RNG rnd = RNG.getInstance();
		int ownRes = rnd.getNumber(getStamina()); 
		int ownSpd = rnd.getNumber(getSpeed());
		int vsRes = rnd.getNumber(vsPlayer.getStamina());
		int vsSpd = rnd.getNumber(vsPlayer.getSpeed());
		return((ownRes-vsRes+((ownSpd-vsSpd)*distance))>0);
	}
	public void play(){
		RNG rnd = RNG.getInstance();
		if(hasBall()){
			int decision = rnd.getNumber(2);
			switch (decision){
			case 0:
				passChance(getDistance());
			case 1:
				shootChance(getDistance());
			case 2:
				//runOff(getDistance(),)
			}
		}
		
	}

}
