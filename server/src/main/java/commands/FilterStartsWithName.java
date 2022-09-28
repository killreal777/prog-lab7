package commands;

import data.dao.Dao;
import model.Organization;

import java.util.function.Predicate;

public class FilterStartsWithName extends ArguedServerCommand<String> {
    public FilterStartsWithName(Dao dao) {
        super(dao);
        this.name = "filter_starts_with_name name";
    }

    @Override
    public void execute() {
        Predicate<String> startsWithName = (name) -> name.startsWith(commandArgument);
        dao.getCollection().stream().map(Organization::toString).filter(startsWithName).sorted().forEach(this::writeResult);
        if (result.equals(""))
            setBadResult("В коллекции нет элементов, значение поля name которых начинается с заданной подстроки");
    }
}
