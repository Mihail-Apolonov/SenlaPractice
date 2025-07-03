package Task_3_2;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {

    List<Flower> bouquet;

    public Bouquet(){
        this.bouquet = new ArrayList<>();
    }

    public void addFlower(Flower flower){
        this.bouquet.add(flower);
        System.out.println("Добавлен цветок: " + flower.getClass().getSimpleName() +
                " Цена: " + flower.getPrice());
    }

    public List<Flower> getBouquet(){return this.bouquet;}
}
