package vermietung.logik;

import vermietung.model.Fahrzeug;
import vermietung.model.Kunde;

import java.time.LocalDate;

/**
 * Design Pattern: Strategy.
 * Kapselt die Regel, wie der Mietpreis berechnet wird. Der VermietungService
 * delegiert die Berechnung an die aktuell gesetzte Strategie, die zur Laufzeit
 * gewechselt werden kann (z.B. Aktionswochen), ohne den Service zu aendern.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public interface IPreisStrategie {

    double berechnePreis(Fahrzeug fahrzeug, Kunde kunde, LocalDate von, LocalDate bis);

    String getBezeichnung();
}
