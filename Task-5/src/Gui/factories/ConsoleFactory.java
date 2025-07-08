package Gui.factories;

import Gui.ConsoleMenu;
import Gui.Menu;
import Gui.commands.*;

public class ConsoleFactory implements IFactory {
    @Override
    public Menu createMenu() {
        return new ConsoleMenu();
    }

    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> new addMasterCommand();
            case 2 -> new addGarageSpotCommand();
            case 3 -> new addOrderCommand();
            case 4 -> new viewOrdersCommand();
            case 5 -> new completeOrderCommand();
            case 6 -> new importCommand();
            case 7 -> new exportCommand();
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
