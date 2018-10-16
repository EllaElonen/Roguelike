
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost extends Item implements Enemy {
	private BufferedImage[] images;

	private int index;

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
		image = ControlPanel.ghost0;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		images = new BufferedImage[] { ControlPanel.ghost0, ControlPanel.ghost1 };
		index = 0;
	}

	public void step() {
		image = images[index++ / 10 % images.length];
	}
	public int getScore() {
		return 10;
	}
	public int hurt() {
		return 5;
	}
}
