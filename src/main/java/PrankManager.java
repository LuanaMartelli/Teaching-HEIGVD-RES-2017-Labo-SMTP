import java.io.IOException;

/**
 * Class PrankManager
 *
 * This class creates a client and a prank
 * It then sends the prank through the client
 * It manages all the beginning and the end of the connexion
 */
public class PrankManager {

    private Prank prank;
    private SmtpClient client;

    public PrankManager() throws IOException{
        client = new SmtpClient();
    }

    public void sendPrank() throws IOException{
        prank = new Prank();
        client.send(prank);
    }

    public void start() throws IOException {
        client.connect();
    }

    public void stop() throws IOException {
        client.disconnect();
    }

}
