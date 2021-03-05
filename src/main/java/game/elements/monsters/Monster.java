package game.elements.monsters;

import com.googlecode.lanterna.graphics.TextGraphics;
import game.elements.Element;
import game.util.Position;

public abstract class Monster extends Element {
    public Monster(int x, int y){
        super(x,y);
    }

    public abstract Position move();

    public abstract void draw(TextGraphics graphics);
}
