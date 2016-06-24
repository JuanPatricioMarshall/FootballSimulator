package my.game.logic;

public class Goal {

	//locality = true -> Home
	//locality = false -> Away
	private boolean locality;
	private int time;
	private Player scorer;
	
	public Goal(boolean locality, int time, Player scorer){
		this.locality = locality;
		this.time = time;
		this.scorer = scorer;
	}
	public boolean getLocality(){
		return locality;
	}
	public int getTime(){
		return time;
		
	}
	public Player getScorer(){
		return scorer;
	}
}
