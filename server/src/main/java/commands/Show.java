package commands;

import data.dao.Dao;
import model.Organization;

public class Show extends ServerCommand {

    public Show(Dao dao) {
        super(dao);
        this.name = "show";
    }

    @Override
    public void execute() {
        dao.getCollection().stream().sorted().map(Organization::toString).forEach(this::writeResult);
        if (result.equals(""))
            result = "Коллекция пуста";
    }
}
