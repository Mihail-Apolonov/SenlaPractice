package Gui.Menu;

import Config.ConfigProperty;
import Config.Configurator;
import Gui.commands.Command;
import Gui.factories.ICommandFactory;
import Gui.factories.OrderFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class OrderMenu implements Menu {
    private final ICommandFactory factory;
    private final Scanner scanner;
    @ConfigProperty(propertyName = "allowRescheduleOrder")
    private Boolean allowRescheduleOrder;
    @ConfigProperty(propertyName = "allowDeleteOrder")
    private Boolean allowDeleteOrder;

    public OrderMenu() {
        this.factory = new OrderFactory();
        this.scanner = new Scanner(System.in);
        Configurator.configure(this);
    }

    @Override
    public void display() {
        System.out.println("1. Добавить заказ");
        System.out.println("2. Завершить заказ");
        System.out.println("3. Удалить заказ");
        System.out.println("4. Изменить дату выполнения заказа");
        System.out.println("5. Посмотреть заказы");
        System.out.println("0. Назад");
    }

    @Override
    public void processInput(int choice) {
        if (choice == 3 & !allowDeleteOrder){
            System.out.println("В данной конфигурации запрещено удалять заказы");
            return;
        } else if (choice == 4 & !allowRescheduleOrder){
            System.out.println("В данной конфигурации запрещено изменять время выполнения заказа");
            return;
        }
        Command command = factory.createCommand(choice);
        if (command != null) {
            command.execute();
        }
    }
    @Override
    public void run() {
        while (true) {
            display();
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) break;
            processInput(choice);
        }
    }

}
