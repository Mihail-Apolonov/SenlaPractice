package Gui.factories;

import DI.DependencyInjector;
import Gui.commands.Command;
import Gui.commands.MasterCommands.viewMastersCommand;
import Gui.commands.OrderCommands.*;

public class OrderFactory implements  ICommandFactory {
    @Override
    public Command createCommand(int type) {
        return switch (type) {
            case 1 -> DependencyInjector.createInstance(addOrderCommand.class);
            case 2 -> DependencyInjector.createInstance(completeOrderCommand.class);
            case 3 -> DependencyInjector.createInstance(deleteOrderCommand.class);
            case 4 -> DependencyInjector.createInstance(rescheduleOrderCommand.class);
            case 5 -> DependencyInjector.createInstance(viewOrdersCommand.class);
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
