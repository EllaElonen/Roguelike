import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class PlayerTest {

	@Test
	void DifferentObjects(){
		
        Player player1 = new Player();
        Player player2 = new Player();
        assertNotEquals(player1, player2);
    }
}
