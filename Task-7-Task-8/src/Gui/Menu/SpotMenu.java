package Gui.Menu;

import Config.ConfigProperty;
import Config.Configurator;
import Gui.commands.Command;
import Gui.factories.ICommandFactory;
import Gui.factories.SpotFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class SpotMenu implements Menu {
    private final ICommandFactory factory;
    private final Scanner scanner;
    @ConfigProperty(propertyName = "allowEditSpots")
    private Boolean allowEditSpots;

    public SpotMenu() {
        this.factory = new SpotFactory();
        this.scanner = new Scanner(System.in);
        Configurator.configure(this);
    }

    @Override
    public void display() {
        System.out.println("1. Добавить гаражное место");
        System.out.println("2. Удалить гаражное место");
        System.out.println("3. Посмотреть гаражные места");
        System.out.println("0. Назад");
    }

    @Override
    public void processInput(int choice) {
        if (choice == 2 & !allowEditSpots) {
            System.out.println("В данной конфигурации запрещено удалять Гаражные места");
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
