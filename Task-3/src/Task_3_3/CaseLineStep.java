package Task_3_3;

public class CaseLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Часть Case сделана");
        return new Case();
    }
}

