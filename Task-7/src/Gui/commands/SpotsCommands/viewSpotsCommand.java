package Gui.commands.SpotsCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

public class viewSpotsCommand implements Command {
    @Override
    public void execute() {
        AutoServiceAdmin.getInstance().getAllGarageSpots().forEach(System.out::println);
    }
}
