package game.creators;

import game.Arena;
import game.drawables.elements.Wall;

import java.util.ArrayList;
import java.util.List;

public class WallCreator extends ElementCreator {
    Arena arena;
    public WallCreator(Arena arena){
        this.arena = arena;
    }

    @Override
    public List<Wall> create() {
        List<Wall> walls = new ArrayList<>();
        for(int i = 0; i < arena.getWidth(); ++i){
            walls.add(new Wall(i,0));
            walls.add(new Wall(i, arena.getHeight()-1));
        }
        for(int i = 1; i < arena.getHeight() - 1; ++i){
            walls.add(new Wall(0, i));
            walls.add(new Wall(arena.getWidth()-1, i));
        }
        return walls;
    }
}
