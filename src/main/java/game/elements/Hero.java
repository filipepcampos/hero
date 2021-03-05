package game.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import game.util.Position;

import java.io.IOException;

public class Hero extends Element{
    int hp = 5;

    public Hero(int x, int y){
        super(x,y);
    }

    public void draw(TextGraphics graphics) throws IOException{
        graphics.setForegroundColor(TextColor.Factory.fromString("#00C5C6"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }

    public void loseHP(){
        hp--;
    }

    public int getHP(){
        return hp;
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY()-1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY()+1);
    }

    public Position moveLeft(){
        return new Position(position.getX()-1, position.getY());
    }

    public Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }
}
