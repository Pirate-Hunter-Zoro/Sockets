import java.io.IOException;

public class Main {

    static int port = -1;

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e){} // Do nothing - port will become default port in later constructors
        }

        spawnServerAndClient();
    }

    private static void spawnServerAndClient() throws IOException, InterruptedException {
        Talker.serverListens = (Math.random() > 0.5);
        System.out.println((Talker.serverListens) ? "SERVER WILL LISTEN TO USER AND SEND TO CLIENT\n\n"
                : "CLIENT WILL LISTEN TO USER AND SEND TO SERVER\n\n");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server server = new Server(Main.port);
                    server.syncIO();
                    server.close();
                } catch (IOException e) {
                } // Eh... I'm not gonna worry about it
            }
        }).start();
        Thread.sleep(100); // To give the server enough time to claim the port

        Client client = new Client(Main.port);
        client.syncIO();
        client.close();
        Thread.sleep(1000); // Give the user some time to register
        System.out.println("Let's go again!\n\n\n\n");

        spawnServerAndClient();
    }

}
