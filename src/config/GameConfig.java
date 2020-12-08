package config;

import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;

public interface GameConfig {

    // Canvas and Background
    int CANVAS_WIDTH = 640;
    int CANVAS_HEIGHT = 320;
    String BACKGROUND_IMAGE_PATH = "assets/Background.png";
    // Presents
    String[] PRESENT_IMAGE_PATHS = {"assets/Present-A.png", "assets/Present-B.png", "assets/Present-C.png", "assets/Present-D.png"};
    int MAX_NUMBER_OF_PRESENTS = 4;
    int NEW_PRESENT_OFFSET = 32;
    int MIN_PRESENT_SPEED = 1;
    int MAX_PRESENT_SPEED = 4;
    int PRESENT_CATCH_DISTANCE = 16;
    // Santa
    String SANTA_IMAGE_PATH = "assets/Santa.png";
    int PLAYER_MOVEMENT_SPEED = 16;
    int PLAYER_START_X_POSITION = 32;
    int PLAYER_START_Y_POSITION = 196;
    // UI
    int SCORE_VIEW_X = 16;
    int SCORE_VIEW_Y = 16;
    int SCORE_VIEW_FONT_SIZE = 14;
    Color SCORE_VIEW_COLOR = Colors.RED;
    String SCORE_VIEW_PREFIX = "Catched Presents: ";
}
