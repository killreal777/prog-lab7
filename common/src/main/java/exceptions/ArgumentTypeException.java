package exceptions;

public class ArgumentTypeException extends MessagedRuntimeException {
    public ArgumentTypeException(ArgumentType expectedArgumentType) {
        super(String.format("Неверный тип аргумента (ожидалось: %s)", expectedArgumentType.getDescription()));
    }

    public enum ArgumentType {
        LONG("целое число типа Long");

        private final String description;

        ArgumentType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

}
