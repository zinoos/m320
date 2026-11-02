package garage;

/**
 * Superklasse aller Fahrzeuge in der Garage.
 * Enthaelt die gemeinsamen Attribute Marke, Kundenname und istRepariert.
 */
public abstract class Fahrzeug {

    private String marke;
    private String kundenname;
    private boolean istRepariert;

    public Fahrzeug(String marke, String kundenname, boolean istRepariert) {
        this.marke = marke;
        this.kundenname = kundenname;
        this.istRepariert = istRepariert;
    }

    /**
     * Gibt die Attribute der Superklasse aus.
     * Die Subklassen ueberschreiben diese Methode und geben zusaetzlich
     * ihre eigenen Attribute aus (Polymorphismus).
     */
    public void print() {
        System.out.println("Typ:          " + getClass().getSimpleName());
        System.out.println("Marke:        " + marke);
        System.out.println("Kundenname:   " + kundenname);
        System.out.println("Repariert:    " + (istRepariert ? "ja" : "nein"));
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getKundenname() {
        return kundenname;
    }

    public void setKundenname(String kundenname) {
        this.kundenname = kundenname;
    }

    public boolean isIstRepariert() {
        return istRepariert;
    }

    public void setIstRepariert(boolean istRepariert) {
        this.istRepariert = istRepariert;
    }
}
