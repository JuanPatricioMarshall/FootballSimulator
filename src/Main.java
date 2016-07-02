package my.game.logic;

public class Main {

	static final int MINUTES_TO_PLAY_TEST = 60;
	
	//main method of the program
	//creates the game, loading a file from the pathfile
	public static void main(String[] args) {
		
		//in the future, it should ask the user the path from where to load the players&team file
		//it should also mantain a default path
		Game game = new Game("/home/juampa_94/belatrix/ProjectGameLogic/src/my/game/logic/AllPlayers.txt",MINUTES_TO_PLAY_TEST);
		
		
		game.showScore();
		
		game.playGame();
		game.showScore();
		game.showGoldenBoot();
		game.showScorers();
	}

}
