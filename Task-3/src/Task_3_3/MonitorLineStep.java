package Task_3_3;

public class MonitorLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Часть Monitor сделана");
        return new Monitor();
    }
}
