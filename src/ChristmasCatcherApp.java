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
    private ChristmasPresent[] presents;
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
        presents = new ChristmasPresent[MAX_NUMBER_OF_PRESENTS];
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

    /**
     * Prüft, ob Stellen des Arrays zur Speicherung der dargestellen Geschenke aktuell "leer" sind und erstellt ggf.
     * ein neues Geschenk.
     */
    private void refillPresentArray() {
        for (int i = 0; i < presents.length; i++) {
            if (presents[i] == null) {
                presents[i] = ChristmasPresentFactory.createRandomPresent(this);
                spawnedPresents++;
                updateScoreView();
            }
        }

    }

    /**
     * Such im Array nach dem übergebenen Geschenk und entfernt dieses, in dem der gespeicherte Referenzwert
     * mit null überschrieben wird
     *
     * @param present Geschenk, das entfernt werden soll
     */
    private void removePresentFromArray(ChristmasPresent present) {
        for (int i = 0; i < presents.length; i++) {
            if (presents[i] == present) {
                presents[i] = null;
                return;
            }
        }
    }

    /**
     * Zeichnet und bewegt alle Geschenke
     */
    private void drawAndUpdatePresents() {
        for (int i = 0; i < presents.length; i++) {
            if (presents[i] != null) {
                /**
                 * Um zu verhindern, das ein, durch das Aktualisieren der Position entfernte Geschenk, gezeichnet wird,
                 * werden alle Geschenke zuerst gezeichnet und anschließend für den nächsten Frame neu positioniert.
                 * Dadurch wird ein möglicher Fehler umgangen, bei dem im umgekehrten Fall die Referenz auf das
                 * Geschenk-Objekt nach dem Aktualisieren (und dadurch ausgelösten Entfernen) nicht mehr gültig ist,
                 * und der draw-Aufruf zu einem Null Pointer-Fehler führen würde.
                 */
                presents[i].draw();
                /// Wenn die Distanz zwischen Spieler und diesem Geschenk den Grenzwert errreicht (oder unterschreitet) ...
                if (presents[i].distanceTo(player) <= PRESENT_CATCH_DISTANCE) {
                    // ... wird die Referenz auf das Objekt entfernt ...
                    presents[i] = null;
                    // ... der Punktestand angepasst ...
                    catchedPresents++;
                    // ... und das Label zur Darstellung des PUnktestands aktualisiert
                    updateScoreView();
                } else {
                    // Nur wenn da Geschenk noch nicht gefangen - und daher nicht entfernt wurde - wird es bewegt
                    presents[i].update();
                }
            }
        }
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
        // Entfern das Geschenk aus dem Array, um Platz für ein neues zu machen
        removePresentFromArray(present);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
