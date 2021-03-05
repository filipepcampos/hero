package game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.elements.Coin;
import game.elements.Hero;
import game.elements.monsters.Crawler;
import game.elements.monsters.Monster;
import game.elements.Wall;
import game.elements.monsters.Zombie;
import game.userInterface.InfoBar;
import game.util.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    public enum gameAction{
        CONTINUE,
        QUIT,
        RESTART
    }

    private Hero hero;
    private InfoBar infoBar;
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
        this.hero = new Hero(10,10);
        this.infoBar = new InfoBar(0, height, width, statusBarHeight, this.hero.getHP());
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ECECEC"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for(Coin coin : coins){
            coin.draw(graphics);
        }
        for(Monster monster : monsters){
            monster.draw(graphics);
        }
        for(Wall wall : walls){
            wall.draw(graphics);
        }
        infoBar.draw(graphics);
    }

    public void moveHero(Position position){
        if(canMove(position) && !gameOver && !won){
            hero.setPosition(position);
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
            if(hero.getPosition().equals(coin.getPosition())){
                coins.remove(coin);
                this.infoBar.increaseNumCoins();
                if(coins.size() == 0){
                    won = true;
                    infoBar.setWon(true);
                }
                break;
            }
        }
    }

    private void verifyMonsterCollision(){
        for(Monster monster : monsters){
            if(hero.getPosition().equals(monster.getPosition())){
                hero.loseHP();
                if(hero.getHP() <= 0){
                    gameOver = true;
                    this.infoBar.setGameOver(true);
                }
                else{
                    this.infoBar.setHP(hero.getHP());
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

    private Monster createMonster(Random random){
        int x = random.nextInt(width-2)+1;
        int y = random.nextInt(height-2)+1;
        switch(random.nextInt(2)){
            case 0: return new Zombie(x,y);
            case 1: return new Crawler(x,y);
        }
        return new Zombie(random.nextInt(width-2)+1, random.nextInt(height-2)+1);
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        int nMonsters = random.nextInt(8)+1;
        for(int i = 0; i < nMonsters; ++i){
            monsters.add(createMonster(random));
        }
        return monsters;
    }

    public gameAction processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp: moveHero(hero.moveUp()); break;
            case ArrowDown: moveHero(hero.moveDown()); break;
            case ArrowLeft: moveHero(hero.moveLeft()); break;
            case ArrowRight: moveHero(hero.moveRight()); break;
            case Character:
                if(key.getCharacter() == 'q'){
                    return gameAction.QUIT;
                }
                if(key.getCharacter() == 'r' && (won || gameOver)){
                    return gameAction.RESTART;
                }
                break;
        }
        return gameAction.CONTINUE;
    }
}
