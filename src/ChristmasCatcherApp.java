import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.audio.AudioClip;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import objects.ChristmasPresent;
import objects.ChristmasPresentFactory;
import objects.ChristmasPresentListener;
import objects.Player;

import java.util.ArrayList;

/**
 * In diesem Spiel müssen Spieler*innen Geschenke einsammeln, die von zufälligen Positionen am oberen
 * Bildschirmrand herunterfallen. Dazu bewegen Sie mit den Pfeiltasten der Tastatur eine Spielfigur
 * über den Boden der Spielwelt. Berührt die Spielfigur ein Geschenk, erhalten die Spieler*innen einen
 * Punkt. Wird ein Geschenk aufgefangen oder fällt es aus dem Bildschirm heraus, wird ein neues, zufällig
 * positioniertes Geschenk erzeugt.
 */

public class ChristmasCatcherApp extends GraphicsApp implements GameConfig, ChristmasPresentListener {

    private Image backgroundImage;
    private AudioClip backgroundMusic;
    private Player player;
    private ArrayList<ChristmasPresent> presents;
    private ArrayList<ChristmasPresent> presentsToBeRemovedAfterFrame;
    private int score;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        initBackground();
        player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION);
        presents = new ArrayList<>();
        presentsToBeRemovedAfterFrame = new ArrayList<>();
        score = 0;
        // backgroundMusic.loop();
    }

    private void initBackground() {
        backgroundImage = new Image(0, 0, BACKGROUND_IMAGE_PATH);
        backgroundMusic = new AudioClip(BACKGROUND_MUSIC_PATH);
    }

    private void refillPresentList() {
        if(presents.size() < MAX_NUMBER_OF_PRESENTS) {
            ChristmasPresent newPresent = ChristmasPresentFactory.createRandomPresent();
            newPresent.registerChristmasPresentListener(this);
            presents.add(newPresent);
            // TODO count number of spawned presents and update score view
        }
    }

    @Override
    public void draw() {
        // Zeichnet Hintergrundbild neu
        backgroundImage.draw();
        refillPresentList();
        drawAndUpdatePresents();
        player.draw();
    }

    private void drawAndUpdatePresents() {
        for(ChristmasPresent currentPresent: presents) {
            currentPresent.update();
            currentPresent.draw();
            if(currentPresent.distanceToPlayer(player) <= PRESENT_CATCH_DISTANCE) {
                score++;
                System.out.println("Punkte: " + score + " (das sind aber sehr wenige Punkte)");
                presentsToBeRemovedAfterFrame.add(currentPresent);
            }
        }
        presents.removeAll(presentsToBeRemovedAfterFrame);
        presentsToBeRemovedAfterFrame.clear();
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

    @Override
    public void onPresentLeftCanvas(ChristmasPresent present) {
        presentsToBeRemovedAfterFrame.add(present);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
