package app;

import abstractions.command.Command;
import abstractions.requests.CommandRequest;
import client.Connector;
import exceptions.MessagedRuntimeException;
import io.Format;
import io.TextFormatter;
import script.UnixScriptedTerminal;

import java.util.Optional;

public class ClientExecutionManager {
    private final ClientHistory history;
    private final UnixScriptedTerminal terminal;
    private final CommandReader commandReader;
    private final LocalCommandManager localCommandManager;
    private final RequestsManager requestsManager;
    private final Connector connector;
    private String userName;

    public ClientExecutionManager() {
        this.history = new ClientHistory();
        this.terminal = new UnixScriptedTerminal();
        this.commandReader = new CommandReader(terminal);
        this.requestsManager = new RequestsManager(terminal);
        this.localCommandManager = new LocalCommandManager(terminal, history);
        this.connector = new Connector(terminal);
        this.userName = "";
    }

    public void run() {
        while (true) {
            if (!userName.equals(""))
                executeNextCommand();
            else {
                terminal.print("Вы вошли как гость");
                new LoginManager(terminal, connector, this::setUserName).login();
                terminal.print("Для вывода справки по доступным командам введите help");
            }
        }
    }

    public void executeNextCommand() {
        try {
            CommandReader.UserInput input = commandReader.readCommand();
            String name = input.getCommandName();
            String[] args = input.getCommandArgs();
            executeCommand(name, args);
        } catch (MessagedRuntimeException e) {
            terminal.print(e.getMessage());
        }
    }

    private void executeCommand(String commandName, String[] commandArgs) {
        if (localCommandManager.contains(commandName))
            executeLocalCommand(commandName, commandArgs);
        else if (requestsManager.contains(commandName))
            executeServerCommand(commandName, commandArgs);
        else
            terminal.print(TextFormatter.format("Команда не найдена", Format.RED));
    }

    private void executeLocalCommand(String commandName, String[] commandArgs) {
        Command command = localCommandManager.clonePrototype(commandName);
        command.setArgs(commandArgs);
        command.execute();
        terminal.print(command.getResult());
        history.addCommand(command);
    }

    private void executeServerCommand(String commandName, String[] commandArgs) {
        CommandRequest request = requestsManager.clonePrototype(commandName);
        request.setCommandArgs(commandArgs);
        request.setUserName(userName);
        Optional<String> result = connector.executeCommandOnServer(request);
        if (result.isPresent()) {
            terminal.print(result.get());
            history.addRequest(request);
        }
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }
}
