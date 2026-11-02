package vermietung;

import vermietung.daten.CsvFahrzeugRepository;
import vermietung.daten.CsvKundeRepository;
import vermietung.daten.CsvMietvertragRepository;
import vermietung.fehler.FahrzeugNichtVerfuegbarException;
import vermietung.fehler.ValidierungException;
import vermietung.logik.StammkundenPreisStrategie;
import vermietung.logik.StandardPreisStrategie;
import vermietung.logik.VermietungService;
import vermietung.logik.WochenendPreisStrategie;
import vermietung.model.Fahrzeug;
import vermietung.model.FahrzeugTyp;
import vermietung.model.Kunde;
import vermietung.model.Mietvertrag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * Kleiner Selbsttest ohne Framework (keine externen Bibliotheken).
 * Start: java -cp out vermietung.SelbstTest
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class SelbstTest {

    private static final LocalDate MONTAG = LocalDate.of(2026, 11, 2);
    private static int bestanden = 0;

    public static void main(String[] args) throws IOException {
        Path ordner = Files.createTempDirectory("vermietung-test");
        VermietungService service = neuerService(ordner);

        Fahrzeug golf = service.fahrzeugErfassen(FahrzeugTyp.AUTO, "VW", "Golf", 70, 1000, 5);
        Fahrzeug bus = service.fahrzeugErfassen(FahrzeugTyp.AUTO, "VW", "Multivan", 70, 1000, 7);
        Fahrzeug sprinter = service.fahrzeugErfassen(FahrzeugTyp.LIEFERWAGEN, "MB", "Sprinter", 100, 0, 10);
        Kunde anna = service.kundeRegistrieren("Anna", "anna@test.ch", true);

        pruefe(golf.getTagespreis() == 70, "Auto mit 5 Sitzen: kein Zuschlag");
        pruefe(bus.getTagespreis() == 90, "Auto mit 7 Sitzen: +20 Zuschlag (Polymorphismus)");
        pruefe(sprinter.getTagespreis() == 120, "Lieferwagen 10 m3: +20 Zuschlag");

        // Mo -> Mi = 2 Tage
        pruefe(new StandardPreisStrategie().berechnePreis(golf, anna, MONTAG, MONTAG.plusDays(2)) == 140,
                "Standardtarif 2 Tage");
        // Sa + So je 25% billiger: 2 * 70 * 0.75
        pruefe(new WochenendPreisStrategie().berechnePreis(golf, anna, MONTAG.minusDays(2), MONTAG) == 105,
                "Wochenendtarif");
        pruefe(new StammkundenPreisStrategie(new StandardPreisStrategie())
                        .berechnePreis(golf, anna, MONTAG, MONTAG.plusDays(1)) == 63,
                "Stammkundenrabatt 10% (Delegation an Basis-Strategie)");

        Mietvertrag vertrag = service.vermieten(golf.getId(), anna.getId(), MONTAG, MONTAG.plusDays(3));
        pruefe(!golf.istVerfuegbar(), "Fahrzeug nach Vermietung nicht verfuegbar");
        pruefeFehler(FahrzeugNichtVerfuegbarException.class,
                () -> service.vermieten(golf.getId(), anna.getId(), MONTAG, MONTAG.plusDays(1)),
                "Doppelte Vermietung wird verhindert");
        pruefeFehler(ValidierungException.class,
                () -> service.zurueckgeben(vertrag.getId(), MONTAG.plusDays(1), 500),
                "Kilometerstand darf nicht sinken");
        pruefeFehler(ValidierungException.class,
                () -> service.fahrzeugErfassen(FahrzeugTyp.AUTO, "X", "Y", -5, 0, 5),
                "Negativer Preis wird abgelehnt");
        pruefeFehler(ValidierungException.class,
                () -> service.kundeRegistrieren("Doppelt", "ANNA@test.ch", false),
                "E-Mail darf nur einmal registriert werden");

        service.zurueckgeben(vertrag.getId(), MONTAG.plusDays(2), 1250);
        pruefe(vertrag.getGefahreneKm() == 250 && vertrag.getBetrag() == 140, "Rueckgabe berechnet km und Endbetrag");
        pruefe(service.berechneUmsatz() == 140, "Umsatz zaehlt abgeschlossene Vertraege");

        VermietungService neuGeladen = neuerService(ordner);
        pruefe(neuGeladen.getAlleFahrzeuge().size() == 3, "Fahrzeuge werden persistent gespeichert");
        pruefe(neuGeladen.getFahrzeug("f-001").getKilometerstand() == 1250, "Kilometerstand persistent, ID case-insensitiv");
        pruefe(!neuGeladen.getAlleVertraege().get(0).istOffen(), "Vertragsstatus persistent");
        pruefe(neuGeladen.getFahrzeug("F-002") instanceof vermietung.model.Auto, "Factory erzeugt richtige Subklasse beim Laden");

        System.out.println("Alle " + bestanden + " Pruefungen bestanden.");
    }

    private static VermietungService neuerService(Path ordner) {
        return new VermietungService(new CsvFahrzeugRepository(ordner), new CsvKundeRepository(ordner),
                new CsvMietvertragRepository(ordner), new StandardPreisStrategie());
    }

    private static void pruefe(boolean bedingung, String beschreibung) {
        if (!bedingung) {
            throw new AssertionError("FEHLGESCHLAGEN: " + beschreibung);
        }
        bestanden++;
        System.out.println("ok   " + beschreibung);
    }

    private static void pruefeFehler(Class<? extends Exception> erwartet, Runnable aktion, String beschreibung) {
        try {
            aktion.run();
        } catch (Exception e) {
            pruefe(erwartet.isInstance(e), beschreibung + " (" + e.getClass().getSimpleName() + ")");
            return;
        }
        pruefe(false, beschreibung + " (keine Exception)");
    }
}
