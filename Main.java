package my.game.logic;

public class Main {

	public static void main(String[] args) {
		Game game = new Game("Los Toxos ", "Los Soldados de Lauty",10);
		game.showScore();
		game.showTeams();
		Player scorerTest = game.getHomeTeam().getPlayers().get(0);
		Player scorerTest2 = game.getHomeTeam().getPlayers().get(1);
		Goal goal = new Goal(true,12,scorerTest );
		game.scoreGoal(goal);
		game.showScore();
		game.showGoldenBoot();
		Goal goal2 = new Goal(true,132,scorerTest2 );
		game.scoreGoal(goal2);
		game.showScore();
		game.showGoldenBoot();
		Goal goal3 = new Goal(true,132,scorerTest );
		game.scoreGoal(goal3);
		game.showScore();
		game.showGoldenBoot();
		
		game.playGame();
	}

}
