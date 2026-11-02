public class Main {

    public static void main(String[] args) {
        AbstractEmailHandler spam = new SpamHandler();
        AbstractEmailHandler verkauf = new SalesHandler();
        AbstractEmailHandler allgemein = new GeneralInboxHandler();

        spam.setNext(verkauf).setNext(allgemein);

        Email[] emails = {
            new Email("kunde@example.ch", "Angebot für Server", "Guten Tag, wir hätten gerne ein Angebot."),
            new Email("werbung@example.ch", "Gratis Gewinn!", "Jetzt klicken und gewinnen."),
            new Email("chef@example.ch", "Sitzung morgen", "Bitte pünktlich erscheinen.")
        };

        for (Email email : emails) {
            System.out.println("Verarbeite " + email);
            spam.handle(email);
        }
    }
}
