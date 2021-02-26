import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;

public class Arena {
    private Hero alfredo;
    private StatusBar statusBar;
    private int width;
    private int height;
    private boolean gameOver = false;
    private boolean won = false;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    Arena(int width, int height, int statusBarHeight){
        this.width = width;
        this.height = height;
        this.alfredo = new Hero(10,10);
        this.statusBar = new StatusBar(0, height, width, statusBarHeight, this.alfredo.getHP());
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ECECEC"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        alfredo.draw(graphics);
        for(Coin coin : coins){
            coin.draw(graphics);
        }
        for(Monster monster : monsters){
            monster.draw(graphics);
        }
        for(Wall wall : walls){
            wall.draw(graphics);
        }
        statusBar.draw(graphics);
    }

    public void moveHero(Position position){
        if(canMove(position) && !gameOver && !won){
            alfredo.setPosition(position);
            retrieveCoins();
            verifyMonsterCollision();
            moveMonsters();
            verifyMonsterCollision();
        }
    }

    private boolean canMove(Position position){
        int x = position.getX();
        int y = position.getY();
        if(x>=0 && x<width && y>=0 && y<height){
            for(Wall wall : walls){
                if(wall.getPosition().equals(position)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void moveMonsters(){
        for(Monster monster : monsters){
            Position p = monster.move();
            if(canMove(p)){
                monster.setPosition(p);
            }
        }
    }

    private void retrieveCoins(){
        for(Coin coin : coins){
            if(alfredo.getPosition().equals(coin.getPosition())){
                coins.remove(coin);
                this.statusBar.increaseNumCoins();
                if(coins.size() == 0){
                    won = true;
                    statusBar.setWon(true);
                }
                break;
            }
        }
    }

    private void verifyMonsterCollision(){
        for(Monster monster : monsters){
            if(alfredo.getPosition().equals(monster.getPosition())){
                alfredo.loseHP();
                if(alfredo.getHP() <= 0){
                    gameOver = true;
                    this.statusBar.setGameOver(true);
                }
                else{
                    this.statusBar.setHP(alfredo.getHP());
                }
            }
        }
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

    private List<Coin> createCoins(){
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for(int i = 0; i < 5; ++i){
            coins.add(new Coin(random.nextInt(width-2)+1, random.nextInt(height-2)+1));
        }
        return coins;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        int nMonsters = random.nextInt(8)+1;
        for(int i = 0; i < nMonsters; ++i){
            monsters.add(new Monster(random.nextInt(width-2)+1, random.nextInt(height-2)+1));
        }
        return monsters;
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
