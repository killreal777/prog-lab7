package commands.simple.arged;

import commands.abstractions.ArguedServerCommand;
import data.dao.Dao;
import model.Organization;

import java.util.function.Predicate;

public class RemoveByID extends ArguedServerCommand<Integer> {
    public RemoveByID(Dao dao) {
        super(dao);
        this.name = "remove_by_id id";
    }

    @Override
    public void execute() {
        Predicate<Organization> matchId = (org) -> org.getId().equals(this.commandArgument);
        dao.getCollection().stream().filter(matchId).findFirst()
                .ifPresentOrElse(this::removeOrganizationFromDataCollection, this::setBadResult);
    }

    private void removeOrganizationFromDataCollection(Organization organization) {
        dao.removeById(organization.getId());
        setGoodResult(String.format("Удалена оганизация \"%s\"", organization.getName()));
    }

    private void setBadResult() {
        setBadResult("В коллекции нет подходящего элемента");
    }
}
