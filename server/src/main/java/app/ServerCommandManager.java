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
import data.management.DataManager;
import register.CommandRecord;
import register.CommandsChecker;

public class ServerCommandManager extends PrototypesManager<ServerCommand> {
    private final DataManager dataManager;
    private final Save saveCommand;

    public ServerCommandManager(DataManager dataManager) {
        this.dataManager = dataManager;
        this.saveCommand = new Save(dataManager);
        definePrototypes();
        CommandsChecker.check(CommandRecord.CommandType.SERVER, getPrototypesNameList(), "ServerCommandManager");
    }

    @Override
    protected void definePrototypes() {
        // server creation commands
        addPrototype("add", new Add(dataManager));
        addPrototype("add_if_max", new AddIfMax(dataManager));
        addPrototype("update", new Update(dataManager));
        addPrototype("remove_any_by_official_address", new RemoveByAddress(dataManager));

        // server simple arged commands
        addPrototype("remove_by_id", new RemoveByID(dataManager));
        addPrototype("filter_starts_with_name", new FilterStartsWithName(dataManager));

        // server simple argless commands
        addPrototype("clear", new Clear(dataManager));
        addPrototype("show", new Show(dataManager));
        addPrototype("head", new Head(dataManager));
        addPrototype("print_ascending", new PrintAscending(dataManager));
        addPrototype("info", new Info(dataManager));
        // addPrototype("save", new Save(dataManager));
    }

    public Save getSaveCommand() {
        return saveCommand;
    }
}
