package roguelike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.awt.*;

public class Teleport implements Ability {
	private String name;
	private  int requiredIntelligence ;

	public void use(Player player, Level level) {
		ArrayList<Point> possiblePositions = new ArrayList<>(level.getPositions());
		if (player.intelligence >= requiredIntelligence) {
			Random rand = new Random();
			int positionIndex = rand.nextInt(possiblePositions.size());
			boolean illegalPlace = true;
			while (illegalPlace) {
				Point place = possiblePositions.get(positionIndex);
				illegalPlace = !level.positionAvailable(place);
				if (!illegalPlace) {
					level.placeEntity(player, place);
					player.intelligence -= requiredIntelligence;

				} else {
					positionIndex = rand.nextInt(possiblePositions.size());
				}
			}
		}
	}

	public Teleport(String name, int requiredIntelligence) {
		this.name = name;
		this.requiredIntelligence = requiredIntelligence;
	}

	public String getName() {
		return name;
	}
}
