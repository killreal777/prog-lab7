package commands.creation;

import commands.abstractions.ArguedServerCommand;
import data.dao.Dao;
import model.Organization;

import java.util.PriorityQueue;

public class Add extends ArguedServerCommand<Organization> {
    public Add(Dao dao) {
        super(dao);
        this.name = "add {element}";
    }

    @Override
    public void execute() {
        Organization organization = this.commandArgument;
        if (collectionContainsFullName(dao.getCollection(), organization.getFullName())) {
            setBadResult("Полное имя организации неуникально");
        } else {
            dao.add(organization);
            setGoodResult("Элемент успешно добавлен");
        }
    }

    private boolean collectionContainsFullName(PriorityQueue<Organization> collection, String fullName) {
        return collection.stream().map(Organization::getFullName).anyMatch(fullName::equals);
    }
}
