package abstractions.requests;

import abstractions.prototypes.CloneablePrototype;
import exceptions.ArgumentAmountException;

import java.io.Serializable;

public abstract class CommandRequest extends CloneablePrototype implements Serializable {
    protected String commandName;
    protected String userName;

    public CommandRequest(String commandName) {
        this.commandName = commandName;
    }

    abstract public void setCommandArgs(String[] args);

    protected void checkArgumentsAmount(String[] args, int expectedAmount) {
        int inputtedAmount = args.length;
        if (inputtedAmount != expectedAmount)
            throw new ArgumentAmountException(inputtedAmount, expectedAmount);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommandName() {
        return commandName;
    }

    @Override
    public String toString() {
        return commandName;
    }
}
