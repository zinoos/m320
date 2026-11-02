public class SalesHandler extends AbstractEmailHandler {

    @Override
    protected boolean canHandle(Email email) {
        String betreff = email.getSubject().toLowerCase();
        return betreff.contains("angebot") || betreff.contains("bestellung") || betreff.contains("verkauf");
    }

    @Override
    protected void process(Email email) {
        System.out.println("  SalesHandler: an Verkaufsabteilung weitergeleitet -> " + email);
    }
}
