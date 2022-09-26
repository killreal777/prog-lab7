package commands;

import abstractions.command.Command;
import exceptions.MessagedRuntimeException;
import script.ScriptReader;

import java.io.FileNotFoundException;

public class ExecuteScript extends Command {
    private final ScriptReader scriptReader;
    private String scriptPath;

    public ExecuteScript(ScriptReader scriptReader) {
        this.name = "execute_script file_name";
        this.scriptReader = scriptReader;
    }

    @Override
    public void setArgs(String[] args) {
        checkArgumentsAmount(args, 1);
        this.scriptPath = args[0];
    }

    @Override
    public void execute() {
        try {
            scriptReader.readScript(scriptPath);
            result = String.format("Скрипт %s начал исполняться", scriptPath);
        } catch (FileNotFoundException e) {
            result = String.format("Файл со скриптом %s не найден", scriptPath);
            throw new MessagedRuntimeException(result);
        } catch (MessagedRuntimeException e) {
            result = String.format("Скрипт %s не может быть исполнен: %s", scriptPath, e.getMessage());
            throw new MessagedRuntimeException(result);
        }
    }
}
