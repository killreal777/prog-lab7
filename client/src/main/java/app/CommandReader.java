package app;

import exceptions.CommandNotFoundException;
import io.Terminal;

import java.util.Arrays;

public class CommandReader {
    private final Terminal terminal;

    public CommandReader(Terminal terminal) {
        this.terminal = terminal;
    }

    public UserInput readCommand() throws CommandNotFoundException {
        String[] inputLine = readNextLine();
        String name = findCommandNameInInputLine(inputLine);
        String[] args = findArgsInInputLine(inputLine);
        return new UserInput(name, args);
    }

    private String findCommandNameInInputLine(String[] inputLine) {
        return inputLine[0];
    }

    private String[] findArgsInInputLine(String[] inputLine) {
        return Arrays.copyOfRange(inputLine, 1, inputLine.length);
    }

    private String[] readNextLine() {
        String[] inputLine = terminal.readLineSplit();
        if (isInputLineEmpty(inputLine))
            return readNextLine(); // empty input -> read again
        else
            return inputLine;
    }

    private boolean isInputLineEmpty(String[] inputLine) {
        return inputLine[0].equals("");
    }

    class UserInput {
        private final String commandName;
        private final String[] commandArgs;

        UserInput(String commandName, String[] commandArgs) {
            this.commandName = commandName;
            this.commandArgs = commandArgs;
        }

        public String getCommandName() {
            return commandName;
        }

        public String[] getCommandArgs() {
            return commandArgs;
        }
    }
}
