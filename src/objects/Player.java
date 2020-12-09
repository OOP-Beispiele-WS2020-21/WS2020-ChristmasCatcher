package objects;

import config.GameConfig;
import de.ur.mi.oop.graphics.GraphicsObject;
import de.ur.mi.oop.graphics.Image;

/**
 * Diese Klasse repräsentiert eine horizontal bewegbare Spielfigur. Die Figur
 * kann den Bildschirmrand nicht verlasssen.
 */

public class Player implements GameConfig {

    // Bild, das zur Darstellung der Spielfigur verwendet wird
    private Image character;

    /**
     * Mit diesem Konstruktor wird die Spielfigur erstellt. Dazu wird die initale Position auf dem Bildschirm übergeben.
     * @param x x-Koordinate der oberen linke Ecke der Spielfigur
     * @param y y-Koordinate der oberen linke Ecke der Spielfigur
     */
    public Player(int x, int y) {
        // Erzeugen des Bildes, dass zur Darstellung verwendet wird
        character = new Image(x, y, SANTA_IMAGE_PATH);
    }

    /**
     * Zeichnet die Spielfigur auf der Zeichenfläche
     */
    public void draw() {
        character.draw();
    }

    /**
     * Gibt die Distanz der Spielfigur (in Pixel) zu einem beliebigen GraphicsObject an
     * @param object GraphicsObject, zu dem die Distanz berechnet werden soll
     * @return
     */
    public int distanceTo(GraphicsObject object) {
        return (int) character.distanceTo(object);
    }

    /**
     * Bewegt die Spielfigur nach links (die Bewegungsdistanz in Pixel wird aus der GameConfig ausgelesen)
     */
    public void moveLeft() {
        move(-PLAYER_MOVEMENT_SPEED, 0);
    }

    /**
     * Bewegt die Spielfigur nach rechts (die Bewegungsdistanz in Pixel wird aus der GameConfig ausgelesen)
     */
    public void moveRight() {
        move(PLAYER_MOVEMENT_SPEED, 0);
    }

    /**
     * Bewegt die Spielfigur und prüft im Anschluss, ob durchd die Bewegung der Bildschirm verlassen wurde. Falls ja, wird
     * die Spielfigur auf die vorherige Position zurück gesetzt
     * @param x Wert in Pixeln, um den die Figur auf der x-Achse nach rechts bewegt werden soll
     * @param y Wert in Pixeln, um den die Figur auf der y-Achse nach unten bewegt werden soll
     */
    private void move(int x, int y) {
        float oldX = character.getXPos();
        float oldY = character.getYPos();
        character.move(x, y);
        if(checkIfPlayerHasLeftOnCanvas()) {
            character.setPosition(oldX, oldY);
        }
    }

    /**
     * Prüft, ob die Spielfigur die Zeichenfläche verlassen hat
     * @return Gibt true zurück, wenn die Spielfigur sich teilweise nicht mehr in der Zeichenfläche befindet
     */
    private boolean checkIfPlayerHasLeftOnCanvas() {
        if(character.getLeftBorder() < 0) {
            return true;
        }
        if(character.getRightBorder() > CANVAS_WIDTH) {
            return true;
        }
        if(character.getTopBorder() < 0) {
            return true;
        }
        if(character.getBottomBorder() > CANVAS_HEIGHT) {
            return true;
        }
        return false;
    }
}
