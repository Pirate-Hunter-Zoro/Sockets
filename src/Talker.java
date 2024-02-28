import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Talker implements Closeable {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 12987;

    public static boolean serverInitializerListens = true;

    protected int port;
    protected String hostName;
    protected Socket listenTo;
    protected BufferedReader sockin;
    protected PrintWriter sockout;
    protected BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    protected String messageTag = "";

    Talker() {
        this.port = DEFAULT_PORT;
        this.hostName = DEFAULT_HOST;
    }

    Talker(int port) {
        this.port = (port > 0) ? port : DEFAULT_PORT;
        this.hostName = DEFAULT_HOST;
    }

    Talker(String hostName) {
        this.port = DEFAULT_PORT;
        this.hostName = (hostName != null) ? hostName : DEFAULT_HOST;
    }

    Talker(int port, String hostName) {
        this.port = (port > 0) ? port : DEFAULT_PORT;
        this.hostName = (hostName != null) ? hostName : DEFAULT_HOST;
    }

    protected void setUpCommunication() throws IOException {
        sockin = new BufferedReader(new InputStreamReader(this.listenTo.getInputStream()));
        sockout = new PrintWriter(listenTo.getOutputStream(), true);
    }

    protected abstract void setUpSocket();

    protected abstract boolean shouldListenToUser();

    void syncIO() throws IOException {
        if (shouldListenToUser()) {
            System.out.println("Type the message you want to send:");
            sockout.println(stdin.readLine());
        } else
            System.out.println(messageTag + sockin.readLine());
    }

    void asyncIO() throws IOException {
        if (shouldListenToUser()) {
            if (stdin.ready())
                sockout.println(stdin.readLine());
        } else {
            if (sockin.ready())
                System.out.println(messageTag + sockin.readLine());
        }
    }

    @Override
    public void close() throws IOException {
        listenTo.close();
    }

}
