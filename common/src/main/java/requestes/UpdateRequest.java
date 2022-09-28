package requestes;

import abstractions.creator.Creator;
import exceptions.ArgumentTypeException;
import abstractions.requests.CreationCommandRequest;
import model.Organization;

public class UpdateRequest extends CreationCommandRequest<Organization> {
    public UpdateRequest(Creator<Organization> creator) {
        super("update", creator);
    }

    @Override
    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 1);
        Integer id = parseId(args[0]);
        createArgument();
        this.commandArgument.setId(id);
    }

    private Integer parseId(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new ArgumentTypeException(ArgumentTypeException.ArgumentType.LONG);
        }
    }
}
