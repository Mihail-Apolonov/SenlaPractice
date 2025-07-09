package Gui.commands;


import Entity.AutoServiceAdmin;

import java.util.Scanner;

public class completeOrderCommand implements Command {
    @Override
    public void execute(){
        System.out.println("Введите id заказа: ");
        Scanner scanner = new Scanner(System.in);
        int orderId = scanner.nextInt();
        AutoServiceAdmin.getInstance().completeOrder(orderId);
        System.out.println("Заказ выполнен!");
    }
}
