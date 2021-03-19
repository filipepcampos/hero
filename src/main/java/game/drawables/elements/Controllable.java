package game.drawables.elements;

import game.gameMove;
import game.util.Position;

public interface Controllable {
    Position move(gameMove move);
}
