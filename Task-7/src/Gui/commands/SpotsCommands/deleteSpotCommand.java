package Gui.commands.SpotsCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

public class deleteSpotCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID места: ");
        int id = scanner.nextInt();

        AutoServiceAdmin.getInstance().removeGarageSpot(id);
    }
}
