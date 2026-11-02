package vermietung.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Liest Benutzereingaben von der Konsole und wiederholt die Frage, bis die Eingabe
 * formal gueltig ist (Zahl, Datum, ...). Fachliche Pruefungen macht die Logik-Schicht.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Eingabe {

    private static final DateTimeFormatter DATUM_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final Scanner scanner;

    public Eingabe(Scanner scanner) {
        this.scanner = scanner;
    }

    /** Wirft NoSuchElementException, wenn die Eingabe beendet wurde (z.B. Ctrl+D / Pipe zu Ende). */
    public String leseZeile(String frage) {
        System.out.print(frage);
        if (!scanner.hasNextLine()) {
            throw new NoSuchElementException("Eingabe beendet");
        }
        return scanner.nextLine().trim();
    }

    public String leseText(String frage) {
        while (true) {
            String text = leseZeile(frage);
            if (!text.isEmpty()) {
                return text;
            }
            zeigeFehler("Bitte einen Wert eingeben.");
        }
    }

    public int leseGanzzahl(String frage, int min, int max) {
        while (true) {
            String text = leseZeile(frage);
            try {
                int zahl = Integer.parseInt(text);
                if (zahl >= min && zahl <= max) {
                    return zahl;
                }
                zeigeFehler("Bitte eine Zahl zwischen " + min + " und " + max + " eingeben.");
            } catch (NumberFormatException e) {
                zeigeFehler("'" + text + "' ist keine ganze Zahl.");
            }
        }
    }

    public double leseBetrag(String frage) {
        while (true) {
            String text = leseZeile(frage);
            try {
                // Komma wie in der Schweiz ueblich ebenfalls akzeptieren
                return Double.parseDouble(text.replace(',', '.'));
            } catch (NumberFormatException e) {
                zeigeFehler("'" + text + "' ist kein gueltiger Betrag.");
            }
        }
    }

    /** Leere Eingabe = Standardwert (z.B. heute). */
    public LocalDate leseDatum(String frage, LocalDate standard) {
        while (true) {
            String text = leseZeile(frage + " [" + standard.format(DATUM_FORMAT) + "]: ");
            if (text.isEmpty()) {
                return standard;
            }
            try {
                return LocalDate.parse(text, DATUM_FORMAT);
            } catch (DateTimeParseException e) {
                zeigeFehler("Datum bitte im Format TT.MM.JJJJ eingeben.");
            }
        }
    }

    public boolean leseJaNein(String frage) {
        while (true) {
            String text = leseZeile(frage + " (j/n): ").toLowerCase();
            if (text.equals("j") || text.equals("ja")) {
                return true;
            }
            if (text.equals("n") || text.equals("nein")) {
                return false;
            }
            zeigeFehler("Bitte j oder n eingeben.");
        }
    }

    public void zeigeFehler(String meldung) {
        System.out.println("  ! " + meldung);
    }
}
