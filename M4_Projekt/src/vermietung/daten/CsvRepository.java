package vermietung.daten;

import vermietung.fehler.DatenzugriffException;
import vermietung.logik.IRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Gemeinsame Basis aller CSV-Repositories (Vererbung + Template Method):
 * Laden, Cachen, Schreiben und ID-Vergabe sind hier einmal implementiert,
 * die Subklassen liefern nur die Umwandlung Objekt <-> CSV-Zeile.
 *
 * shortcut: schreibt bei jedem speichern() die ganze Datei neu; fuer ein paar hundert Eintraege ok,
 * bei grossen Datenmengen auf eine echte DB (z.B. H2/SQLite) umstellen.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public abstract class CsvRepository<T> implements IRepository<T> {

    protected static final String TRENNZEICHEN = ";";

    private final Path datei;
    private final String idPraefix;
    private final Map<String, T> cache = new LinkedHashMap<>();

    protected CsvRepository(Path datei, String idPraefix) {
        this.datei = datei;
        this.idPraefix = idPraefix;
        laden();
    }

    /** CSV-Felder -> Objekt. Darf bei defekten Daten eine RuntimeException werfen. */
    protected abstract T ausFeldern(String[] felder);

    /** Objekt -> CSV-Felder (gleiche Reihenfolge wie ausFeldern). */
    protected abstract String[] inFelder(T objekt);

    protected abstract String idVon(T objekt);

    @Override
    public void speichern(T objekt) {
        cache.put(idVon(objekt), objekt);
        allesSchreiben();
    }

    @Override
    public Optional<T> findeNachId(String id) {
        return Optional.ofNullable(cache.get(id == null ? null : id.trim().toUpperCase()));
    }

    @Override
    public List<T> findeAlle() {
        return List.copyOf(cache.values());
    }

    @Override
    public String naechsteId() {
        int hoechsteNummer = cache.keySet().stream()
                .map(id -> id.substring(idPraefix.length() + 1))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);
        return String.format("%s-%03d", idPraefix, hoechsteNummer + 1);
    }

    private void laden() {
        if (!Files.exists(datei)) {
            return;
        }
        List<String> zeilen = leseZeilen();
        for (int nr = 0; nr < zeilen.size(); nr++) {
            String zeile = zeilen.get(nr);
            if (zeile.isBlank()) {
                continue;
            }
            try {
                T objekt = ausFeldern(zeile.split(TRENNZEICHEN, -1));
                cache.put(idVon(objekt), objekt);
            } catch (RuntimeException e) {
                throw new DatenzugriffException("Defekte Zeile " + (nr + 1) + " in " + datei + ": " + e.getMessage(), e);
            }
        }
    }

    private List<String> leseZeilen() {
        try {
            return Files.readAllLines(datei, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DatenzugriffException("Datei " + datei + " konnte nicht gelesen werden.", e);
        }
    }

    private void allesSchreiben() {
        List<String> zeilen = new ArrayList<>();
        for (T objekt : cache.values()) {
            zeilen.add(String.join(TRENNZEICHEN, inFelder(objekt)));
        }
        try {
            Path ordner = datei.toAbsolutePath().getParent();
            Files.createDirectories(ordner);
            Files.write(datei, zeilen, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DatenzugriffException("Datei " + datei + " konnte nicht geschrieben werden.", e);
        }
    }
}
