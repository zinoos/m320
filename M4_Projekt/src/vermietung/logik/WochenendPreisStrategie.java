package vermietung.logik;

import vermietung.model.Fahrzeug;
import vermietung.model.Kunde;
import vermietung.model.Mietvertrag;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Wochenend-Aktion: Samstage und Sonntage kosten 25% weniger.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class WochenendPreisStrategie implements IPreisStrategie {

    private static final double WOCHENEND_FAKTOR = 0.75;

    @Override
    public double berechnePreis(Fahrzeug fahrzeug, Kunde kunde, LocalDate von, LocalDate bis) {
        long tage = Mietvertrag.berechneTage(von, bis);
        double summe = 0.0;
        for (int i = 0; i < tage; i++) {
            LocalDate tag = von.plusDays(i);
            summe += istWochenende(tag) ? fahrzeug.getTagespreis() * WOCHENEND_FAKTOR : fahrzeug.getTagespreis();
        }
        return summe;
    }

    // Clean Code: kleine Methode mit sprechendem Namen statt verschachtelter Bedingung
    private boolean istWochenende(LocalDate tag) {
        return tag.getDayOfWeek() == DayOfWeek.SATURDAY || tag.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public String getBezeichnung() {
        return "Wochenend-Aktion (Sa/So -25%)";
    }
}
