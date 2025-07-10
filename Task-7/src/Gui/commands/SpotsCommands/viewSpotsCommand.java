package Gui.commands.SpotsCommands;

import DI.Component;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

@Component
public class viewSpotsCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute() {
        adminService.getAllGarageSpots().forEach(System.out::println);
    }
}
