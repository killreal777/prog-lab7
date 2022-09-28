package requestes;

import abstractions.requests.ArguedCommandRequest;

public class RegistrationRequest extends ArguedCommandRequest<String> {
    public RegistrationRequest() {
        super("registration");
    }

    @Override
    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 1);
        this.commandArgument = args[0];
    }
}
