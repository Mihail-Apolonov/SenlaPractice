package Gui.commands.MasterCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

import java.util.Scanner;

public class deleteMasterCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID мастера: ");
        int id = scanner.nextInt();

        AutoServiceAdmin.getInstance().removeMaster(id);
    }
}
