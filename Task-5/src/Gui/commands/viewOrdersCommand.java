package Gui.commands;

import Entity.AutoServiceAdmin;
import Entity.Order;

import java.util.Comparator;
import java.util.Scanner;

public class viewOrdersCommand implements Command{
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
            case 1: AutoServiceAdmin.getInstance().getOrdersSortedBy(Comparator.comparing(Order::getId)).forEach(i->
                    System.out.println(i.toString())); return;
            case 2: AutoServiceAdmin.getInstance().getOrdersSortedBy(Comparator.comparing(Order::getStatus)).forEach(i->
                    System.out.println(i.toString())); return;
            case 3:AutoServiceAdmin.getInstance().getActiveOrders(Comparator.comparing(Order::getId)).forEach(i ->
                    System.out.println(i.toString())); return;
            default: System.out.println("Неверный ввод");
        }

    }
}
