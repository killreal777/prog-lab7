package commands;

import data.dao.Dao;
import model.Organization;

public class AddIfMax extends Add {
    public AddIfMax(Dao dao) {
        super(dao);
        this.name = "add_if_max {element}";
    }

    @Override
    public void execute() {
        Organization organization = this.commandArgument;
        if (!isUserExists()) {
            setBadResult("Пользователь не зарегестрирован");
        } else if (!isOrganizationMax(organization)) {
            setBadResult("Значение элемента не превышает значение наибольщего элемента в коллекции");
        } else {
            super.execute();
        }
    }

    private boolean isOrganizationMax(Organization newOrganization) {
        return dao.getCollection().stream().map(newOrganization::compareTo).allMatch((a) -> a > 0);
    }
}
