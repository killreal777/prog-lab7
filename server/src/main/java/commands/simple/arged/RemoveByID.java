package commands.simple.arged;

import commands.abstractions.ArguedServerCommand;
import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import model.Organization;

import java.util.function.Predicate;


public class RemoveByID extends ArguedServerCommand<Integer> {
    public RemoveByID(DataManager dataManager) {
        super(dataManager);
        this.name = "remove_by_id id";
    }


    @Override
    public void execute() {
        Predicate<Organization> matchId = (org) -> org.getId().equals(this.commandArgument);
        dataManager.getCollection().stream().filter(matchId).findFirst().
                ifPresentOrElse(this::removeOrganizationFromDataCollection, this::setBadResult);
    }


    private void removeOrganizationFromDataCollection(Organization organization) {
        dataManager.getCollection().remove(organization);
        dataManager.getIdGenerator().setToRemoved(organization.getId());
        dataManager.getCollectionInfo().decrementElementsAmount();
        setGoodResult(organization.getName());
    }

    private void setGoodResult(String removedOrganizationName) {
        result = String.format("Удалена оганизация \"%s\"", removedOrganizationName);
        result = TextFormatter.format(result, Format.GREEN);
    }

    private void setBadResult() {
        result = "В коллекции нет подходящего элемента";
        result = TextFormatter.format(result, Format.RED);
    }
}
