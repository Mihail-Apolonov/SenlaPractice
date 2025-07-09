package Entity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

public class AutoServiceAdmin {
    private List<GarageSpot> garageSpots;
    private List<Master> masters;
    private List<Order> orders;
    private Map<LocalDate, ServiceDate> serviceDates;
    private int nextOrderId = 1;
    private static AutoServiceAdmin instance;

    public AutoServiceAdmin() {
        this.garageSpots = new ArrayList<>();
        this.masters = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.serviceDates = new HashMap<>();
    }

    public static synchronized AutoServiceAdmin getInstance() {
        if (instance == null) {
            instance = new AutoServiceAdmin();
        }
        return instance;
    }

    // Методы для работы с гаражами
    public void addGarageSpot(int id) {
        garageSpots.add(new GarageSpot(id));
    }

    public void removeGarageSpot(int id) {
        garageSpots.removeIf(spot -> spot.getId() == id);
        // Освобождаем это место во всех датах
        serviceDates.values().forEach(date ->
                date.getAvailableSpots(garageSpots).removeIf(spot -> spot.getId() == id));
    }

    public List<GarageSpot> getAllGarageSpots() {
        return new ArrayList<>(garageSpots);
    }

    public List<GarageSpot> getFreeGarageSpot(LocalDate date){
        return serviceDates.get(date).getAvailableSpots(garageSpots);
    }

    // Методы для работы с мастерами
    public void addMaster(int id, String name) {
        masters.add(new Master(id, name));
    }

    public void removeMaster(int id) {
        masters.removeIf(master -> master.getId() == id);
        // Освобождаем этого мастера во всех датах
        serviceDates.values().forEach(date ->
                date.getAvailableMasters(masters).removeIf(master -> master.getId() == id));
    }

    public int getLastAddedOrderId() {
        if (orders.isEmpty()) {
            return -1;
        }
        return orders.get(orders.size() - 1).getId();
    }

    public List<Master> getAllMasters() {
        return new ArrayList<>(masters);
    }

    public List<Master> getMastersAlphabetically() {
        return masters.stream()
                .sorted(Comparator.comparing(Master::getName))
                .collect(Collectors.toList());
    }

    // Методы для работы с датами
    private ServiceDate getOrCreateServiceDate(LocalDate date) {
        return serviceDates.computeIfAbsent(date, ServiceDate::new);
    }

    // Методы для работы с заказами
    public void addOrder(LocalDate plannedDate, double price) {
        Order order = new Order(nextOrderId++, LocalDate.now(), plannedDate, price);
        orders.add(order);
    }

    public Boolean assignResourcesToOrder(int orderId, int masterId, int spotId) {
        Order order = findOrderById(orderId);
        if (order == null || !"active".equals(order.getStatus())) return false;

        Master master = findMasterById(masterId);
        GarageSpot spot = findSpotById(spotId);
        if (master == null || spot == null) return false;

        ServiceDate serviceDate = getOrCreateServiceDate(order.getPlannedDate());

        if (!serviceDate.isMasterAvailable(master) || !serviceDate.isSpotAvailable(spot)) {
            cancelOrder(orderId);
            return false;
        }

        serviceDate.reserveMaster(master);
        serviceDate.reserveSpot(spot);

        order.setAssignedMaster(master);
        order.setAssignedSpot(spot);
        return true;
    }

    public void cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null && "active".equals(order.getStatus())) {
            order.setStatus("canceled");
            releaseOrderResources(order);
        }
    }

    public void deleteOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus("deleted");
            releaseOrderResources(order);
        }
    }

    public void completeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null && "active".equals(order.getStatus())) {
            order.setStatus("completed");
            order.setCompletionDate(LocalDate.now());
            releaseOrderResources(order);
        }
    }

    public boolean rescheduleOrder(int orderId, LocalDate newDate) {
        Order order = findOrderById(orderId);
        if (order == null || !"active".equals(order.getStatus())) return false;

        Master master = order.getAssignedMaster();
        GarageSpot spot = order.getAssignedSpot();

        // Освобождаем ресурсы на старую дату
        releaseOrderResources(order);

        // Проверяем доступность на новую дату
        ServiceDate newServiceDate = getOrCreateServiceDate(newDate);
        if ((master == null || newServiceDate.isMasterAvailable(master)) &&
                (spot == null || newServiceDate.isSpotAvailable(spot))) {

            order.setPlannedDate(newDate);

            // Резервируем ресурсы на новую дату
            if (master != null) newServiceDate.reserveMaster(master);
            if (spot != null) newServiceDate.reserveSpot(spot);

            return true;
        }
        return false;
    }

    private void releaseOrderResources(Order order) {
        if (order.getAssignedMaster() != null && order.getPlannedDate() != null) {
            ServiceDate serviceDate = serviceDates.get(order.getPlannedDate());
            if (serviceDate != null) {
                serviceDate.releaseMaster(order.getAssignedMaster());
            }
        }
        if (order.getAssignedSpot() != null && order.getPlannedDate() != null) {
            ServiceDate serviceDate = serviceDates.get(order.getPlannedDate());
            if (serviceDate != null) {
                serviceDate.releaseSpot(order.getAssignedSpot());
            }
        }
    }

    // Методы для получения информации
    public List<Order> getOrdersSortedBy(Comparator<Order> comparator) {
        return orders.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Order> getActiveOrders(Comparator<Order> comparator) {
        return orders.stream()
                .filter(order -> "active".equals(order.getStatus()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Order findOrderByMaster(int masterId) {
        return orders.stream()
                .filter(order -> order.getAssignedMaster() != null &&
                        order.getAssignedMaster().getId() == masterId &&
                        "active".equals(order.getStatus()))
                .findFirst()
                .orElse(null);
    }

    public Master findMasterByOrder(int orderId) {
        return orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .map(Order::getAssignedMaster)
                .orElse(null);
    }

    public List<Order> getOrdersByStatusAndPeriod(String status, LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(order -> status.equals(order.getStatus()) &&
                        !order.getSubmissionDate().isBefore(startDate) &&
                        !order.getSubmissionDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    public int countAvailableSpotsForDate(LocalDate date) {
        ServiceDate serviceDate = getOrCreateServiceDate(date);
        return serviceDate.countAvailableSpots(garageSpots);
    }

    public int countAvailableMastersForDate(LocalDate date) {
        ServiceDate serviceDate = getOrCreateServiceDate(date);
        return serviceDate.countAvailableMasters(masters);
    }

    public int countAvailableMastersAndSpots(LocalDate date){
        int masters = countAvailableMastersForDate(date);
        int spots = countAvailableSpotsForDate(date);
        return min(masters, spots);
    }

    public LocalDate findNearestAvailableDate() {
        LocalDate date = LocalDate.now();
        while (true) {
            ServiceDate serviceDate = getOrCreateServiceDate(date);
            if (serviceDate.countAvailableSpots(garageSpots) > 0 &&
                    serviceDate.countAvailableMasters(masters) > 0) {
                return date;
            }
            date = date.plusDays(1);
        }
    }

    // Вспомогательные методы
    public Order findOrderById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Master findMasterById(int id) {
        return masters.stream()
                .filter(master -> master.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public GarageSpot findSpotById(int id) {
        return garageSpots.stream()
                .filter(spot -> spot.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Методы для экспорта
    public void exportAllData(String basePath) throws IOException {
        try {
            CsvHandler.exportOrdersToCSV(orders, basePath + "/orders.csv");
            CsvHandler.exportMastersToCSV(masters, basePath + "/masters.csv");
            CsvHandler.exportSpotsToCSV(garageSpots, basePath + "/spots.csv");
            CsvHandler.exportServiceDatesToCSV(serviceDates, basePath + "/service_dates.csv");
        } catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    // Методы для импорта
    public void importAllData(String basePath) throws IOException {
        try {
            this.masters = CsvHandler.importMastersFromCSV(basePath + "/masters.csv");
            this.garageSpots = CsvHandler.importSpotsFromCSV(basePath + "/spots.csv");
            this.orders = CsvHandler.importOrdersFromCSV(basePath + "/orders.csv", masters, garageSpots);
            this.serviceDates = CsvHandler.importServiceDatesFromCSV(basePath + "/service_dates.csv", masters, garageSpots);
        } catch (IOException e) {
            System.out.println(e.getCause());
        }

        // Обновляем nextOrderId
        if (!orders.isEmpty()) {
            nextOrderId = orders.stream()
                    .mapToInt(Order::getId)
                    .max()
                    .getAsInt() + 1;
        } else {
            nextOrderId = 1;
        }
    }
}

