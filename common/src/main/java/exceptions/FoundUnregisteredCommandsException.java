package exceptions;

public class FoundUnregisteredCommandsException extends MessagedRuntimeException {
    public FoundUnregisteredCommandsException(String commandNames, String structureName) {
        super(String.format("Commands %s in structure %s are not registered in CommandRecord enum", commandNames,
                structureName));
    }
}
