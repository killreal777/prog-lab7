package app;

import abstractions.command.Command;
import abstractions.requests.CommandRequest;

/**
 * Class for storing last 10 executed Commands
 */

public class ClientHistory {
    private final Object[] history;

    ClientHistory() {
        this.history = new Object[10];
    }

    public void addCommand(Command command) {
        shiftCommandsOnePositionToThePast();
        history[0] = command;
    }

    public void addRequest(CommandRequest request) {
        shiftCommandsOnePositionToThePast();
        history[0] = request;
    }

    private void shiftCommandsOnePositionToThePast() {
        for (int i = 9; i > 0; i--)
            history[i] = history[i - 1];
        history[0] = null;
    }

    public String toString() {
        if (history[0] == null)
            return "History is empty";
        else
            return historyCommandsToString();
    }

    private String historyCommandsToString() {
        String out = "";
        for (int index = 0; index < 10; index++) {
            if (history[index] == null)
                break;
            out = addLineFeed(out) + commandToString(index);
        }
        return out;
    }

    private String commandToString(int commandIndexInArray) { // for beautiful history output
        int stepIntoThePast = commandIndexInArray + 1; // 1 - the previous, 2 - the command before the previous...
        Object command = history[commandIndexInArray];
        String commandName = command.toString().split(" ")[0];
        return String.format("-%2d: %s", stepIntoThePast, commandName);
    }

    private String addLineFeed(String string) {
        if (!string.equals(""))
            string += "\n";
        return string;
    }
}
