package game;
import java.awt.image.BufferedImage;
abstract public class Item {
	protected BufferedImage image;
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	
public void setX(int x) {
	this.x=x;
}
public void setY(int y) {
	this.y=y;
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public void step() {
	
}

}
