# V2 Serie C – Erweiterung der Garagen-App (Polymorphismus)

## Klassen
| Klasse | Attribute |
|---|---|
| `Fahrzeug` (abstrakt, Superklasse) | `marke`, `kundenname`, `istRepariert` |
| `Auto` | + `anzahlTueren`, `treibstoff` |
| `Motorrad` | + `hubraum` (ccm), `hatSeitenwagen` |
| `Lastwagen` | + `nutzlast` (t), `anzahlAchsen` |
| `Garage` | verwaltet `List<Fahrzeug>` |
| `Main` | User-Menu |

## Umsetzung
- Jede Subklasse **überschreibt** `print()` (`@Override`), ruft zuerst `super.print()`
  auf (Attribute der Superklasse) und gibt danach ihre eigenen Attribute aus.
- `Garage.print()` läuft über die `List<Fahrzeug>` und ruft bei jedem Objekt
  `print()` auf. Welche Methode ausgeführt wird, entscheidet sich erst zur Laufzeit
  anhand des echten Objekttyps → **Polymorphismus / dynamisches Binden**.
- `sucheNachKunde()` / `sucheUndPrint()` – ein bestimmtes Fahrzeug suchen und ausgeben.
- `sucheReparierte()` / `printReparierte()` – alle reparierten Fahrzeuge ausgeben.
- Menu: alle anzeigen, suchen, reparierte anzeigen, als repariert markieren.

```mermaid
classDiagram
    class Fahrzeug {
        <<abstract>>
        -String marke
        -String kundenname
        -boolean istRepariert
        +print() void
    }
    class Auto {
        -int anzahlTueren
        -String treibstoff
        +print() void
    }
    class Motorrad {
        -int hubraum
        -boolean hatSeitenwagen
        +print() void
    }
    class Lastwagen {
        -double nutzlast
        -int anzahlAchsen
        +print() void
    }
    class Garage {
        -List~Fahrzeug~ fahrzeuge
        +hinzufuegen(Fahrzeug f) void
        +print() void
        +sucheNachKunde(String name) Fahrzeug
        +sucheReparierte() List~Fahrzeug~
        +printReparierte() void
        +reparieren(String name) boolean
    }
    Fahrzeug <|-- Auto
    Fahrzeug <|-- Motorrad
    Fahrzeug <|-- Lastwagen
    Garage "1" o-- "0..*" Fahrzeug
```

## Ausführen
```bash
javac -d out src/garage/*.java
java -cp out garage.Main
```
