package game.gui;

import game.util.Position;

public interface GenericTextGraphics {
    void setForegroundColor(String color);
    void enableModifiers(String modifier);
    void putString(Position pos, String str);
    void setBackgroundColor(String color);
    void fillRectangle(Position pos, int width, int height);
}
