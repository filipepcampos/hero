package game.elements;

import game.gui.GenericTextGraphics;

public class Coin extends Element {
    public Coin(int x, int y){
        super(x,y);
    }

    public void draw(GenericTextGraphics graphics){
        graphics.setForegroundColor("#D4AF37");
        graphics.enableModifiers("BOLD");
        graphics.putString(position, "O");
    }
}
