package Gui.commands.OrderCommands;


import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

@Component
public class completeOrderCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute(){
        System.out.print("Введите id заказа: ");
        Scanner scanner = new Scanner(System.in);
        int orderId = scanner.nextInt();
        adminService.completeOrder(orderId);
        System.out.println("Заказ выполнен!");
    }
}
