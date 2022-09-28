package app;

import abstractions.requests.CommandRequest;
import client.Connector;
import io.Terminal;
import requestes.AuthorizationRequest;
import requestes.RegistrationRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

public class LoginManager {
    private final Terminal terminal;
    private final Connector connector;
    private final Consumer<String> setUserName;
    private String userName;
    private String password;

    public LoginManager(Terminal terminal, Connector connector, Consumer<String> setUserName) {
        this.terminal = terminal;
        this.connector = connector;
        this.setUserName = setUserName;
    }

    public void login() {
        CommandRequest request = prepareRequest();
        configureRequest(request);
        String response = connector.executeCommandOnServer(request).get();
        parseResponse(response);
    }

    private CommandRequest prepareRequest() {
        return switch (readStartCommand()) {
            case "a" -> new AuthorizationRequest();
            case "r" -> new RegistrationRequest();
            default -> null;
        };
    }

    private String readStartCommand() {
        String input = "";
        while (!input.equals("a") && !input.equals("r"))
            input = terminal.readLineEntire("Введите \"a\" для авторизации или \"r\" для регистрации: ");
        return input;
    }

    private void configureRequest(CommandRequest request) {
        userName = terminal.readLineEntire("Login: ");
        password = cryptPassword(terminal.readLineEntire("Password: "));
        request.setUserName(userName);
        request.setCommandArgs(new String[]{password});
    }

    private String cryptPassword(String password) {
        try {
            String a = MessageDigest.getInstance("MD2").digest(password.getBytes()).toString();
            System.out.println(a);
            return a;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseResponse(String response) {
        terminal.print(response);
        if (!isResponseGood(response))
            login();
        else
            setUserName.accept(userName);
    }

    private boolean isResponseGood(String response) {
        return Arrays.asList(response.split(" ")).contains("вошли");
    }
}
