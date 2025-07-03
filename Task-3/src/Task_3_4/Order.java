package Task_3_4;

import java.time.LocalDateTime;

public class Order {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final Master master;
    private final GarageSpot garageSpot;

    public Order(Integer id, LocalDateTime startTime, LocalDateTime endTime,
                 Master master, GarageSpot garageSpot) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.master = master;
        this.garageSpot = garageSpot;
    }

    public Integer getId(){return id;}
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Master getMaster() {
        return master;
    }

    public GarageSpot getGarageSpot() {
        return garageSpot;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", startTime=" + startTime.getHour() + ":" + startTime.getMinute() +
                ", endTime=" + endTime.getHour() + ":" + endTime.getMinute();
    }
}

