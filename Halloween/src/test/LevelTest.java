import Roguelike.Level;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.util.HashSet;

public class LevelTest {

    @Test
    public void addTwoIdenticalPositions(){
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,1);
        Level level = new Level();
        level.addPosition(p1);
        level.addPosition(p2);
        HashSet <Point> positions = level.getPositions();
        assertEquals(positions.size(), 1);
    }
    @Test
    public void movePlayerToUnavailablePosition(){
        Level level = new Level();
        level.addPosition(new Point(1,1));
        level.addPosition((new Point(1,2)));
        Player player = new Player(new Point(1,1), "name", 10, 3, 2);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            level.moveEntityToPosition(player, new Point(2,2));
        });
    }
}
