package game.drawables.elements;

import game.gui.GenericTextGraphics;
import game.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ElementTest {
    class ElementStub extends Element{
        public ElementStub(int x, int y) {
            super(x, y);
        }

        @Override
        public void draw(GenericTextGraphics graphics) throws IOException {
            return;
        }
    }

    @Test
    void constructor(){
        Element e = new ElementStub(5, 10);
        Assertions.assertEquals(e.getPosition(), new Position(5, 10));
    }

    @Test
    void setPos(){
        Element e = new ElementStub(5, 10);
        e.setPosition(new Position(7, 9));
        Assertions.assertEquals(e.getPosition(), new Position(7, 9));
    }
}
