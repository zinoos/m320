# M3 Serie A – Interfaces im UML-Klassendiagramm & Sequenzdiagramm

Grundlage ist der Code aus **V3 Serie B** (`../V3_SerieB`).
Die Diagramme sind mit draw.io gezeichnet: `klassendiagramm.drawio` und
`sequenzdiagramm.drawio` lassen sich unter <https://app.diagrams.net> öffnen und
bearbeiten, die PNGs daneben sind der Export.

## Notation: Interface vs. Vererbung

| Beziehung | Bedeutung | Notation |
|---|---|---|
| **Vererbung** (IST) | `Krieger extends Figur` | durchgezogene Linie, leeres Dreieck zur Superklasse |
| **Realisierung** (Interface) | `AxtVerhalten implements IWaffenVerhalten` | **gestrichelte** Linie, leeres Dreieck zum Interface |
| **Aggregation** (HAT) | `Figur` hat ein `IWaffenVerhalten` | Linie mit **leerer Raute** beim Ganzen (Figur), Kardinalitäten an beiden Enden |
| Interface | `IWaffenVerhalten` | Stereotyp `<<interface>>`, nur Methoden |
| abstrakte Klasse | `Figur` | Name *kursiv* bzw. `{abstract}` |

Kardinalitäten: Eine Figur hat genau **1** Waffen-Verhalten; ein Waffen-Verhalten
kann von **0..\*** Figuren verwendet werden.

## a) Klassendiagramm

[![Klassendiagramm](klassendiagramm.png)](klassendiagramm.drawio)

## b) Sequenzdiagramm – Use Case „Krieger kämpft und wechselt die Waffe“

Abgeleitet aus `Main.main()` in V3: Ein Krieger wird mit einer Axt erzeugt,
kämpft, bekommt zur Laufzeit einen Dolch und kämpft erneut.
Man sieht schön, dass `kaempfen()` an das jeweils gesetzte Waffen-Objekt delegiert.
Magier und Schurke laufen in `main()` genau gleich ab und sind deshalb weggelassen.

[![Sequenzdiagramm](sequenzdiagramm.png)](sequenzdiagramm.drawio)
