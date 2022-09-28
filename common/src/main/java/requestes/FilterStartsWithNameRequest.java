package requestes;

import abstractions.requests.ArguedCommandRequest;

public class FilterStartsWithNameRequest extends ArguedCommandRequest<String> {
    public FilterStartsWithNameRequest() {
        super("filter_starts_with_name");
    }

    @Override
    public void setCommandArgs(String[] args) {
        this.commandArgument = joinArgsInOneString(args);
    }

    private String joinArgsInOneString(String[] args) {
        StringBuilder result = new StringBuilder();
        for (String arg : args)
            result.append(" ").append(arg);
        return result.toString().trim();
    }
}
