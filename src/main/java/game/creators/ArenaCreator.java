package game.creators;

import game.Arena;
import game.drawables.elements.Coin;
import game.drawables.elements.Wall;
import game.drawables.elements.monsters.Monster;

import java.util.List;

public class ArenaCreator {
    public void create(Arena arena){
        WallCreator wallCreator = new WallCreator(arena);
        List<Wall> walls = wallCreator.create();

        CoinCreator coinCreator = new CoinCreator(arena);
        List<Coin> coins = coinCreator.create();

        MonsterCreator monsterCreator = new MonsterCreator(arena);
        List<Monster> monsters = monsterCreator.create();

        arena.setCoins(coins);
        arena.setWalls(walls);
        arena.setMonsters(monsters);
    }
}
