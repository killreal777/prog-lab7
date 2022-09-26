package commands.creation;

import commands.abstractions.ArguedServerCommand;
import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import model.Address;
import model.Organization;

import java.util.function.Predicate;

public class RemoveByAddress extends ArguedServerCommand<Address> {
    public RemoveByAddress(DataManager dataManager) {
        super(dataManager);
        this.name = "remove_any_by_official_address {officialAddress}";
    }

    @Override
    public void execute() {
        Predicate<Organization> matchAddress = (org) -> org.getOfficialAddress().equals(this.commandArgument);
        dataManager.getCollection().stream().filter(matchAddress).findFirst()
                .ifPresentOrElse(this::removeOrganizationFromDataCollection, this::setBadResult);
    }

    private void removeOrganizationFromDataCollection(Organization organization) {
        dataManager.getCollection().remove(organization);
        dataManager.getIdGenerator().setToRemoved(organization.getId());
        dataManager.getCollectionInfo().decrementElementsAmount();
        segGoodResult(organization.getName());
    }

    private void segGoodResult(String removedOrganizationName) {
        result = String.format("Удалена оганизация \"%s\"", removedOrganizationName);
        result = TextFormatter.format(result, Format.GREEN);
    }

    private void setBadResult() {
        result = "В коллекции нет подходящего элемента";
        result = TextFormatter.format(result, Format.RED);
    }
}
