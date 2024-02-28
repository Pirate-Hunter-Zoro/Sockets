import java.io.IOException;
import java.net.Socket;

public class ClientInitializer extends Talker {

    @Override
    protected boolean shouldListenToUser() {
        return !Talker.serverInitializerListens;
    }

    ClientInitializer() {
        super();
        setUpSocket();
    }

    ClientInitializer(int port) {
        super(port);
        setUpSocket();
    }

    ClientInitializer(String hostName) {
        super(hostName);
        setUpSocket();
    }

    ClientInitializer(int port, String hostName) {
        super(port, hostName);
        setUpSocket();
    }

    @Override
    protected void setUpSocket() {
        try {
            System.out.println("Client attempting to connect on host \"" + this.hostName + "\" and port " + this.port);
            listenTo = new Socket(this.hostName, this.port); // Throws an Exception if no server has claimed this port
            messageTag = "Client just heard:\n";
            setUpCommunication();
            System.out.println("Client was accepted!\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
