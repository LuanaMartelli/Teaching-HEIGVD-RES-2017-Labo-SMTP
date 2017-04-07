import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class SmtpClient
 *
 * This class starts a connexion with a SMTP server
 * It sends  an email according to the protocol.
 *
 */
public class SmtpClient {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public SmtpClient() {

    }

    /**
     * Opens a connexion between a SMTP server and a client
     * @throws IOException
     */
    public void connect() throws IOException {
        socket = new Socket(SmtpServerInfo.HOST, SmtpServerInfo.PORT);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        /* Welcome message */
        String feedback = reader.readLine();
        System.out.println(feedback);

        /* EHLO */
        writer.println(SmtpProtocol.HELLO + " you");
        writer.flush();

        /* The server will send an unknow number of data with this prefix */
        while ((feedback = reader.readLine()).contains(SmtpProtocol.ACCEPTED+"-"))
            System.out.println(feedback);

        /* At this point, we know we reached end of data, we can start writing an email */
        if (!feedback.contains(SmtpProtocol.ACCEPTED+" ")) {
            System.out.println("Error at the beginning of the mail\n" +
                    "Quitting...\n");
            writer.println(SmtpProtocol.QUIT);
            writer.flush();
        }
        System.out.println(feedback);
    }

    /**
     * Sends a prank
     * @param prank a prank contains an email and a group of people
     * @throws IOException
     */
    public void send(Prank prank) throws IOException {

        Mail mail = prank.getMail();

        String feedback;

        /* MAIL FROM */
        do {
            writer.print(SmtpProtocol.FROM);
            writer.flush();
            writer.println(mail.getFrom().getAddress());
            writer.flush();

            feedback = reader.readLine();
            System.out.println(feedback);
        } while (!feedback.contains(String.valueOf(SmtpProtocol.ACCEPTED)));


        /* RCPT TO */
        List<Person> to = new ArrayList<>(mail.getTo().getGroup());

        for (int i = 0; i < to.size(); ++i) {
            writer.print(SmtpProtocol.TO);
            writer.flush();
            writer.println(to.get(i).getAddress());
            writer.flush();

            feedback = reader.readLine();
            System.out.println(feedback);

            if (!feedback.contains(String.valueOf(SmtpProtocol.ACCEPTED))) {
                System.out.print(to.get(i).getAddress() +" was not acceped");
            }
        }

        /* DATA */
        do {
            writer.println(SmtpProtocol.DATA);
            writer.flush();

            feedback = reader.readLine();
            System.out.println(feedback);

            writer.println("From: "+ mail.getFrom().getAddress());
            writer.flush();
            writer.println("To: " + mail.getTo().group());
            writer.println(mail.getSubject() + System.lineSeparator());
            writer.flush();
            writer.println(mail.getBody());
            writer.flush();

            writer.print(SmtpProtocol.END_OF_DATA);
            writer.flush();

            feedback = reader.readLine();
            System.out.println(feedback);
        } while (!feedback.contains(String.valueOf(SmtpProtocol.ACCEPTED)));

    }

    public void disconnect() throws IOException {
        /* Sanity check */
        if (socket == null || socket.isClosed()) {
            return;
        }

        /* QUIT */
        writer.println(SmtpProtocol.QUIT);
        writer.flush();
        String feedback = reader.readLine();
        System.out.println(feedback);

        reader.close();
        writer.close();
        socket.close();

        socket = null;
    }
}
