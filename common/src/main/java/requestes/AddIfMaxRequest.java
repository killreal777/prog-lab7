package requestes;

import abstractions.creator.Creator;
import model.Organization;

public class AddIfMaxRequest extends AddRequest {
    public AddIfMaxRequest(Creator<Organization> creator) {
        super(creator);
        this.commandName = "add_if_max";
        // commandName field is the only difference between AddRequest and AddIfMaxRequest
    }
}
