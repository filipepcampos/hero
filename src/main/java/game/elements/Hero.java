package game.elements;

import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class Hero extends Element{
    int hp = 5;

    public Hero(int x, int y){
        super(x,y);
    }

    public void draw(GenericTextGraphics graphics) throws IOException{
        graphics.setForegroundColor("#00C5C6");
        graphics.enableModifiers("BOLD");
        graphics.putString(position, "X");
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
