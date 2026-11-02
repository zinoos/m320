package vermietung.daten;

import vermietung.fehler.DatenzugriffException;
import vermietung.logik.IMietListener;
import vermietung.model.Mietvertrag;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Observer: schreibt jede Vermietung und Rueckgabe als Zeile in protokoll.log.
 * Der Service weiss nichts von dieser Datei; er ruft nur das Listener-Interface auf.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class MietProtokoll implements IMietListener {

    private static final DateTimeFormatter ZEITSTEMPEL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path datei;

    public MietProtokoll(Path datei) {
        this.datei = datei;
    }

    @Override
    public void fahrzeugVermietet(Mietvertrag vertrag) {
        schreibe("VERMIETET   " + vertrag.getId() + " Fahrzeug " + vertrag.getFahrzeugId()
                + " an " + vertrag.getKundeId() + String.format(" (voraussichtlich %.2f CHF)", vertrag.getBetrag()));
    }

    @Override
    public void fahrzeugZurueckgegeben(Mietvertrag vertrag) {
        schreibe("ZURUECK     " + vertrag.getId() + " Fahrzeug " + vertrag.getFahrzeugId()
                + ", " + vertrag.getGefahreneKm() + " km" + String.format(", %.2f CHF", vertrag.getBetrag()));
    }

    private void schreibe(String text) {
        String zeile = LocalDateTime.now().format(ZEITSTEMPEL) + "  " + text + System.lineSeparator();
        try {
            Files.createDirectories(datei.toAbsolutePath().getParent());
            Files.writeString(datei, zeile, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new DatenzugriffException("Protokoll konnte nicht geschrieben werden.", e);
        }
    }
}
