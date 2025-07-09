package Gui.factories;

import Gui.Menu.*;

public class MainFactory implements IMenuFactory {
    @Override
    public Menu createMenu() {
        return new MainMenu();
    }

    @Override
    public Menu createMenu(int type) {
        return switch (type) {
            case 1 -> new MasterMenu();
            case 2 -> new SpotMenu();
            case 3 -> new OrderMenu();
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}
