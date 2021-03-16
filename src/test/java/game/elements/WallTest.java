package game.elements;

import game.gui.GenericTextGraphics;
import game.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WallTest {
    @Test
    void constructor(){
        Wall w = new Wall(5, 10);
        Assertions.assertEquals(w.getPosition(), new Position(5, 10));
    }

    @Test
    void draw(){
        GenericTextGraphics graphics = Mockito.mock(GenericTextGraphics.class);
        Wall w = new Wall(5, 10);
        w.draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).putString(Mockito.any(), Mockito.anyString());
    }
}
