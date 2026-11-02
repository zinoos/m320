package vermietung.model;

import vermietung.fehler.ValidierungException;

/**
 * Motorrad. Ab 600 ccm Hubraum wird ein Zuschlag (hoehere Versicherung) verrechnet.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Motorrad extends Fahrzeug {

    private static final int HUBRAUM_GRENZE_CCM = 600;
    private static final double ZUSCHLAG_GROSSER_HUBRAUM = 15.0;

    private final int hubraumCcm;

    public Motorrad(String id, String marke, String modell, double grundpreisProTag,
                    int kilometerstand, boolean verfuegbar, int hubraumCcm) {
        super(id, marke, modell, grundpreisProTag, kilometerstand, verfuegbar);
        if (hubraumCcm < 50 || hubraumCcm > 2500) {
            throw new ValidierungException("Der Hubraum muss zwischen 50 und 2500 ccm liegen.");
        }
        this.hubraumCcm = hubraumCcm;
    }

    @Override
    protected double berechneZuschlagProTag() {
        return hubraumCcm >= HUBRAUM_GRENZE_CCM ? ZUSCHLAG_GROSSER_HUBRAUM : 0.0;
    }

    @Override
    public FahrzeugTyp getTyp() {
        return FahrzeugTyp.MOTORRAD;
    }

    @Override
    public int getZusatzwert() {
        return hubraumCcm;
    }
}
