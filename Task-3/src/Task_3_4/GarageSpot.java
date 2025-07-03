package Task_3_4;

public class GarageSpot {
    private final int spotNumber;
    private Boolean status;

    public GarageSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.status = true;
    }

    // Геттеры и сеттеры
    public int getSpotNumber() {
        return spotNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "spotNumber=" + spotNumber +
                ", status=" + status;
    }
}
