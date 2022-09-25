package commands.simple.argless;

import commands.abstractions.ServerCommand;
import data.management.DataManager;
import model.Organization;

import java.util.function.Consumer;


public class PrintAscending extends ServerCommand {
    public PrintAscending(DataManager dataManager) {
        super(dataManager);
        this.name = "print_ascending";
    }

    @Override
    public void execute() {
        dataManager.getCollection().stream().sorted().map(Organization::toString).forEach(this::writeResult);
        if (result.equals(""))
            result = "Коллекция пуста";
    }
}
