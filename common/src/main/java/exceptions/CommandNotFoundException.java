package exceptions;

public class CommandNotFoundException extends MessagedRuntimeException {
    public CommandNotFoundException(String commandName) {
        super(String.format("Команда \"%s\" не найдена", commandName));
    }
}
