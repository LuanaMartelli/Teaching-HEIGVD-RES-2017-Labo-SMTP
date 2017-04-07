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

        /* EHLO */
        writer.println(SmtpProtocol.HELLO + " you");
        writer.flush();

        /* The server will send an unknow number of data with this prefix */
        while ((feedback = reader.readLine()).contains(SmtpProtocol.ACCEPTED+"-"));

        /* At this point, we know we reached end of data, we can start writing an email */
        if (!feedback.contains(SmtpProtocol.ACCEPTED+" ")) {
            System.out.println("Error at the beginning of the mail\n" +
                    "Quitting...\n");
            writer.println(SmtpProtocol.QUIT);
            writer.flush();
        }
    }

    /**
     * Sends a prank
     * @param prank a prank contains an email and a group of people
     * @throws IOException
     */
    public void send(Prank prank) throws IOException {

        Mail[] mails = prank.getMails();


        for (int i = 0; i < mails.length; i++) {
            String feedback;

            /* MAIL FROM */
            writer.print(SmtpProtocol.FROM);
            writer.flush();
            writer.println(mails[i].getFrom().getAddress());
            writer.flush();

            System.out.println("FROM: " + mails[i].getFrom().getAddress());

            reader.readLine();


            /* RCPT TO */
            List<Person> to = new ArrayList<>(mails[i].getTo().getGroup());

            for (int j = 0; j < to.size(); ++j) {
                writer.print(SmtpProtocol.TO);
                writer.flush();
                writer.println(to.get(j).getAddress());
                writer.flush();

                feedback = reader.readLine();

                if (!feedback.contains(String.valueOf(SmtpProtocol.ACCEPTED))) {
                    System.out.print(to.get(j).getAddress() +" was not acceped");
                }
            }

            System.out.println("TO: " + mails[i].getTo().group());

            /* DATA */
            writer.println(SmtpProtocol.DATA);
            writer.flush();

            reader.readLine();

            writer.println("From: "+ mails[i].getFrom().getAddress());
            writer.flush();
            writer.println("To: " + mails[i].getTo().group());
            writer.println(mails[i].getSubject() + System.lineSeparator());
            writer.flush();
            writer.println(mails[i].getBody());
            writer.flush();

            writer.print(SmtpProtocol.END_OF_DATA);
            writer.flush();

            reader.readLine();
        }


    }

    public void quit() throws IOException {
        /* Sanity check */
        if (socket == null || socket.isClosed()) {
            return;
        }

        /* QUIT */
        writer.println(SmtpProtocol.QUIT);
        writer.flush();
        reader.readLine();
    }

    public void close() throws IOException {
        /* Sanity check */
        if (socket == null || socket.isClosed()) {
            return;
        }

        /* CLOSE */
        reader.close();
        writer.close();
        socket.close();

        socket = null;
        reader = null;
        writer = null;
    }
}
