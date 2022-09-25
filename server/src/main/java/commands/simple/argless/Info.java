package commands.simple.argless;

import commands.abstractions.ServerCommand;
import data.management.DataManager;

public class Info extends ServerCommand {
    public Info(DataManager dataManager) {
        super(dataManager);
        this.name = "info";
    }

    @Override
    public void execute() {
        this.result = dataManager.getCollectionInfo().toString();
    }
}
