package game;

import com.googlecode.lanterna.input.KeyStroke;
import game.creators.ArenaCreator;
import game.drawables.Drawable;
import game.drawables.elements.Coin;
import game.drawables.elements.Hero;
import game.drawables.elements.monsters.Monster;
import game.drawables.elements.Wall;
import game.drawables.userInterface.InfoBar;
import game.util.Position;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Arena {
    public enum gameAction{
        CONTINUE,
        QUIT,
        RESTART
    }

    private Hero hero;
    final private InfoBar infoBar;
    final private int width;
    final private int height;
    private boolean gameOver = false;
    private boolean won = false;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    Arena(int width, int height, int statusBarHeight){
        this.width = width;
        this.height = height;
        this.hero = new Hero(10,10);
        this.infoBar = new InfoBar(0, height, width, statusBarHeight, this);

        ArenaCreator creator = new ArenaCreator();
        creator.create(this);
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
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

    public Hero getHero() {
        return hero;
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
        int x = position.getX(), y = position.getY();
        if(x>=0 && x<width && y>=0 && y<height){
            CollisionChecker collisionChecker = new CollisionChecker();
            if(collisionChecker.check(position, walls)){
                return false;
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
        CoinRetriever coinRetriever = new CoinRetriever();
        int nCoins = coinRetriever.retrieveCoins(this.coins, this.hero.getPosition());
        this.infoBar.increaseNumCoins(nCoins);
    }

    private void verifyMonsterCollision(){
        CollisionChecker collisionChecker = new CollisionChecker();
        if(collisionChecker.check(hero.getPosition(), monsters)){
            hero.loseHP();
        }
    }

    private boolean checkGameOver(){
        if(hero.getHpTracker().getHp() <= 0){
            setGameOver();
            return true;
        }
        return false;
    }

    private void setGameOver(){
        gameOver = true;
        this.infoBar.setGameOver(true);
    }

    private boolean checkWin(){
        if(coins.size() == 0){
            setWin();
            return true;
        }
        return false;
    }

    private void setWin(){
        won = true;
        infoBar.setWon(true);
    }

    public gameAction step(KeyStroke key){
        gameAction action = processKey(key);
        checkGameOver();
        checkWin();
        return action;
    }

    private gameAction processKey(KeyStroke key){ // TODO: This is still lanterna dependent
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
