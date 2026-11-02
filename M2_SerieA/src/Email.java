public class Email {

    private final String sender;
    private final String subject;
    private final String body;

    public Email(String sender, String subject, String body) {
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Email{von=" + sender + ", betreff=" + subject + "}";
    }
}
