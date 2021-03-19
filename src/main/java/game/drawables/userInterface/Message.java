package game.drawables.userInterface;

import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class Message extends InterfaceElement{
    String message = "";
    String color = "#FFFFFF";

    public Message(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw(GenericTextGraphics graphics) throws IOException {
        graphics.setForegroundColor(color);
        graphics.putString(new Position(x,y), message);
    }
}
