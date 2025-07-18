package Gui.commands.MasterCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

@Component
public class addMasterCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID мастера: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите имя мастера: ");
        String name = scanner.nextLine();

        adminService.addMaster(id, name);
        System.out.println("Мастер добавлен успешно!");
    }
}
