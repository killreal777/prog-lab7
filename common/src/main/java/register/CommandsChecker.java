package register;

import exceptions.FoundUnregisteredCommandsException;
import exceptions.RegisteredCommandsNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandsChecker {
    private final static HashMap<CommandRecord.CommandType, ArrayList<String>> correctListsMap;

    static {
        correctListsMap = new HashMap<>();
        correctListsMap.put(CommandRecord.CommandType.SERVER, new ArrayList<>());
        correctListsMap.put(CommandRecord.CommandType.LOCAL, new ArrayList<>());
        for (CommandRecord record : CommandRecord.values()) {
            CommandRecord.CommandType type = record.getType();
            String name = record.getName();
            correctListsMap.get(type).add(name);
        }
    }

    public static void check(CommandRecord.CommandType checkingType, ArrayList<String> checkingList, String structure) {
        ArrayList<String> correctList = correctListsMap.get(checkingType);
        ArrayList<String> missingCommandsList = new ArrayList<>();
        for (String command : correctList) {
            if (checkingList.contains(command))
                checkingList.remove(command);
            else
                missingCommandsList.add(command);
        }
        throwExceptionIfNecessary(missingCommandsList, checkingList, structure);
    }

    private static void throwExceptionIfNecessary(ArrayList<String> missingCommands, ArrayList<String> extraCommands,
            String structureName) {
        if (!missingCommands.isEmpty())
            throw new RegisteredCommandsNotFoundException(missingCommands.toString(), structureName);
        if (!extraCommands.isEmpty())
            throw new FoundUnregisteredCommandsException(extraCommands.toString(), structureName);
    }
}
