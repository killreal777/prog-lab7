package server;

import exceptions.ServerException;
import io.Format;
import io.TextFormatter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
        SocketAddress serverSocketAddress = new InetSocketAddress(host, port);
        serverSocketChannel.socket().bind(serverSocketAddress);
        serverSocketChannel.configureBlocking(false);
    }

    public void run() throws IOException {
        System.out.println(TextFormatter.format("Сервер начал работу", Format.GREEN));
        while (true) {
            registerNewClient();
        }
    }

    private void registerNewClient() throws IOException {
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
        } catch (IOException ioException) {
            try {
                client.close();
            } catch (IOException ignored) {
                System.out.println("Клиент отключился");
            }
        }
    }

    private void read(SocketChannel client) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        client.read(buffer);
        handleRequestBuffer(buffer);
    }

    private void write(SocketChannel client) throws IOException {
        cachedThreadPool.execute(() -> {
            try {
                client.write(prepareResponseBuffer());
            } catch (IOException ignored) {
                System.out.println("Клиент отключился");
            }
        });
    }

    abstract protected void handleRequestBuffer(ByteBuffer requestBuffer);

    abstract protected ByteBuffer prepareResponseBuffer();
}
