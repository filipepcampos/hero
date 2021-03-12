package game.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.util.Position;

public class LanternaTextGraphics implements GenericTextGraphics {
    TextGraphics graphics;

    public LanternaTextGraphics(TextGraphics newTextGraphics) {
        this.graphics = newTextGraphics;
    }

    @Override
    public void setForegroundColor(String color) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString(color));
    }

    @Override
    public void setBackgroundColor(String color){
        this.graphics.setBackgroundColor(TextColor.Factory.fromString(color));
    }

    @Override
    public void fillRectangle(Position pos, int width, int height) {
        graphics.fillRectangle(new TerminalPosition(pos.getX(),pos.getY()), new TerminalSize(width, height), ' ');
    }

    @Override
    public void enableModifiers(String modifier) { // TODO: This is kinda stupid
        if(modifier == "BOLD"){
            this.graphics.enableModifiers(SGR.BOLD);
        }
    }

    @Override
    public void putString(Position pos, String str) {
        this.graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), str);
    }
}
