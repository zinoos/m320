// Subklasse: erbt alles von Fahrzeug und hat zusaetzliche Attribute
public class Lastwagen extends Fahrzeug {

    private double ladekapazitaet;
    private int anzahlAchsen;

    public Lastwagen(String marke, String kundenname, boolean istRepariert, double ladekapazitaet, int anzahlAchsen) {
        super(marke, kundenname, istRepariert); // Konstruktor der Superklasse setzt die gemeinsamen Attribute
        this.ladekapazitaet = ladekapazitaet;
        this.anzahlAchsen = anzahlAchsen;
    }

    // Ueberschreibt print(): erst Attribute der Superklasse, dann die eigenen
    @Override
    public void print() {
        System.out.print("[Lastwagen] ");
        super.print();
        System.out.println("            Ladekapazitaet: " + ladekapazitaet + " t, Achsen: " + anzahlAchsen);
    }
}
