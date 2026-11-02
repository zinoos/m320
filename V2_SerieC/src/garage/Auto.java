package garage;

public class Auto extends Fahrzeug {

    private int anzahlTueren;
    private String treibstoff;

    public Auto(String marke, String kundenname, boolean istRepariert,
                int anzahlTueren, String treibstoff) {
        super(marke, kundenname, istRepariert);
        this.anzahlTueren = anzahlTueren;
        this.treibstoff = treibstoff;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Anzahl Tueren: " + anzahlTueren);
        System.out.println("Treibstoff:   " + treibstoff);
    }

    public int getAnzahlTueren() {
        return anzahlTueren;
    }

    public void setAnzahlTueren(int anzahlTueren) {
        this.anzahlTueren = anzahlTueren;
    }

    public String getTreibstoff() {
        return treibstoff;
    }

    public void setTreibstoff(String treibstoff) {
        this.treibstoff = treibstoff;
    }
}
