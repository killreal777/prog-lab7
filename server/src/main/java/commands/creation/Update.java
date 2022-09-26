package commands.creation;

import commands.abstractions.ArguedServerCommand;
import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import model.Organization;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class Update extends ArguedServerCommand<Organization> {
    public Update(DataManager dataManager) {
        super(dataManager);
        this.name = "update id {element}";
    }

    @Override
    public void execute() {
        Organization organization = this.commandArgument;
        Predicate<Organization> sameId = (org) -> org.getId().equals(organization.getId());
        dataManager.getCollection().stream().filter(sameId).findAny().ifPresentOrElse(this::update,
                this::setBadResultIdNotUnique);
    }

    private void update(Organization oldOrganization) {
        Organization newOrganization = this.commandArgument;
        if (collectionContainsFullName(dataManager.getCollection(), oldOrganization)) {
            setBadResultFullNameNotUnique();
        } else {
            updateInCollection(oldOrganization, newOrganization);
            setGoodResult(oldOrganization.getName());
        }
    }

    private boolean collectionContainsFullName(PriorityQueue<Organization> collection, Organization oldOrganization) {
        String newName = this.commandArgument.getFullName();
        String oldName = oldOrganization.getFullName();
        if (newName.equals(oldName))
            return false;
        else
            return collection.stream().map(Organization::getFullName).anyMatch(newName::equals);
    }

    private void updateInCollection(Organization oldOrganization, Organization newOrganization) {
        dataManager.getCollection().remove(oldOrganization);
        defineAutogenFields(oldOrganization, newOrganization);
        dataManager.getCollection().add(newOrganization);
    }

    private void defineAutogenFields(Organization oldOrganization, Organization newOrganization) {
        newOrganization.setId(oldOrganization.getId());
        newOrganization.setCreationDate(LocalDateTime.now());
    }

    private void setGoodResult(String oldOrganizationName) {
        String message = String.format("Обновлена оганизация \"%s\"", oldOrganizationName);
        result = TextFormatter.format(message, Format.GREEN);
    }

    private void setBadResultIdNotUnique() {
        String message = "В коллекции нет элемента с указанным id";
        result = TextFormatter.format(message, Format.RED);
    }

    private void setBadResultFullNameNotUnique() {
        String message = "Полное имя организации неуникально";
        result = TextFormatter.format(message, Format.RED);
    }
}
