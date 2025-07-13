package Task_9_4;

import java.time.LocalTime;

public class TimePrinterThread extends Thread {

    public TimePrinterThread() {
        // Устанавливаем как демон-поток (служебный)
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            // Выводим текущее время
            System.out.println("Текущее время: " + LocalTime.now().withNano(0));

            try {
                // Ждем 10 секунд
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Поток был прерван");
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Создаем и запускаем поток
        TimePrinterThread timeThread = new TimePrinterThread();
        timeThread.start();

        try {
            Thread.sleep(61000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Завершение main");
    }
}
