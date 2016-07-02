package my.game.logic;

public class Field {
	
	private Position homeGoalPosition;
	private Position awayGoalPosition;
	private int height;
	private int width;
	
	public Field(Position homeGoalPosition, Position awayGoalPosition, int height, int width){
		
		this.homeGoalPosition = homeGoalPosition;
		this.awayGoalPosition = awayGoalPosition;
		this.height = height;
		this.width = width;
		
	}
	
	public boolean isPositionValidInThisField(Position position){
		return ((position.getPosx()<0 || position.getPosY()<0)||(position.getPosx()>width || position.getPosY()>height));
	}
	
	public int distanceToGoal(boolean home, Position position){
		if(home){
			return position.distance(awayGoalPosition);
		}
		else{
			return position.distance(homeGoalPosition);
		}
	}
	
	public Position getHomeGoalPosition() {
		return homeGoalPosition;
	}
	public void setHomeGoalPosition(Position homeGoalPosition) {
		this.homeGoalPosition = homeGoalPosition;
	}
	public Position getAwayGoalPosition() {
		return awayGoalPosition;
	}
	public void setAwayGoalPosition(Position awayGoalPosition) {
		this.awayGoalPosition = awayGoalPosition;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

}
