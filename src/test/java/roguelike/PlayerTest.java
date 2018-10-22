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

		assertEquals(1, testPlayer.getInventorySize());// det rätta förväntade
														// värdet skrivs som
														// första parametern

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


}
