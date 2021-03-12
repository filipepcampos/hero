package game.userInterface;

import com.googlecode.lanterna.graphics.TextGraphics;
import game.gui.GenericTextGraphics;

import java.io.IOException;

public abstract class InterfaceElement {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public InterfaceElement(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(GenericTextGraphics graphics) throws IOException;
}
