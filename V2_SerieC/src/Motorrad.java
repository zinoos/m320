// Subklasse: erbt alles von Fahrzeug und hat zusaetzliche Attribute
public class Motorrad extends Fahrzeug {

    private int hubraum;
    private boolean hatSeitenwagen;

    public Motorrad(String marke, String kundenname, boolean istRepariert, int hubraum, boolean hatSeitenwagen) {
        super(marke, kundenname, istRepariert); // Konstruktor der Superklasse setzt die gemeinsamen Attribute
        this.hubraum = hubraum;
        this.hatSeitenwagen = hatSeitenwagen;
    }

    // Ueberschreibt print(): erst Attribute der Superklasse, dann die eigenen
    @Override
    public void print() {
        System.out.print("[Motorrad] ");
        super.print();
        System.out.println("           Hubraum: " + hubraum + " ccm, Seitenwagen: " + (hatSeitenwagen ? "ja" : "nein"));
    }
}
