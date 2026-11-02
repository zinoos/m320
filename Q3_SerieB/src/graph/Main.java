package graph;

public class Main {

    public static void main(String[] args) {
        Netzwerk beispiel = new Netzwerk();
        beispiel.addKante("1", "2");
        beispiel.addKante("1", "3");
        beispiel.addKante("2", "4");
        beispiel.addKante("3", "4");
        beispiel.addKante("4", "5");
        beispiel.addKante("5", "6");

        System.out.println("=== Beispiel-Graph (Adjazenzliste) ===");
        beispiel.print();
        System.out.println("Kuerzester Weg 1 -> 6: " + beispiel.kuerzesterWeg("1", "6"));
        System.out.println();

        Netzwerk freunde = new Netzwerk();
        freunde.addPerson("Ich", false);
        freunde.addPerson("Anna", false);
        freunde.addPerson("Ben", false);
        freunde.addPerson("Clara", false);
        freunde.addPerson("David", true);
        freunde.addPerson("Eva", false);
        freunde.addPerson("Felix", true);
        freunde.addPerson("Gina", false);

        freunde.addFreundschaft("Ich", "Anna");
        freunde.addFreundschaft("Ich", "Ben");
        freunde.addFreundschaft("Anna", "Clara");
        freunde.addFreundschaft("Ben", "Clara");
        freunde.addFreundschaft("Ben", "Eva");
        freunde.addFreundschaft("Clara", "David");
        freunde.addFreundschaft("Eva", "Felix");
        freunde.addFreundschaft("Felix", "Gina");

        System.out.println("=== Personen-Netzwerk ===");
        freunde.print();
        System.out.println();

        String besitzer = freunde.sucheNaechstenAutoBesitzer("Ich");
        System.out.println("Naechste Person mit Auto (BFS ab 'Ich'): "
                + (besitzer != null ? besitzer : "niemand gefunden"));
        System.out.println("Weg dorthin: " + freunde.kuerzesterWeg("Ich", besitzer));
        System.out.println("Kuerzester Weg Ich -> Gina: " + freunde.kuerzesterWeg("Ich", "Gina"));
    }
}
