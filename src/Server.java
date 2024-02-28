import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Talker {

    private ServerSocket serverSocket;

    @Override
    protected boolean shouldListenToUser() {
        return Talker.serverListens;
    }

    Server() {
        super();
        setUpSocket();
    }

    Server(int port) {
        super(port);
        setUpSocket();
    }

    Server(String hostName) {
        super(hostName);
        setUpSocket();
    }

    Server(int port, String hostName) {
        super(port, hostName);
        setUpSocket();
    }

    @Override
    protected void setUpSocket() {
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server is ready to accept a client on port " + this.port);
            listenTo = serverSocket.accept(); // Blocked until a client socket on this port is initialized
            messageTag = "Server just heard:\n";
            setUpCommunication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        serverSocket.close();
    }

}
