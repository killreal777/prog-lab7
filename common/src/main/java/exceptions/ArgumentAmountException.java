package exceptions;

public class ArgumentAmountException extends MessagedRuntimeException {
    public ArgumentAmountException(int inputted, int expected) {
        super(String.format("Неверное число аргументов (введено: %d, ожидалось: %d)", inputted, expected));
    }
}
