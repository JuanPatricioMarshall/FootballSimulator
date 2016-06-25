package my.game.logic;

import java.util.ArrayList;

public class Game {

	private Team homeTeam;
	private Team awayTeam;
	private Score score;
	private int minutesToPlay;

	public Game (String homeTeamId, String awayTeamId, int minutesToPlay){
		
		ArrayList<Player> playersHomeTeam = new ArrayList();
		ArrayList<Player> playersAwayTeam = new ArrayList();
		
		homeTeam = new Team(homeTeamId);
		awayTeam = new Team(awayTeamId);
		
		Position position1 = new Position (25,25);
		Position position2 = new Position (25,25);
		Position position3 = new Position (25,25);
		Position position4 = new Position (25,25);
		Player toxico = new Player("Toxico", 12, 500, 55, 63,50,homeTeam, position1);
		Player lauty = new Player("Lauty", 1, 300, 99, 99,0,homeTeam, position2);
		Player eduNegro = new Player("Edu el Negro", 4, 500, 55, 99,50,awayTeam, position3);
		Player gabot = new Player("Gabot", 6, 500, 99, 99,99,awayTeam, position4);
		
		homeTeam.addPlayer(toxico);
		homeTeam.addPlayer(lauty);
		awayTeam.addPlayer(eduNegro);
		awayTeam.addPlayer(gabot);
		
		score = new Score(homeTeam, awayTeam);
		this.minutesToPlay = minutesToPlay;
		//The home team starts with the ball
		giveBall(true);
		
		
	}
	public Team getHomeTeam(){return homeTeam;}
	public Team getAwayTeam(){return awayTeam;}
	public Score getScore(){return score;}
	public int getTimeLeft(){return minutesToPlay;}
	
	public void showScore(){
		score.printResult();
	}
	
	public void showTeams(){
		homeTeam.showPlayers();
		awayTeam.showPlayers();
		
	}
	//method to give the ball possession to any of the two teams
	//Pre: neither of teams have the possession
	//Post: one of the two teams won possession
	public void giveBall(boolean home){
		if (home) homeTeam.giveStartBall();
		else awayTeam.giveStartBall();
	}
	
	public void scoreGoal(Goal goal){
		score.scoreGoal(goal);
	}
	public void showGoldenBoot(){
		System.out.println(score.getGoldenBoot().getName());
	}
	public void playGame(){
		for (int i = 0; i < minutesToPlay; i++){
			homeTeam.play(awayTeam, score);
			awayTeam.play(homeTeam, score);
		}
	}
}
