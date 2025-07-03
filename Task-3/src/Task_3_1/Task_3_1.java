package Task_3_1;

import java.util.Random;

public class Task_3_1 {
    public static void main(String[] args) {
        //Написать программу, генерирующую 3 рандомных трехзначный числа и выводящую сумму их первых чисел

        int x = (new Random().nextInt(100, 1000));
        int y = (new Random().nextInt(100, 1000));
        int z = (new Random().nextInt(100, 1000));

        System.out.println("Сгенерированные числа: " + x + " " + y + " " + z);

        x = x / 100;
        y = y / 100;
        z = z / 100;

        int sum = x + y + z;

        System.out.println("Сумма первых чисел: " + sum);
    }
}
