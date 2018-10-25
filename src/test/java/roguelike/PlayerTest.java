package roguelike;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	Player testPlayer;
	Level level;
	Item testItem;

	@BeforeEach
	void setUp() throws Exception {

		testPlayer = new Player(level, "name", 10, 3, 2, 10);
		level = new Level("levelName");
		testItem = new Item(level, "testName", EquipmentSlot.WEAPON, 0, 0);
	}

	@Test

	void nonEqualStats() {
		Player testPlayer1 = new Player(level, "name", 10, 9, 4, 10);
		Player testPlayer2 = new Player(level, "name", 10, 9, 4, 10);
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
		Item testItem2 = new Item(level, "Candy", EquipmentSlot.TORSO, 0, 0);
		Item testItem3 = new Item(level, "Shield", EquipmentSlot.SHIELD, 0, 0);

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
					.addItemToInventory(new Item(level, "Weapon" + index, EquipmentSlot.WEAPON, 0, 0)));
		}
		assertFalse(testPlayer.addItemToInventory(testItem), "Exceeded item limit");

	}

	@Test
	void testThatItemWasDropped() {
		Item testItem2 = new Item(level, "Candy", EquipmentSlot.HELMET, 0, 0);

		testPlayer.addItemToInventory(testItem);
		testPlayer.addItemToInventory(testItem2);

		int inventoryBefore = testPlayer.getInventorySize();

		assertTrue(testPlayer.dropItemFromInventory(testItem2));
		assertEquals(inventoryBefore, testPlayer.getInventorySize() + 1);

	}

	@Test
	void testDropNonExistantItem() {
		assertFalse(testPlayer.dropItemFromInventory(testItem));
	}

	@Test
	void setupPlayerObject() {
		Player testPlayer = new Player(level, "name", 10, 3, 2, 5);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
	public void nonEqualObjects() {
		Player testPlayer1 = new Player(level, "name", 10, 3, 2, 5);
		Player testPlayer2 = new Player(level, "name", 10, 3, 2, 5);
		assertNotEquals(testPlayer1, testPlayer2);
	}

	@Test
	void moveLeft() {
		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
				level.addPosition(new Point(i,j));
			}
		}
		
		Player player = new Player(level, "name", 10, 3, 2, 5);
		level.placeEntity(player, new Point(2,2));

		Point newPoint = new Point(1,2);
		player.move(Direction.LEFT);
		assertEquals(newPoint,level.getEntityPlacement(player));
	}


	@Test
	public void addExtraLives() {
		Player player = new Player(level, "name", 10, 3, 2, 5);
		player.addExtraLives(1);
		assertEquals(player.getLives(), 1);
	}

	

	@Test
	void attackWeakerMonster() {
		Player player = new Player(level, "player", 10, 3, 2, 5);
		Actor monster = new Actor(level, "Monster1", 8, 3, 2, 5);
		player.takeDamage(3);
		assertEquals(player.getHealthPoints(), 7);

	}

	@Test
	void attackEqualMonster() {
		Player player = new Player(level, "Player1", 10, 3, 2, 5);
		Actor monster = new Actor(level, "Monster1", 10, 3, 2, 5);
		player.takeDamage(3);
		assertEquals(player.getHealthPoints(), 7);
	}

	@Test
	void attackStrongerMonster() {
		Player player = new Player(level, "Player1", 8, 3, 2, 5);
		Actor monster = new Actor(level, "Monster1", 10, 3, 2, 5);
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
		Item helmet1 = new Item(level, "Candy", EquipmentSlot.HELMET, 0, 0);
		Item helmet2 = new Item(level, "Candy", EquipmentSlot.HELMET, 0, 0);

		testPlayer.addItemToInventory(helmet1);
		testPlayer.addItemToInventory(helmet2);

		testPlayer.equip(helmet1);
		testPlayer.equip(helmet2); // testItem has helmet

		assertEquals(helmet2, testPlayer.getSlot(EquipmentSlot.HELMET));
	}

	@Test
	void attackWithWeapon() {
		Item weapon = new Item(level, "testName", EquipmentSlot.WEAPON, 5, 0);
		Actor actor = new Actor(level, "name", 20, 10, 10, 1);
		Level level = new Level("level");
		
		testPlayer.addItemToInventory(weapon);
		testPlayer.equip(weapon);
		actor.onContact(testPlayer, level);
		
		assertEquals(5, actor.getHealthPoints());
	}

	@Test
	void takeDamageWithArmor() {
		Item shield = new Item(level, "testName", EquipmentSlot.SHIELD, 5, 2);
		Item helmet = new Item(level, "testName", EquipmentSlot.HELMET, 5, 3);
		
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
