package requestes;

import abstractions.requests.ArguedCommandRequest;

public class AuthorizationRequest extends ArguedCommandRequest<String> {
    public AuthorizationRequest() {
        super("authorization");
    }

    @Override
    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 1);
        this.commandArgument = args[0];
    }
}
