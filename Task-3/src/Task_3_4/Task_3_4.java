package Task_3_4;

import java.time.LocalDateTime;

public class Task_3_4 {
    public static void main(String[] args) {
        CarService service  = new CarService();

        // Добавляем мастеров
        service.addMaster(new Master("Иван"));
        service.addMaster(new Master("Петр"));

        // Добавляем места в гараже
        service.addGarageSpot(new GarageSpot(1));
        service.addGarageSpot(new GarageSpot(2));

        // Создаем заказы
        LocalDateTime now = LocalDateTime.now();
        service.addOrder(now, now.plusHours(2), service.getMasterByName("Иван"),
                service.getGarageSpotById(1));
        service.addOrder(now, now.plusHours(2), service.getMasterByName("Петр"),
                service.getGarageSpotById(2));

        // Выводим информацию
        System.out.println("Мастера:");
        service.getAllMasters().forEach(System.out::println);

        System.out.println("\nМеста в гараже:");
        service.getAllGarageSpots().forEach(System.out::println);

        System.out.println("\nЗаказы:");
        service.getAllOrders().forEach(System.out::println);

        // Пример изменения времени заказа при недоступности мастера
        service.addOrder(now, now.plusHours(2), service.getMasterByName("Петр"),
                service.getGarageSpotById(2));

        System.out.println("\nЗаказы:");
        service.getAllOrders().forEach(System.out::println);

        // Пример закрытия заказа
        service.removeOrder(1);
        System.out.println("\nПосле закрытия заказа 1:");
        service.getAllOrders().forEach(System.out::println);

        // Пример изменения времени заказа при недоступности места
        service.addOrder(now, now.plusHours(2), service.getMasterByName("Иван"),
                service.getGarageSpotById(2));

        System.out.println("\nЗаказы:");
        service.getAllOrders().forEach(System.out::println);
    }
}
