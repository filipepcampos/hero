package game.elements.monsters;

import game.gui.GenericTextGraphics;
import game.util.Position;

import java.util.Random;

public class Zombie extends  Monster{
    public Zombie(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(GenericTextGraphics graphics) {
        graphics.setForegroundColor("#03AC13");
        graphics.putString(position, "Z");
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
