
package roguelike;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

public class PlayerTest {

	Player testPlayer;
	Point position;
	Item testItem;

	@BeforeEach
	void setUp() throws Exception {
		testPlayer = new Player(position, "name", 10, 3, 2,4);
		position = new Point(1, 1);
		testItem = new Item("testName");
	}

	@Test

	void nonEqualStats() {
		Player testPlayer1 = new Player(position, "name", 10, 9, 4,4);
		Player testPlayer2 = new Player(position, "name", 10, 9, 4,4);
		assertNotEquals(testPlayer1, testPlayer2);
	}
	
	@Test
	void listStats() {
		int healthPoints = 10;
		int speed = 5;
		int intelligence = 7;
		Player player = new Player(position, "name", healthPoints, speed, intelligence,8);
		assertNotEquals(player.getStats(),"healthPoints: " + healthPoints + "\nSpeed: " + speed + "\nIntelligence: " + intelligence);

    }

	void testAddItemToInventory_returnsAddedItem() {
		testPlayer.addItemToInventory(testItem);

		assertTrue(testPlayer.itemExistsInInventory(testItem));

	}

	@Test
	void testAddItemToInventory_addsOnlyOneItem() {
		testPlayer.addItemToInventory(testItem);

		assertEquals(1, testPlayer.getInventorySize());// det r�tta f�rv�ntade
														// v�rdet skrivs som
														// f�rsta parametern

	}

	@Test
	void testGetAllItemsFromInventory() {
		Item testItem2 = new Item("Candy");
		Item testItem3 = new Item("Shield");

		testPlayer.addItemToInventory(testItem);
		testPlayer.addItemToInventory(testItem2);
		testPlayer.addItemToInventory(testItem3);

		assertTrue(testPlayer.getItemsFromInventory().contains(testItem));
		assertTrue(testPlayer.getItemsFromInventory().contains(testItem2));
		assertTrue(testPlayer.getItemsFromInventory().contains(testItem3));

	}

	@Test
	void testAddTooManyItemsToInventory() {
		for (int index = 0; index < 30; index++) {
			assertTrue(testPlayer.addItemToInventory(new Item("Weapon" + index)));
		}
		assertFalse(testPlayer.addItemToInventory(testItem), "Exceeded item limit");

	}

	@Test
	void testThatItemWasDropped() {
		Item testItem2 = new Item("Candy");

		testPlayer.addItemToInventory(testItem);
		testPlayer.addItemToInventory(testItem2);

		int inventoryBefore = testPlayer.getInventorySize();

		assertTrue(testPlayer.dropItemFromInventory(testItem2));
		assertEquals(inventoryBefore, testPlayer.getInventorySize() + 1);

	}
	@Test
	void failMovingToRandomPlace() {
		Player testPlayer3 = new Player(position, "name3", 10, 3, 0,8);
		// testPlayer3.moveToRandomPlace();
		assertEquals(testPlayer3.moveToRandomPlace(), position);
	}

	@Test
	void succeedInMovingToRandomPlace() {
		Player testPlayer4 = new Player(position, "name3", 10, 3, 1,8);
		/**
		 * ordning påverkar resultat
		 */
		// failed
		// assertNotEquals(testPlayer4.moveToRandomPlace(), testPlayer4.position);
		assertNotEquals(testPlayer4.position, testPlayer4.moveToRandomPlace());
	}
	@Test
	void failThrowingFire() {
		Player testPlayer5 = new Player(position, "name3", 10, 3, 1,8);
		assertEquals(testPlayer5.throwFire(), null);
	}
	@Test
	void succeedInThrowingFireRightwards() {
		Player testPlayer6 = new Player(position, "name3", 10, 3, 3,8);
		testPlayer6.moveRight();
		//arrayen måste vara exakt samma
		//Point[] points = { new Point(5, 1), new Point(3, 1), new Point(4, 1) };
		Point[] points = { new Point(3, 1), new Point(4, 1), new Point(5, 1) };
		assertArrayEquals(points, testPlayer6.throwFire());
	}
	@Test
	void succeedInThrowingFireUpwards(){	
		Player testPlayer7 = new Player(new Point(10,10), "name3", 10, 3, 3,8);	
		Point[] points = { new Point(10, 9), new Point(10, 8), new Point(10, 7) };
		assertArrayEquals(points, testPlayer7.throwFire());
	}


	@Test
	void setupPlayerObject() {
		Player testPlayer = new Player(position, "name", 10, 3, 2, 5);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
    public void nonEqualObjects(){
		Player testPlayer1 = new Player(position, "name", 10, 3, 2, 5);
		Player testPlayer2 = new Player(position, "name", 10, 3, 2, 5);
		assertNotEquals(testPlayer1, testPlayer2);
	}

    @Test
    void moveUp() {
	    Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2, 5);
        Point newPoint = new Point(1,2);
        player.moveUp(level);
        assertEquals(player.position, newPoint);
    }

    @Test
    public void moveDown() {
        Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2, 5);
        Point newPoint = new Point(1,0);
        player.moveDown(level);
        assertEquals(player.position, newPoint);
    }

    @Test
    public void moveLeft() {
        Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2, 5);
        player.moveLeft(level);
        Point newPoint = new Point(0, 1);
        assertEquals(player.position, newPoint);
    }

    @Test
    public void addExtraLives(){
	    Player player = new Player(position, "name", 10, 3, 2, 5);
	    player.addExtraLives(1);
        assertEquals(player.getExtraLives(), 1);
    }
    @Test
    public void moveRight() {
        Level level = new Level();
        Player player = new Player(position, "name", 10, 3, 2, 5);
        player.moveRight(level);
        Point newPoint = new Point(2,1);
        assertEquals(player.position, newPoint);
    }

    @Test
    void attackWeakerMonster(){
        Player player = new Player(position, "player", 10, 3, 2, 5);
        Monster monster = new Monster(position, "Monster1", 8, 3, 2, 5);
        player.takeDamage(3);
        assertEquals(player.getHealthPoints(), 8);

    }

    @Test
    void attackEqualMonster(){
        Player player = new Player(position, "Player1", 10, 3, 2, 5);
        Monster monster = new Monster(position, "Monster1", 10, 3, 2, 5);
        player.takeDamage(3);
        assertEquals(player.getHealthPoints(), 6);
    }

    @Test
    void attackStrongerMonster(){
        Player player = new Player(position, "Player1", 8, 3, 2,5 );
        Monster monster = new Monster(position, "Monster1", 10, 3, 2, 5);
        player.takeDamage(3);
       assertEquals(player.getHealthPoints(), 2);
    }
}
