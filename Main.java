package my.game.logic;

public class Main {

	public static void main(String[] args) {
		
		Game game = new Game("/home/juampa_94/belatrix/ProjectGameLogic/src/my/game/logic/AllPlayers.txt",30);
		/*Game game = new Game("Los Toxos", "Los Soldados de Lauty",30);
		game.showScore();
		game.showTeams();
		Player scorerTest = game.getHomeTeam().getPlayers().get(0);
		Player scorerTest2 = game.getHomeTeam().getPlayers().get(1);
		//Goal goal = new Goal("Los Toxos",12,scorerTest );
		//game.scoreGoal(goal);
		*/
		game.showScore();
		//game.showGoldenBoot();
		//Goal goal2 = new Goal("Los Toxos",132,scorerTest2 );
		//game.scoreGoal(goal2);
		//game.showScore();
		//game.showGoldenBoot();
		//Goal goal3 = new Goal("Los Toxos",132,scorerTest );
		//game.scoreGoal(goal3);
		//game.showScore();
		//game.showGoldenBoot();
		
		game.playGame();
		game.showScore();
		game.showGoldenBoot();
	}

}
