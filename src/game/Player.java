
import java.awt.image.BufferedImage;

public class Player extends Item{
	private int life;
	private int attack;
	private int defence;

	public Player() {
		image = ControlPanel.player;
		width = 50;
		//width = image.getWidth();
		//height = image.getHeight();
		height= 50;
		x = 250;
		y = 500;
		life = 3;		
	}
	public void moveTo(int x,int y) {
		this.x = x-this.width/2;
		this.y = y -this.height/2;
	}
	public void moveLeft() {
		x-=50;	
	}
	public void moveRight() {
		x+=50;	
	}
	public void moveUp() {
		y-=50;	
	}
	public void moveDown() {
		y+=50;	
	}
}
