package Gui.factories;

import Gui.commands.Command;
import Gui.commands.SpotsCommands.addSpotCommand;
import Gui.commands.SpotsCommands.deleteSpotCommand;
import Gui.commands.SpotsCommands.viewSpotsCommand;

public class SpotFactory implements ICommandFactory {
    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> new addSpotCommand();
            case 2 -> new deleteSpotCommand();
            case 3 -> new viewSpotsCommand();
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
