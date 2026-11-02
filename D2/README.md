# D2 – Flug und Passagiere

Java-Konsolenanwendung ohne externe Bibliotheken.

## Klassen

- `Passagier`: Attribut `name`, `nameAusgeben()` gibt den Namen aus.
- `Flug`: **hat** eine Liste von Passagieren (`List<Passagier>`).
  - `passagierHinzufuegen(Passagier)` bucht einen Passagier.
  - `passagierEntfernen(String name)` entfernt den ersten Passagier mit diesem Namen und gibt zurück,
    ob einer gefunden wurde.
  - `passagierListeAusgeben()` gibt alle Passagiere aus, oder einen Hinweis, wenn der Flug leer ist.
- `Main`: Menü zum Buchen, Anzeigen und Entfernen von Passagieren.

## Ausführen

```bash
javac -d out src/*.java
java -cp out Main
```
