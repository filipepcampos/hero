package game.elements.monsters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.util.Position;

import java.util.Random;

public class Zombie extends  Monster{
    public Zombie(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#03AC13"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "Z");
    }

    @Override
    public Position move() {
        Random random = new Random();
        switch(random.nextInt(4)){
            case 0: return new Position(position.getX()-1, position.getY());
            case 1: return new Position(position.getX()+1, position.getY());
            case 2: return new Position(position.getX(), position.getY()-1);
            case 3: return new Position(position.getX(), position.getY()+1);
        }
        return null;
    }
}
