package my.game.logic.utils;

import java.util.Random;

//singletone pattern class
//can give random numbers
public class RNG {
	
	private Random rnd;

	private static RNG instance = null;
	protected RNG(){
		//uses Random java default class
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
