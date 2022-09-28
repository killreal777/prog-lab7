package commands;

import data.dao.Dao;
import model.Address;
import model.Organization;

import java.util.function.Predicate;

public class RemoveByAddress extends ArguedServerCommand<Address> {
    public RemoveByAddress(Dao dao) {
        super(dao);
        this.name = "remove_any_by_official_address {officialAddress}";
    }

    @Override
    public void execute() {
        Predicate<Organization> matchAddress = (org) -> org.getOfficialAddress().equals(this.commandArgument);
        dao.getCollection().stream().filter(matchAddress).findFirst()
                .ifPresentOrElse(this::removeOrganizationFromDataCollection, this::setBadResult);
    }

    private void removeOrganizationFromDataCollection(Organization organization) {
        if (!isUserExists()) {
            setBadResult("Пользователь не зарегестрирован");
        } else if (!organization.getOwnerLogin().equals(userName)) {
            setBadResult("Вы не являетесь владельцем этого объекта");
        } else {
            dao.removeById(organization.getId());
            setGoodResult(String.format("Удалена оганизация \"%s\"", organization.getName()));
        }
    }

    private void setBadResult() {
        setBadResult("В коллекции нет подходящего элемента");
    }
}
