import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Listener {
    private DatagramSocket socket;
    private final String IP;
    private final int PORT;

    public Listener(String ip, int port) {
        PORT = port;
        IP = ip;
        try {
            socket = new DatagramSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        byte[] buffer = new byte[256*4];
        Sender.throwAPacket(IP, PORT);
        while (socket.isBound()) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Packet received = " + Arrays.toString(packet.getData()));
                Main.setI(packet.getData()[0]);
                Sender.throwAPacket(IP, PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
