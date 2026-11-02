package vermietung.model;

import vermietung.fehler.ValidierungException;

/**
 * Lieferwagen. Der Zuschlag steigt mit dem Ladevolumen (2 CHF pro m3).
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Lieferwagen extends Fahrzeug {

    private static final double ZUSCHLAG_PRO_KUBIKMETER = 2.0;

    private final int ladevolumenM3;

    public Lieferwagen(String id, String marke, String modell, double grundpreisProTag,
                       int kilometerstand, boolean verfuegbar, int ladevolumenM3) {
        super(id, marke, modell, grundpreisProTag, kilometerstand, verfuegbar);
        if (ladevolumenM3 < 1 || ladevolumenM3 > 40) {
            throw new ValidierungException("Das Ladevolumen muss zwischen 1 und 40 m3 liegen.");
        }
        this.ladevolumenM3 = ladevolumenM3;
    }

    @Override
    protected double berechneZuschlagProTag() {
        return ladevolumenM3 * ZUSCHLAG_PRO_KUBIKMETER;
    }

    @Override
    public FahrzeugTyp getTyp() {
        return FahrzeugTyp.LIEFERWAGEN;
    }

    @Override
    public int getZusatzwert() {
        return ladevolumenM3;
    }
}
