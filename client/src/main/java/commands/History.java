package commands;

import abstractions.command.Command;
import app.ClientHistory;

public class History extends Command {
    private final ClientHistory history;

    public History(ClientHistory history) {
        this.name = "history";
        this.history = history;
    }

    @Override
    public void execute() {
        this.result = history.toString();
    }
}
