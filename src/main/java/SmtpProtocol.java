/**
 * Class SmtpProtocol
 *
 * This class contains keys words for the protocol SMTP
 */
public class SmtpProtocol {

    public static final String HELLO = "EHLO";
    public static final String FROM = "MAIL FROM: ";
    public static final String TO = "RCPT TO: ";
    public static final String DATA = "DATA";
    public static final String END_OF_DATA = System.lineSeparator() + "." + System.lineSeparator();
    public static final String QUIT = "quit";

    public static final int ACCEPTED = 250;


}
