import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    Hero hero = new Hero(10, 10);

    private Screen screen;

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
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
        int x = hero.getX();
        int y = hero.getY();
        if (key.getKeyType() == KeyType.ArrowUp)
            hero.moveUp();
        else if (key.getKeyType() == KeyType.ArrowDown)
            hero.moveDown();
        else if (key.getKeyType() == KeyType.ArrowRight)
            hero.moveRight();
        else if (key.getKeyType() == KeyType.ArrowLeft)
            hero.moveLeft();
    }



}


