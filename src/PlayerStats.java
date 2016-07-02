package my.game.logic;

import java.util.ArrayList;

public class PlayerStats {
	private int speed;
	private int power;
	private int stamina;
	private int technique;
	
	public PlayerStats(int speed, int power, int techinque, int stamina){
		this.speed = speed;
		this.power = power;
		this.technique = technique;
		this.stamina = stamina;
	}
	public int getSpeed(){return speed;}
	public int getPower(){return power;}
	public int getTechnique(){return technique;}
	public int getStamina(){return stamina;}
}
