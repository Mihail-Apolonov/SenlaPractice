package Gui.commands.OrderCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

public class deleteOrderCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID заказа: ");
        int id = scanner.nextInt();

        AutoServiceAdmin.getInstance().deleteOrder(id);
    }
}
