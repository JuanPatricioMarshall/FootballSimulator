package my.game.logic;

import java.util.Random;

public class RNG {
	
	private Random rnd;

	private static RNG instance = null;
	protected RNG(){
		rnd = new Random();
	}
	public static RNG getInstance(){
		if(instance==null)
			instance = new RNG();
		return instance;
	}
	public int getNumber(int range){
		return rnd.nextInt(range);
	}
	
}
