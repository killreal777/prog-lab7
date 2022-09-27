package app;

import server.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        final ServerExecutionManager serverExecutionManager = new ServerExecutionManager();
        final Server server = new Server(serverExecutionManager::executeCommand);
        server.run();
    }
}
