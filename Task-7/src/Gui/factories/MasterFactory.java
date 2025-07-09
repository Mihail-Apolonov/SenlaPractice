package Gui.factories;

import Gui.commands.Command;
import Gui.commands.MasterCommands.addMasterCommand;
import Gui.commands.MasterCommands.deleteMasterCommand;
import Gui.commands.MasterCommands.viewMastersCommand;

public class MasterFactory implements ICommandFactory {

    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> new addMasterCommand();
            case 2 -> new deleteMasterCommand();
            case 3 -> new viewMastersCommand();
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
