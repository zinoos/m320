package figuren;

public class Schurke extends Figur {

    public Schurke(String characterName, IWaffenVerhalten waffe) {
        super(characterName, waffe);
    }

    @Override
    public void kaempfen() {
        System.out.println("Schurke " + getCharacterName() + " schleicht sich von hinten an");
        super.kaempfen();
    }
}
