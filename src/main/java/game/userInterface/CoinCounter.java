package game.userInterface;

import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class CoinCounter extends InterfaceElement{
    int nCoins = 0;

    public CoinCounter(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    @Override
    public void draw(GenericTextGraphics graphics) throws IOException {
        graphics.setForegroundColor("#D4AF37");
        String coinString = String.valueOf(nCoins);
        graphics.putString(new Position(this.x, this.y), coinString);
    }

    public void increaseNCoins(){
        nCoins++;
    }
}
