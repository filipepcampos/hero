package game.drawables.elements;

import game.gui.GenericTextGraphics;
import game.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoinTest {
    @Test
    void constructor(){
        Coin c = new Coin(5, 10);
        Assertions.assertEquals(c.getPosition(), new Position(5, 10));
    }

    @Test
    void draw(){
        GenericTextGraphics graphics = Mockito.mock(GenericTextGraphics.class);
        Coin c = new Coin(5, 10);
        c.draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).putString(Mockito.any(), Mockito.anyString());
    }
}
