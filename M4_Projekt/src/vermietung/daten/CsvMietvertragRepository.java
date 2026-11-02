package vermietung.daten;

import vermietung.model.Mietvertrag;

import java.nio.file.Path;
import java.time.LocalDate;

/**
 * Speichert Mietvertraege in mietvertraege.csv.
 * Format: id;fahrzeugId;kundeId;von;bisGeplant;rueckgabe(leer = offen);gefahreneKm;betrag
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class CsvMietvertragRepository extends CsvRepository<Mietvertrag> {

    public CsvMietvertragRepository(Path datenOrdner) {
        super(datenOrdner.resolve("mietvertraege.csv"), "M");
    }

    @Override
    protected Mietvertrag ausFeldern(String[] f) {
        LocalDate rueckgabe = f[5].isEmpty() ? null : LocalDate.parse(f[5]);
        return new Mietvertrag(f[0], f[1], f[2], LocalDate.parse(f[3]), LocalDate.parse(f[4]),
                rueckgabe, Integer.parseInt(f[6]), Double.parseDouble(f[7]));
    }

    @Override
    protected String[] inFelder(Mietvertrag v) {
        return new String[]{
                v.getId(), v.getFahrzeugId(), v.getKundeId(), v.getVon().toString(), v.getBisGeplant().toString(),
                v.istOffen() ? "" : v.getRueckgabe().toString(),
                String.valueOf(v.getGefahreneKm()), String.valueOf(v.getBetrag())
        };
    }

    @Override
    protected String idVon(Mietvertrag vertrag) {
        return vertrag.getId();
    }
}
