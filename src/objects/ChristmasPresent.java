package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.Image;

/**
 * Repräsentiert ein Geschenk, das  sich über den Bildschirm bewegt und aufgefangen werden soll
 */
public class ChristmasPresent implements GameConfig {

    private Image image;
    private int speed;
    // Listener (Observer) der informiert werden soll, wenn das Geschenk den unteren Bildschirmrand überschritten hat
    private ChristmasPresentListener listener;

    /**
     * Mit diesem Konstruktor wird ein neues Geschenk erstellt
     * @param x initale x-Position des Geschenks
     * @param y initale y-Position des Geschenks
     * @param speed Bewegungsgeschwindigkeit des Geschenks auf der y-Achse
     * @param imagePath Pfad zum Bild, mit dem das Geschenk dargestellt werden soll
     * @param listener Listener (Observer) der informiert werden soll, wenn das Geschenk den unteren Bildschirmrand überschritten hat
     */
    public ChristmasPresent(int x, int y, int speed, String imagePath, ChristmasPresentListener listener) {
        image = new Image(x, y, imagePath);
        this.speed = speed;
        this.listener = listener;
    }

    /**
     * Bewegt das Geschenk nach unten und prüft, ob es dadurch den Bildschirmrand nach unten überschritten hat. Falls ja,
     * wird der an den Konstruktor übergebene Listener informiert
     */
    public void update() {
        image.move(0, speed);
        if(image.getTopBorder() > CANVAS_HEIGHT) {
            listener.onPresentLeftCanvas(this);
        }
    }

    public void draw() {
        image.draw();
    }

    /**
     * Berechnet die Distanz zwischen diesem Geschenk und der Spielfigur
     * @param player Spielfigur, zu der die Distanz berechnet werden soll
     * @return Distanz zwischen Geschenk und Spielfigur in Pixel
     */
    public int distanceTo(Player player) {
        return player.distanceTo(image);
    }
}
