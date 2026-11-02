# D1 – Klasse Heizung

Java-Konsolenanwendung ohne externe Bibliotheken.

## Klassen

- `Heizung`: Attribute `temperature`, `min`, `max` und `increment` (alle `private`, mit Getter und Setter).
  - Zwei Konstruktoren (überladen): `Heizung(temperature, increment)` setzt `min = 10` und `max = 30`,
    `Heizung(temperature, increment, min, max)` nimmt eigene Grenzen.
  - `warmer()` erhöht die Temperatur um `increment`, `colder()` senkt sie. Würde die Grenze `max` bzw.
    `min` überschritten, bleibt die Temperatur gleich und es erscheint eine Warnung.
  - `toString()` gibt alle Werte aus.
- `HeizungTest`: interaktiver Test mit zwei Heizungen (Wohnzimmer und Büro). Mit `+` und `-` wird die
  aktive Heizung wärmer oder kälter, mit `1` und `2` wechselt man die Heizung, `q` beendet.

## Ausführen

```bash
javac -d out src/*.java
java -cp out HeizungTest
```
