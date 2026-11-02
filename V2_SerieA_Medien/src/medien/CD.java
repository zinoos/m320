package medien;

public class CD extends Medium {

    private String interpret;
    private int anzahlTracks;

    public CD(String titel, int erscheinungsjahr, String interpret, int anzahlTracks) {
        super(titel, erscheinungsjahr);
        this.interpret = interpret;
        this.anzahlTracks = anzahlTracks;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Interpret:    " + interpret);
        System.out.println("Tracks:       " + anzahlTracks);
    }

    public String getInterpret() {
        return interpret;
    }

    public int getAnzahlTracks() {
        return anzahlTracks;
    }
}
