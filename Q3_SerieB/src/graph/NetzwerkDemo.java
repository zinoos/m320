package graph;

import static graph.BeispielNetzwerke.BEISPIEL_START;
import static graph.BeispielNetzwerke.BEISPIEL_ZIEL;
import static graph.BeispielNetzwerke.ENTFERNTESTE_PERSON;
import static graph.BeispielNetzwerke.START_PERSON;

import java.util.List;
import java.util.Optional;

/**
 * Ablauf der Demo: holt die Beispieldaten, laesst die Breitensuche rechnen und gibt die Ergebnisse aus.
 * Rechnet und druckt selbst nichts, sondern delegiert an Breitensuche und KonsolenAusgabe.
 */
public class NetzwerkDemo {

    private final KonsolenAusgabe ausgabe;

    public NetzwerkDemo(KonsolenAusgabe ausgabe) {
        this.ausgabe = ausgabe;
    }

    public void starten() {
        zeigeBeispielGraph();
        ausgabe.zeigeLeerzeile();
        zeigePersonenNetzwerk();
    }

    private void zeigeBeispielGraph() {
        Netzwerk beispiel = BeispielNetzwerke.erstelleBeispielGraph();
        ausgabe.zeigeTitel("Beispiel-Graph (Adjazenzliste)");
        ausgabe.zeigeAdjazenzliste(beispiel);
        List<String> weg = new Breitensuche(beispiel).kuerzesterWeg(BEISPIEL_START, BEISPIEL_ZIEL);
        ausgabe.zeigeKuerzestenWeg(BEISPIEL_START, BEISPIEL_ZIEL, weg);
    }

    private void zeigePersonenNetzwerk() {
        Netzwerk freunde = BeispielNetzwerke.erstelleFreundesNetzwerk();
        ausgabe.zeigeTitel("Personen-Netzwerk");
        ausgabe.zeigeAdjazenzliste(freunde);
        ausgabe.zeigeLeerzeile();
        zeigeAutoSuche(new Breitensuche(freunde));
    }

    private void zeigeAutoSuche(Breitensuche suche) {
        Optional<String> besitzer = suche.sucheNaechstenAutoBesitzer(START_PERSON);
        ausgabe.zeigeAutoBesitzer(START_PERSON, besitzer);
        ausgabe.zeigeWegDorthin(besitzer.map(ziel -> suche.kuerzesterWeg(START_PERSON, ziel)).orElse(List.of()));
        ausgabe.zeigeKuerzestenWeg(START_PERSON, ENTFERNTESTE_PERSON,
                suche.kuerzesterWeg(START_PERSON, ENTFERNTESTE_PERSON));
    }
}
