package Entity;

public class Master {
    private int id;
    private String name;

    public Master(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Master(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}
