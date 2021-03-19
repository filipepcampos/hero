package game;

import game.drawables.elements.Element;

import java.util.List;

public class CollisionChecker {
    boolean check(Element element, List<? extends Element> elementList){
        for(Element element1 : elementList){
            if(element.getPosition().equals(element1.getPosition())){
                return true;
            }
        }
        return false;
    }
}
