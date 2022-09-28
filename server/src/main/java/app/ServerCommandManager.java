package app;

import abstractions.prototypes.PrototypesManager;
import commands.*;
import commands.ServerCommand;
import commands.Add;
import commands.AddIfMax;
import commands.RemoveByAddress;
import commands.Update;
import data.DaoProxy;
import data.dao.Dao;

public class ServerCommandManager extends PrototypesManager<ServerCommand> {
    private final Dao dao;

    public ServerCommandManager() {
        this.dao = new DaoProxy();
        definePrototypes();
    }

    @Override
    protected void definePrototypes() {
        addPrototype("registration", new Registration(dao));
        addPrototype("authorization", new Authorization(dao));

        // server creation commands
        addPrototype("add", new Add(dao));
        addPrototype("add_if_max", new AddIfMax(dao));
        addPrototype("update", new Update(dao));
        addPrototype("remove_any_by_official_address", new RemoveByAddress(dao));

        // server simple arged commands
        addPrototype("remove_by_id", new RemoveByID(dao));
        addPrototype("filter_starts_with_name", new FilterStartsWithName(dao));

        // server simple argless commands
        addPrototype("clear", new Clear(dao));
        addPrototype("show", new Show(dao));
        addPrototype("head", new Head(dao));
        addPrototype("print_ascending", new PrintAscending(dao));
        addPrototype("info", new Info(dao));
    }
}
