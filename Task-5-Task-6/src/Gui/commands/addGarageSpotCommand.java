package Gui.commands;

import Entity.AutoServiceAdmin;

import java.util.Scanner;

public class addGarageSpotCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID гаража: ");
        int id = scanner.nextInt();

        AutoServiceAdmin.getInstance().addGarageSpot(id);
        System.out.println("Гаражное место добавлено успешно!");
    }
}
