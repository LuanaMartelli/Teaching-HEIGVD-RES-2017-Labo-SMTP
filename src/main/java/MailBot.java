import java.io.IOException;
import java.util.Scanner;

/**
 * Class MailBot
 *
 * This is the main class.
 * It creates a PrankManager and sends Pranks
 * User can chooses to send others Pranks or stop the program
 */
public class MailBot {

    public static void main (String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        boolean send = true;

        PrankManager manager = new PrankManager();

        manager.start();

        while (send) {
            manager.sendPrank();
            System.out.print("Continue ? ");
            send = scanner.nextBoolean();
        }

        manager.stop();
    }
}
