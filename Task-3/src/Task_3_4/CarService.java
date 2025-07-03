package Task_3_4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarService {
    private List<Master> masters;
    private List<GarageSpot> garageSpots;
    private List<Order> orders;
    private int nextOrderId;

    public CarService() {
        this.masters = new ArrayList<>();
        this.garageSpots = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.nextOrderId = 1;
    }

    // Методы для работы с мастерами
    public void addMaster(Master master) {
        masters.add(master);
    }

    public boolean removeMaster(String name) {
        return masters.removeIf(master -> Objects.equals(master.getName(), name));
    }
    public Master getMasterByName(String name){
        for (var i: masters)
            if (Objects.equals(i.getName(), name))
                return i;
        return null;
    }

    public List<Master> getAllMasters() {
        return new ArrayList<>(masters);
    }

    // Методы для работы с местами в гараже
    public void addGarageSpot(GarageSpot spot) {
        garageSpots.add(spot);
    }

    public boolean removeGarageSpot(int spotNumber) {
        return garageSpots.removeIf(spot -> spot.getSpotNumber() == spotNumber);
    }
    public GarageSpot getGarageSpotById(Integer id){
        for (var i: garageSpots)
            if (i.getSpotNumber() == id)
                return i;
        return null;
    }

    public List<GarageSpot> getAllGarageSpots() {
        return new ArrayList<>(garageSpots);
    }

    // Методы для работы с заказами
    public Order addOrder(LocalDateTime startTime, LocalDateTime endTime,
                          Master master, GarageSpot garageSpot) {
        if (!master.getStatus()){
            Order order = new Order(nextOrderId++, startTime, endTime, master, garageSpot);
            System.out.println("Матер сейчас занят, переносим заказ на 2 часа");
            shiftOrderTime(order);
            orders.add(order);
            return order;
        }
        if (!garageSpot.getStatus()){
            Order order = new Order(nextOrderId++, startTime, endTime, master, garageSpot);
            System.out.println("Нет свободных мест в гараже, переносим заказ на 2 часа");
            shiftOrderTime(order);
            orders.add(order);
            return order;
        }
        Order order = new Order(nextOrderId++, startTime, endTime, master, garageSpot);
        orders.add(order);
        master.setStatus(false);
        garageSpot.setStatus(false);
        return order;
    }

    public boolean removeOrder(int orderId) {
        for (var i: orders)
            if (i.getId() == orderId){
                i.getMaster().setStatus(true);
                i.getGarageSpot().setStatus(true);
            }
        return orders.removeIf(order -> order.getId() == orderId);
    }

    public void shiftOrderTime(Order order) {
        order.setStartTime(order.getStartTime().plusHours(2));
        order.setEndTime(order.getEndTime().plusHours(2));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}
