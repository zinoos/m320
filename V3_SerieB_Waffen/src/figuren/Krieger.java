package figuren;

public class Krieger extends Figur {

    public Krieger(String characterName, IWaffenVerhalten waffe) {
        super(characterName, waffe);
    }

    @Override
    public void kaempfen() {
        System.out.println("Krieger " + getCharacterName() + " stuermt bruellend nach vorne");
        super.kaempfen(); // eigentliches Kaempfen via Waffen-Verhalten
    }
}
