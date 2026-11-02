package figuren;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Figur krieger = new Krieger("Conan", new AxtVerhalten());
        Figur magier = new Magier("Merlin", new StabVerhalten());
        Figur schurke = new Schurke("Shadow", new DolchVerhalten());

        System.out.println("=== Kampf mit Start-Waffen ===");
        for (Figur f : List.of(krieger, magier, schurke)) {
            f.kaempfen();
        }

        System.out.println();
        System.out.println("=== Waffenwechsel zur Laufzeit ===");
        krieger.setWaffe(new DolchVerhalten());
        krieger.kaempfen();
        magier.setWaffe(new AxtVerhalten());
        magier.kaempfen();

        System.out.println();
        krieger.laufen();
    }
}
