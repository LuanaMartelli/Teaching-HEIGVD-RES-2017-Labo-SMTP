import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class Mail
 *
 * This class represents a Mail
 * It reads the body of the Mail from a file
 */
public class Mail {

    private Person from;
    private Group to;
    private String subject = "";
    private String body = "";

    private static int message_number = 0;
    private static final int NB_MESSAGES = 4;


    public boolean readMailFromFile() throws IOException {
        int next_message = (message_number++ % NB_MESSAGES) + 1;
        BufferedReader file = new BufferedReader(new FileReader("src/main/data/mail" +next_message+".utf8"));

        String line;

        /* Gets subject of email */
        line = file.readLine();

        if (!line.contains("Subject")) {
            System.out.println("Error in the format of the mail !\n" +
                    "Quitting...");
            return false;
        }

        subject = line;

        /* Reads the body */
        while ((line = file.readLine()) != null) {
            body += line;
        }

        return true;
    }

    public Person getFrom() {
        return from;
    }

    public Group getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Mail(Person source, Group group) {
        this.from = source;
        this.to = group;
        try {
            readMailFromFile();
        } catch (IOException e) {
            System.out.println("Error while reading a file...\n" +
                    "Empty message !");
            subject = "";
            body = "";
        }
    }
}
