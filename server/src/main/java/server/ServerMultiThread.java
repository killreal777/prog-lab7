package server;

import exceptions.ConnectionException;
import exceptions.ServerException;
import io.Format;
import io.TextFormatter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ServerMultiThread {
    private final ServerSocketChannel serverSocketChannel;
    private final ExecutorService cachedThreadPool;

    public ServerMultiThread(String host, int port) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.cachedThreadPool = Executors.newCachedThreadPool();
        configureServerSocketChannel(host, port);
    }

    private void configureServerSocketChannel(String host, int port) throws IOException {
        SocketAddress serverAddress = new InetSocketAddress(host, port);
        serverSocketChannel.socket().bind(serverAddress);
        serverSocketChannel.configureBlocking(false);
    }

    public void run() throws IOException {
        System.out.println(TextFormatter.format("Сервер начал работу", Format.GREEN));
        while (true) {
            acceptNewClient();
        }
    }

    private void acceptNewClient() throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        if (client != null)
            new Thread(() -> handleClient(client)).start();
    }

    protected void handleClient(SocketChannel client) {
        try {
            while (true) {
                read(client);
                write(client);
            }
        } catch (IOException | ConnectionException ignored) {
            System.out.println("Client disconnected");
        }
    }

    private void read(SocketChannel client) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        client.read(buffer);
        handleRequestBuffer(buffer);
    }

    private void write(SocketChannel client) throws IOException {
        Runnable write = () -> {
            try {
                client.write(prepareResponseBuffer());
            } catch (IOException e) {
                throw new ConnectionException();
            }
        };
        cachedThreadPool.execute(write);
    }

    abstract protected void handleRequestBuffer(ByteBuffer requestBuffer);

    abstract protected ByteBuffer prepareResponseBuffer();
}
