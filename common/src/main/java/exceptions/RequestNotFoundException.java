package exceptions;

public class RequestNotFoundException extends MessagedRuntimeException {
    public RequestNotFoundException(String commandName) {
        super(String.format("Команда \"%s\" не найдена", commandName));
    }
}
