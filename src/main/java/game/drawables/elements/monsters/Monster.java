package game.drawables.elements.monsters;

import game.drawables.elements.Element;
import game.gui.GenericTextGraphics;
import game.util.Position;

public abstract class Monster extends Element{
    public Monster(int x, int y){
        super(x,y);
    }

    public abstract Position move();

    public abstract void draw(GenericTextGraphics graphics);
}
