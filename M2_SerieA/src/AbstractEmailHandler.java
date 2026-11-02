public abstract class AbstractEmailHandler {

    private AbstractEmailHandler next;

    public AbstractEmailHandler setNext(AbstractEmailHandler next) {
        this.next = next;
        return next;
    }

    public void handle(Email email) {
        if (canHandle(email)) {
            process(email);
        } else if (next != null) {
            next.handle(email);
        } else {
            System.out.println("  Keine Zuständigkeit für " + email);
        }
    }

    protected abstract boolean canHandle(Email email);

    protected abstract void process(Email email);
}
