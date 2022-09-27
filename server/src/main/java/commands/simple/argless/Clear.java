package commands.simple.argless;

import commands.abstractions.ServerCommand;
import data.dao.Dao;
import io.Format;
import io.TextFormatter;

public class Clear extends ServerCommand {
    public Clear(Dao dao) {
        super(dao);
        this.name = "clear";
    }

    @Override
    public void execute() {
        dao.getCollection().clear();
        result = TextFormatter.format("Коллекция очищена", Format.GREEN);
    }
}
