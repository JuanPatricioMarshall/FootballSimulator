package my.game.logic;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class Score {

	private ArrayList homeGoals;
	private ArrayList awayGoals;
	private Team homeTeam;
	private Team awayTeam;
	private Hashtable<Player, Integer> scorers;

	public Score (Team homeTeam, Team awayTeam){
		homeGoals = new ArrayList();
		awayGoals = new ArrayList();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		scorers = new Hashtable<Player, Integer>();
	}
	
	public ArrayList getGoals(String teamId){
		if(teamId.equals(homeTeam.getName()))
			return homeGoals;	
		return awayGoals; 
	}
	
	public boolean scoreGoal(Goal goal){
		Player scorer = goal.getScorer();
		if(scorers.containsKey(scorer))
			scorers.put(scorer, 1+scorers.get(scorer));
		else scorers.put(goal.getScorer(), 1);
		if(goal.getTeamId().equals(homeTeam.getName()))
			return homeGoals.add(goal) && homeTeam.getGoals().add(goal);
		return awayGoals.add(goal) && awayTeam.getGoals().add(goal);
		
	}
	
	public void printResult()
	{
		String toPrint = "";
		toPrint = toPrint + homeTeam.getName() + Integer.toString(homeGoals.size()) +" - "
		+ awayTeam.getName() + " "+ Integer.toString(awayGoals.size()); 
		System.out.println(toPrint);
	}
	
	public Player getGoldenBoot(){
		Enumeration<Player> scorers = this.scorers.keys();
		Player goldenBoot = scorers.nextElement();
		while(scorers.hasMoreElements()){
			Player newGoldenBoot = scorers.nextElement();
			goldenBoot = (this.scorers.get(goldenBoot)<this.scorers.get(newGoldenBoot)) ? newGoldenBoot : goldenBoot;
		}
		return goldenBoot;
	}
	
}
