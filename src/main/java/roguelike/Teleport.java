package roguelike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.awt.*;
import java.util.Collections;

public class Teleport implements Ability {
	private String name;
	private int requiredIntelligence;

	public Teleport(String name, int requiredIntelligence) {
		this.name = name;

		if (requiredIntelligence <1 || requiredIntelligence > 99) {
			throw new IllegalArgumentException();
		}
		this.requiredIntelligence = requiredIntelligence;
	}

	public void use(Player player, Level level) {
		if (player.intelligence >= requiredIntelligence) {
			ArrayList<Point> possiblePositions = level.getAvailablePlaces();
			if (possiblePositions.size() > 0) {
				Collections.shuffle(possiblePositions);
				level.placeEntity(player, possiblePositions.get(0));

			}
			player.intelligence -= requiredIntelligence;
		}
	}
}
