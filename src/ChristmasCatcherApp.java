import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import objects.ChristmasPresent;
import objects.ChristmasPresentFactory;
import objects.ChristmasPresentListener;
import objects.Player;

public class ChristmasCatcherApp extends GraphicsApp implements GameConfig, ChristmasPresentListener {

    private Image backgroundImage;
    private Player player;
    private ChristmasPresent[] presents;
    private Label scoreView;
    private int score;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        backgroundImage = new Image(0, 0, BACKGROUND_IMAGE_PATH);
        presents = new ChristmasPresent[MAX_NUMBER_OF_PRESENTS];
        player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION);
        score = 0;
        scoreView = new Label(SCORE_VIEW_X, SCORE_VIEW_Y, SCORE_VIEW_PREFIX + score, SCORE_VIEW_COLOR);
        scoreView.setFontSize(SCORE_VIEW_FONT_SIZE);
    }

    @Override
    public void draw() {
        backgroundImage.draw();
        refillPresentArray();
        updateAndDrawPresents();
        player.draw();
        scoreView.draw();
    }

    private void refillPresentArray() {
        for (int i = 0; i < presents.length; i++) {
            if (presents[i] == null) {
                presents[i] = ChristmasPresentFactory.createRandomPresent(this);
            }
        }

    }

    private void removePresentFromArray(ChristmasPresent present) {
        for (int i = 0; i < presents.length; i++) {
            if (presents[i] == present) {
                presents[i] = null;
                return;
            }
        }
    }

    private void updateAndDrawPresents() {
        for (int i = 0; i < presents.length; i++) {
            if (presents[i] != null) {
                presents[i].update();
                if (presents[i] == null) {
                    return;
                }
                presents[i].draw();
                if (presents[i].distanceTo(player) <= PRESENT_CATCH_DISTANCE) {
                    presents[i] = null;
                    increaseScore();
                }
            }
        }
    }

    private void increaseScore() {
        score++;
        scoreView.setText(SCORE_VIEW_PREFIX + score);
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_LEFT:
                player.moveLeft();
                break;
            case KeyPressedEvent.VK_RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPresentLeftCanvas(ChristmasPresent present) {
        removePresentFromArray(present);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
