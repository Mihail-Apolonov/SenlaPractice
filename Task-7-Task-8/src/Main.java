import DI.DependencyInjector;
import Entity.AutoServiceAdmin;
import Entity.DataPersistence;
import Gui.Menu.MainMenu;
import Gui.Menu.Menu;
import Gui.factories.IMenuFactory;
import Gui.factories.MainFactory;

public class Main {

    public static void main(String[] args) {

        // 1. Загрузка данных
        AutoServiceAdmin admin = DataPersistence.loadData();

        // 2. Регистрация экземпляра в DI-контейнере
        DependencyInjector.registerInstance(AutoServiceAdmin.class, admin);

        // 3. Инициализация главного меню с внедрением зависимостей
        MainMenu mainMenu = new MainMenu();
        DependencyInjector.injectDependencies(mainMenu);

        // 4. Сохранение данных при завершении
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataPersistence.saveData(admin);
            System.out.println("Данные сохранены при завершении программы");
        }));

        // 5. Запуск
        mainMenu.run();
    }
}

