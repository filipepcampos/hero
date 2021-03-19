package game.drawables.elements;

import game.Controllable;
import game.gameMove;
import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class Hero extends Element implements Controllable {
    int hp = 5;

    public Hero(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(GenericTextGraphics graphics) throws IOException{
        graphics.setForegroundColor("#00C5C6");
        graphics.enableModifiers("BOLD");
        graphics.putString(position, "X");
    }

    public void loseHP(){
        if(hp > 0){
            hp--;
        }
    }

    public int getHP(){
        return hp;
    }

    private Position moveUp(){
        return new Position(position.getX(), position.getY()-1);
    }

    private Position moveDown(){
        return new Position(position.getX(), position.getY()+1);
    }

    private Position moveLeft(){
        return new Position(position.getX()-1, position.getY());
    }

    private Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }

    @Override
    public Position move(gameMove move) {
        switch (move){
            case up: return moveUp();
            case down: return moveDown();
            case left: return moveLeft();
            case right: return moveRight();
        }
        return this.position;
    }
}
