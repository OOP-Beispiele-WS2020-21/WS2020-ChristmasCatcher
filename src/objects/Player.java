package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.Image;

/**
 * Diese Klasse repräsentiert eine Spielfigur die sich horizontal über den Boden
 * der Spielwelt bewegen kann. Die Figur kann den Bildschirmrand nicht verlassen.
 */


public class Player implements GameConfig {

    private Image character;

    public Player(int x, int y) {
        character = new Image(x, y, SANTA_IMAGE_PATH);
    }

    public Image getImage() {
        return character;
    }

    public void draw() {
        character.draw();
    }

    public void moveLeft() {
        move(-PLAYER_MOVEMENT_SPEED);
    }

    public void moveRight() {
        move(PLAYER_MOVEMENT_SPEED);
    }
    private void move(int x) {
        character.move(x, 0);
    }

}
