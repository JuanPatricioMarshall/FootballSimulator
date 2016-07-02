package my.game.logic.utils;

import my.game.logic.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import my.game.logic.Field;
import my.game.logic.Game;
import my.game.logic.Player;
import my.game.logic.PlayerStats;
import my.game.logic.Position;

public class ParserTeams {

	private Team homeTeam;
	private Team awayTeam;
	private Game game;
	private String filePath;
	

	public ParserTeams (String filePath, Game game){
		this.filePath = filePath;
		this.game = game;
		parserFile();
	}
	
	public Team getHomeTeam(){return homeTeam;}
	public Team getAwayTeam(){return awayTeam;}
	
	//method that always need to be done
	private void parserFile(){
		try {
			boolean firstLine = true;
			for (String line : Files.readAllLines(Paths.get(filePath))) {
				String[] arr = line.split("\\,");
				if(firstLine){
					Position homeGoal = new Position(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
				    Position awayGoal = new Position(Integer.parseInt(arr[4]),Integer.parseInt(arr[5]));
					Field field = new Field(homeGoal,awayGoal,Integer.parseInt(arr[6]),Integer.parseInt(arr[7]));
				    //homeTeam = new Team(arr[0],game, awayGoal);
					//awayTeam = new Team(arr[3],game, homeGoal);
					
					homeTeam = new Team(arr[0], game, field, true);
					awayTeam = new Team(arr[3], game, field, false);
					
					firstLine = false;
				}
				else{
					Team team;
					if((arr[0]).equals("true")){ team = homeTeam;}
					else{team = awayTeam;}
					Position initialPosition = new Position(Integer.parseInt(arr[7]),Integer.parseInt(arr[8]));
					PlayerStats stats = new PlayerStats(Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6]));
					Player newPlayer = new Player(arr[1],Integer.parseInt(arr[2]),stats,team, initialPosition);
					team.addPlayer(newPlayer);
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File error");
		}
	}
	
}
