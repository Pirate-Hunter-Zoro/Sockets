import java.io.IOException;

public class Main {

    static int port = -1;

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
            } // Do nothing - port will become default port in later constructors
        }

        spawnServerAndClient();
    }

    private static void spawnServerAndClient() throws IOException, InterruptedException {
        Talker.serverInitializerListens = (Math.random() > 0.5);
        System.out.println((Talker.serverInitializerListens) ? "SERVER-INITIALIZER WILL LISTEN TO USER AND SEND TO THE CLIENT-INITIALIZER\n\n"
                : "CLIENT-INITIALIZER WILL LISTEN TO USER AND SEND TO THE SERVER-INITIALIZER\n\n");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerInitializer server = new ServerInitializer(Main.port);
                    server.syncIO();
                    server.close();
                } catch (IOException e) {
                } // Eh... I'm not gonna worry about it
            }
        }).start();
        Thread.sleep(100); // To give the server enough time to claim the port

        ClientInitializer client = new ClientInitializer(Main.port);
        client.syncIO();
        client.close();
        Thread.sleep(1000); // Give the user some time to register
        System.out.println("Let's go again!\n\n\n\n");

        spawnServerAndClient();
    }

}
