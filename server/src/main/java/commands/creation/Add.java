package commands.creation;

import commands.abstractions.ArguedServerCommand;
import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import model.Organization;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class Add extends ArguedServerCommand<Organization> {
    public Add(DataManager dataManager) {
        super(dataManager);
        this.name = "add {element}";
    }

    @Override
    public void execute() {
        Organization organization = this.commandArgument;
        if (collectionContainsFullName(dataManager.getCollection(), organization.getFullName())) {
            setBadResult();
        } else {
            defineAutogenFields(organization);
            addOrganizationToTheCollection(organization);
            setGoodResult();
        }
    }

    protected void addOrganizationToTheCollection(Organization organization) {
        this.dataManager.getCollection().add(organization);
        this.dataManager.getCollectionInfo().incrementElementsAmount();
    }

    protected void defineAutogenFields(Organization organization) {
        organization.setId(dataManager.getIdGenerator().generateId());
        organization.setCreationDate(LocalDateTime.now());
    }

    private boolean collectionContainsFullName(PriorityQueue<Organization> collection, String fullName) {
        return collection.stream().map(Organization::getFullName).anyMatch(fullName::equals);
    }

    private void setGoodResult() {
        this.result = TextFormatter.format("Элемент успешно добавлен", Format.GREEN);
    }

    private void setBadResult() {
        this.result = TextFormatter.format("Полное имя организации неуникально", Format.RED);
    }
}
