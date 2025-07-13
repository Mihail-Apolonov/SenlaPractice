package Gui.factories;

import DI.DependencyInjector;
import Gui.commands.Command;
import Gui.commands.MasterCommands.addMasterCommand;
import Gui.commands.MasterCommands.deleteMasterCommand;
import Gui.commands.MasterCommands.viewMastersCommand;

public class MasterFactory implements ICommandFactory {

    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> DependencyInjector.createInstance(addMasterCommand.class);
            case 2 -> DependencyInjector.createInstance(deleteMasterCommand.class);
            case 3 -> DependencyInjector.createInstance(viewMastersCommand.class);
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
