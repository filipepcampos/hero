package game.elements;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

import game.util.Position;

public abstract class Element {
    Position position;

    Element(int x, int y){
        position = new Position(x,y);
    }

    public abstract void draw(TextGraphics graphics) throws IOException;

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }
}
