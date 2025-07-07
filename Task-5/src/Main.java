import Gui.factories.ConsoleFactory;
import Gui.factories.IFactory;
import Gui.Menu;

public class Main {
    private final Menu menu;

    public Main(IFactory factory) {
        this.menu = factory.createMenu();
    }

    public void run() {
        menu.run();
    }

    public static void main(String[] args) {
        IFactory factory = new ConsoleFactory();
        Main app = new Main(factory);
        app.run();
    }
}