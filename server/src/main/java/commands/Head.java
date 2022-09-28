package commands;

import data.dao.Dao;
import model.Organization;

public class Head extends ServerCommand {
    public Head(Dao dao) {
        super(dao);
        this.name = "head";
    }

    @Override
    public void execute() {
        dao.getCollection().stream().findFirst().map(Organization::toString).ifPresent(this::writeResult);
        if (result.equals(""))
            result = "Коллекция пуста";
    }
}
