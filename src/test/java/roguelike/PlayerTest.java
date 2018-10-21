package roguelike;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

	private Point position = new Point(1, 1);

	@Test
	void setupPlayerObject() throws Exception {
		Player testPlayer = new Player(position, "name", 10, 3, 2);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
    public void nonEqualObjects(){
		Player testPlayer1 = new Player(position, "name", 10, 3, 2);
		Player testPlayer2 = new Player(position, "name", 10, 3, 2);
		assertNotEquals(testPlayer1, testPlayer2);
	}

    @Test
    void moveUp() {
	    Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2);
        Point newPoint = new Point(1,2);
        player.moveUp(level);
        assertEquals(player.position, newPoint);
    }

    @Test
    public void moveDown() {
        Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2);
        Point newPoint = new Point(1,0);
        player.moveDown(level);
        assertEquals(player.position, newPoint);
    }

    @Test
    public void moveLeft() {
        Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2);
        player.moveLeft(level);
        Point newPoint = new Point(0, 1);
        assertEquals(player.position, newPoint);
    }

    @Test
    public void addExtraLives(){
	    Player player = new Player(position, "name", 10, 3, 2);
	    player.addExtraLives(1);
        Assertions.assertEquals(player.getExtraLives(), 1);
    }
    @Test
    public void moveRight() {
        Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2);
        player.moveRight(level);
        Point newPoint = new Point(2,1);
        assertEquals(player.position, newPoint);
    }

    @Test
    void attackWeakerMonster(){
        Player player = new Player(position, "player", 10, 3, 2);
        Monster monster = new Monster(position, "Monster1", 8, 3, 2);
        player.attack(monster);
        assertEquals(player.getHealthPoints(), 8);

    }

    @Test
    void attackEqualMonster(){
        Player player = new Player(position, "Player1", 10, 3, 2);
        Monster monster = new Monster(position, "Monster1", 10, 3, 2);
        player.attack(monster);
        assertEquals(player.getHealthPoints(), 6);
    }

    @Test
    void attackStrongerMonster(){
        Player player = new Player(position, "Player1", 8, 3, 2);
        Monster monster = new Monster(position, "Monster1", 10, 3, 2);
        player.attack(monster);
        Assertions.assertEquals(player.getHealthPoints(), 2);
    }
}
