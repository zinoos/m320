package graph;

import java.util.List;
import java.util.Optional;

/**
 * Gibt Netzwerke und Suchergebnisse auf der Konsole aus. Einzige Klasse, die System.out verwendet.
 */
public class KonsolenAusgabe {

    private static final String AUTO_MARKIERUNG = " [Auto]";
    private static final String KEIN_TREFFER = "niemand gefunden";

    public void zeigeTitel(String titel) {
        System.out.println("=== " + titel + " ===");
    }

    public void zeigeLeerzeile() {
        System.out.println();
    }

    /**
     * Gibt pro Person eine Zeile "Person [Auto] -> [Nachbarn]" aus.
     */
    public void zeigeAdjazenzliste(Netzwerk netzwerk) {
        for (String person : netzwerk.getPersonen()) {
            System.out.println(person + autoMarkierung(netzwerk, person) + " -> " + netzwerk.getNachbarn(person));
        }
    }

    public void zeigeKuerzestenWeg(String start, String ziel, List<String> weg) {
        System.out.println("Kuerzester Weg " + start + " -> " + ziel + ": " + weg);
    }

    public void zeigeAutoBesitzer(String start, Optional<String> besitzer) {
        System.out.println("Naechste Person mit Auto (BFS ab '" + start + "'): " + besitzer.orElse(KEIN_TREFFER));
    }

    public void zeigeWegDorthin(List<String> weg) {
        System.out.println("Weg dorthin: " + weg);
    }

    private String autoMarkierung(Netzwerk netzwerk, String person) {
        return netzwerk.hatAuto(person) ? AUTO_MARKIERUNG : "";
    }
}
