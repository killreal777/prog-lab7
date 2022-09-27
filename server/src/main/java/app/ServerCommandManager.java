package app;

import abstractions.prototypes.PrototypesManager;
import commands.abstractions.ServerCommand;
import commands.creation.Add;
import commands.creation.AddIfMax;
import commands.creation.RemoveByAddress;
import commands.creation.Update;
import commands.simple.arged.FilterStartsWithName;
import commands.simple.arged.RemoveByID;
import commands.simple.argless.*;
import data.DaoProxy;
import data.dao.Dao;
import register.CommandRecord;
import register.CommandsChecker;

public class ServerCommandManager extends PrototypesManager<ServerCommand> {
    private final Dao dao;

    public ServerCommandManager() {
        this.dao = new DaoProxy();
        definePrototypes();
        CommandsChecker.check(CommandRecord.CommandType.SERVER, getPrototypesNameList(), "ServerCommandManager");
    }

    @Override
    protected void definePrototypes() {
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
