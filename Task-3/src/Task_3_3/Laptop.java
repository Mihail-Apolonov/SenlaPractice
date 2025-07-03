package Task_3_3;

public class Laptop implements IProduct {

    @Override
    public void installFirstPart(IProductPart part) {
        System.out.println("Часть " + part.getClass().getSimpleName() + " установлена");
    }

    @Override
    public void installSecondPart(IProductPart part) {
        System.out.println("Часть " + part.getClass().getSimpleName() + " установлена");
    }

    @Override
    public void installThirdPart(IProductPart part) {
        System.out.println("Часть " + part.getClass().getSimpleName() + " установлена");
    }
}


