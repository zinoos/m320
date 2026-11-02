package vermietung.daten;

import vermietung.model.Fahrzeug;
import vermietung.model.FahrzeugFactory;
import vermietung.model.FahrzeugTyp;

import java.nio.file.Path;

/**
 * Speichert Fahrzeuge in fahrzeuge.csv.
 * Format: id;typ;marke;modell;grundpreis;kilometerstand;verfuegbar;zusatzwert
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public class CsvFahrzeugRepository extends CsvRepository<Fahrzeug> {

    public CsvFahrzeugRepository(Path datenOrdner) {
        super(datenOrdner.resolve("fahrzeuge.csv"), "F");
    }

    @Override
    protected Fahrzeug ausFeldern(String[] f) {
        // Factory entscheidet anhand des Typs, welche Subklasse entsteht
        return FahrzeugFactory.erstelle(FahrzeugTyp.valueOf(f[1]), f[0], f[2], f[3],
                Double.parseDouble(f[4]), Integer.parseInt(f[5]), Boolean.parseBoolean(f[6]),
                Integer.parseInt(f[7]));
    }

    @Override
    protected String[] inFelder(Fahrzeug fz) {
        return new String[]{
                fz.getId(), fz.getTyp().name(), fz.getMarke(), fz.getModell(),
                String.valueOf(fz.getGrundpreisProTag()), String.valueOf(fz.getKilometerstand()),
                String.valueOf(fz.istVerfuegbar()), String.valueOf(fz.getZusatzwert())
        };
    }

    @Override
    protected String idVon(Fahrzeug fahrzeug) {
        return fahrzeug.getId();
    }
}
