package vermietung.logik;

import vermietung.model.Fahrzeug;
import vermietung.model.Kunde;
import vermietung.model.Mietvertrag;

import java.time.LocalDate;

/**
 * Normaler Tarif: Anzahl Tage x Tagespreis des Fahrzeugs.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class StandardPreisStrategie implements IPreisStrategie {

    @Override
    public double berechnePreis(Fahrzeug fahrzeug, Kunde kunde, LocalDate von, LocalDate bis) {
        // Polymorphismus: getTagespreis() liefert je nach Subklasse einen anderen Zuschlag
        return Mietvertrag.berechneTage(von, bis) * fahrzeug.getTagespreis();
    }

    @Override
    public String getBezeichnung() {
        return "Standardtarif";
    }
}
