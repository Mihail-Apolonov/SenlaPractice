package Task_3_2;

public class Task_3_2 {
    public static void main(String[] args){

        //Создать иерархию цветов для цветочного магазина. Собрать букет и узнать стоимость.
        Bouquet bouquet = new Bouquet();
        Flower rose = new Rose(499);
        Flower orchid = new Orchid(699);
        Flower tulip = new Tulip(399);

        //Соберем букет например из 3 роз, 1 орхидеи и 2 тюльпанов
        bouquet.addFlower(rose);
        bouquet.addFlower(rose);
        bouquet.addFlower(rose);
        bouquet.addFlower(orchid);
        bouquet.addFlower(tulip);
        bouquet.addFlower(tulip);

        Integer finalPrice = 0;
        for (var i: bouquet.getBouquet())
            finalPrice += i.getPrice();

        System.out.println("Итоговая цена букета: " + finalPrice);


    }
}
