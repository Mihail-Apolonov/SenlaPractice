package Gui.commands.MasterCommands;

import DI.Component;
import DI.DependencyInjector;
import DI.Inject;
import Entity.AutoServiceAdmin;
import Gui.commands.Command;

@Component
public class viewMastersCommand implements Command {
    @Inject
    private AutoServiceAdmin adminService;
    @Override
    public void execute() {
        adminService.getAllMasters().forEach(System.out::println);
    }
}
