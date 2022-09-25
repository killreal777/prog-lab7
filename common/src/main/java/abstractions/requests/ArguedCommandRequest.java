package abstractions.requests;

public abstract class ArguedCommandRequest<ArgType> extends CommandRequest {
    protected ArgType commandArgument;

    public ArguedCommandRequest(String commandName) {
        super(commandName);
    }

    public ArgType getCommandArgument() {
        return commandArgument;
    }
}
