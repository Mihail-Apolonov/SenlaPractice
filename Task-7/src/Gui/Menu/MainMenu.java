package Gui.Menu;

import Gui.factories.IMenuFactory;
import Gui.factories.MainFactory;

import java.util.Scanner;

public class MainMenu implements Menu {
    private final IMenuFactory factory;
    private final Scanner scanner;

    public MainMenu() {
        this.factory = new MainFactory();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("1. Меню Мастеров");
        System.out.println("2. Меню Гаражных мест");
        System.out.println("3. Меню заказов");
        System.out.println("4. Меню дат");
        System.out.println("0. Выход");
    }

    @Override
    public void processInput(int choice) {
        Menu menu = factory.createMenu(choice);
        if (menu != null) {
            menu.run();
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
