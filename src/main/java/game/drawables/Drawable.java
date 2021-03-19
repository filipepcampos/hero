package game.drawables;

import game.gui.GenericTextGraphics;

import java.io.IOException;

public interface Drawable {
    void draw(GenericTextGraphics graphics) throws IOException;
}
