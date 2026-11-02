public class SpamHandler extends AbstractEmailHandler {

    private static final String[] SPAM_WORTE = {"gewinn", "gratis", "jetzt klicken"};

    @Override
    protected boolean canHandle(Email email) {
        String text = (email.getSubject() + " " + email.getBody()).toLowerCase();
        for (String wort : SPAM_WORTE) {
            if (text.contains(wort)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void process(Email email) {
        System.out.println("  SpamHandler: verschoben in Spam-Ordner -> " + email);
    }
}
