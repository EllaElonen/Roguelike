package roguelike;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	Player testPlayer;
	Point position;
	Item testItem;

	@BeforeEach
	void setUp() throws Exception {

		testPlayer = new Player(position, "name", 10, 3, 2, 10);
		position = new Point(1, 1);
		testItem = new Item(new Point(1, 1), "testName", EquipmentSlot.WEAPON, 0, 0);
	}

	@Test

	void nonEqualStats() {
		Player testPlayer1 = new Player(position, "name", 10, 9, 4, 10);
		Player testPlayer2 = new Player(position, "name", 10, 9, 4, 10);
		assertNotEquals(testPlayer1, testPlayer2);
	}

	@Test
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
		Item testItem2 = new Item(new Point(1, 1), "Candy", EquipmentSlot.TORSO, 0, 0);
		Item testItem3 = new Item(new Point(1, 1), "Shield", EquipmentSlot.SHIELD, 0, 0);

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
			assertTrue(testPlayer
					.addItemToInventory(new Item(new Point(1, 1), "Weapon" + index, EquipmentSlot.WEAPON, 0, 0)));
		}
		assertFalse(testPlayer.addItemToInventory(testItem), "Exceeded item limit");

	}

	@Test
	void testThatItemWasDropped() {
		Item testItem2 = new Item(new Point(1, 1), "Candy", EquipmentSlot.HELMET, 0, 0);

		testPlayer.addItemToInventory(testItem);
		testPlayer.addItemToInventory(testItem2);

		int inventoryBefore = testPlayer.getInventorySize();

		assertTrue(testPlayer.dropItemFromInventory(testItem2));
		assertEquals(inventoryBefore, testPlayer.getInventorySize() + 1);

	}

	@Test
	void testDropNonExistantItem() {
		int inventoryBefore = testPlayer.getInventorySize();

		assertFalse(testPlayer.dropItemFromInventory(testItem));
	}

	@Test
	void setupPlayerObject() {
		Player testPlayer = new Player(position, "name", 10, 3, 2, 5);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
	public void nonEqualObjects() {
		Player testPlayer1 = new Player(position, "name", 10, 3, 2, 5);
		Player testPlayer2 = new Player(position, "name", 10, 3, 2, 5);
		assertNotEquals(testPlayer1, testPlayer2);
	}

	@Test
	void moveUp() {
		Level level = new Level("bla");
		Player player = new Player(position, "name", 10, 3, 2, 5);
		Point newPoint = new Point(1, 2);
		player.moveUp(level);
		assertEquals(player.position, newPoint);
	}

	@Test
	public void moveDown() {
		Level level = new Level("bla");
		Player player = new Player(position, "name", 10, 3, 2, 5);
		Point newPoint = new Point(1, 0);
		player.moveDown(level);
		assertEquals(player.position, newPoint);
	}

	@Test
	public void moveLeft() {
		Level level = new Level("bla");
		Player player = new Player(position, "name", 10, 3, 2, 5);
		player.moveLeft(level);
		Point newPoint = new Point(0, 1);
		assertEquals(player.position, newPoint);
	}

	@Test
	public void addExtraLives() {
		Player player = new Player(position, "name", 10, 3, 2, 5);
		player.addExtraLives(1);
		assertEquals(player.getExtraLives(), 1);
	}

	@Test
	public void moveRight() {
		Level level = new Level("bla");
		Player player = new Player(position, "name", 10, 3, 2, 5);
		player.moveRight(level);
		Point newPoint = new Point(2, 1);
		assertEquals(player.position, newPoint);
	}

	@Test
	void attackWeakerMonster() {
		Player player = new Player(position, "player", 10, 3, 2, 5);
		Monster monster = new Monster(position, "Monster1", 8, 3, 2, 5);
		player.takeDamage(3);
		assertEquals(player.getHealthPoints(), 7);

	}

	@Test
	void attackEqualMonster() {
		Player player = new Player(position, "Player1", 10, 3, 2, 5);
		Monster monster = new Monster(position, "Monster1", 10, 3, 2, 5);
		player.takeDamage(3);
		assertEquals(player.getHealthPoints(), 7);
	}

	@Test
	void attackStrongerMonster() {
		Player player = new Player(position, "Player1", 8, 3, 2, 5);
		Monster monster = new Monster(position, "Monster1", 10, 3, 2, 5);
		player.takeDamage(3);
		assertEquals(player.getHealthPoints(), 5);
	}

	@Test
	void equipWithWeapon() {
		testPlayer.addItemToInventory(testItem);
		testPlayer.equip(testItem);// testItem has slot weapon
		assertEquals(testItem, testPlayer.getSlot(EquipmentSlot.WEAPON));
	}

	@Test
	void equipWithNonexistentItem() {
		testPlayer.equip(testItem);// testItem has slot weapon
		assertNull(testPlayer.getSlot(EquipmentSlot.WEAPON));
	}

	@Test
	void switchEquipedHelmets() {
		Item helmet1 = new Item(new Point(1, 1), "Candy", EquipmentSlot.HELMET, 0, 0);
		Item helmet2 = new Item(new Point(1, 1), "Candy", EquipmentSlot.HELMET, 0, 0);

		testPlayer.addItemToInventory(helmet1);
		testPlayer.addItemToInventory(helmet2);

		testPlayer.equip(helmet1);
		testPlayer.equip(helmet2); // testItem has helmet

		assertEquals(helmet2, testPlayer.getSlot(EquipmentSlot.HELMET));
	}

	@Test
	void attackWithWeapon() {
		Item weapon = new Item(new Point(1, 1), "testName", EquipmentSlot.WEAPON, 5, 0);
		Actor actor = new Actor(position, "name", 20, 10, 10, 1);
		Level level = new Level("level");
		
		testPlayer.addItemToInventory(weapon);
		testPlayer.equip(weapon);
		actor.onContact(testPlayer, level);
		
		assertEquals(5, actor.getHealthPoints());
	}

	@Test
	void takeDamageWithArmor() {
		Item shield = new Item(new Point(1, 1), "testName", EquipmentSlot.SHIELD, 5, 2);
		Item helmet = new Item(new Point(1, 1), "testName", EquipmentSlot.HELMET, 5, 3);
		
		int damage=10;
		int effectiveDamage=damage-((shield.getPlusDefense() + helmet.getPlusDefense())/2);
		int expectedHealth=testPlayer.getHealthPoints() - effectiveDamage;
						

		testPlayer.addItemToInventory(shield);
		testPlayer.addItemToInventory(helmet);
		testPlayer.equip(shield);
		testPlayer.equip(helmet);
		testPlayer.takeDamage(damage);
		assertEquals(expectedHealth,testPlayer.getHealthPoints());
		
	}
}
