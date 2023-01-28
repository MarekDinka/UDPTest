public class Main {
    private static int i = 0;

    public static synchronized int getI() {
        return i;
    }

    public static synchronized void setI(int a) {
        i = a;
    }

    public static void main(String[] args) {
        Listener l = new Listener(4002);
        l.start();
    }
}
