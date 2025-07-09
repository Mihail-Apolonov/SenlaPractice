package Gui.factories;

import Gui.commands.Command;
import Gui.commands.OrderCommands.*;

public class OrderFactory implements  ICommandFactory {
    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> new addOrderCommand();
            case 2 -> new completeOrderCommand();
            case 3 -> new deleteOrderCommand();
            case 4 -> new rescheduleOrderCommand();
            case 5 -> new viewOrdersCommand();
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
