package game.elements.monsters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), image);
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
