package game;
import java.util.Random;

public class Pumpkin extends Item implements Award{
	private int awardType;
	public Pumpkin(int x, int y) {
		this.x=x;
		this.y=y;

		awardType = 3;
		image = ControlPanel.pumpkin;
		height=image.getHeight();
		width=image.getWidth();

	}
	public int getType() {
		return awardType;
	}

}
