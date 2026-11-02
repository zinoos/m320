# Modul 320 – Vererbung und Spezialklassen

Die Lösung ist eine Java-Konsolenanwendung ohne externe Bibliotheken.

## Enthaltene Anforderungen

- `Employee` ist die abstrakte Basisklasse mit gekapselten Attributen `name`, `id` und `salary`.
- `FullTimeEmployee` berechnet den Lohn und berücksichtigt bei `hasBonus = true` einen Bonus von 10 %.
- `PartTimeEmployee` berechnet `hourlyRate * hoursWorked`.
- `Intern` besitzt einen fixen Lohn und prüft, dass `hoursWorked` niemals grösser als `maxHours` wird.
- `EmployeeManager` führt für jede Spezialklasse eine eigene Liste und kann über alle Mitarbeiter hinweg nach Namen suchen.
- `Main` bietet eine einfache Benutzereingabe zum Anzeigen, Suchen und Hinzufügen von Mitarbeitern.

## Kompilieren und starten

```bash
mkdir -p out
javac -d out src/main/java/*.java
java -cp out Main
```

## Tests ausführen

```bash
javac -cp out -d out src/test/java/EmployeeManagerTest.java
java -ea -cp out EmployeeManagerTest
```

Die Bonusberechnung und die Bedeutung des fixen Praktikantenlohns sind im Code bewusst einfach gehalten, damit die Vererbung im Vordergrund bleibt.
