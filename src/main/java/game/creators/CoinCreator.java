package game.creators;

import game.Arena;
import game.drawables.elements.Coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoinCreator extends ElementCreator {
    Arena arena;
    public CoinCreator(Arena arena){
        this.arena = arena;
    }

    @Override
    public List<Coin> create() {
        Random random = new Random();
        List<Coin> coins = new ArrayList<>();
        for(int i = 0; i < 5; ++i){
            coins.add(new Coin(random.nextInt(arena.getWidth()-2)+1, random.nextInt(arena.getHeight()-2)+1));
        }
        return coins;
    }
}
