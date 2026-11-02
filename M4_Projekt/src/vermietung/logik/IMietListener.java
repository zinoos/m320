package vermietung.logik;

import vermietung.model.Mietvertrag;

/**
 * Design Pattern: Observer.
 * Wer ueber Vermietungen informiert werden will (Protokoll-Datei, Konsole, ...),
 * implementiert dieses Interface und registriert sich beim VermietungService.
 *
 * KI-Deklaration: erstellt mit Unterstuetzung von Claude (Anthropic).
 */
public interface IMietListener {

    void fahrzeugVermietet(Mietvertrag vertrag);

    void fahrzeugZurueckgegeben(Mietvertrag vertrag);
}
