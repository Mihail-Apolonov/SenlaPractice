package Task_9_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // Поток-производитель
        new Thread(() -> {
            Random random = new Random();
            while (true) {
                synchronized (lock) {
                    while (buffer.size() == BUFFER_SIZE) {
                        try { lock.wait(); }
                        catch (InterruptedException e) { e.printStackTrace(); }
                    }
                    int num = random.nextInt(100);
                    buffer.add(num);
                    System.out.println("Произведено: " + num);
                    lock.notifyAll();
                }
            }
        }).start();

        // Поток-потребитель
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (buffer.isEmpty()) {
                        try { lock.wait(); }
                        catch (InterruptedException e) { e.printStackTrace(); }
                    }
                    int num = buffer.poll();
                    System.out.println("Потреблено: " + num);
                    lock.notifyAll();
                }
            }
        }).start();
    }
}
