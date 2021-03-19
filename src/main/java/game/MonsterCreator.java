package game;

import game.drawables.elements.Coin;
import game.drawables.elements.monsters.Crawler;
import game.drawables.elements.monsters.Monster;
import game.drawables.elements.monsters.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterCreator extends ElementCreator {
    Arena arena;
    public MonsterCreator(Arena arena){
        this.arena = arena;
    }

    private Monster createMonster(Random random){
        int x = random.nextInt(arena.getWidth()-2)+1;
        int y = random.nextInt(arena.getHeight()-2)+1;
        switch(random.nextInt(2)){
            case 0: return new Zombie(x,y);
            case 1: return new Crawler(x,y);
        }
        return new Zombie(random.nextInt(arena.getWidth()-2)+1, random.nextInt(arena.getHeight()-2)+1);
    }

    @Override
    public List<Monster> create(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        int nMonsters = random.nextInt(8)+1;
        for(int i = 0; i < nMonsters; ++i){
            monsters.add(createMonster(random));
        }
        return monsters;
    }
}
