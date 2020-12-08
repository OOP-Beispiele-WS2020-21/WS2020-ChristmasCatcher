package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class ChristmasPresent implements GameConfig {

    private Image image;
    private int speed;
    private ChristmasPresentListener listener;

    public ChristmasPresent(int x, int y, int speed, String imagePath, ChristmasPresentListener listener) {
        image = new Image(x, y, imagePath);
        this.speed = speed;
        this.listener = listener;
    }

    public void update() {
        image.move(0, speed);
        if(image.getTopBorder() > CANVAS_HEIGHT) {
            listener.onPresentLeftCanvas(this);
        }
    }

    public void draw() {
        image.draw();
    }

    public int distanceTo(Player player) {
        return player.distanceTo(image);
    }
}
