package exceptions;

public class InvalidRecordException extends MessagedRuntimeException {
    public InvalidRecordException() {
        super("Invalid record found: command name or/and command help is/are missing");
    }
}
