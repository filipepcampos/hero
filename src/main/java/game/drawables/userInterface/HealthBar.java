package game.drawables.userInterface;

import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class HealthBar extends InterfaceElement{
    private int hp;

    public HealthBar(int x, int y, int width, int height, int maxHP){
        super(x, y, width, height);
        this.hp = maxHP;
    }

    @Override
    public void draw(GenericTextGraphics graphics) throws IOException {
        graphics.setBackgroundColor("#FF0000");
        graphics.enableModifiers("BOLD");
        int healthX = this.x + 1;
        for(int i = 0; i < hp; ++i){
            if(healthX > x+width){
                break;
            }
            graphics.putString(new Position(healthX, y), " ");
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

