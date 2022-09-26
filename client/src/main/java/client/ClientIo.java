package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public abstract class ClientIo {
    private Socket socket;
    private final String hostname;
    private final int port;

    protected ClientIo(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    protected void connect() throws IOException {
        this.socket = new Socket(hostname, port);
    }

    protected void sendRequest(byte[] request) throws IOException {
        socket.getOutputStream().write(request);
    }

    protected InputStream getResponse() throws IOException {
        return socket.getInputStream();
    }
}
