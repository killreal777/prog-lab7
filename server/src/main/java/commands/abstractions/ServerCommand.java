package commands.abstractions;

import abstractions.command.Command;
import data.dao.Dao;

public abstract class ServerCommand extends Command {
    protected Dao dao;

    public ServerCommand(Dao dao) {
        this.dao = dao;
    }
}
