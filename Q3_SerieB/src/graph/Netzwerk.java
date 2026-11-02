package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Netzwerk {

    private final Map<String, List<String>> graph = new LinkedHashMap<>();
    private final Set<String> autoBesitzer = new HashSet<>();

    public void addPerson(String name) {
        graph.putIfAbsent(name, new ArrayList<>());
    }

    public void addPerson(String name, boolean hatAuto) {
        addPerson(name);
        if (hatAuto) {
            autoBesitzer.add(name);
        }
    }

    public void addKante(String von, String nach) {
        addPerson(von);
        addPerson(nach);
        if (!graph.get(von).contains(nach)) {
            graph.get(von).add(nach);
        }
    }

    public void addFreundschaft(String a, String b) {
        addKante(a, b);
        addKante(b, a);
    }

    public List<String> getNachbarn(String name) {
        return graph.getOrDefault(name, Collections.emptyList());
    }

    public boolean hatAuto(String name) {
        return autoBesitzer.contains(name);
    }

    public String sucheNaechstenAutoBesitzer(String start) {
        Queue<String> queue = new ArrayDeque<>(getNachbarn(start));
        Set<String> geprueft = new HashSet<>();
        geprueft.add(start);

        while (!queue.isEmpty()) {
            String person = queue.poll();
            if (geprueft.contains(person)) {
                continue;
            }
            geprueft.add(person);
            if (hatAuto(person)) {
                return person;
            }
            queue.addAll(getNachbarn(person));
        }
        return null;
    }

    public List<String> kuerzesterWeg(String start, String ziel) {
        if (!graph.containsKey(start) || !graph.containsKey(ziel)) {
            return Collections.emptyList();
        }
        Map<String, String> vorgaenger = new HashMap<>();
        Set<String> besucht = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        besucht.add(start);

        while (!queue.isEmpty()) {
            String aktuell = queue.poll();
            if (aktuell.equals(ziel)) {
                List<String> weg = new ArrayList<>();
                for (String k = ziel; k != null; k = vorgaenger.get(k)) {
                    weg.add(k);
                }
                Collections.reverse(weg);
                return weg;
            }
            for (String nachbar : getNachbarn(aktuell)) {
                if (besucht.add(nachbar)) {
                    vorgaenger.put(nachbar, aktuell);
                    queue.add(nachbar);
                }
            }
        }
        return Collections.emptyList();
    }

    public void print() {
        for (Map.Entry<String, List<String>> e : graph.entrySet()) {
            String auto = hatAuto(e.getKey()) ? " [Auto]" : "";
            System.out.println(e.getKey() + auto + " -> " + e.getValue());
        }
    }
}
