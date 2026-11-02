package figuren;

public class Magier extends Figur {

    public Magier(String characterName, IWaffenVerhalten waffe) {
        super(characterName, waffe);
    }

    @Override
    public void kaempfen() {
        System.out.println("Magier " + getCharacterName() + " murmelt eine Beschwoerung");
        super.kaempfen();
    }
}
