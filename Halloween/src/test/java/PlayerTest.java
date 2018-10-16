import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.text.Position;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	private Point position = new Point(1, 1);

	@Test
	void setupPlayerObject() throws Exception {
		Player testPlayer = new Player(position, "name", 10, 3, 2);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
	void nonEqualObjects(){
		Player testPlayer1 = new Player(position, "name", 10, 3, 2);
		Player testPlayer2 = new Player(position, "name", 10, 3, 2);
		assertNotEquals(testPlayer1, testPlayer2);
	}
}
