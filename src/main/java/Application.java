import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Application {
public static void main(String[] args) throws IOException {
 Terminal terminal = new
         DefaultTerminalFactory().createTerminal();
 Screen screen = new TerminalScreen(terminal);
 screen.setCursorPosition(null); // we don't need a cursor
 screen.startScreen(); // screens must be started
 screen.doResizeIfNecessary(); // resize screen if necessary
 try {
  terminal = new
          DefaultTerminalFactory().createTerminal();
  screen = new TerminalScreen(terminal);
  screen.setCursorPosition(null); // we don't need a cursor
  screen.startScreen(); // screens must be started
  screen.doResizeIfNecessary(); // resize screen if
  Object necessary = null;
  Object necessary1 = null;
 } catch (IOException e) {
  e.printStackTrace();
 }
 TerminalSize terminalSize = new TerminalSize(40, 20);
 DefaultTerminalFactory terminalFactory = new
         DefaultTerminalFactory()
         .setInitialTerminalSize(terminalSize);
 terminal = terminalFactory.createTerminal();
 screen.clear();
 screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')
         [0]);
 screen.refresh();
 }
}

