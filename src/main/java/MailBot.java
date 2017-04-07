import java.io.IOException;

/**
 * Class MailBot
 *
 * This is the main class.
 * It creates a PrankManager and sends Pranks
 * User can chooses to send others Pranks or stop the program
 */
public class MailBot {

    public static void main (String[] args) throws IOException {

        PrankManager manager = new PrankManager();

        try {
            manager.start();

            manager.sendPrank();

            manager.stop();

        } catch (IOException e) {
            System.err.println("Error in mail program");
        } finally {
            manager.close();
        }

    }
}
