import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private int x = 10;
    private int y = 10;

    private Screen screen;

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    public Game() throws IOException {
        Terminal terminal = new
            DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF)
                break;
            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp)
            y = y -1;
        else if (key.getKeyType() == KeyType.ArrowDown)
            y = y +1;
        else if (key.getKeyType() == KeyType.ArrowRight)
            x = x +1;
        else if (key.getKeyType() == KeyType.ArrowLeft)
            x = x -1;
    }

}


