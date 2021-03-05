package game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class Game {
    private Screen screen;
    private Arena arena;

    Game(){
        try {
            // ---- Copy pasta from Slack ----
            // Load Font
            File fontFile = new File("./resources/square.ttf");
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // Register Font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            //Configure Default Terminal Factory
            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            TerminalSize terminalSize = new TerminalSize(40, 23);
            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            defaultTerminalFactory.setForceAWTOverSwing(true);
            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            // ---- End of Copy pasta ----
            Terminal terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
        arena = new Arena(40, 20, 3);
    }

    public void run(){
        try {
            while(true){
                this.draw();
                KeyStroke key = screen.readInput();
                if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                    screen.close();
                }
                if(key.getKeyType() == EOF){
                    break;
                }
                processKey(key);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException{
        arena.processKey(key);
    }
}
