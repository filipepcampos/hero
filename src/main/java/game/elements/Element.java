package game.elements;

import java.io.IOException;

import game.gui.GenericTextGraphics;
import game.util.Position;

public abstract class Element {
    protected Position position;

    public Element(int x, int y){
        position = new Position(x,y);
    }

    public abstract void draw(GenericTextGraphics graphics) throws IOException;

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }
}
