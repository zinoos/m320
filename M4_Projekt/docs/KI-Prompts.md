# KI-Prompts – Dokumentation der KI-Unterstützung

Verwendetes Werkzeug: **Claude Code** (Anthropic, Modell Claude Opus 5.5), im Terminal direkt auf diesem Repository.
Alle Java-Dateien tragen im Kopfkommentar eine **KI-Deklaration**.

## 1. Auftrag und Projektidee

> **Prompt:** *(Auftragstext „Kompetenznachweis für M4 – eigenes Projekt als Einzelarbeit“ eingefügt)*
> „mache das und mache es mit dem repo von zino 320 mache es mit draw io“

Daraus abgeleitete Arbeitsschritte (von der KI vorgeschlagen, von mir geprüft):

1. Projektidee wählen, die Vererbung, Interfaces, Polymorphismus, Delegation und mindestens 2 Design Patterns
   sinnvoll braucht → **Fahrzeugvermietung** (Fahrzeugtypen = Hierarchie, Tarife = Strategy).
2. Muss-/Nice-to-have-Kriterien und Use Cases formulieren.
3. Grobe Skizze von Klassendiagramm und Sequenzdiagramm in draw.io.
4. Implementation in Schichten (ui / logik / model / daten / fehler), ohne externe Bibliotheken (wie die anderen Aufträge im Repo).
5. Selbsttest und Testlauf der Konsole mit allen Use Cases.
6. Detailliertes Klassendiagramm und Sequenzdiagramm nach der Implementation.

## 2. Prompts für die Diagramme (draw.io)

Die `.drawio`-Dateien wurden von der KI per Skript als draw.io-XML erzeugt und anschliessend mit der
draw.io-Desktop-CLI als PNG exportiert:

```
draw.io.exe -x -f png -s 1.5 -b 20 -o klassendiagramm.png klassendiagramm.drawio
```

Die Dateien lassen sich in draw.io (Desktop oder <https://app.diagrams.net>) öffnen und weiter bearbeiten.

### Skizze Klassendiagramm (Planung)

> „Skizziere ein grobes UML-Klassendiagramm für eine Fahrzeugvermietung in draw.io: nur Klassennamen,
> Pakete ui / logik / model / daten. Zeige die Vererbung Fahrzeug → Auto / Motorrad / Lieferwagen,
> die Interfaces IPreisStrategie, IRepository<T> und IMietListener und wo Strategy, Factory und Observer eingesetzt werden.“

### Skizze Sequenzdiagramm (Planung)

> „Skizziere den Use Case ‚Fahrzeug vermieten‘ als groben Ablauf: Mitarbeiter → UI → Service → Fahrzeug / Speicher.“

### Detailliertes Klassendiagramm (nach Abschluss)

> „Erstelle aus dem fertigen Code in `M4_Projekt/src` ein detailliertes UML-Klassendiagramm in draw.io:
> alle Klassen mit Attributen und Methoden inkl. Sichtbarkeit (+ - #), abstrakte Klassen kursiv,
> Interfaces mit «interface», Vererbung (durchgezogen, leeres Dreieck), Realisierung (gestrichelt),
> Aggregation mit Kardinalitäten. Pakete farbig hinterlegen, Design Patterns in einer Notiz erklären.“

Korrekturen im Dialog: überlappende Boxen verschieben, Kanten der Strategien / Exceptions als Baum führen,
Assoziation Mietvertrag → Fahrzeug nicht durch die FahrzeugFactory zeichnen.

### Detailliertes Sequenzdiagramm (nach Abschluss)

> „Erstelle in draw.io ein Sequenzdiagramm für UC4 ‚Fahrzeug vermieten‘ genau so, wie es im Code abläuft:
> KonsolenUI → Eingabe → VermietungService → IRepository / IPreisStrategie / Fahrzeug / Mietvertrag / IMietListener.
> Mit Aktivierungsbalken, Rückgabe-Pfeilen, einem alt-Fragment für ‚Fahrzeug bereits vermietet‘
> (Exception) und einem loop-Fragment für die Listener. Mietvertrag erst beim «create» erscheinen lassen.“

## 3. Prompts für den Code

> „Implementiere das Projekt in Java 21 ohne externe Bibliotheken, Paket `vermietung`, Unterpakete ui, logik,
> model, daten, fehler. Persistenz als CSV-Dateien über ein Repository-Interface. Eingaben validieren,
> eigene Exceptions, Clean-Code-Stellen im Code mit `// Clean Code:` markieren, jede Datei mit KI-Deklaration.“

> „Schreibe einen kleinen Selbsttest ohne JUnit, der Preisberechnung, Validierung und Persistenz prüft.“

## 4. Was ich selbst geprüft bzw. entschieden habe

- Preisregeln (Zuschläge pro Fahrzeugtyp, Wochenend-/Stammkundenrabatt) nachgerechnet,
  z.B. Fr–Mo Motorrad: 75 + 56.25 + 56.25 + 75 = 262.50 CHF.
- Alle Use Cases in der Konsole durchgespielt, inkl. Fehleingaben (Buchstaben statt Zahl, falsches Datum,
  ungültige E-Mail, `;` im Namen, Kilometerstand kleiner als vorher, Fahrzeug doppelt vermieten).
- `java -cp out vermietung.SelbstTest` läuft mit 17 bestandenen Prüfungen.
