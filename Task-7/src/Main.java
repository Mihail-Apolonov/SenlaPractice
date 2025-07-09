import Entity.AutoServiceAdmin;
import Entity.DataPersistence;
import Gui.Menu.Menu;
import Gui.factories.IMenuFactory;
import Gui.factories.MainFactory;

public class Main {
    private final Menu menu;

    public Main(IMenuFactory factory) {
        this.menu = factory.createMenu();
    }

    public void run() {
        menu.run();
    }

    public static void main(String[] args) {

        // Загрузка данных при старте
        AutoServiceAdmin admin = DataPersistence.loadData();
        AutoServiceAdmin.setInstance(admin);

        // Сохранение при завершении
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataPersistence.saveData(AutoServiceAdmin.getInstance());
            System.out.println("Данные сохранены при завершении программы");
        }));



        IMenuFactory factory = new MainFactory();
        Main app = new Main(factory);
        app.run();
    }
}