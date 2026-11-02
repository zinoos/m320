package vermietung.model;

/**
 * Design Pattern: Factory (Simple Factory).
 * Einziger Ort, der entscheidet, welche Fahrzeug-Subklasse zu einem FahrzeugTyp gehoert.
 * Wird von der UI (neues Fahrzeug erfassen) und vom CSV-Repository (Fahrzeug aus Datei laden) genutzt.
 * Kommt ein neuer Typ dazu, muss nur das Enum und diese Methode erweitert werden.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public final class FahrzeugFactory {

    private FahrzeugFactory() {
        // nur statische Erzeugungsmethode
    }

    public static Fahrzeug erstelle(FahrzeugTyp typ, String id, String marke, String modell,
                                    double grundpreisProTag, int kilometerstand,
                                    boolean verfuegbar, int zusatzwert) {
        return switch (typ) {
            case AUTO -> new Auto(id, marke, modell, grundpreisProTag, kilometerstand, verfuegbar, zusatzwert);
            case MOTORRAD -> new Motorrad(id, marke, modell, grundpreisProTag, kilometerstand, verfuegbar, zusatzwert);
            case LIEFERWAGEN -> new Lieferwagen(id, marke, modell, grundpreisProTag, kilometerstand, verfuegbar, zusatzwert);
        };
    }
}
