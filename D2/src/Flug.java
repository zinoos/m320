import java.util.ArrayList;
import java.util.List;

public class Flug {
    private List<Passagier> passagiere = new ArrayList<>();

    public void passagierHinzufuegen(Passagier passagier) {
        passagiere.add(passagier);
    }

    public boolean passagierEntfernen(String name) {
        for (Passagier p : passagiere) {
            if (p.getName().equals(name)) {
                passagiere.remove(p);
                return true;
            }
        }
        return false;
    }

    public void passagierListeAusgeben() {
        if (passagiere.isEmpty()) {
            System.out.println("Keine Passagiere auf diesem Flug gebucht.");
            return;
        }
        for (Passagier p : passagiere) {
            p.nameAusgeben();
        }
    }
}
