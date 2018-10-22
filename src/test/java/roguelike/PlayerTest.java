
package roguelike;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

public class PlayerTest {

	Player testPlayer;
	Point position;
	Item testItem;

	@BeforeEach
	void setUp() throws Exception {
		testPlayer = new Player(position, "name", 10, 3, 2);
		position = new Point(1, 1);
		testItem = new Item("testName");
	}

	@Test
	void setupPlayerObject() throws Exception {
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
	void nonEqualObjects() {

		Player testPlayer1 = new Player(position, "name", 10, 3, 2);

		Player testPlayer2 = new Player(position, "name", 10, 3, 2);

		assertNotEquals(testPlayer, testPlayer2);
	}

	@Test

	void nonEqualStats() {
		Player testPlayer1 = new Player(position, "name", 10, 9, 4);
		Player testPlayer2 = new Player(position, "name", 10, 9, 4);
		assertNotEquals(testPlayer1, testPlayer2);
	}
	
	@Test
	void listStats() {
		int healthPoints = 10;
		int speed = 5;
		int intelligence = 7;
		Player player = new Player(position, "name", healthPoints, speed, intelligence);
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
		Player testPlayer3 = new Player(position, "name3", 10, 3, 0);
		// testPlayer3.moveToRandomPlace();
		assertEquals(testPlayer3.moveToRandomPlace(), position);
	}

	@Test
	void succeedInMovingToRandomPlace() {
		Player testPlayer4 = new Player(position, "name3", 10, 3, 1);
		/**
		 * ordning påverkar resultat
		 */
		// failed
		// assertNotEquals(testPlayer4.moveToRandomPlace(), testPlayer4.position);
		assertNotEquals(testPlayer4.position, testPlayer4.moveToRandomPlace());
	}
	@Test
	void failThrowingFire() {
		Player testPlayer5 = new Player(position, "name3", 10, 3, 1);
		assertEquals(testPlayer5.throwFire(), null);
	}
	@Test
	void succeedInThrowingFireRightwards() {
		Player testPlayer6 = new Player(position, "name3", 10, 3, 3);
		testPlayer6.moveRight();
		//arrayen måste vara exakt samma
		//Point[] points = { new Point(5, 1), new Point(3, 1), new Point(4, 1) };
		Point[] points = { new Point(3, 1), new Point(4, 1), new Point(5, 1) };
		assertArrayEquals(points, testPlayer6.throwFire());
	}
	@Test
	void succeedInThrowingFireUpwards(){	
		Player testPlayer7 = new Player(new Point(10,10), "name3", 10, 3, 3);	
		Point[] points = { new Point(10, 9), new Point(10, 8), new Point(10, 7) };
		assertArrayEquals(points, testPlayer7.throwFire());
	}


}
