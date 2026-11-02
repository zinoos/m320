package figuren;

public class StabVerhalten implements IWaffenVerhalten {
    @Override
    public void verwendeWaffe() {
        System.out.println("  -> wirkt einen Zauber mit dem Stab!");
    }
}
