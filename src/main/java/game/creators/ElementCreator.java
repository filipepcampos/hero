package game.creators;

import game.drawables.elements.Element;

import java.util.List;

public abstract class ElementCreator {
    public abstract List<? extends Element> create();
}
