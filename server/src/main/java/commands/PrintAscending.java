package commands;

import data.dao.Dao;
import model.Organization;

public class PrintAscending extends ServerCommand {
    public PrintAscending(Dao dao) {
        super(dao);
        this.name = "print_ascending";
    }

    @Override
    public void execute() {
        dao.getCollection().stream().sorted().map(Organization::toString).forEach(this::writeResult);
        if (result.equals(""))
            result = "Коллекция пуста";
    }
}
