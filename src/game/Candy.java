package game;
import java.util.Random;

public class Candy extends Item implements Award{
	private int awardType;
	public Candy(int x, int y) {
		this.x=x;
		this.y=y;
		Random rand = new Random();

		awardType = rand.nextInt(2);
		image = ControlPanel.candy;
		height=image.getHeight();
		width=image.getWidth();

	}
	public int getType() {
		return awardType;
	}

}
