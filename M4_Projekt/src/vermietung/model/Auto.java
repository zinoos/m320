package vermietung.model;

import vermietung.fehler.ValidierungException;

/**
 * Personenwagen. Grosse Autos (mehr als 5 Sitze) kosten einen Zuschlag.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Auto extends Fahrzeug {

    // Clean Code: keine Magic Numbers, sprechende Konstanten
    private static final int STANDARD_SITZE = 5;
    private static final double ZUSCHLAG_GROSSRAUM = 20.0;

    private final int anzahlSitze;

    public Auto(String id, String marke, String modell, double grundpreisProTag,
                int kilometerstand, boolean verfuegbar, int anzahlSitze) {
        super(id, marke, modell, grundpreisProTag, kilometerstand, verfuegbar);
        if (anzahlSitze < 2 || anzahlSitze > 9) {
            throw new ValidierungException("Ein Auto hat 2 bis 9 Sitze.");
        }
        this.anzahlSitze = anzahlSitze;
    }

    @Override
    protected double berechneZuschlagProTag() {
        return anzahlSitze > STANDARD_SITZE ? ZUSCHLAG_GROSSRAUM : 0.0;
    }

    @Override
    public FahrzeugTyp getTyp() {
        return FahrzeugTyp.AUTO;
    }

    @Override
    public int getZusatzwert() {
        return anzahlSitze;
    }
}
