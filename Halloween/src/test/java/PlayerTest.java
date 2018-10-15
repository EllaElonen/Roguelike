import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	private int amountOfLivesLeft;
	private double x;
	private double y;
	private int speed;

	@Test
	public void setupPlayerObject() throws Exception {
		Player testPlayer = new Player(3, 0, 0, 0);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
	public void equalObjects(){
		Player testPlayer1 = new Player(3, 0, 0, 0);
		Player testPlayer2 = new Player(3, 0, 0, 0);
		assertEquals(testPlayer1, testPlayer2);
	}

	@Test
	public void nonEqualObjects(){
		Player testPlayer1 = new Player(3, 0, 0, 0);
		Player testPlayer2 = new Player(1, 1, 1, 1);
		assertNotEquals(testPlayer1, testPlayer2);
	}
	
	@Test
	public void SetLives() {
		int amountOfLivesLeft = 3;
		assertEquals(amountOfLivesLeft, 3);
	}
	
	@Test
	public void jump(int x, int y) {
		x = 3;
		y = 3;
		assertEquals(x, 3);
		assertEquals(y, 3);
	}

	public void jumpRandom(){
		x = Math.random();
		y = Math.random();
	}

	@Test
	public void move(){
		fail("not implemented");
	}
	
	@Test
	public void setSpeed() {
		speed = 1;
		assertEquals(speed, 1);
	}

}
