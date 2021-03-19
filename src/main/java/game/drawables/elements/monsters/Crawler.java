package game.drawables.elements.monsters;

import game.gui.GenericTextGraphics;
import game.util.Position;

import java.util.Random;

public class Crawler extends Monster{
    String image;
    boolean vertical;
    int direction;
    int maxNumSteps;
    int currentNumSteps = 0;

    public Crawler(int x, int y){
        super(x,y);
        Random random = new Random();
        vertical = random.nextBoolean();
        direction = random.nextBoolean() ? 1 : -1;
        maxNumSteps = 3+random.nextInt(30);
        image = vertical ? "|" : "-";
    }

    @Override
    public void draw(GenericTextGraphics graphics) {
        graphics.setForegroundColor("#FF0000");
        graphics.putString(position, image);
    }

    @Override
    public Position move() {
        if(currentNumSteps==maxNumSteps){
            direction *= -1;
            currentNumSteps = 0;
        }
        currentNumSteps++;

        return vertical ? new Position(position.getX(), position.getY()+direction) : new Position(position.getX()+direction, position.getY());
    }
}
