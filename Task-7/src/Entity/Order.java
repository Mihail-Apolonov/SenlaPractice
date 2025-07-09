package Entity;

import java.time.LocalDate;

public class Order {
    private int id;
    private LocalDate submissionDate;
    private LocalDate plannedDate;
    private LocalDate completionDate;
    private double price;
    private String status; // "active", "completed", "canceled", "deleted"
    private Master assignedMaster;
    private GarageSpot assignedSpot;

    public Order(int id, LocalDate submissionDate,
                 LocalDate plannedDate, double price) {
        this.id = id;
        this.submissionDate = submissionDate;
        this.plannedDate = plannedDate;
        this.price = price;
        this.status = "active";
    }

    public Order(){}

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Master getAssignedMaster() {
        return assignedMaster;
    }

    public GarageSpot getAssignedSpot() {
        return assignedSpot;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignedMaster(Master assignedMaster) {
        this.assignedMaster = assignedMaster;
    }

    public void setAssignedSpot(GarageSpot assignedSpot) {
        this.assignedSpot = assignedSpot;
    }

    public void setPlannedDate(LocalDate plannedDate) {
        this.plannedDate = plannedDate;
    }


    @Override
    public String toString() {
        return "(ID: " + id + ")" +
                ", создано " + submissionDate +
                ", нч " + plannedDate +
                ", кц " + completionDate +
                ", цена " + price +
                ", статус '" + status + '\'' +
                ", мастер " + assignedMaster +
                ", место " + assignedSpot;
    }
}
