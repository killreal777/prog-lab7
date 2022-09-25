package commands.abstractions;

import abstractions.command.Command;
import data.management.DataManager;

/**
 * Superclass for all Commands, which deal with DataManager
 */

public abstract class ServerCommand extends Command {
    protected DataManager dataManager;

    public ServerCommand(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
