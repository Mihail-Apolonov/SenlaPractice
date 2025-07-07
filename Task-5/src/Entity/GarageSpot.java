package Entity;

public class GarageSpot {
    private int id;

    public GarageSpot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Гаражное место #" + id;
    }
}
