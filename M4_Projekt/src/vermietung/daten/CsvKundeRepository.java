package vermietung.daten;

import vermietung.model.Kunde;

import java.nio.file.Path;

/**
 * Speichert Kunden in kunden.csv.
 * Format: id;name;email;stammkunde
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class CsvKundeRepository extends CsvRepository<Kunde> {

    public CsvKundeRepository(Path datenOrdner) {
        super(datenOrdner.resolve("kunden.csv"), "K");
    }

    @Override
    protected Kunde ausFeldern(String[] f) {
        return new Kunde(f[0], f[1], f[2], Boolean.parseBoolean(f[3]));
    }

    @Override
    protected String[] inFelder(Kunde k) {
        return new String[]{k.getId(), k.getName(), k.getEmail(), String.valueOf(k.istStammkunde())};
    }

    @Override
    protected String idVon(Kunde kunde) {
        return kunde.getId();
    }
}
