package Task_9_1;

public class threadStates {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        final Object secondLock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("\nПоток входит в WAITING...");
                    lock.wait();
                    System.out.println("Поток вышел из WAITING");

                    System.out.println("\nПоток входит в TIMED_WAITING...");
                    Thread.sleep(100);
                    System.out.println("Поток вышел из TIMED_WAITING");

                    System.out.println("\nПоток пытается захватить secondLock...");
                    synchronized (secondLock) {
                        System.out.println("Поток захватил secondLock");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // NEW состояние
        System.out.println("Состояние после создания: " + thread.getState());

        thread.start();
        // RUNNABLE состояние
        System.out.println("\nСостояние после start(): " + thread.getState());

        // WAITING состояние
        Thread.sleep(50);
        System.out.println("Состояние после wait(): " + thread.getState());
        synchronized (lock) {
            lock.notify();
        }

        // TIMED WAITING состояние
        Thread.sleep(50);
        System.out.println("Состояние потока в Timed waiting: " + thread.getState());


        // BLOCKED состояние
        synchronized (secondLock) {
            Thread.sleep(100);
            System.out.println("Состояние при попытке захватить блокировку: " + thread.getState());
        }

        // TERMINATED состояние
        thread.join();
        System.out.println("\nСостояние после завершения: " + thread.getState());
    }
}
