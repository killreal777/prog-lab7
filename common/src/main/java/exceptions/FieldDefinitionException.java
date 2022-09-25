package exceptions;

public class FieldDefinitionException extends RuntimeException {
    private final String message;

    public FieldDefinitionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\033[0;91m" + message + "\033[0m"; // red
    }
}
