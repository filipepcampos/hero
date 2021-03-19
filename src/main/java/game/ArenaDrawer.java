package game;

import game.drawables.Drawable;
import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class ArenaDrawer {
    public void draw(Arena arena, GenericTextGraphics graphics) throws IOException {
        graphics.setBackgroundColor("#ECECEC");
        graphics.fillRectangle(new Position(0, 0), arena.getWidth(), arena.getHeight());
        for(Drawable d : arena.getDrawables()) {
            graphics.setBackgroundColor("#ECECEC");
            d.draw(graphics);
        }
    }
}
