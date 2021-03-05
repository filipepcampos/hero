package game.userInterface;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class HealthBar extends InterfaceElement{
    private int hp;

    public HealthBar(int x, int y, int width, int height, int maxHP){
        super(x, y, width, height);
        this.hp = maxHP;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        int healthX = this.x + 1;
        for(int i = 0; i < hp; ++i){
            if(healthX > x+width){
                break;
            }
            graphics.putString(new TerminalPosition(healthX, y), " ");
            healthX++;
        }
    }

    public void setHP(int hp){
        this.hp = hp;
    }

    public int getHP(int hp){
        return this.hp;
    }
}

