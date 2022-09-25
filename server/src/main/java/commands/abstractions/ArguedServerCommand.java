package commands.abstractions;

import abstractions.requests.ArguedCommandRequest;
import data.management.DataManager;

public abstract class ArguedServerCommand<ArgType> extends ServerCommand {
    protected ArgType commandArgument;

    public ArguedServerCommand(DataManager dataManager) {
        super(dataManager);
    }

    public void extractArgumentFromRequest(ArguedCommandRequest<ArgType> request) {
        this.commandArgument = request.getCommandArgument();
    }
}
