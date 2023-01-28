import static java.lang.System.exit;

public class Main {
    private static int i = 0;

    public static synchronized int getI() {
        return i;
    }

    public static synchronized void setI(int a) {
        i = a;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java jar UDPTest.jar ip port safety\n" +
                               "safety -> 1 (no ack)\n" +
                               "       -> 2 (wait for ack from receiver)\n" +
                               "       -> 3 (TCP)");
            exit(1);
        }
        Listener l = new Listener(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        l.listen();
    }
}
