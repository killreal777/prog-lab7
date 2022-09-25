package requestes.simple.argless;

import abstractions.requests.CommandRequest;

public class ArglessCommandRequest extends CommandRequest {

    public ArglessCommandRequest(String commandName) {
        super(commandName);
    }

    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 0);
    }
}
