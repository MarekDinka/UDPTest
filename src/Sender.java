import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void throwAPacket(String ip, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(new DatagramPacket(new byte[] {(byte) Main.getI()}, 1, InetAddress.getByName(ip), port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void throwASafePacket(String ip, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(new DatagramPacket(new byte[] {(byte) Main.getI(), 0}, 2, InetAddress.getByName(ip), port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void throwAckPacket(String ip, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(new DatagramPacket(new byte[] {(byte) Main.getI(), 1}, 1, InetAddress.getByName(ip), port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
