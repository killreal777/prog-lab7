package commands.simple.argless;

import commands.abstractions.ServerCommand;
import data.management.DataManager;
import model.Organization;

import java.util.function.Consumer;


public class Head extends ServerCommand {
    public Head(DataManager dataManager) {
        super(dataManager);
        this.name = "head";
    }

    @Override
    public void execute() {
        Consumer<String> writeResultLine = (line) -> result = result + line;
        dataManager.getCollection().stream().findFirst().map(Organization::toString).ifPresent(writeResultLine);
        if (result.equals(""))
            result = "Коллекция пуста";
    }
}
