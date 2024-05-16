import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

class SimpleNIOHTTPServer implements HTTPServerHandler {
    
    /*
        Implement a simple NIO HTTP server that handle concurrent HHTP clients
        and serves requested template resources.
        In addition, it should respond to POST requests and respond with a success message
        */

    private String bindAddress;
    private int bindPort;
    private Selector selector;

    //TODO 
    public SimpleNIOHTTPServer(String bindAddress, int bindPort){
        // Implement constructor
        this.bindAddress = bindAddress;
        this.bindPort = bindPort;
        
    }

    // TODO
    /*
     * Handles the core server functionality
     * 
     * Wrapper instance method for running server
     */
    public void run(){
        try {
            this.selector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(False);
            InetSocketAddress hostAddress = new InetSocketAddress(this.bindAddress, this.bindPort);
            serverChannel.socket().bind(hostAddress);
            serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            while (true) {
                this.selector.select();
                Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey Key = iterator.next();
                    if (Key.isAcceptable()) {
                        // accpt client connection
                        SocketChannel Client = serverChannel.accept();
                        client.configureBlocking(false);
                        client.register(this.selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) Key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        client.read(buffer);
                        String request = new String (buffer.array()).trim();

                        // print reqest
                        System.out.println("request: " + request);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
