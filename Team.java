package my.game.logic;

import java.util.ArrayList;

public class Team {

	private String name;
	private ArrayList<Player> players;
	private boolean possession;
	private ArrayList<Goal> goals;
	
	public Team (String name){
		this.name = name;
		this.players = new ArrayList<Player>();
		possession = false;
		this.goals = new ArrayList<Goal>();
	}
	public String getName (){return name;}
	public ArrayList<Player> getPlayers(){return players;}
	public boolean hasBall(){return possession;}
	public ArrayList<Goal> getGoals(){return goals;}
	
	public void giveStartBall(){players.get(0).gotBall();} 
	public void gotBall(){possession = true;}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	private Player getNearestPlayer(Player player, Team team ){
		int distance = 1000;
		Player nearestPlayer = team.getPlayers().get(0);
		Position myPosition = player.getPosition();
		for (int i = 0; i < team.getPlayers().size(); i++){
			Player otherPlayer = team.getPlayers().get(i);
			int auxDistance = otherPlayer.getPosition().distance(myPosition);
			if(player.getName().equals(otherPlayer.getName())) continue;
			if(auxDistance<distance){
				nearestPlayer = otherPlayer;
				distance = auxDistance;
			}
			
		}
		return nearestPlayer;
	}
	
	public void play(Team otherTeam, Score score){
		for(int i = 0; i < players.size();i++){
			Player player = players.get(i);
			Player nearestTeammate = getNearestPlayer(player, player.getTeam());
			Player nearestopponent = getNearestPlayer(player, otherTeam);
			players.get(i).play(nearestTeammate, nearestopponent, score);
		}
	}
	public void showPlayers(){
		System.out.println("Integrantes del equipo:"+name+"\n");
		for(int i = 0; i < players.size();i++){
			Player player = players.get(i);
			System.out.println(player.getNumber()+" - "+player.getName()+"\n");
	
		}

	}
}