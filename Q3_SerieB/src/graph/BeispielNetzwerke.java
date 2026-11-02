package graph;

import static graph.Mobilitaet.MIT_AUTO;
import static graph.Mobilitaet.OHNE_AUTO;

/**
 * Liefert die Beispieldaten: den gerichteten Graphen vom Auftragsblatt und ein ungerichtetes Personen-Netzwerk.
 */
public final class BeispielNetzwerke {

    public static final String BEISPIEL_START = "1";
    public static final String BEISPIEL_ZIEL = "6";
    public static final String START_PERSON = "Ich";
    public static final String ENTFERNTESTE_PERSON = "Gina";

    private static final String[][] BEISPIEL_KANTEN = {
            {"1", "2"}, {"1", "3"}, {"2", "4"}, {"3", "4"}, {"4", "5"}, {"5", "6"}
    };

    private static final String[][] FREUNDSCHAFTEN = {
            {"Ich", "Anna"}, {"Ich", "Ben"}, {"Anna", "Clara"}, {"Ben", "Clara"},
            {"Ben", "Eva"}, {"Clara", "David"}, {"Eva", "Felix"}, {"Felix", "Gina"}
    };

    private BeispielNetzwerke() {
    }

    /**
     * Gerichteter Graph mit den Knoten 1 bis 6 (Kanten nur in eine Richtung).
     */
    public static Netzwerk erstelleBeispielGraph() {
        Netzwerk beispiel = new Netzwerk();
        for (String[] kante : BEISPIEL_KANTEN) {
            beispiel.fuegeKanteHinzu(kante[0], kante[1]);
        }
        return beispiel;
    }

    /**
     * Ungerichtetes Freundes-Netzwerk; David und Felix besitzen ein Auto.
     */
    public static Netzwerk erstelleFreundesNetzwerk() {
        Netzwerk freunde = new Netzwerk();
        fuegePersonenHinzu(freunde);
        for (String[] freundschaft : FREUNDSCHAFTEN) {
            freunde.fuegeFreundschaftHinzu(freundschaft[0], freundschaft[1]);
        }
        return freunde;
    }

    private static void fuegePersonenHinzu(Netzwerk freunde) {
        freunde.fuegePersonHinzu("Ich", OHNE_AUTO);
        freunde.fuegePersonHinzu("Anna", OHNE_AUTO);
        freunde.fuegePersonHinzu("Ben", OHNE_AUTO);
        freunde.fuegePersonHinzu("Clara", OHNE_AUTO);
        freunde.fuegePersonHinzu("David", MIT_AUTO);
        freunde.fuegePersonHinzu("Eva", OHNE_AUTO);
        freunde.fuegePersonHinzu("Felix", MIT_AUTO);
        freunde.fuegePersonHinzu("Gina", OHNE_AUTO);
    }
}
