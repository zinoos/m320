package garage;

public class Motorrad extends Fahrzeug {

    private int hubraum;
    private boolean hatSeitenwagen;

    public Motorrad(String marke, String kundenname, boolean istRepariert,
                    int hubraum, boolean hatSeitenwagen) {
        super(marke, kundenname, istRepariert);
        this.hubraum = hubraum;
        this.hatSeitenwagen = hatSeitenwagen;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Hubraum:      " + hubraum + " ccm");
        System.out.println("Seitenwagen:  " + (hatSeitenwagen ? "ja" : "nein"));
    }

    public int getHubraum() {
        return hubraum;
    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public boolean isHatSeitenwagen() {
        return hatSeitenwagen;
    }

    public void setHatSeitenwagen(boolean hatSeitenwagen) {
        this.hatSeitenwagen = hatSeitenwagen;
    }
}
