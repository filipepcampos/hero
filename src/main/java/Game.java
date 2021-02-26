import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class Game {
    private Screen screen;
    private int x = 10;
    private int y = 10;

    Game(){
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while(true){
                this.draw();
                KeyStroke key = screen.readInput();
                if(!processKey(key)){
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw() throws IOException{
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    private boolean processKey(KeyStroke key) throws IOException{
        switch (key.getKeyType()){
            case ArrowUp: y -= 1; break;
            case ArrowDown: y += 1; break;
            case ArrowLeft: x -= 1; break;
            case ArrowRight: x += 1; break;
            case Character:
                if(key.getCharacter() == 'q'){
                    screen.close();
                }
                break;
            case EOF: return false;
        }
        return true;
    }
}
