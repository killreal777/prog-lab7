package creators;

import io.Terminal;
import model.Coordinates;
import exceptions.FieldDefinitionException;

public class CoordinatesCreator extends AbstractCreator<Coordinates> {
    private enum CoordinatesArgument {
        X, Y
    }

    private CoordinatesArgument lastSetArgument;

    public CoordinatesCreator(Terminal terminal) {
        super(terminal);
        this.lastSetArgument = CoordinatesArgument.Y;
    }

    @Override
    protected Coordinates createNewInstance() {
        return new Coordinates();
    }

    @Override
    protected void defineFields() throws FieldDefinitionException {
        switch (lastSetArgument) {
        case Y:
            defineX();
        case X:
            defineY();
        }
        // break statement MUST NOT be here
    }

    private void defineX() throws RuntimeException {
        String[] input = terminal
                .readLineSplit("Введите координату X организации: " + formatRequirements("int, > -535"));
        checkArgumentsAmount(input, 1);
        try {
            creatingObject.setX(Integer.parseInt(input[0]));
        } catch (NumberFormatException e) {
            throw new FieldDefinitionException("Ожидалось целое число (int)");
        }
        this.lastSetArgument = CoordinatesArgument.X;
    }

    private void defineY() {
        String[] input = terminal
                .readLineSplit("Введите координату Y организации: " + formatRequirements("int, <= 630"));
        checkArgumentsAmount(input, 1);
        try {
            creatingObject.setY(Integer.parseInt(input[0]));
        } catch (NumberFormatException e) {
            throw new FieldDefinitionException("Ожидалось целое число (int)");
        }
        this.lastSetArgument = CoordinatesArgument.Y;
    }
}
