package Gui.factories;

import Gui.Menu.Menu;

public interface IMenuFactory {
    Menu createMenu();
    Menu createMenu(int type);
}
