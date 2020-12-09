package objects;

import config.GameConfig;

import java.util.Random;

public class ChristmasPresentFactory implements GameConfig {

    public static ChristmasPresent createRandomPresent() {
        Random random = new Random();
        int imageIndex = random.nextInt(PRESENT_IMAGE_PATHS.length);
        int xPosition = NEW_PRESENT_OFFSET + (random.nextInt(CANVAS_WIDTH - (2 * NEW_PRESENT_OFFSET)));
        int speed = random.nextInt(MAX_PRESENT_SPEED - MIN_PRESENT_SPEED) + MIN_PRESENT_SPEED;
        return new ChristmasPresent(xPosition, -NEW_PRESENT_OFFSET, speed, PRESENT_IMAGE_PATHS[imageIndex]);
    }

}
