package garage;

public class Lastwagen extends Fahrzeug {

    private double nutzlast;  // in Tonnen
    private int anzahlAchsen;

    public Lastwagen(String marke, String kundenname, boolean istRepariert,
                     double nutzlast, int anzahlAchsen) {
        super(marke, kundenname, istRepariert);
        this.nutzlast = nutzlast;
        this.anzahlAchsen = anzahlAchsen;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Nutzlast:     " + nutzlast + " t");
        System.out.println("Anzahl Achsen: " + anzahlAchsen);
    }

    public double getNutzlast() {
        return nutzlast;
    }

    public void setNutzlast(double nutzlast) {
        this.nutzlast = nutzlast;
    }

    public int getAnzahlAchsen() {
        return anzahlAchsen;
    }

    public void setAnzahlAchsen(int anzahlAchsen) {
        this.anzahlAchsen = anzahlAchsen;
    }
}
