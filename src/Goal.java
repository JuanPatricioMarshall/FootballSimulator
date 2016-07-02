package my.game.logic;

public class Goal {

	//locality = true -> Home
	//locality = false -> Away
	private String teamId;
	private int time;
	private Player scorer;
	
	public Goal(String teamId, int time, Player scorer){
		this.teamId = teamId;
		this.time = time;
		this.scorer = scorer;
	}
	public String getTeamId(){
		return teamId;
	}
	public int getTime(){
		return time;
		
	}
	public Player getScorer(){
		return scorer;
	}
}
