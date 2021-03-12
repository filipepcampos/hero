package game.elements;

import game.gui.GenericTextGraphics;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }

    public void draw(GenericTextGraphics graphics){
        graphics.setBackgroundColor("#616161");
        graphics.enableModifiers("BOLD");
        graphics.putString(position, " ");
    }
}
