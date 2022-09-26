package app;

import io.Terminal;

import java.io.IOException;
import java.util.function.Supplier;

public class ServerTerminalInputManager {
    private final Terminal terminal;
    private final Supplier<String> executeSaveCommandMethodLink;

    public ServerTerminalInputManager(Terminal terminal, Supplier<String> executeSaveCommandMethodLink) {
        this.terminal = terminal;
        this.executeSaveCommandMethodLink = executeSaveCommandMethodLink;
    }

    public void checkTerminalRequest() {
        if (isRequestFromTerminal())
            checkSaveCommand();
    }

    private boolean isRequestFromTerminal() {
        try {
            return System.in.available() > 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkSaveCommand() {
        String input = terminal.readLineEntire("");
        if (input.equals("save") || input.equals("exit"))
            terminal.print(executeSaveCommandMethodLink.get());
        if (input.equals("exit"))
            System.exit(0);
    }
}
