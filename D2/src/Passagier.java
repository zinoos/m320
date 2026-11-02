public class Passagier {
    private String name;

    public Passagier(String name) {
        this.name = name;
    }

    public void nameAusgeben() {
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
