package my.game.logic;

import java.util.ArrayList;

import my.game.logic.utils.ParserTeams;

public class Game {

	private Team homeTeam;
	private Team awayTeam;
	private Score score;
	private int minutesToPlay;

	//public Game (String homeTeamId, String awayTeamId, int minutesToPlay){
	public Game(String pathFile, int minutesToPlay){
		ParserTeams parser = new ParserTeams(pathFile, this);
		parser.parserFile();
		homeTeam = parser.getHomeTeam();
		awayTeam = parser.getAwayTeam();
		
		System.out.println(homeTeam.getPlayers().size());
		System.out.println(awayTeam.getPlayers().size());
		
		this.minutesToPlay = minutesToPlay;
		score = new Score(homeTeam, awayTeam);
		//The home team starts with the ball
		giveBall(true);
		
	}
	/*
		ArrayList<Player> playersHomeTeam = new ArrayList();
		ArrayList<Player> playersAwayTeam = new ArrayList();
	
		Position homeGoalPosition = new Position(0,0);
		Position awayGoalPosition = new Position(100,100);
		
		homeTeam = new Team(homeTeamId, this, homeGoalPosition);
		awayTeam = new Team(awayTeamId, this, awayGoalPosition);
		
		
		Position position1 = new Position (25,25);
		Position position2 = new Position (25,25);
		Position position3 = new Position (25,25);
		Position position4 = new Position (25,25);
		//new Player(Stirng name, int number, int stamina, int power, int speed, int distance, Team team, Position position, Position enemyGoalPosition
		Player toxico = new Player("Toxico", 12, 500, 55, 63,homeTeam, position1);
		Player lauty = new Player("Lauty", 1, 300, 99, 99,homeTeam, position2);
		Player eduNegro = new Player("Edu el Negro", 4, 500, 55, 99,awayTeam, position3);
		Player gabot = new Player("Gabot", 6, 500, 99, 99,awayTeam, position4);
		
		homeTeam.addPlayer(toxico);
		homeTeam.addPlayer(lauty);
		awayTeam.addPlayer(eduNegro);
		awayTeam.addPlayer(gabot);
		
		score = new Score(homeTeam, awayTeam);
		this.minutesToPlay = minutesToPlay;
		//The home team starts with the ball
		giveBall(true);
	}
		*/
		
	
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
	//method to take the ball from any team
	public void takeBall(){
		homeTeam.looseBall();
		awayTeam.looseBall();
	}
	
	public void scoreGoal(Goal goal){
		score.scoreGoal(goal);
	}
	public void showGoldenBoot(){
		score.showGoldenBoot();
	}
	public void kickOff(String teamNameRecentlyScored){
		resetPositions();
		takeBall();
		giveBall(!teamNameRecentlyScored.equals(homeTeam.getName()));
	}
	private void resetPositions() {
		homeTeam.resetPositions();
		awayTeam.resetPositions();
	}


	public void playGame(){
		for (int minute = 0; minute < minutesToPlay; minute++){
			homeTeam.play(awayTeam, score, minute );
			awayTeam.play(homeTeam, score, minute);
		}
	}
}
