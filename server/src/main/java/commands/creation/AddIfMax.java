package commands.creation;

import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import model.Organization;


public class AddIfMax extends Add {
    public AddIfMax(DataManager dataManager) {
        super(dataManager);
        this.name = "add_if_max {element}";
    }


    @Override
    public void execute() {
        Organization organization = this.commandArgument;
        if (!isOrganizationMax(organization))
            setBadResult();
        else
            super.execute();
    }

    private boolean isOrganizationMax(Organization newOrganization) {
        return dataManager.getCollection().stream().map(newOrganization::compareTo).allMatch((a) -> a > 0);
    }


    private void setBadResult() {
        this.result = "Значение элемента не превышает значение наибольщего элемента в коллекции";
        this.result = TextFormatter.format(result, Format.RED);
    }
}
