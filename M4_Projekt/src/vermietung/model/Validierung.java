package vermietung.model;

import vermietung.fehler.ValidierungException;

/**
 * Zentrale Pruefregeln fuer Eingaben und Modell-Daten.
 * Clean Code (DRY): jede Regel existiert genau einmal und wird von Modell, Factory und Service genutzt.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public final class Validierung {

    private static final int MAX_TEXT_LAENGE = 60;

    private Validierung() {
        // Utility-Klasse, keine Instanzen
    }

    public static String pruefeText(String wert, String feldname) {
        if (wert == null || wert.isBlank()) {
            throw new ValidierungException(feldname + " darf nicht leer sein.");
        }
        String bereinigt = wert.trim();
        if (bereinigt.length() > MAX_TEXT_LAENGE) {
            throw new ValidierungException(feldname + " darf hoechstens " + MAX_TEXT_LAENGE + " Zeichen lang sein.");
        }
        // ';' ist das Trennzeichen der CSV-Dateien und wuerde die Persistenz zerstoeren
        if (bereinigt.contains(";")) {
            throw new ValidierungException(feldname + " darf kein ';' enthalten.");
        }
        return bereinigt;
    }

    public static String pruefeEmail(String email) {
        String bereinigt = pruefeText(email, "E-Mail");
        if (!bereinigt.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new ValidierungException("'" + bereinigt + "' ist keine gueltige E-Mail-Adresse.");
        }
        return bereinigt.toLowerCase();
    }

    public static double pruefePositiv(double wert, String feldname) {
        if (wert <= 0 || Double.isNaN(wert) || Double.isInfinite(wert)) {
            throw new ValidierungException(feldname + " muss groesser als 0 sein.");
        }
        return wert;
    }

    public static int pruefeNichtNegativ(int wert, String feldname) {
        if (wert < 0) {
            throw new ValidierungException(feldname + " darf nicht negativ sein.");
        }
        return wert;
    }
}
