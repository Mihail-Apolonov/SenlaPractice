package Task_3_3;

public class Task_3_3 {
    public static void main(String[] args){

        MonitorLineStep monitorLineStep = new MonitorLineStep();
        MotherBoardLineStep motherBoardLineStep = new MotherBoardLineStep();
        CaseLineStep caseLineStep = new CaseLineStep();

        IAssemblyLine assemblyLine = new LaptopAssemblyLine(caseLineStep, monitorLineStep, motherBoardLineStep);
        IProduct product = new Laptop();

        assemblyLine.assembleProduct(product);
    }
}
