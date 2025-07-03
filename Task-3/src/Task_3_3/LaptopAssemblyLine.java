package Task_3_3;

public class LaptopAssemblyLine implements IAssemblyLine {
    private final MonitorLineStep monitorLineStep;
    private final MotherBoardLineStep motherBoardLineStep;
    private final CaseLineStep caseLineStep;

    public LaptopAssemblyLine(CaseLineStep casee, MonitorLineStep monitor, MotherBoardLineStep motherboard){
        this.caseLineStep = casee;
        this.monitorLineStep = monitor;
        this.motherBoardLineStep = motherboard;
    }
    @Override
    public IProduct assembleProduct(IProduct product) {
        System.out.println("Начинаем сборку продукта");
        product.installFirstPart(monitorLineStep.buildProductPart());
        product.installSecondPart(caseLineStep.buildProductPart());
        product.installThirdPart(motherBoardLineStep.buildProductPart());
        System.out.println("Продукт собран");
        return product;
    }
}

