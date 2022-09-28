package commands;

import abstractions.requests.ArguedCommandRequest;
import data.dao.Dao;

public abstract class ArguedServerCommand<ArgType> extends ServerCommand {
    protected ArgType commandArgument;

    public ArguedServerCommand(Dao dao) {
        super(dao);
    }

    public void extractArgumentFromRequest(ArguedCommandRequest<ArgType> request) {
        this.commandArgument = request.getCommandArgument();
    }
}
