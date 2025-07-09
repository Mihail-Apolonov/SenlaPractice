package Gui.commands;

import Entity.AutoServiceAdmin;
import java.io.IOException;


public class importCommand implements Command {
    @Override
    public void execute() {
        try {
            AutoServiceAdmin.getInstance().importAllData("src/CsvFiles");
        } catch (IOException e) {System.out.println(e);}
        System.out.println("Импорт завершен!");
    }
}
