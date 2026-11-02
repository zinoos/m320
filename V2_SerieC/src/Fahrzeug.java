// Superklasse: gemeinsame Attribute aller Fahrzeuge
public class Fahrzeug {

    // private = nur ueber Methoden erreichbar (Kapselung)
    private String marke;
    private String kundenname;
    private boolean istRepariert;

    public Fahrzeug(String marke, String kundenname, boolean istRepariert) {
        this.marke = marke;
        this.kundenname = kundenname;
        this.istRepariert = istRepariert;
    }

    // Wird von jeder Subklasse ueberschrieben (Polymorphismus)
    public void print() {
        System.out.println("Marke: " + marke + ", Kunde: " + kundenname + ", repariert: " + (istRepariert ? "ja" : "nein"));
    }

    // Getter/Setter, damit NB, die Garage suchen und filtern kann
    public String getKundenname() {
        return kundenname;
    }

    public boolean isRepariert() {
        return istRepariert;
    }

    public void setRepariert(boolean istRepariert) {
        this.istRepariert = istRepariert;
    }
}
