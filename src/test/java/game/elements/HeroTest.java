package game.elements;

import game.gui.GenericTextGraphics;
import game.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class HeroTest {
    @Test
    void constructor(){
        Hero h = new Hero(5, 5);

        Assertions.assertEquals(h.getHP(), 5);
        Assertions.assertEquals(h.getPosition(), new Position(5, 5));
    }

    @Test
    void loseHP(){
        Hero h = new Hero(0, 0);

        for(int i = 5; i >= 0; i--){
            Assertions.assertEquals(h.getHP(), i);
            h.loseHP();
        }
        h.loseHP();
        Assertions.assertEquals(h.getHP(), 0);
    }

    @Test
    void draw() throws IOException {
        Hero h = new Hero(0, 0);

        GenericTextGraphics graphics = Mockito.mock(GenericTextGraphics.class);
        h.draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).putString(Mockito.any(), Mockito.anyString());
    }

    @Test
    void move(){
        Hero h = new Hero(1, 1);
        Assertions.assertEquals(h.moveDown(), new Position(1, 2));
        Assertions.assertEquals(h.moveUp(), new Position(1, 0));
        Assertions.assertEquals(h.moveLeft(), new Position(0, 1));
        Assertions.assertEquals(h.moveRight(), new Position(2, 1));
    }
}
