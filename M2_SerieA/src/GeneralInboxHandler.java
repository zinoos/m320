public class GeneralInboxHandler extends AbstractEmailHandler {

    @Override
    protected boolean canHandle(Email email) {
        return true;
    }

    @Override
    protected void process(Email email) {
        System.out.println("  GeneralInboxHandler: in den allgemeinen Posteingang -> " + email);
    }
}
