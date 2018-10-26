package roguelike;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.Test;

public class ActorTest {
	Level level= new Level("levelName");
	//Point point = new Point(1,1);
	
	@Test
	public void negativeHP() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", -10, 5, 2, 10);
		});
	}
	
	@Test
	public void negativeStrength() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", 10, 5, 2, -10);
		});
	}
	
	@Test
	public void negativeSpeed() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", 10, -5, 2, 10);
		});	
	}
	
	@Test
	public void negativeIntelligence() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", 10, 5, -2, 10);
		});	
	}

	
	@Test
	public void listStats() {
		int healthPoints = 10;
		int speed = 5;
		int intelligence = 7;
		int strength = 5;
		Player player = new Player(level, "name", healthPoints, speed, intelligence, strength);

		assertEquals(player.getStats(),"healthPoints: " + healthPoints + "\nSpeed: " + speed + "\nIntelligence: " + intelligence + "\nStrength: " + strength);

    }
	
	@Test
	public void attackedByPlayerWithWeaponAndHelmet() {
		Actor actor = new Actor(level, "name", 10, 5, 7, 5);
		Player player = new Player(level, "name", 10, 5, 7, 5);
		Item weapon = new Item(level, "testName", EquipmentSlot.WEAPON, 5, 3);
		Item helmet = new Item(level, "testName", EquipmentSlot.HELMET, 5, 3);
		
		player.addItemToInventory(weapon);
		player.addItemToInventory(helmet);
		player.equip(weapon);
		player.equip(helmet);
		
		int playerDamage = player.calculateDamage();
		int actorDamage = actor.calculateDamage();
		int expectedHealth = actor.getHealthPoints() - (playerDamage + actorDamage / 5);
		
		actor.onContact(player, level);
		assertEquals(expectedHealth, actor.getHealthPoints());
	}
	
	@Test
	public void attackedByPlayerWithWeaponAndShieldAndHelmet() {
		Actor actor = new Actor(level, "name", 25, 5, 7, 5);
		Player player = new Player(level, "name", 10, 5, 7, 5);
		Item weapon = new Item(level, "testName", EquipmentSlot.WEAPON, 5, 3);
		Item shield = new Item(level, "testName", EquipmentSlot.SHIELD, 5, 3); 
		Item helmet = new Item(level, "testName", EquipmentSlot.HELMET, 5, 3);
		
		player.addItemToInventory(weapon);
		player.addItemToInventory(helmet);
		player.addItemToInventory(shield);
		player.equip(weapon);
		player.equip(helmet);
		player.equip(shield);
		actor.onContact(player, level);
		int expectedActorHealth = 9;
		
		assertEquals(expectedActorHealth, actor.getHealthPoints());
	}
	
	@Test
	public void attackedByPlayerWithWeaponAndLegsAndTorsoAndHelmet() {
		Actor actor = new Actor(level, "name", 25, 5, 7, 20);
		Player player = new Player(level, "name", 10, 5, 7, 5);
		Item weapon = new Item(level, "testName", EquipmentSlot.WEAPON, 5, 3);
		Item torso = new Item(level, "testName", EquipmentSlot.TORSO, 5, 3); 
		Item helmet = new Item(level, "testName", EquipmentSlot.HELMET, 5, 3);
		Item legs = new Item(level, "testName", EquipmentSlot.LEGS, 5, 3);
		
		player.addItemToInventory(weapon);
		player.addItemToInventory(helmet);
		player.addItemToInventory(torso);
		player.addItemToInventory(legs);
		player.equip(weapon);
		player.equip(helmet);
		player.equip(torso);
		player.equip(legs);
		actor.onContact(player, level);
		
		int expectedActorHealth = 6;
		int expectedPlayerHealth = 3;
		
		assertEquals(expectedActorHealth, actor.getHealthPoints());
		assertEquals(expectedPlayerHealth, player.getHealthPoints());
	}
	
	@Test
	public void attackedByPlayerWithAllEquipmentTypes() {
		Actor actor = new Actor(level, "name", 25, 5, 7, 20);
		Player player = new Player(level, "name", 10, 5, 7, 5);
		Item weapon = new Item(level, "testName", EquipmentSlot.WEAPON, 5, 3);
		Item shield = new Item(level, "testName", EquipmentSlot.SHIELD, 5, 3);
		Item torso = new Item(level, "testName", EquipmentSlot.TORSO, 5, 3); 
		Item helmet = new Item(level, "testName", EquipmentSlot.HELMET, 5, 3);
		Item legs = new Item(level, "testName", EquipmentSlot.LEGS, 5, 3);
		
		player.addItemToInventory(weapon);
		player.addItemToInventory(helmet);
		player.addItemToInventory(torso);
		player.addItemToInventory(legs);
		player.addItemToInventory(shield);
		player.equip(weapon);
		player.equip(helmet);
		player.equip(torso);
		player.equip(legs);
		player.equip(shield);
		actor.onContact(player, level);
		
		int expectedActorHealth = 1;
		int expectedPlayerHealth = 4;
		
		assertEquals(expectedActorHealth, actor.getHealthPoints());
		assertEquals(expectedPlayerHealth, player.getHealthPoints());
	}

}
