import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.audio.AudioClip;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * In diesem Spiel müssen Spieler*innen Geschenke einsammeln, die von zufälligen Positionen am oberen
 * Bildschirmrand herunterfallen. Dazu bewegen Sie mit den Pfeiltasten der Tastatur eine Spielfigur
 * über den Boden der Spielwelt. Berührt die Spielfigur ein Geschenk, erhalten die Spieler*innen einen
 * Punkt. Wird ein Geschenk aufgefangen oder fällt es aus dem Bildschirm heraus, wird ein neues, zufällig
 * positioniertes Geschenk erzeugt.
 */

public class ChristmasCatcherApp extends GraphicsApp implements GameConfig {

    // Hintergrundmusik, die während des Spiels abgespielt wird (loop)
    private AudioClip backgroundMusic;
    // Hintergrundbild für die Spielszene
    private Image backgroundImage;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        backgroundMusic = new AudioClip(BACKGROUND_MUSIC_PATH);
        backgroundImage = new Image(0, 0, BACKGROUND_IMAGE_PATH);
        // Start der Musikwiedergabe als "Endlosschleife"
        backgroundMusic.loop();
    }

    @Override
    public void draw() {
        backgroundImage.draw();
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
