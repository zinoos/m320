# Modul 320 – Objektorientiert programmieren (TBZ)

Lösungen zu den Aufträgen. Jeder Auftrag liegt in einem eigenen Ordner mit README.

| Auftrag | Thema | Ordner |
|---|---|---|
| D1 | Klasse Heizung, überladene Konstruktoren | [`D1`](D1) |
| D2 | Flug mit Liste von Passagieren | [`D2`](D2) |
| M2 Serie A | Chain of Responsibility (E-Mail-Handler), Sequenzdiagramme | [`M2_SerieA`](M2_SerieA) |
| M3 Serie A | Interfaces im UML-Klassendiagramm, Sequenzdiagramm | [`M3_SerieA`](M3_SerieA) |
| M4 | Eigenes Projekt: Fahrzeugvermietung (Kompetenznachweis) | [`M4_Projekt`](M4_Projekt) |
| Q3 Serie B | Graphen und Breitensuche (BFS) | [`Q3_SerieB`](Q3_SerieB) |
| V1 | Vererbung und Spezialklassen (Employees) | [`V1`](V1) |
| V2 Serie A | Methoden überschreiben – Medien-Verwaltung | [`V2_SerieA`](V2_SerieA) |
| V3 Serie B | Dynamisches Verhalten in Interface auslagern | [`V3_SerieB`](V3_SerieB) |

Voraussetzung: Java 17+ (getestet mit Java 21). Keine externen Bibliotheken.

Alle Diagramme sind mit draw.io gezeichnet (`*.drawio`, öffnen mit <https://app.diagrams.net>);
die READMEs zeigen den PNG-Export.

Beispiel:

```bash
cd V2_SerieA
javac -d out src/medien/*.java
java -cp out medien.Main
```
