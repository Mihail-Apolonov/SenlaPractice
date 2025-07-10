package Gui.commands.OrderCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Entity.Order;
import Gui.commands.Command;

import java.util.Comparator;
import java.util.Scanner;

@Component
public class viewOrdersCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute(){
        System.out.println("Как отсортировать заказы?");
        System.out.println("1. По id");
        System.out.println("2. По статусу");
        System.out.println("3. Только активные");
        System.out.print("Введите: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        System.out.println("Список всех заказов:");
        switch (input){
            case 1: adminService.getOrdersSortedBy(Comparator.comparing(Order::getId)).forEach(i->
                    System.out.println(i.toString())); return;
            case 2: adminService.getOrdersSortedBy(Comparator.comparing(Order::getStatus)).forEach(i->
                    System.out.println(i.toString())); return;
            case 3: adminService.getActiveOrders(Comparator.comparing(Order::getId)).forEach(i ->
                    System.out.println(i.toString())); return;
            default: System.out.println("Неверный ввод");
        }

    }
}
