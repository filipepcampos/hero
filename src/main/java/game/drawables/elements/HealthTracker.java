package game.drawables.elements;

import game.drawables.HealthObserver;
import game.drawables.HealthSubject;

import java.util.ArrayList;
import java.util.List;

public class HealthTracker implements HealthSubject {
    List<HealthObserver> observers;
    int hp;
    public HealthTracker(int hp){
        this.hp = hp;
        this.observers = new ArrayList<HealthObserver>();
    }

    public int getHp() {
        return hp;
    }

    private void setHp(int newHp){
        this.hp = newHp;
        notifyObservers();
    }

    public void loseHp(){
        setHp(hp-1);
    }

    public void loseHp(int n){
        setHp(hp-n);
    }

    public void gainHp(){
        setHp(hp+1);
    }

    public void gainHp(int n){
        setHp(hp+n);
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
