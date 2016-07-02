package my.game.logic;

public class Position {

	private int posx;
	private int posy;
	
	public Position(int posx, int posy) {
		// TODO Auto-generated constructor stub
		this.posx = posx;
		this.posy = posy;
	}
	public int getPosx(){return posx;}
	public int getPosY(){return posy;}
	
	public int distance(Position otherPosition){
		
		int distancex = this.posx - otherPosition.getPosx();
		int distancey = this.posy - otherPosition.getPosY();
		int distancexAlCuadrado = distancex * distancex;
		int distanceyAlCuadrado = distancey * distancey;
		return (int) (Math.sqrt(distancexAlCuadrado + distanceyAlCuadrado));
	}
	public int movePos(int dx, int dy){
		posx = posx + dx;
		posy = posy + dy;
		int distancexAlCuadrado = (posx-dx) * (posx-dx);
		int distanceyAlCuadrado = (posy-dy) * (posy-dy);
		return (int) (Math.sqrt(distancexAlCuadrado + distanceyAlCuadrado));
	}
	
	public void show(){
		System.out.println("Posx: "+ posx);
		System.out.println("Posy: "+posy);
	}
}
