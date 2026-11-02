# V3 Serie B – Dynamisches Verhalten in Interface auslagern

## Problem (Ausgangslage)
Für jede Kombination Figur × Waffe gab es eine eigene Klasse
(`AxtKrieger`, `DolchKrieger`, `AxtMagier`, `StabMagier`, …). Jede neue Waffe oder
Figur führt zu vielen neuen Klassen, und die Waffe kann zur Laufzeit nicht gewechselt werden.

## Lösung (Strategy-Pattern)
- Interface `IWaffenVerhalten` mit `verwendeWaffe()`.
- Implementierungen: `AxtVerhalten`, `StabVerhalten`, `DolchVerhalten`.
- `Figur` **hat** ein `IWaffenVerhalten` (Aggregation / HAT-Beziehung statt Vererbung)
  und bietet `setWaffe(...)` an.
- `kaempfen()` in `Figur` delegiert an `waffe.verwendeWaffe()`.
- Nur noch drei Figuren-Subklassen: `Krieger`, `Magier`, `Schurke`.

→ Neue Waffe = **eine** neue Klasse, keine Änderung an den Figuren.
→ Waffe wechselbar zur Laufzeit: `krieger.setWaffe(new DolchVerhalten());`

Klassen- und Sequenzdiagramm dazu: siehe [`../M3_SerieA`](../M3_SerieA).

## Ausführen
```bash
javac -d out src/figuren/*.java
java -cp out figuren.Main
```
