package config;

import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;


/**
 * In diesem Interface werden eine Reihe von Konstanten definiert, die an verschiedenen Stellen des Spiels benötigt werden.
 * Alle Klassen, die Zugriff auf diese Wert benötigen, implementieren das Interface und haben damit Zugriff auf die Konstanten.
 */
public interface GameConfig {

    // Größe der Zeichenfläche und Hintergrundbild
    int CANVAS_WIDTH = 640;
    int CANVAS_HEIGHT = 320;
    String BACKGROUND_IMAGE_PATH = "assets/Background.png";
    // Bilder und Parameter für die Geschenke
    String[] PRESENT_IMAGE_PATHS = {"assets/Present-A.png", "assets/Present-B.png", "assets/Present-C.png", "assets/Present-D.png"};
    int MAX_NUMBER_OF_PRESENTS = 4;
    int NEW_PRESENT_OFFSET = 32;
    int MIN_PRESENT_SPEED = 1;
    int MAX_PRESENT_SPEED = 4;
    int PRESENT_CATCH_DISTANCE = 16;
    // Bild und Parameter für die Spielfigur
    String SANTA_IMAGE_PATH = "assets/Santa.png";
    int PLAYER_MOVEMENT_SPEED = 16;
    int PLAYER_START_X_POSITION = 32;
    int PLAYER_START_Y_POSITION = 196;
    // Parameter für die Punkteanzeige
    int SCORE_VIEW_X = 8;
    int SCORE_VIEW_Y = 8;
    int SCORE_VIEW_FONT_SIZE = 14;
    Color SCORE_VIEW_COLOR = Colors.RED;
    String SCORE_VIEW_PREFIX = "Catched: ";
    // Pfad zur Datei mit der Hintergrundmusik
    String BACKGROUND_MUSIC_PATH = "assets/251461__joshuaempyre__arcade-music-loop.wav";

}
