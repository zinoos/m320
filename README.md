# Modul 320 – Objektorientiert programmieren (TBZ)

Lösungen zu den Aufträgen. Jeder Auftrag liegt in einem eigenen Ordner mit README.

| Auftrag | Thema | Ordner |
|---|---|---|
| V2 Serie A | Methoden überschreiben – Medien-Verwaltung | [`V2_SerieA`](V2_SerieA) |
| V2 Serie C | Garagen-App, Polymorphismus | [`V2_SerieC`](V2_SerieC) |
| V3 Serie B | Dynamisches Verhalten in Interface auslagern | [`V3_SerieB`](V3_SerieB) |
| M3 Serie A | Interfaces im UML-Klassendiagramm, Sequenzdiagramm | [`M3_SerieA`](M3_SerieA) |
| Q3 Serie B | Graphen und Breitensuche (BFS) | [`Q3_SerieB`](Q3_SerieB) |

Voraussetzung: Java 17+ (getestet mit Java 21). Keine externen Bibliotheken.

Alle Diagramme sind mit draw.io gezeichnet (`*.drawio`, öffnen mit <https://app.diagrams.net>);
die READMEs zeigen den PNG-Export.

Beispiel:

```bash
cd V2_SerieC
javac -d out src/garage/*.java
java -cp out garage.Main
```
