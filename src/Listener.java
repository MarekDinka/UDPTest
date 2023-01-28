import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Listener {
    private DatagramSocket socket;
    private final String IP;
    private final int PORT;
    private final int SAFETY;

    public Listener(String ip, int port, int safety) {
        PORT = port;
        IP = ip;
        SAFETY = safety;
        try {
            socket = new DatagramSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        byte[] buffer = new byte[256*4];
        Sender.throwASafePacket(IP, PORT);
        if (SAFETY == 1) {
            while (socket.isBound()) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
//                System.out.println("Packet received = " + Arrays.toString(packet.getData()));
                    System.out.println("i = " + packet.getData()[0]);
                    Main.setI(packet.getData()[0] + 1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Sender.throwAPacket(IP, PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (SAFETY == 2) {
            while (socket.isBound()) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    if (packet.getData()[1] == 1) { // TODO -> check if I is right
                        socket.receive(packet);
                    }
                    System.out.println("i = " + packet.getData()[0]);
                    Sender.throwAckPacket(IP, PORT);
                    Main.setI(packet.getData()[0] + 1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Sender.throwASafePacket(IP, PORT);
                    socket.receive(packet);
                    if (packet.getData()[1] == 1) { // received an ack packet
                        if (packet.getData()[0] != Main.getI()) { // it is for the wrong I
                            Sender.throwASafePacket(IP, PORT);
                        }
                    } else { // not an ack packet
                        if (packet.getData()[0] < Main.getI()) {
                            Main.setI(packet.getData()[0]);
                            Sender.throwAckPacket(IP, PORT);
                            Main.setI(Main.getI()+1);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
