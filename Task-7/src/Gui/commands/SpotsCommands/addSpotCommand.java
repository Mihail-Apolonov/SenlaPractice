package Gui.commands.SpotsCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

@Component
public class addSpotCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID гаража: ");
        int id = scanner.nextInt();

        adminService.addGarageSpot(id);
        System.out.println("Гаражное место добавлено успешно!");
    }
}
