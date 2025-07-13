package Gui.factories;

import DI.DependencyInjector;
import Gui.commands.Command;
import Gui.commands.MasterCommands.viewMastersCommand;
import Gui.commands.SpotsCommands.addSpotCommand;
import Gui.commands.SpotsCommands.deleteSpotCommand;
import Gui.commands.SpotsCommands.viewSpotsCommand;

public class SpotFactory implements ICommandFactory {
    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> DependencyInjector.createInstance(addSpotCommand.class);
            case 2 -> DependencyInjector.createInstance(deleteSpotCommand.class);
            case 3 -> DependencyInjector.createInstance(viewSpotsCommand.class);
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
