package game.drawables.elements;

import java.io.IOException;

import game.drawables.Drawable;
import game.gui.GenericTextGraphics;
import game.util.Position;

public abstract class Element implements Drawable {
    protected Position position;

    public Element(int x, int y){
        position = new Position(x,y);
    }

    @Override
    public abstract void draw(GenericTextGraphics graphics) throws IOException;

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }
}
