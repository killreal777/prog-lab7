package app;

import abstractions.prototypes.PrototypesManager;
import abstractions.command.Command;
import commands.ExecuteScript;
import commands.Exit;
import commands.Help;
import register.CommandRecord;
import register.CommandsChecker;
import script.ScriptReader;

public class LocalCommandManager extends PrototypesManager<Command> {
    private final ClientHistory history;
    private final ScriptReader scriptReader;

    public LocalCommandManager(ScriptReader scriptReader, ClientHistory history) {
        this.history = history;
        this.scriptReader = scriptReader;
        definePrototypes();
        CommandsChecker.check(CommandRecord.CommandType.LOCAL, getPrototypesNameList(), "LocalCommandManager");
    }

    @Override
    protected void definePrototypes() {
        addPrototype("execute_script", new ExecuteScript(scriptReader));
        addPrototype("exit", new Exit());
        addPrototype("history", new commands.History(history));
        addPrototype("help", new Help());
    }
}
