import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x = 10;
    private int y = 10;


    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Screen screen) {
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp() {
        y = y -1;
    }

    public void moveDown() {
        y = y +1;
    }

    public void moveRight() {
        x = x +1;
    }

    public void moveLeft() {
        x = x -1;
    }
}
