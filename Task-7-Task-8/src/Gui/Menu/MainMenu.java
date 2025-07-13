package Gui.Menu;

import DI.Component;
import DI.Inject;
import Gui.factories.IMenuFactory;
import Gui.factories.MainFactory;

import java.util.Scanner;
@Component
public class MainMenu implements Menu {

    private final IMenuFactory factory = new MainFactory();

    private final Scanner scanner = new Scanner(System.in);

    public MainMenu() {}

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
