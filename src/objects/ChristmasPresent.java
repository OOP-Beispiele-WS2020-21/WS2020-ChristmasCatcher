package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class ChristmasPresent implements GameConfig {

    private Image image;
    private int speed;

    public ChristmasPresent(int x, int y, int speed, String imagePath) {
        image = new Image(x, y, imagePath);
        this.speed = speed;
    }

    public void update() {
        image.move(0, speed);
    }

    public void draw() {
        image.draw();
    }

}
