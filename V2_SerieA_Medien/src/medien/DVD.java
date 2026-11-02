package medien;

public class DVD extends Medium {

    private String regisseur;
    private int dauerMinuten;

    public DVD(String titel, int erscheinungsjahr, String regisseur, int dauerMinuten) {
        super(titel, erscheinungsjahr);
        this.regisseur = regisseur;
        this.dauerMinuten = dauerMinuten;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Regisseur:    " + regisseur);
        System.out.println("Dauer:        " + dauerMinuten + " min");
    }

    public String getRegisseur() {
        return regisseur;
    }

    public int getDauerMinuten() {
        return dauerMinuten;
    }
}
