import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero alfredo;
    private int width;
    private int height;
    private List<Wall> walls;

    Arena(int width, int height){
        this.width = width;
        this.height = height;
        alfredo = new Hero(10,10);
        this.walls = createWalls();
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ECECEC"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        alfredo.draw(graphics);
        for(Wall wall : walls){
            wall.draw(graphics);
        }
    }

    public void moveHero(Position position){
        if(canHeroMove(position)){
            alfredo.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position){
        int x = position.getX();
        int y = position.getY();
        if(x>=0 && x<width && y>=0 && y<height){
            for(Wall wall : walls){
                if(wall.getPosition().getX() == x && wall.getPosition().getY() == y){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for(int i = 0; i < width; ++i){
            walls.add(new Wall(i,0));
            walls.add(new Wall(i, height-1));
        }
        for(int i = 1; i < height - 1; ++i){
            walls.add(new Wall(0, i));
            walls.add(new Wall(width-1, i));
        }
        return walls;
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
