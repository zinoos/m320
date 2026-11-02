package vermietung.logik;

import java.util.List;
import java.util.Optional;

/**
 * Design Pattern: Repository.
 * Die Logik-Schicht definiert, WAS sie an Persistenz braucht; WIE gespeichert wird
 * (CSV, Datenbank, ...) entscheidet die Daten-Schicht. Dependency Inversion:
 * die Logik haengt nur von diesem Interface ab, nicht von Dateien.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public interface IRepository<T> {

    /** Fuegt ein neues Objekt hinzu oder ueberschreibt eines mit gleicher ID. */
    void speichern(T objekt);

    Optional<T> findeNachId(String id);

    List<T> findeAlle();

    /** Liefert die naechste freie ID, z.B. "F-004". */
    String naechsteId();
}
