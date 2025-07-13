package Gui.factories;

import Gui.commands.Command;

public interface ICommandFactory {
    Command createCommand(int type);
}
