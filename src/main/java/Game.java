import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Arena arena;
    private Screen screen;

    private void draw() throws IOException {
        this.screen.clear();
        this.arena.draw(screen.newTextGraphics());
        this.screen.refresh();
    }
    public Game() throws IOException {
        arena = new Arena(40,20);
        TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }

    public void run() throws IOException {
        draw();
        while (true) {
            KeyStroke key = screen.readInput();
            processKey(key);
            draw();
            if (key.getKeyType() == KeyType.EOF)
                break;
            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        arena.processKey(key, screen);
    }
}


