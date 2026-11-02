package figuren;

/**
 * Superklasse aller Figuren.
 * Das Waffen-Verhalten ist nicht mehr per Vererbung fest eingebaut,
 * sondern als Aggregation (HAT-Beziehung) ueber das Interface ausgelagert.
 * Dadurch kann die Waffe zur Laufzeit gewechselt werden.
 */
public abstract class Figur {

    private String characterName;
    private IWaffenVerhalten waffe;

    public Figur(String characterName, IWaffenVerhalten waffe) {
        this.characterName = characterName;
        this.waffe = waffe;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setWaffe(IWaffenVerhalten waffe) {
        this.waffe = waffe;
    }

    public IWaffenVerhalten getWaffe() {
        return waffe;
    }

    public void laufen() {
        System.out.println(characterName + " laeuft.");
    }

    /** Kaempfen delegiert an das aktuelle Waffen-Verhalten. */
    public void kaempfen() {
        if (waffe == null) {
            System.out.println("  -> kaempft mit blossen Faeusten.");
        } else {
            waffe.verwendeWaffe();
        }
    }
}
