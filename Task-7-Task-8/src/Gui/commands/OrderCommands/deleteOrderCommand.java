package Gui.commands.OrderCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

@Component
public class deleteOrderCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();

        adminService.deleteOrder(id);
    }
}
