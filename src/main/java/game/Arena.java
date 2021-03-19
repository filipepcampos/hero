package game;

import com.googlecode.lanterna.input.KeyStroke;
import game.drawables.Drawable;
import game.drawables.elements.Coin;
import game.drawables.elements.Element;
import game.drawables.elements.Hero;
import game.drawables.elements.monsters.Crawler;
import game.drawables.elements.monsters.Monster;
import game.drawables.elements.Wall;
import game.drawables.elements.monsters.Zombie;
import game.drawables.userInterface.InfoBar;
import game.util.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        WallCreator wallCreator = new WallCreator(this);
        this.walls = wallCreator.create();

        CoinCreator coinCreator = new CoinCreator(this);
        this.coins = coinCreator.create();

        MonsterCreator monsterCreator = new MonsterCreator(this);
        this.monsters = monsterCreator.create();
    }

    public List<Drawable> getDrawables() {
        List<Drawable> drawables = Stream.of(walls, coins, monsters)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList());
        drawables.add(hero);
        drawables.add(infoBar);
        return drawables;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public gameAction processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp: moveHero(hero.move(gameMove.up)); break;
            case ArrowDown: moveHero(hero.move(gameMove.down)); break;
            case ArrowLeft: moveHero(hero.move(gameMove.left)); break;
            case ArrowRight: moveHero(hero.move(gameMove.right)); break;
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
