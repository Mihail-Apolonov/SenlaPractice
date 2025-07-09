package Gui.factories;

import Gui.Menu;
import Gui.commands.Command;

public interface IFactory {
    Menu createMenu();
    Command createCommand(int type);
}
