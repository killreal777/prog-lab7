package commands;

import abstractions.command.Command;

public class Exit extends Command {
    public Exit() {
        this.name = "exit";
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
