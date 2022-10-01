package commands;

import data.dao.Dao;
import model.Organization;

import java.util.Collection;
import java.util.PriorityQueue;

public class Add extends ArguedServerCommand<Organization> {
    public Add(Dao dao) {
        super(dao);
        this.name = "add {element}";
    }

    @Override
    public void execute() {
        Organization organization = this.commandArgument;
        if (!isUserExists()) {
            setBadResult("Пользователь не зарегестрирован");
        } else if (collectionContainsFullName(dao.getCollection(), organization.getFullName())) {
            setBadResult("Полное имя организации неуникально");
        } else {
            organization.setOwnerLogin(userName);
            dao.add(organization);
            setGoodResult("Элемент успешно добавлен");
        }
    }

    private boolean collectionContainsFullName(Collection<Organization> collection, String fullName) {
        return collection.stream().map(Organization::getFullName).anyMatch(fullName::equals);
    }
}
