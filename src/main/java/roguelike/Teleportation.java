package roguelike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.awt.*;
import java.util.Collections;

public class Teleportation implements Ability {
	private String name;
	private int requiredIntelligence;

	public Teleportation(String name, int requiredIntelligence) {
		this.name = name;

//		if (requiredIntelligence <1 || requiredIntelligence > 99) {
//			throw new IllegalArgumentException();
//		}
		this.requiredIntelligence = requiredIntelligence;
	}

	public void use(Player player, Level level) {
		if (player.getIntelligence() >= requiredIntelligence) {
			ArrayList<Point> possiblePositions = level.getAvailablePlaces();
			if (possiblePositions.size() > 0) {
				Collections.shuffle(possiblePositions);
				level.placeEntity(player, possiblePositions.get(0));

			}
			player.setIntelligence(player.getIntelligence()-requiredIntelligence) ;
		}
	}
	
	public String getName() {
		return name;
	}
}
