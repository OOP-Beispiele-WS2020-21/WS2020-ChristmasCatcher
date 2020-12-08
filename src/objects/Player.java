package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.GraphicsObject;
import de.ur.mi.oop.graphics.Image;

public class Player implements GameConfig {

    private Image character;

    public Player(int x, int y) {
        character = new Image(x, y, SANTA_IMAGE_PATH);
    }

    public void draw() {
        character.draw();
    }

    public int distanceTo(GraphicsObject object) {
        return (int) character.distanceTo(object);
    }

    public void moveLeft() {
        move(-PLAYER_MOVEMENT_SPEED, 0);
    }

    public void moveRight() {
        move(PLAYER_MOVEMENT_SPEED, 0);
    }

    private void move(int x, int y) {
        float oldX = character.getXPos();
        float oldY = character.getYPos();
        character.move(x, y);
        if(checkIfPlayerHasLeftOnCanvas()) {
            character.setPosition(oldX, oldY);
        }
    }

    private boolean checkIfPlayerHasLeftOnCanvas() {
        if(character.getLeftBorder() < 0) {
            return true;
        }
        if(character.getRightBorder() > CANVAS_WIDTH) {
            return true;
        }
        if(character.getTopBorder() < 0) {
            return true;
        }
        if(character.getBottomBorder() > CANVAS_HEIGHT) {
            return true;
        }
        return false;
    }
}
