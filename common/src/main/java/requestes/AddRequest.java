package requestes;

import abstractions.creator.Creator;
import abstractions.requests.CreationCommandRequest;
import model.Organization;

public class AddRequest extends CreationCommandRequest<Organization> {
    public AddRequest(Creator<Organization> creator) {
        super("add", creator);
    }

    @Override
    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 0);
        createArgument();
    }
}
