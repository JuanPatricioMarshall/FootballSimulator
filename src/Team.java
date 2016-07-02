package my.game.logic;

import java.util.ArrayList;

import my.game.logic.utils.RNG;

public class Team {

	private String name;
	private ArrayList<Player> players;
	private boolean possession;
	private ArrayList<Goal> goals;
	private Score score;
	private Game game;
	//private Position enemyGoalPosition;
	private Field field;
	private boolean home;
	
	//public Team (String name, Game game, Position enemyGoalPosition){
	public Team (String name, Game game, Field field, boolean home){
		this.name = name;
		this.players = new ArrayList<Player>();
		possession = false;
		this.goals = new ArrayList<Goal>();
		this.game = game;
		//this.enemyGoalPosition = enemyGoalPosition;
		this.field = field;
		this.home = home;
	}
	
	public String getName (){return name;}
	public ArrayList<Player> getPlayers(){return players;}
	public boolean hasBall(){return possession;}
	public ArrayList<Goal> getGoals(){return goals;}
	public boolean getHomeBoolean(){return home;}
	//public Position getEnemyGoalPosition(){return enemyGoalPosition;}
	public Field getField(){return field;}
	
	public void giveStartBall(){
		RNG rnd = RNG.getInstance();
		int chance = rnd.getNumber(players.size());
		System.out.println(chance);
		players.get(chance).gotBall();
		
	} 
	public void gotBall(){possession = true;}
	public void lostBall(){possession = false;}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	private Player getNearestPlayer(Player player, Team team ){
		int distance = 1000;
		Player nearestPlayer = team.getPlayers().get(0);
		for (int i = 0; i < team.getPlayers().size(); i++){
			Player otherPlayer = team.getPlayers().get(i);
			int auxDistance = player.getDistanceBetweenPlayers(otherPlayer);
			if(player.getName().equals(otherPlayer.getName())) continue;
			if(auxDistance<distance){
				nearestPlayer = otherPlayer;
				distance = auxDistance;
			}
			
		}
		return nearestPlayer;
	}
	
	public void play(Team otherTeam, Score score, int minute){
		for(int i = 0; i < players.size();i++){
			Player player = players.get(i);
			Player nearestTeammate = getNearestPlayer(player, player.getTeam());
			Player nearestOpponent = getNearestPlayer(player, otherTeam);
			boolean goal = player.play(nearestTeammate, nearestOpponent, score, minute);
			if(goal) continue;
		}
	}
	public void showPlayers(){
		System.out.println("Integrantes del equipo:"+name+"\n");
		for(int i = 0; i < players.size();i++){
			Player player = players.get(i);
			System.out.println(player.getNumber()+" - "+player.getName()+"\n");
	
		}

	}
	public void scoreGoal(Player player, int minute) {
		Goal goal = new Goal(this.name,minute,player);
		game.scoreGoal(goal);
		game.kickOff(name);
	}
	public void looseBall() {
		for(int i = 0; i < players.size();i++){
			Player player = players.get(i);
			player.looseBall();
		}

	}
	public void resetPositions() {
		for(int i = 0; i < players.size();i++){
			Player player = players.get(i);
			player.resetPositions();
		}
	}
	public void missedShot() {
		looseBall();
		boolean condition = name.equals(game.getHomeTeam().getName());
		game.giveBall(!condition);
	}
}