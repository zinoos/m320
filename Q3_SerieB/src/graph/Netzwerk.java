package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Graph von Personen als Adjazenzliste: jeder Person ist die Liste ihrer Nachbarn zugeordnet.
 * Die Klasse speichert und liefert nur die Daten; Suche und Ausgabe liegen in anderen Klassen.
 */
public class Netzwerk {

    private final Map<String, List<String>> nachbarnProPerson = new LinkedHashMap<>();
    private final Set<String> autoBesitzer = new HashSet<>();

    public void fuegePersonHinzu(String name) {
        nachbarnProPerson.putIfAbsent(name, new ArrayList<>());
    }

    public void fuegePersonHinzu(String name, Mobilitaet mobilitaet) {
        fuegePersonHinzu(name);
        if (mobilitaet == Mobilitaet.MIT_AUTO) {
            autoBesitzer.add(name);
        }
    }

    /**
     * Fuegt eine gerichtete Kante hinzu: {@code nach} wird Nachbar von {@code von}, nicht umgekehrt.
     * Fehlende Personen werden angelegt, doppelte Kanten ignoriert.
     */
    public void fuegeKanteHinzu(String von, String nach) {
        fuegePersonHinzu(von);
        fuegePersonHinzu(nach);
        List<String> nachbarn = nachbarnProPerson.get(von);
        if (!nachbarn.contains(nach)) {
            nachbarn.add(nach);
        }
    }

    /**
     * Fuegt eine ungerichtete Kante hinzu: eine Freundschaft gilt in beide Richtungen.
     */
    public void fuegeFreundschaftHinzu(String person, String freund) {
        fuegeKanteHinzu(person, freund);
        fuegeKanteHinzu(freund, person);
    }

    public boolean enthaelt(String name) {
        return nachbarnProPerson.containsKey(name);
    }

    public Set<String> getPersonen() {
        return Collections.unmodifiableSet(nachbarnProPerson.keySet());
    }

    public List<String> getNachbarn(String name) {
        return Collections.unmodifiableList(nachbarnProPerson.getOrDefault(name, List.of()));
    }

    public boolean hatAuto(String name) {
        return autoBesitzer.contains(name);
    }
}
