package commands.creation;

import commands.abstractions.ArguedServerCommand;
import data.dao.Dao;
import model.Organization;

import java.util.PriorityQueue;
import java.util.function.Predicate;

public class Update extends ArguedServerCommand<Organization> {
    private Integer id;
    private Organization organization;

    public Update(Dao dao) {
        super(dao);
        this.name = "update id {element}";
    }

    @Override
    public void execute() {
        organization = this.commandArgument;
        id = organization.getId();
        Predicate<Organization> sameId = (organization) -> organization.getId().equals(id);
        dao.getCollection().stream().filter(sameId).findAny()
                .ifPresentOrElse(this::update, this::setResultNoMatchingId);
    }

    private void update(Organization oldOrganization) {
        if (collectionContainsFullName(dao.getCollection(), oldOrganization)) {
            setBadResult("Полное имя организации неуникально");
        } else {
            dao.updateById(id, organization);
            setGoodResult(String.format("Обновлена оганизация \"%s\"", oldOrganization.getName()));
        }
    }

    private boolean collectionContainsFullName(PriorityQueue<Organization> collection, Organization oldOrganization) {
        if (organization.getFullName().equals(oldOrganization.getFullName()))
            return false;
        else
            return collection.stream().map(Organization::getFullName).anyMatch(organization.getFullName()::equals);
    }

    private void setResultNoMatchingId() {
        setBadResult("В коллекции нет элемента с указанным id");
    }
}
