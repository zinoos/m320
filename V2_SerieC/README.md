# V2 Serie C – Garagen-App (Vererbung und Polymorphismus)

Eigene Lösung (aus dem früheren Projekt `m320_zino_daniel`, Ordner `src/garage`).

## Klassen

| Klasse | Attribute / Aufgabe |
|---|---|
| `Fahrzeug` (Superklasse) | `marke`, `kundenname`, `istRepariert` |
| `Auto` | + `anzahlTueren`, `treibstoff` |
| `Motorrad` | + Hubraum (ccm), Seitenwagen ja/nein |
| `Lastwagen` | + Ladekapazität (t), Anzahl Achsen |
| `Garage` | verwaltet eine `List<Fahrzeug>`: hinzufügen, alle ausgeben, nach Kunde suchen, reparierte ausgeben |
| `GarageApp` | Startklasse mit User-Menü und Testdaten |

## Umsetzung

- Jede Subklasse **überschreibt** `print()` (`@Override`), ruft zuerst `super.print()` auf (gemeinsame Attribute)
  und gibt danach ihre eigenen Attribute aus.
- `Garage` speichert alle Fahrzeuge in **einer** Liste vom Typ `Fahrzeug`. Beim Aufruf von `f.print()` entscheidet
  erst zur Laufzeit der echte Objekttyp, welche `print()`-Methode läuft → **Polymorphismus / dynamisches Binden**.
- Menü: `1` alle Fahrzeuge, `2` Fahrzeug nach Kundenname suchen, `3` reparierte Fahrzeuge, `q` beenden.

## Ausführen

```bash
javac -d out src/*.java
java -cp out GarageApp
```
