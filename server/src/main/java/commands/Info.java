package commands;

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
