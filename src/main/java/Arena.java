import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ECECEC"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        alfredo.draw(graphics);
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
