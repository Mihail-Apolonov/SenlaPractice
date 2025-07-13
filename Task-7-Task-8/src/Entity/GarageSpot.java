package Entity;

public class GarageSpot {
    private int id;

    public GarageSpot(int id) {
        this.id = id;
    }

    public GarageSpot(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Гаражное место #" + id;
    }
}
