import Entity.Order;

import java.io.InputStream;
import java.util.Comparator;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Main {
    public static void main(String[] args) {
        AutoServiceAdmin admin = new AutoServiceAdmin();

        // Добавляем гаражи
        admin.addGarageSpot(1);
        admin.addGarageSpot(2);
        admin.addGarageSpot(3);

        // Добавляем мастеров
        admin.addMaster(1, "Яков");
        admin.addMaster(2, "Алексей");
        admin.addMaster(3, "Сергей");

        // Добавляем заказы
        admin.addOrder(now(), 5000);
        admin.addOrder(now(), 7000);
        admin.addOrder(now().plusDays(1), 3990);
        admin.addOrder(now().plusDays(2), 7800);
        admin.addOrder(now().plusDays(1), 5500);
        admin.addOrder(now(), 11800);
        admin.addOrder(now().plusDays(1), 3990);
        admin.addOrder(now(), 11800);

        // Назначаем ресурсы на заказы
        admin.assignResourcesToOrder(1, 1, 1);
        admin.assignResourcesToOrder(2, 2, 2);
        admin.assignResourcesToOrder(3, 1, 2);
        admin.assignResourcesToOrder(4, 2, 3);
        admin.assignResourcesToOrder(5, 2, 3);
        admin.assignResourcesToOrder(6, 3, 3);
        admin.assignResourcesToOrder(7, 1, 3);
        admin.assignResourcesToOrder(8, 3, 3);

        admin.completeOrder(3);

        System.out.println("Ищем свободные места на " + now().plusDays(2));
        for (var i: admin.getFreeGarageSpot(now().plusDays(2)))
            System.out.println(i.toString());

        System.out.println("\nВсе заказы сортированные по дате выполнения: ");
        for (var i: admin.getOrdersSortedBy(Comparator.comparing(Order::getPlannedDate)))
            System.out.println(i.toString());

        System.out.println("\nСписок матеров отсортированных по имени");
        for (var i: admin.getMastersAlphabetically())
            System.out.println(i.toString());

        System.out.println("\nСписок активных заказов по цене: ");
        for (var i: admin.getActiveOrders(Comparator.comparing(Order::getPrice)))
            System.out.println(i.toString());

        System.out.println("\nЗаказ выполняемый конкретным мастером: ");
        System.out.println(admin.findOrderByMaster(2));

        System.out.println("\nМастер выполняющий конкретный заказ: ");
        System.out.println(admin.findMasterByOrder(3));

        System.out.println("\nОтмененные заказы за промежуток времени");
        for (var i: admin.getOrdersByStatusAndPeriod("canceled", now(), now().plusDays(3)))
            System.out.println(i.toString());

        System.out.println("\nСвободные места на определенную дату: ");
        System.out.println(admin.countAvailableMastersAndSpots(now().plusDays(2)));

        System.out.println("\nБлижайшее свободное место:");
        System.out.println(admin.findNearestAvailableDate());

        
    }
}
