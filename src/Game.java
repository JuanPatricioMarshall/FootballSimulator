package my.game.logic;

import java.util.ArrayList;

import my.game.logic.utils.ParserTeams;

public class Game {

	private Team homeTeam;
	private Team awayTeam;
	private Score score;
	private int minutesToPlay;

	public Game(String pathFile, int minutesToPlay){
		ParserTeams parser = new ParserTeams(pathFile, this);
		//parser.parserFile();
		homeTeam = parser.getHomeTeam();
		awayTeam = parser.getAwayTeam();
		
		System.out.println(homeTeam.getPlayers().size());
		System.out.println(awayTeam.getPlayers().size());
		
		this.minutesToPlay = minutesToPlay;
		score = new Score(homeTeam, awayTeam);
		//The home team starts with the ball
		giveBall(true);
		
	}
	
	public Team getHomeTeam(){return homeTeam;}
	public Team getAwayTeam(){return awayTeam;}
	public Score getScore(){return score;}
	public int getTimeLeft(){return minutesToPlay;}
	
	public void showScore(){score.printResult();}
	
	public void showScorers(){score.showAllScorers();}
	
	//display all the player from both teams
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
	//method to take the ball from both teams
	public void takeBall(){
		homeTeam.looseBall();
		awayTeam.looseBall();
	}
	
	//adds a goal to the game score
	public void scoreGoal(Goal goal){score.scoreGoal(goal);}
	
	//display the top scorer from the game
	public void showGoldenBoot(){score.showGoldenBoot();}
	
	//method to call after scoring a goal
	//resets all the player positions from both teams
	//gives the ball to a player of the team that got scored on
	public void kickOff(String teamNameRecentlyScored){
		resetPositions();
		takeBall();
		giveBall(!teamNameRecentlyScored.equals(homeTeam.getName()));
	}
	
	//resets to initial position all players
	private void resetPositions() {
		homeTeam.resetPositions();
		awayTeam.resetPositions();
	}

	//most important method of the game
	//execute the game
	public void playGame(){
		for (int minute = 0; minute < minutesToPlay; minute++){
			homeTeam.play(awayTeam, score, minute );
			awayTeam.play(homeTeam, score, minute);
		}
	}
}
