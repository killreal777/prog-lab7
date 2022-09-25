package server;

import abstractions.requests.CommandRequest;
import exceptions.ConnectionException;
import exceptions.DeserializationException;
import io.Format;
import io.TextFormatter;
import serialization.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Function;


public class Server extends ServerNio {
    private final Function<CommandRequest, String> executeCommandFunction;
    private final Runnable checkTerminalRequest;
    private final Serializer<CommandRequest> commandRequestSerializer;
    private final Serializer<String> stringSerializer;
    private String response;


    public Server(Function<CommandRequest, String> executeCommandFunction, Runnable checkTerminalRequest) throws IOException {
        super("localhost", 7700);
        this.executeCommandFunction = executeCommandFunction;
        this.checkTerminalRequest = checkTerminalRequest;
        this.commandRequestSerializer = new Serializer<>();
        this.stringSerializer = new Serializer<>();
    }


    public void run() throws IOException {
        System.out.println(TextFormatter.format("Сервер начал работу", Format.BOLD));
        while (true) {
            checkTerminalRequest.run();
            handleSelector();
        }
    }


    @Override
    protected void handleRequestBuffer(ByteBuffer requestBuffer) {
        try {
            CommandRequest request = commandRequestSerializer.deserialize(requestBuffer.array());
            response = executeCommandFunction.apply(request);
        } catch (DeserializationException e) {
            throw new ConnectionException();
        }
    }

    @Override
    protected ByteBuffer prepareResponseBuffer() {
        return ByteBuffer.wrap(stringSerializer.serialize(response));
    }
}
