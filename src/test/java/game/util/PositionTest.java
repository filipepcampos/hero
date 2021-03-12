package game.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    void constructor(){
        Position pos = new Position(-5, 10);

        Assertions.assertEquals(pos.getX(), -5);
        Assertions.assertEquals(pos.getY(), 10);
    }

    @Test
    void set(){
        Position pos = new Position(0, 0);
        pos.setX(50);
        pos.setY(75);

        Assertions.assertEquals(pos.getX(), 50);
        Assertions.assertEquals(pos.getY(), 75);
    }

    @Test
    void equals(){
        Position pos1 = new Position(10, 10);
        Position pos2 = new Position(10, 10);
        Assertions.assertEquals(pos1, pos2);
    }
}
