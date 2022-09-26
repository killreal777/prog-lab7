package client;

import abstractions.requests.CommandRequest;
import serialization.Serializer;

import java.io.IOException;

public class Client extends ClientIo {
    private final Serializer<CommandRequest> requestSerializer;
    private final Serializer<String> stringSerializer;

    protected Client() {
        super("localhost", 7700);
        this.requestSerializer = new Serializer<>();
        this.stringSerializer = new Serializer<>();
    }

    protected String executeCommandOnServer(CommandRequest request) throws IOException {
        sendRequest(requestSerializer.serialize(request));
        return stringSerializer.deserialize(getResponse());
    }
}
