package objects;

import config.GameConfig;

import java.util.Random;

/**
 * Die statische Methode in dieser Klasse erzeugt ein zufällig platziertes Geschenk und gibt dieses als
 * Rückgabewert zurück. Die App nutzt diese Klasse, um das Erstellen der Geschenke auszulagern.
 */
public class ChristmasPresentFactory implements GameConfig {

    /**
     * Erstellt ein zufälliges Geschenk auf Basis der Parameter aus der GameConfig
     * @param listener Observer, der auf dem neuen Geschenk registriert werden soll
     * @return Das neue Geschenk
     */
    public static ChristmasPresent createRandomPresent(ChristmasPresentListener listener) {
        // Zufallsgenerator für die Auswahl variabler Parameter
        Random random = new Random();
        // Zufällige Auswahl eines der Bildpfade aus dem entsprechenden Array
        int imageIndex = random.nextInt(PRESENT_IMAGE_PATHS.length);
        // Zufällige Bestimmung einer Start-Koordinate auf der X-Achse (die Geschenke spawnen nicht direkt am Rand!)
        int xPosition = NEW_PRESENT_OFFSET + random.nextInt(CANVAS_WIDTH - 2 * NEW_PRESENT_OFFSET);
        // Zufällige Bestimmung der Bewegungsgeschwindigkeit
        int speed = random.nextInt(MAX_PRESENT_SPEED - MIN_PRESENT_SPEED) + MIN_PRESENT_SPEED;
        // Erstellen und Zurückgeben des neuen Geschenks mit den im Vorfeld zufällig bestimmten Initalwerten
        return new ChristmasPresent(xPosition, -NEW_PRESENT_OFFSET, speed, PRESENT_IMAGE_PATHS[imageIndex], listener);
    }
}
