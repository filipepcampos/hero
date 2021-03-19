package game;

import game.drawables.elements.Element;
import game.util.Position;

import java.util.List;

public class CollisionChecker {
    boolean check(Position position, List<? extends Element> elementList){
        for(Element element1 : elementList){
            if(position.equals(element1.getPosition())){
                return true;
            }
        }
        return false;
    }
}
