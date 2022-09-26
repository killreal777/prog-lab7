package commands;

import abstractions.command.Command;
import io.Format;
import io.TextFormatter;
import register.CommandRecord;

public class Help extends Command {
    public Help() {
        this.name = "help";
        writeResult();
    }

    private void writeResult() {
        for (CommandRecord record : CommandRecord.values()) {
            String name = TextFormatter.format(record.getName(), Format.YELLOW);
            String args = record.getArgumentsNames();
            if (!args.equals(""))
                args = TextFormatter.format(TextFormatter.format(" " + args, Format.WHITE), Format.BOLD);
            String description = TextFormatter.format(record.getHelp(), Format.ITALIC);
            writeResult(String.format("%s%s: %s", name, args, description));
        }
    }

    @Override
    public void execute() {
        // nothing to do
    }
}
