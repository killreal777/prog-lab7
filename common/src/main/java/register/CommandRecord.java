package register;

import exceptions.InvalidRecordException;

public enum CommandRecord {

    // server creation commands
    ADD(CommandType.SERVER, "add", "{Organization}",
            "добавить новый элемент в коллекцию"),

    ADD_IF_MAX(CommandType.SERVER, "add_if_max", "{Organization}",
            "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента"),

    UPDATE(CommandType.SERVER, "update", "id {Organization}",
            "обновить значение элемента коллекции, id которого равен заданному"),

    REMOVE_BY_ADDRESS(CommandType.SERVER, "remove_any_by_official_address", "{Address}",
            "удалить из коллекции один элемент, значение поля officialAddress которого эквивалентно заданному"),

    // server simple arged commands
    REMOVE_BY_ID(CommandType.SERVER, "remove_by_id", "id",
            "удалить элемент из коллекции по его id"),

    FILTER_STARTS_WITH_NAME(CommandType.SERVER, "filter_starts_with_name", "name",
            "вывести элементы, значение поля name которых начинается с заданной подстроки"),

    // server simple argless commands
    CLEAR(CommandType.SERVER, "clear", "",
            "очистить коллекцию"),

    SHOW(CommandType.SERVER, "show", "",
            "вывести в стандартный поток вывода все элементы коллекции в строковом представлении"),

    HEAD(CommandType.SERVER, "head", "",
            "вывести в стандартный поток вывода первый элемент коллекции в строковом представлении"),

    PRINT_ASCENDING(CommandType.SERVER, "print_ascending", "",
            "вывести элементы коллекции в порядке возрастания"),

    INFO(CommandType.SERVER, "info", "",
            "вывести в стандартный поток вывода информацию о коллекции"),

    // local commands
    EXECUTE_SCRIPT(CommandType.LOCAL, "execute_script", "file_name",
            "считать и исполнить скрипт из указанного файла"),

    EXIT(CommandType.LOCAL, "exit", "",
            "завершить работу программы"),

    HISTORY(CommandType.LOCAL, "history", "",
            "вывести последние 10 команд (без их аргументов)"),

    HELP(CommandType.LOCAL, "help", "",
            "вывести справку по доступным командам");

    private final CommandType type;
    private final String name;
    private final String argumentsNames;
    private final String help;

    CommandRecord(CommandType type, String name, String argumentsNames, String help) {
        if (name.equals("") || help.equals(""))
            throw new InvalidRecordException();
        this.type = type;
        this.name = name;
        this.argumentsNames = argumentsNames;
        this.help = help;
    }

    public CommandType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getArgumentsNames() {
        return argumentsNames;
    }

    public String getHelp() {
        return help;
    }

    public enum CommandType {
        LOCAL, SERVER;
    }
}
