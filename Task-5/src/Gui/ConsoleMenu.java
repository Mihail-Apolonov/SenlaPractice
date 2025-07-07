package Gui;

import Gui.commands.Command;
import Gui.factories.ConsoleFactory;
import Gui.factories.IFactory;

import java.util.Scanner;

public class ConsoleMenu implements Menu {
    private final IFactory factory;
    private final Scanner scanner;

    public ConsoleMenu() {
        this.factory = new ConsoleFactory();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("1. Добавить мастера");
        System.out.println("2. Добавить гаражное место");
        System.out.println("3. Создать заказ");
        System.out.println("4. Просмотреть заказы");
        System.out.println("5. Завершить заказ");
        System.out.println("0. Выход");
    }

    @Override
    public void processInput(int choice) {
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
