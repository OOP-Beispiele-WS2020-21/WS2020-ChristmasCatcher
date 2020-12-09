package objects;

/**
 * Mit diesem Inteface wird die Schnittstelle definiert, über die Instanzen der ChristmasPresent-Klasse
 * als Observable andere Objekte (Observer) darüber informieren sollen, wenn das Geschenk durch die Bewegung
 * nach unten den Bildschirmrand überschritten hat.
 */
public interface ChristmasPresentListener {

    /**
     * Wird aufgerufen, wenn ein Geschenk den Bildschirmrand nach unten überschritten hat
     * @param present Das Geschenk, das den Bilschirmrand überschritten hat
     */
    void onPresentLeftCanvas(ChristmasPresent present);

}
