package Gui.commands;

import Entity.AutoServiceAdmin;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class addOrderCommand implements Command {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        // Ввод даты выполнения
        LocalDate plannedDate = readDate(scanner, "Введите дату выполнения (дд.мм.гггг): ");

        // Ввод цены
        double price = readPrice(scanner);

        // Создание заказа
        AutoServiceAdmin admin = AutoServiceAdmin.getInstance();
        admin.addOrder(plannedDate, price);

        System.out.println("Заказ успешно создан!");

        // Предложение назначить ресурсы
        offerAssignResources(scanner, admin.getLastAddedOrderId());
    }

    private LocalDate readDate(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.nextLine();
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (Exception e) {
                System.out.println("Некорректный формат даты. Используйте дд.мм.гггг");
            }
        }
    }

    private double readPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите стоимость работ: ");
                String priceStr = scanner.nextLine();
                return Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                System.out.println("Некорректная сумма. Введите число.");
            }
        }
    }

    private void offerAssignResources(Scanner scanner, int orderId) {
        System.out.print("Хотите назначить мастера и место на этот заказ? (да/нет): ");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("да") || answer.equals("д")) {
            assignResourcesToOrder(scanner, orderId);
        }
    }

    private void assignResourcesToOrder(Scanner scanner, int orderId) {
        AutoServiceAdmin admin = AutoServiceAdmin.getInstance();

        // Вывод списка доступных мастеров
        System.out.println("Доступные мастера:");
        admin.getMastersAlphabetically().forEach(m ->
                System.out.println(m.getId() + ". " + m.getName()));

        System.out.print("Выберите ID мастера: ");
        int masterId = scanner.nextInt();

        // Вывод списка доступных мест
        System.out.println("Доступные гаражные места:");
        admin.getAllGarageSpots().forEach(s ->
                System.out.println(s.getId() + ". " + s));

        System.out.print("Выберите ID гаража: ");
        int spotId = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        // Назначение ресурсов
        boolean success = admin.assignResourcesToOrder(orderId, masterId, spotId);

        if (success) {
            System.out.println("Ресурсы успешно назначены на заказ!");
        } else {
            System.out.println("Не удалось назначить ресурсы. Возможно, они уже заняты. Отменяем заказ");
        }
    }
}
