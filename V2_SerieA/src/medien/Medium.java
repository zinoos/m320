package medien;

/** Superklasse aller Medien. */
public abstract class Medium {

    private String titel;
    private int erscheinungsjahr;
    private boolean istAusgeliehen;

    public Medium(String titel, int erscheinungsjahr) {
        this.titel = titel;
        this.erscheinungsjahr = erscheinungsjahr;
        this.istAusgeliehen = false;
    }

    /** Wird von jeder Subklasse ueberschrieben (Polymorphismus). */
    public void print() {
        System.out.println("Typ:          " + getClass().getSimpleName());
        System.out.println("Titel:        " + titel);
        System.out.println("Jahr:         " + erscheinungsjahr);
        System.out.println("Ausgeliehen:  " + (istAusgeliehen ? "ja" : "nein"));
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(int erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public boolean isIstAusgeliehen() {
        return istAusgeliehen;
    }

    public void setIstAusgeliehen(boolean istAusgeliehen) {
        this.istAusgeliehen = istAusgeliehen;
    }
}
