import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.audio.AudioClip;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.MouseMovedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import objects.ChristmasPresent;
import objects.ChristmasPresentFactory;
import objects.Player;

/**
 * In diesem Spiel müssen Spieler*innen Geschenke einsammeln, die von zufälligen Positionen am oberen
 * Bildschirmrand herunterfallen. Dazu bewegen Sie mit den Pfeiltasten der Tastatur eine Spielfigur
 * über den Boden der Spielwelt. Berührt die Spielfigur ein Geschenk, erhalten die Spieler*innen einen
 * Punkt. Wird ein Geschenk aufgefangen oder fällt es aus dem Bildschirm heraus, wird ein neues, zufällig
 * positioniertes Geschenk erzeugt.
 */

public class ChristmasCatcherApp extends GraphicsApp implements GameConfig {

    private Image backgroundImage;
    private AudioClip backgroundMusic;
    private Player player;

    private ChristmasPresent present;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        initBackground();
        player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION);
        present = ChristmasPresentFactory.createRandomPresent();
        // backgroundMusic.loop();
    }

    private void initBackground() {
        backgroundImage = new Image(0, 0, BACKGROUND_IMAGE_PATH);
        backgroundMusic = new AudioClip(BACKGROUND_MUSIC_PATH);
    }

    @Override
    public void draw() {
        // Zeichnet Hintergrundbild neu
        backgroundImage.draw();
        player.draw();
        present.update();
        present.draw();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        super.onKeyPressed(event);
        switch(event.getKeyCode()) {
            // Pfeiltaste nach links gedrückt
            case KeyPressedEvent.VK_LEFT:
                player.moveLeft();
                break;
            // Pfeiltaste nach rechts gedrückt
            case KeyPressedEvent.VK_RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
