package Gui.commands.SpotsCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

public class addSpotCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID гаража: ");
        int id = scanner.nextInt();

        AutoServiceAdmin.getInstance().addGarageSpot(id);
        System.out.println("Гаражное место добавлено успешно!");
    }
}
