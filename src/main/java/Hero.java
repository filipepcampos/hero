import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    private int x;
    private int y;

    Hero(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Screen screen) throws IOException{
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp(){
        move(0,-1);
    }

    public void moveDown(){
        move(0,1);
    }

    public void moveLeft(){
        move(-1,0);
    }

    public void moveRight(){
        move(1,0);
    }

    private void move(int x, int y){
        this.x += x;
        this.y += y;
    }
}
