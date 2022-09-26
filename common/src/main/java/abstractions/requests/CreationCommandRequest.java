package abstractions.requests;

import abstractions.creator.Creator;

public abstract class CreationCommandRequest<ArgType> extends ArguedCommandRequest<ArgType> {
    transient protected final Creator<ArgType> creator;

    public CreationCommandRequest(String commandName, Creator<ArgType> creator) {
        super(commandName);
        this.creator = creator;
    }

    protected void createArgument() {
        this.commandArgument = creator.create();
    }
}
