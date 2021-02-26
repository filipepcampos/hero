import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private Hero alfredo;
    private int width;
    private int height;

    Arena(int width, int height){
        this.width = width;
        this.height = height;
        alfredo = new Hero(10,10);
    }

    public void draw(Screen screen) throws IOException {
        alfredo.draw(screen);
    }

    public void moveHero(Position position){
        if(canHeroMove(position)){
            alfredo.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position){
        int x = position.getX();
        int y = position.getY();
        return x>=0 && x<width && y>=0 && y<height;
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp: moveHero(alfredo.moveUp()); break;
            case ArrowDown: moveHero(alfredo.moveDown()); break;
            case ArrowLeft: moveHero(alfredo.moveLeft()); break;
            case ArrowRight: moveHero(alfredo.moveRight()); break;
        }
    }
}
