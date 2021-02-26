import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public abstract class Element {
    Position position;

    Element(int x, int y){
        position = new Position(x,y);
    }

    public abstract void draw(TextGraphics graphics) throws IOException;

    public Position getPosition(){
        return this.position;
    }
}
