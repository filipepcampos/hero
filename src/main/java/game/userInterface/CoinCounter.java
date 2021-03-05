package game.userInterface;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class CoinCounter extends InterfaceElement{
    int nCoins = 0;

    public CoinCounter(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#D4AF37"));
        String coinString = String.valueOf(nCoins);
        graphics.putString(new TerminalPosition(this.x, this.y), coinString);
    }

    public void increaseNCoins(){
        nCoins++;
    }
}
