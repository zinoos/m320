# V2 Serie A – Methoden überschreiben (Medien-Verwaltung)

## Klassen
| Klasse | Attribute |
|---|---|
| `Medium` (abstrakt, Superklasse) | `titel`, `erscheinungsjahr`, `istAusgeliehen` |
| `Buch` | + `autor`, `seitenzahl` |
| `DVD` | + `regisseur`, `dauerMinuten` |
| `CD` | + `interpret`, `anzahlTracks` |
| `MedienVerwaltung` | verwaltet `List<Medium>` |
| `Main` | User-Menu |

## Umsetzung
- Jede Subklasse überschreibt `print()` und gibt via `super.print()` auch die
  Attribute der Superklasse aus (Polymorphismus).
- `MedienVerwaltung`: `hinzufuegen()`, `loeschen()`, `print()` (ruft für jedes
  Medium dessen eigene `print()`-Methode auf), `suche(titel)`, `sucheAusgeliehene()`,
  `ausleihen()`, `zurueckgeben()`.
- **User-Interaktion**: Medien hinzufügen, nach ausgeliehenen Medien suchen,
  nur **nicht ausgeliehene** Medien löschen (`loeschen()` verweigert ausgeliehene).

```mermaid
classDiagram
    class Medium {
        <<abstract>>
        -String titel
        -int erscheinungsjahr
        -boolean istAusgeliehen
        +print() void
    }
    class Buch {
        -String autor
        -int seitenzahl
        +print() void
    }
    class DVD {
        -String regisseur
        -int dauerMinuten
        +print() void
    }
    class CD {
        -String interpret
        -int anzahlTracks
        +print() void
    }
    class MedienVerwaltung {
        -List~Medium~ medien
        +hinzufuegen(Medium m) void
        +loeschen(String titel) boolean
        +print() void
        +suche(String titel) Medium
        +sucheAusgeliehene() List~Medium~
        +ausleihen(String titel) boolean
        +zurueckgeben(String titel) boolean
    }
    Medium <|-- Buch
    Medium <|-- DVD
    Medium <|-- CD
    MedienVerwaltung "1" o-- "0..*" Medium
```

## Ausführen
```bash
javac -d out src/medien/*.java
java -cp out medien.Main
```
