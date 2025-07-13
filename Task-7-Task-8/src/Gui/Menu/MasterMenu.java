package Gui.Menu;

import Gui.commands.Command;
import Gui.factories.ICommandFactory;
import Gui.factories.MasterFactory;

import java.util.Scanner;

public class MasterMenu implements Menu {
    private final ICommandFactory factory;
    private final Scanner scanner;

    public MasterMenu() {
        this.factory = new MasterFactory();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("1. Добавить мастера");
        System.out.println("2. Удалить мастера");
        System.out.println("3. Посмотреть мастеров");
        System.out.println("0. Назад");
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
