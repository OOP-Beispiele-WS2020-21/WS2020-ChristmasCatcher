import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.audio.AudioClip;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import objects.ChristmasPresent;
import objects.ChristmasPresentFactory;
import objects.ChristmasPresentListener;
import objects.Player;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * In diesem Spiel müssen Spieler*innen Geschenke einsammeln, die von zufälligen Positionen am oberen
 * Bildschirmrand herunterfallen. Dazu bewegen Sie mit den Pfeiltasten der Tastatur eine Spielfigur
 * über den Boden der Spielwelt. Berührt die Spielfigur ein Geschenk, erhalten die Spieler*innen einen
 * Punkt. Wird ein Geschenk aufgefangen oder fällt es aus dem Bildschirm heraus, wird ein neues, zufällig
 * positioniertes Geschenk erzeugt.
 */

public class ChristmasCatcherApp extends GraphicsApp implements GameConfig, ChristmasPresentListener {

    // Hintergrundmusik, die während des Spiels abgespielt wird (loop)
    private AudioClip backgroundMusic;
    // Hintergrundbild für die Spielszene
    private Image backgroundImage;
    // Spielfigur
    private Player player;
    // Liste der aktuell angezeigten Geschenke
    private ArrayList<ChristmasPresent> presents;
    // Liste für alle Geschenke, die nach dem aktuellen Frame gelöscht werden sollen
    private ArrayList<ChristmasPresent> presentsToBeRemoved;
    // Label für die Darstellung des Punktestands
    private Label scoreView;
    // Anzahl der insgesamt erstellten Geschenke
    private int spawnedPresents;
    // Anzahl der gesammelten Geschenke
    private int catchedPresents;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        backgroundMusic = new AudioClip(BACKGROUND_MUSIC_PATH);
        backgroundImage = new Image(0, 0, BACKGROUND_IMAGE_PATH);
        presents = new ArrayList<>();
        presentsToBeRemoved = new ArrayList<>();
        player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION);
        initScoring();
        initScoreView();
        // Start der Musikwiedergabe als "Endlosschleife"
        backgroundMusic.loop();
    }

    private void initScoring() {
        spawnedPresents = 0;
        catchedPresents = 0;
    }

    private void initScoreView() {
        scoreView = new Label(SCORE_VIEW_X, SCORE_VIEW_Y, "", SCORE_VIEW_COLOR);
        scoreView.setFontSize(SCORE_VIEW_FONT_SIZE);
        updateScoreView();
    }

    @Override
    public void draw() {
        backgroundImage.draw();
        refillPresentArray();
        drawAndUpdatePresents();
        player.draw();
        scoreView.draw();
    }

    /** @TODO
     * Prüft, ob in der Liste bereits die maximale Anzahl an Geschenken vorhanden ist und füllt die Liste bei Bedarf auf
     */
    private void refillPresentArray() {
        if(presents.size() < MAX_NUMBER_OF_PRESENTS) {
            ChristmasPresent newPresent = ChristmasPresentFactory.createRandomPresent(this);
            presents.add(newPresent);
            spawnedPresents++;
            updateScoreView();
        }
    }


    /**
     * Zeichnet und bewegt alle Geschenke
     */
    private void drawAndUpdatePresents() {
        // Alle Geschenke in der Liste werden aktualisiert und gezeichnet
        for(ChristmasPresent present: presents) {
            // Geschenke, die durch das Update den Bildschirm verlassen, werden in der onPresentLeftCanvas-Methode zur presentsToBeRemoved-Liste hinzugefügt
            present.update();
            present.draw();
            // Fangen die Spieler*innen ein Geschenk (Distanz zwischen Spielfigur und Geschenk unterschreitete einen bestimmten Wert) ...
            if (present.distanceTo(player) <= PRESENT_CATCH_DISTANCE) {
                // ... merken wir uns das Geschenk für das Löschen am Ende des Frames vor ...
                presentsToBeRemoved.add(present);
                // ... und aktualisieren Punktestand und Punkteanzeige
                catchedPresents++;
                updateScoreView();
            }
        }
        // Am Ende entfernen wir alle nicht mehr benötigten Geschenke aus der Liste ...
        presents.removeAll(presentsToBeRemoved);
        // ... und leeren auch die temporärer Liste, um im nächsten Frame erneut die zu löschenden Geschenke dort zu sammelngit
        presentsToBeRemoved.clear();
    }

    /**
     * Aktualisiert den Text im Label, um den neuen Punktestand darzsutellen
     */
    private void updateScoreView() {
        scoreView.setText(SCORE_VIEW_PREFIX + catchedPresents + "/" + spawnedPresents);
    }

    /**
     * Abfangen der Tastatur-Events
     *
     * @param event
     */
    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        // Prüft ob eine der beiden relvanten Pfeiltasten gedrückt wurde ...
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_LEFT:
                // .. und bewegt dann die Spielfigur
                player.moveLeft();
                break;
            case KeyPressedEvent.VK_RIGHT:
                // .. und bewegt dann die Spielfigur
                player.moveRight();
                break;
            default:
                break;
        }
    }

    /**
     * Wird von den Geschenke-Instanzen aufgerufen, sobald diese den Bildschirmrand überschritten haben
     *
     * @param present Das Geschenk, das den Bilschirmrand überschritten hat
     */
    @Override
    public void onPresentLeftCanvas(ChristmasPresent present) {
        // Merkt das Geschenk für den Löschvorgang am Ende des aktuellen Frames vor
        presentsToBeRemoved.add(present);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
