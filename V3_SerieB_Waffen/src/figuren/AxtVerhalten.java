package figuren;

public class AxtVerhalten implements IWaffenVerhalten {
    @Override
    public void verwendeWaffe() {
        System.out.println("  -> schwingt die Axt mit voller Wucht!");
    }
}
