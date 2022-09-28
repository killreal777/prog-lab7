package app;

import abstractions.prototypes.PrototypesManager;
import abstractions.requests.CommandRequest;
import creators.AddressCreator;
import creators.OrganizationCreator;
import io.Terminal;
import register.CommandRecord;
import register.CommandsChecker;
import requestes.AddIfMaxRequest;
import requestes.AddRequest;
import requestes.RemoveByAddressRequest;
import requestes.UpdateRequest;
import requestes.FilterStartsWithNameRequest;
import requestes.RemoveByIdRequest;
import requestes.ArglessCommandRequest;

public class RequestsManager extends PrototypesManager<CommandRequest> {
    private final Terminal terminal;
    // private final CommandHistory history;

    public RequestsManager(Terminal terminal) {
        this.terminal = terminal;
        definePrototypes();
        CommandsChecker.check(CommandRecord.CommandType.SERVER, getPrototypesNameList(), "RequestsManager");
        CommandsChecker.check(CommandRecord.CommandType.SERVER, getPrototypesNameList(), "RequestsManager");
    }

    @Override
    protected void definePrototypes() {
        // server creation command requests
        addPrototype("add", new AddRequest(new OrganizationCreator(terminal)));
        addPrototype("add_if_max", new AddIfMaxRequest(new OrganizationCreator(terminal)));
        addPrototype("update", new UpdateRequest(new OrganizationCreator(terminal)));
        addPrototype("remove_any_by_official_address", new RemoveByAddressRequest(new AddressCreator(terminal)));

        // server simple arged command requests
        addPrototype("remove_by_id", new RemoveByIdRequest());
        addPrototype("filter_starts_with_name", new FilterStartsWithNameRequest());

        // server simple argless command requests
        addPrototype("info", new ArglessCommandRequest("info"));
        addPrototype("clear", new ArglessCommandRequest("clear"));
        addPrototype("show", new ArglessCommandRequest("show"));
        addPrototype("head", new ArglessCommandRequest("head"));
        addPrototype("print_ascending", new ArglessCommandRequest("print_ascending"));
    }
}
