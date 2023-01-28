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
        if (args.length < 2) {
            System.out.println("Expect ip and port as arguments");
            exit(1);
        }
        Listener l = new Listener(args[0], Integer.parseInt(args[1]));
        l.start();
    }
}
