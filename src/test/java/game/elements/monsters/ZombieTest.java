package game.elements.monsters;

import game.gui.GenericTextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ZombieTest {
    @Test
    void move(){
        Zombie z = new Zombie(1, 1);

    }

    @Test
    void draw(){
        GenericTextGraphics graphics = Mockito.mock(GenericTextGraphics.class);
        Zombie z = new Zombie(5, 10);
        z.draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).putString(Mockito.any(), Mockito.anyString());
    }
}
