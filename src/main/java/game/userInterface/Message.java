package game.userInterface;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(x, y), message);
    }
}
