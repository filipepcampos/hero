package game.drawables.elements;

import game.Controllable;
import game.HealthObserver;
import game.HealthSubject;
import game.gameMove;
import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hero extends Element implements Controllable, HealthSubject {
    List<HealthObserver> observers;
    int hp = 5;

    public Hero(int x, int y){
        super(x,y);
        this.observers = new ArrayList<HealthObserver>();
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
            notifyObservers();
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

    @Override
    public void attach(HealthObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detatch(HealthObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(HealthObserver observer : observers){
            observer.updateHealth(this.hp);
        }
    }
}
