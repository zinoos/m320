package figuren;

public class DolchVerhalten implements IWaffenVerhalten {
    @Override
    public void verwendeWaffe() {
        System.out.println("  -> sticht blitzschnell mit dem Dolch zu!");
    }
}
