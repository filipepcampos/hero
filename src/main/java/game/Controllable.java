package game;

import game.util.Position;

public interface Controllable {
    Position move(gameMove move);
}
