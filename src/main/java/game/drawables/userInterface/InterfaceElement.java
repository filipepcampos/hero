package game.drawables.userInterface;

import game.drawables.Drawable;
import game.gui.GenericTextGraphics;

import java.io.IOException;

public abstract class InterfaceElement  implements Drawable {
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
