package game.drawables.userInterface;

import game.Arena;
import game.gui.GenericTextGraphics;
import game.util.Position;

import java.io.IOException;

public class InfoBar extends InterfaceElement {
    private HealthBar healthBar;
    private CoinCounter coinCounter;
    private Message message;
    private boolean gameOver = false;
    private boolean won = false;

    public InfoBar(int x, int y, int width, int height, Arena arena){
        super(x,y,width,height);
        healthBar = new HealthBar(1, this.y+1, 5, 1, 5);
        arena.getHero().getHpTracker().attach(healthBar);
        coinCounter = new CoinCounter(width-5, this.y+1, 4, 1);
        message = new Message(width/2-5, this.y+1, 10, 3);
    }

    public void increaseNumCoins(int nCoins){
        this.coinCounter.increaseNCoins(nCoins);
    }

    public void setGameOver(boolean val){
        this.gameOver = val;
    }

    public void setWon(boolean val){
        this.won = val;
    }

    @Override
    public void draw(GenericTextGraphics graphics) throws IOException {
        graphics.setBackgroundColor("#333333");
        graphics.fillRectangle(new Position(x,y), width, height);

        coinCounter.draw(graphics);

        if(gameOver){
            this.message.setColor("#FF0000");
            this.message.setMessage("Game Over");
            this.message.draw(graphics);
        }
        else if(won){
            this.message.setColor("#D4AF37");
            this.message.setMessage("You won!");
            this.message.draw(graphics);
        }
        else{
            healthBar.draw(graphics);
        }
    }
}
