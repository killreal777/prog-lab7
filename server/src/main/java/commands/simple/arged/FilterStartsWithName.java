package commands.simple.arged;

import commands.abstractions.ArguedServerCommand;
import data.management.DataManager;
import io.Format;
import io.TextFormatter;
import model.Organization;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterStartsWithName extends ArguedServerCommand<String> {
    public FilterStartsWithName(DataManager dataManager) {
        super(dataManager);
        this.name = "filter_starts_with_name name";
    }

    @Override
    public void execute() {
        Predicate<Organization> startsWithName = (org) -> org.getName().startsWith(commandArgument);
        dataManager.getCollection().stream().sorted().filter(startsWithName).map(Organization::toString)
                .forEach(this::writeResult);
        if (result.equals(""))
            setBadResult();
    }

    private void setBadResult() {
        String message = "В коллекции нет элементов, значение поля name которых начинается с заданной подстроки";
        result = TextFormatter.format(message, Format.RED);
    }
}
