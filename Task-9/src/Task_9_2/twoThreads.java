package Task_9_2;

public class twoThreads {
    private static volatile int turn = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> printName("Поток Вася", 1, 2)).start();
        new Thread(() -> printName("Поток Гриша", 2, 1)).start();
    }

    private static void printName(String name, int myTurn, int nextTurn) {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                while (turn != myTurn) {
                    try { lock.wait(); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
                System.out.println(name);
                turn = nextTurn;
                lock.notifyAll();
            }
        }
    }
}
