import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class StatusBar {
    private int width;
    private int height;
    private int x;
    private int y;
    private int heroHp;
    private int numCoins = 0;
    private boolean gameOver = false;
    private boolean won = false;

    StatusBar(int x, int y, int width, int height, int hp){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.heroHp = hp;
    }

    public void setHP(int hp){
        this.heroHp = hp;
    }

    public void increaseNumCoins(){
        this.numCoins++;
    }

    public void setGameOver(boolean val){
        this.gameOver = val;
    }

    public void setWon(boolean val){
        this.won = val;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#333333"));
        graphics.fillRectangle(new TerminalPosition(x,y), new TerminalSize(width, height), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#D4AF37"));
        String coinString = String.valueOf(numCoins);
        graphics.putString(new TerminalPosition(width-1-coinString.length(), y+1), coinString);


        if(gameOver){
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            graphics.putString(new TerminalPosition(width/2 - 4, y+1), "Game Over");
        }
        else if(won){
            graphics.putString(new TerminalPosition(width/2 - 4, y+1), "You won!");
        }
        else{
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
            graphics.enableModifiers(SGR.BOLD);
            int healthX = x + 1;
            for(int i = 0; i < heroHp; ++i){
                graphics.putString(new TerminalPosition(healthX, y+1), " ");
                healthX++;
            }
        }
    }
}
