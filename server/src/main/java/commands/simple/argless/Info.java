package commands.simple.argless;

import commands.abstractions.ServerCommand;
import data.dao.Dao;

public class Info extends ServerCommand {
    public Info(Dao dao) {
        super(dao);
        this.name = "info";
    }

    @Override
    public void execute() {
        writeResult(String.format("Количество элементов: %d", dao.getCollection().size()));
    }
}
