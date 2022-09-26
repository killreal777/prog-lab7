package app;

import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import io.UnixTerminal;
import server.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        final UnixTerminal terminal = new UnixTerminal();
        final DataManager dataManager = new DataManager(terminal);
        final ServerExecutionManager serverExecutionManager = new ServerExecutionManager(terminal, dataManager);
        final ServerTerminalInputManager terminalInputManager = new ServerTerminalInputManager(terminal,
                serverExecutionManager::executeSaveCommand);
        final Server server = new Server(serverExecutionManager::executeCommand,
                terminalInputManager::checkTerminalRequest);
        terminal.setPreExitCall(serverExecutionManager::executeSaveCommand);
        terminal.print(TextFormatter.format("Доступные команды: save, exit", Format.YELLOW));
        server.run();
    }
}
