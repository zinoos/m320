package medien;

public class Buch extends Medium {

    private String autor;
    private int seitenzahl;

    public Buch(String titel, int erscheinungsjahr, String autor, int seitenzahl) {
        super(titel, erscheinungsjahr);
        this.autor = autor;
        this.seitenzahl = seitenzahl;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Autor:        " + autor);
        System.out.println("Seiten:       " + seitenzahl);
    }

    public String getAutor() {
        return autor;
    }

    public int getSeitenzahl() {
        return seitenzahl;
    }
}
