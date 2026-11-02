// Subklasse: erbt alles von Fahrzeug und hat zusaetzliche Attribute
public class Auto extends Fahrzeug {

    private int anzahlTueren;
    private String treibstoff;

    public Auto(String marke, String kundenname, boolean istRepariert, int anzahlTueren, String treibstoff) {
        super(marke, kundenname, istRepariert); // Konstruktor der Superklasse setzt die gemeinsamen Attribute
        this.anzahlTueren = anzahlTueren;
        this.treibstoff = treibstoff;
    }

    // Ueberschreibt print(): erst Attribute der Superklasse, dann die eigenen
    @Override
    public void print() {
        System.out.print("[Auto] ");
        super.print();
        System.out.println("       Tueren: " + anzahlTueren + ", Treibstoff: " + treibstoff);
    }
}
