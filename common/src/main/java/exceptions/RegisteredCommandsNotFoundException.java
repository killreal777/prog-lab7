package exceptions;

public class RegisteredCommandsNotFoundException extends MessagedRuntimeException {
    public RegisteredCommandsNotFoundException(String commandNames, String structureName) {
        super(String.format("Registered commands %s are missed in structure \"%s\"", commandNames, structureName));
    }
}
