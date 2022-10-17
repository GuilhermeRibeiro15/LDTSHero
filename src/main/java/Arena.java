import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private int height;
    private int width;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monsters> monsters;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }

    private void retreiveCoins(){
        for (Coin coin : coins){
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
        }
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() < 0) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        return true;
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }


    public void processKey(KeyStroke key, Screen screen) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp)
            moveHero(hero.moveUp());
        else if (key.getKeyType() == KeyType.ArrowDown)
            moveHero(hero.moveDown());
        else if (key.getKeyType() == KeyType.ArrowRight)
            moveHero(hero.moveRight());
        else if (key.getKeyType() == KeyType.ArrowLeft)
            moveHero(hero.moveLeft());
        retreiveCoins();
        moveMonsters();
        verifyMonsterCollisions(screen);
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        this.hero.draw(graphics);
        for (Wall wall: walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monsters monster : monsters)
            monster.draw(graphics);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Monsters> createMonsters(){
        Random random = new Random();
        ArrayList<Monsters> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monsters(random.nextInt(width -2 ) + 1, random.nextInt(height -2) +1));
        return monsters;
    }

    private boolean canMonsterMove(Monsters monster, Position movePosition){
        for (Wall wall : walls)
            if (wall.getPosition().equals(movePosition)) return false;
        return true;
    }

    private void moveMonsters() {
        for (Monsters monster : monsters) {
            Position movePosition = monster.move();
            if (canMonsterMove(monster, movePosition))
                monster.setPosition(movePosition);
        }
    }
    private void verifyMonsterCollisions(Screen screen) throws IOException {
        for (Monsters monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                screen.close();
                System.out.println("Game Over!");
            }
        }
    }

}
