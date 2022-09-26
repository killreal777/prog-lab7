package commands.simple.argless;

import commands.abstractions.ServerCommand;
import data.management.DataManager;
import model.Organization;

import java.util.function.Consumer;

public class Show extends ServerCommand {

    public Show(DataManager dataManager) {
        super(dataManager);
        this.name = "show";
    }

    @Override
    public void execute() {
        dataManager.getCollection().stream().sorted().map(Organization::toString).forEach(this::writeResult);
        if (result.equals(""))
            result = "Коллекция пуста";
    }
}
