package Gui.commands.MasterCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

public class addMasterCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID мастера: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите имя мастера: ");
        String name = scanner.nextLine();

        AutoServiceAdmin.getInstance().addMaster(id, name);
        System.out.println("Мастер добавлен успешно!");
    }
}
