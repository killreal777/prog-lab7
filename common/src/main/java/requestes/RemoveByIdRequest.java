package requestes;

import abstractions.requests.ArguedCommandRequest;
import exceptions.ArgumentTypeException;

public class RemoveByIdRequest extends ArguedCommandRequest<Integer> {
    public RemoveByIdRequest() {
        super("remove_by_id");
    }

    @Override
    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 1);
        this.commandArgument = parseId(args[0]);
    }

    private Integer parseId(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new ArgumentTypeException(ArgumentTypeException.ArgumentType.LONG);
        }
    }
}
