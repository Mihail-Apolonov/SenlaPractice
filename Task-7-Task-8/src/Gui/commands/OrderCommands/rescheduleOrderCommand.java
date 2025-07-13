package Gui.commands.OrderCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class rescheduleOrderCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        LocalDate plannedDate = readDate(scanner, "Введите дату выполнения (дд.мм.гггг): ");

        adminService.rescheduleOrder(id, plannedDate);
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
}
