package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.Image;

import java.util.ArrayList;

public class ChristmasPresent implements GameConfig {

    private Image image;
    private int speed;
    private ArrayList<ChristmasPresentListener> listeners;

    public ChristmasPresent(int x, int y, int speed, String imagePath) {
        image = new Image(x, y, imagePath);
        this.speed = speed;
        listeners = new ArrayList<>();
    }

    public double distanceToPlayer(Player player) {
        Image playerImage = player.getImage();
        return image.distanceTo(playerImage);
    }

    public void registerChristmasPresentListener(ChristmasPresentListener listener) {
        listeners.add(listener);
    }

    public void update() {
        image.move(0, speed);
        if(image.getTopBorder() > CANVAS_HEIGHT) {
            for(ChristmasPresentListener listener: listeners) {
                listener.onPresentLeftCanvas(this);
            }
        }
    }

    public void draw() {
        image.draw();
    }

}
