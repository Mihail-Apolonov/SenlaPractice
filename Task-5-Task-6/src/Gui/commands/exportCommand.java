package Gui.commands;

import Entity.AutoServiceAdmin;

import java.io.IOException;

public class exportCommand implements Command{
    @Override
    public void execute() {
        try {
            AutoServiceAdmin.getInstance().exportAllData("src/CsvFiles");
        } catch (IOException e) {System.out.println(e);}
        System.out.println("Экспорт завершен!");
    }
}
