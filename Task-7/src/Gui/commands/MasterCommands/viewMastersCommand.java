package Gui.commands.MasterCommands;

import Entity.AutoServiceAdmin;
import Gui.commands.Command;

public class viewMastersCommand implements Command {
    @Override
    public void execute() {
        AutoServiceAdmin.getInstance().getAllMasters().forEach(System.out::println);
    }
}
