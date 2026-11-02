package vermietung.logik;

import vermietung.model.Fahrzeug;
import vermietung.model.Kunde;

import java.time.LocalDate;

/**
 * Stammkunden-Rabatt von 10% auf eine beliebige Basis-Strategie.
 * Delegation: die eigentliche Preisberechnung wird an die Basis-Strategie weitergereicht,
 * diese Klasse wendet nur den Rabatt an (Komposition statt Vererbung).
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class StammkundenPreisStrategie implements IPreisStrategie {

    private static final double RABATT_FAKTOR = 0.9;

    private final IPreisStrategie basis;

    public StammkundenPreisStrategie(IPreisStrategie basis) {
        this.basis = basis;
    }

    @Override
    public double berechnePreis(Fahrzeug fahrzeug, Kunde kunde, LocalDate von, LocalDate bis) {
        double preis = basis.berechnePreis(fahrzeug, kunde, von, bis);
        return kunde.istStammkunde() ? preis * RABATT_FAKTOR : preis;
    }

    @Override
    public String getBezeichnung() {
        return basis.getBezeichnung() + " + Stammkunden -10%";
    }
}
