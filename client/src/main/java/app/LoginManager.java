package app;

import abstractions.requests.CommandRequest;
import client.Connector;
import io.Format;
import io.Terminal;
import io.TextFormatter;
import requestes.AuthorizationRequest;
import requestes.RegistrationRequest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.function.Consumer;

public class LoginManager {
    private final Terminal terminal;
    private final Connector connector;
    private final Consumer<String> setUserName;
    private String userName;

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
        String input = terminal.readLineEntire("Введите \"a\" для авторизации или \"r\" для регистрации: ");
        while (!input.equals("a") && !input.equals("r"))
            input = terminal.readLineEntire(TextFormatter.format("Вам доступны только команды \"а\" и \"r\": ", Format.RED));
        return input;
    }

    private void configureRequest(CommandRequest request) {
        userName = terminal.readLineEntire(TextFormatter.format("Login: ", Format.YELLOW));
        //String password = terminal.readLineEntire("Password: ");
        String password = cryptPassword(terminal.readLineEntire(TextFormatter.format("Password: ", Format.YELLOW)));
        request.setUserName(userName);
        request.setCommandArgs(new String[]{password});
    }

    private String cryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            return no.toString(36);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseResponse(String response) {
        terminal.print(response);
        if (responseIsNotGood(response))
            login();
        else
            setUserName.accept(userName);
    }

    private boolean responseIsNotGood(String response) {
        return !Arrays.asList(response.split(" ")).contains("вошли");
    }
}
