package Task_3_3;

public class MotherBoardLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Часть Motherboard сделана");
        return new MotherBoard();
    }
}
