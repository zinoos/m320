package vermietung;

import vermietung.daten.CsvFahrzeugRepository;
import vermietung.daten.CsvKundeRepository;
import vermietung.daten.CsvMietvertragRepository;
import vermietung.daten.MietProtokoll;
import vermietung.fehler.VermietungException;
import vermietung.logik.StandardPreisStrategie;
import vermietung.logik.VermietungService;
import vermietung.model.FahrzeugTyp;
import vermietung.ui.Eingabe;
import vermietung.ui.KonsolenUI;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Einstiegspunkt. Baut die drei Schichten zusammen (Daten -> Logik -> UI) und startet die UI.
 * Optionales Argument: Pfad zum Datenordner (Standard: ./data).
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class Main {

    public static void main(String[] args) {
        Path datenOrdner = Path.of(args.length > 0 ? args[0] : "data");
        try {
            VermietungService service = new VermietungService(
                    new CsvFahrzeugRepository(datenOrdner),
                    new CsvKundeRepository(datenOrdner),
                    new CsvMietvertragRepository(datenOrdner),
                    new StandardPreisStrategie());
            service.registriereListener(new MietProtokoll(datenOrdner.resolve("protokoll.log")));
            legeDemodatenAn(service);

            KonsolenUI ui = new KonsolenUI(service, new Eingabe(new Scanner(System.in)));
            service.registriereListener(ui);
            ui.starten();
        } catch (VermietungException e) {
            System.err.println("Start nicht moeglich: " + e.getMessage());
            System.exit(1);
        }
    }

    /** Beim allerersten Start ein paar Datensaetze anlegen, damit die Demo sofort funktioniert. */
    private static void legeDemodatenAn(VermietungService service) {
        if (!service.getAlleFahrzeuge().isEmpty()) {
            return;
        }
        service.fahrzeugErfassen(FahrzeugTyp.AUTO, "VW", "Golf", 70, 42000, 5);
        service.fahrzeugErfassen(FahrzeugTyp.AUTO, "Toyota", "Proace", 90, 15000, 8);
        service.fahrzeugErfassen(FahrzeugTyp.MOTORRAD, "Honda", "CB650R", 60, 8000, 649);
        service.fahrzeugErfassen(FahrzeugTyp.LIEFERWAGEN, "Mercedes", "Sprinter", 110, 98000, 11);
        service.kundeRegistrieren("Anna Muster", "anna@example.ch", true);
        service.kundeRegistrieren("Ben Beispiel", "ben@example.ch", false);
    }
}
