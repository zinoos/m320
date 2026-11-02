package graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * Breitensuche (Breadth-First-Search) auf einem {@link Netzwerk}.
 * Die Personen werden Ebene fuer Ebene abgearbeitet (zuerst Nachbarn 1. Grades, dann 2. Grades, ...).
 * Dadurch ist der erste Treffer immer derjenige mit den wenigsten Kanten.
 */
public class Breitensuche {

    private final Netzwerk netzwerk;

    public Breitensuche(Netzwerk netzwerk) {
        this.netzwerk = netzwerk;
    }

    /**
     * Sucht ab {@code start} die naechste Person, die ein Auto besitzt. Die Startperson selbst zaehlt nicht.
     *
     * @return die naechste Person mit Auto, oder leer, wenn keine erreichbare Person ein Auto hat
     */
    public Optional<String> sucheNaechstenAutoBesitzer(String start) {
        Queue<String> warteschlange = new ArrayDeque<>(netzwerk.getNachbarn(start));
        Set<String> geprueft = new HashSet<>(Set.of(start));

        while (!warteschlange.isEmpty()) {
            String person = warteschlange.poll();
            if (!geprueft.add(person)) {
                continue;
            }
            if (netzwerk.hatAuto(person)) {
                return Optional.of(person);
            }
            warteschlange.addAll(netzwerk.getNachbarn(person));
        }
        return Optional.empty();
    }

    /**
     * Liefert den kuerzesten Weg (wenigste Kanten) von {@code start} nach {@code ziel}, beide eingeschlossen.
     *
     * @return den Weg, oder eine leere Liste, wenn eine Person fehlt oder das Ziel nicht erreichbar ist
     */
    public List<String> kuerzesterWeg(String start, String ziel) {
        if (!netzwerk.enthaelt(start) || !netzwerk.enthaelt(ziel)) {
            return List.of();
        }
        Map<String, String> vorgaenger = berechneVorgaenger(start);
        if (!vorgaenger.containsKey(ziel)) {
            return List.of();
        }
        return baueWeg(vorgaenger, ziel);
    }

    /*
     * Breitensuche ab start. Fuer jede erreichte Person wird gespeichert, von wem aus sie zuerst
     * erreicht wurde. Der Schluessel dient gleichzeitig als "besucht"-Markierung; die Startperson
     * hat keinen Vorgaenger (Wert null), damit endet spaeter der Rueckweg in baueWeg.
     */
    private Map<String, String> berechneVorgaenger(String start) {
        Map<String, String> vorgaenger = new HashMap<>();
        vorgaenger.put(start, null);
        Queue<String> warteschlange = new ArrayDeque<>(List.of(start));

        while (!warteschlange.isEmpty()) {
            merkeUnbesuchteNachbarn(warteschlange.poll(), vorgaenger, warteschlange);
        }
        return vorgaenger;
    }

    private void merkeUnbesuchteNachbarn(String person, Map<String, String> vorgaenger, Queue<String> warteschlange) {
        for (String nachbar : netzwerk.getNachbarn(person)) {
            if (!vorgaenger.containsKey(nachbar)) {
                vorgaenger.put(nachbar, person);
                warteschlange.add(nachbar);
            }
        }
    }

    private List<String> baueWeg(Map<String, String> vorgaenger, String ziel) {
        LinkedList<String> weg = new LinkedList<>();
        for (String person = ziel; person != null; person = vorgaenger.get(person)) {
            weg.addFirst(person);
        }
        return weg;
    }
}
