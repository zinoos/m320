package graph;

import static graph.Mobilitaet.MIT_AUTO;
import static graph.Mobilitaet.OHNE_AUTO;

import java.util.List;
import java.util.Optional;

/**
 * Konsolen-Demo: baut den Beispiel-Graphen vom Auftragsblatt und ein Personen-Netzwerk auf
 * und zeigt die Ergebnisse der Breitensuche.
 */
public class Main {

    private static final String BEISPIEL_START = "1";
    private static final String BEISPIEL_ZIEL = "6";
    private static final String[][] BEISPIEL_KANTEN = {
            {"1", "2"}, {"1", "3"}, {"2", "4"}, {"3", "4"}, {"4", "5"}, {"5", "6"}
    };

    private static final String START_PERSON = "Ich";
    private static final String ENTFERNTESTE_PERSON = "Gina";
    private static final String[][] FREUNDSCHAFTEN = {
            {"Ich", "Anna"}, {"Ich", "Ben"}, {"Anna", "Clara"}, {"Ben", "Clara"},
            {"Ben", "Eva"}, {"Clara", "David"}, {"Eva", "Felix"}, {"Felix", "Gina"}
    };

    private static final String AUTO_MARKIERUNG = " [Auto]";
    private static final String KEIN_TREFFER = "niemand gefunden";

    public static void main(String[] args) {
        zeigeBeispielGraph();
        System.out.println();
        zeigePersonenNetzwerk();
    }

    private static void zeigeBeispielGraph() {
        Netzwerk beispiel = erstelleBeispielGraph();
        System.out.println("=== Beispiel-Graph (Adjazenzliste) ===");
        zeigeAdjazenzliste(beispiel);
        List<String> weg = new Breitensuche(beispiel).kuerzesterWeg(BEISPIEL_START, BEISPIEL_ZIEL);
        System.out.println("Kuerzester Weg " + BEISPIEL_START + " -> " + BEISPIEL_ZIEL + ": " + weg);
    }

    private static void zeigePersonenNetzwerk() {
        Netzwerk freunde = erstelleFreundesNetzwerk();
        System.out.println("=== Personen-Netzwerk ===");
        zeigeAdjazenzliste(freunde);
        System.out.println();
        zeigeAutoSuche(new Breitensuche(freunde));
    }

    private static void zeigeAutoSuche(Breitensuche suche) {
        Optional<String> besitzer = suche.sucheNaechstenAutoBesitzer(START_PERSON);
        List<String> wegZumBesitzer = besitzer.map(ziel -> suche.kuerzesterWeg(START_PERSON, ziel)).orElse(List.of());

        System.out.println("Naechste Person mit Auto (BFS ab '" + START_PERSON + "'): " + besitzer.orElse(KEIN_TREFFER));
        System.out.println("Weg dorthin: " + wegZumBesitzer);
        System.out.println("Kuerzester Weg " + START_PERSON + " -> " + ENTFERNTESTE_PERSON + ": "
                + suche.kuerzesterWeg(START_PERSON, ENTFERNTESTE_PERSON));
    }

    private static Netzwerk erstelleBeispielGraph() {
        Netzwerk beispiel = new Netzwerk();
        for (String[] kante : BEISPIEL_KANTEN) {
            beispiel.fuegeKanteHinzu(kante[0], kante[1]);
        }
        return beispiel;
    }

    private static Netzwerk erstelleFreundesNetzwerk() {
        Netzwerk freunde = new Netzwerk();
        freunde.fuegePersonHinzu("Ich", OHNE_AUTO);
        freunde.fuegePersonHinzu("Anna", OHNE_AUTO);
        freunde.fuegePersonHinzu("Ben", OHNE_AUTO);
        freunde.fuegePersonHinzu("Clara", OHNE_AUTO);
        freunde.fuegePersonHinzu("David", MIT_AUTO);
        freunde.fuegePersonHinzu("Eva", OHNE_AUTO);
        freunde.fuegePersonHinzu("Felix", MIT_AUTO);
        freunde.fuegePersonHinzu("Gina", OHNE_AUTO);
        for (String[] freundschaft : FREUNDSCHAFTEN) {
            freunde.fuegeFreundschaftHinzu(freundschaft[0], freundschaft[1]);
        }
        return freunde;
    }

    private static void zeigeAdjazenzliste(Netzwerk netzwerk) {
        for (String person : netzwerk.getPersonen()) {
            System.out.println(person + autoMarkierung(netzwerk, person) + " -> " + netzwerk.getNachbarn(person));
        }
    }

    private static String autoMarkierung(Netzwerk netzwerk, String person) {
        return netzwerk.hatAuto(person) ? AUTO_MARKIERUNG : "";
    }
}
