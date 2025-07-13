package Gui.commands.MasterCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

@Component
public class deleteMasterCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID мастера: ");
        int id = scanner.nextInt();

        adminService.removeMaster(id);
    }
}
