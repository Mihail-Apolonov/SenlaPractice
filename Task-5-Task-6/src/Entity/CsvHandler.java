package Entity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CsvHandler {
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";
    private static final String HEADER_ORDER = "ID,SubmissionDate,PlannedDate,CompletionDate,Price,Status,MasterID,SpotID";
    private static final String HEADER_MASTER = "ID,Name";
    private static final String HEADER_SPOT = "ID";
    private static final String HEADER_SERVICE_DATE = "Date,BusyMasters,BusySpots";

    // Метод для экспорта заказов
    public static void exportOrdersToCSV(List<Order> orders, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8))) {

            writer.write(HEADER_ORDER);
            writer.write(NEW_LINE);

            for (Order order : orders) {
                String line = String.join(DELIMITER,
                        String.valueOf(order.getId()),
                        order.getSubmissionDate().toString(),
                        order.getPlannedDate().toString(),
                        order.getCompletionDate() != null ? order.getCompletionDate().toString() : null,
                        String.valueOf(order.getPrice()),
                        order.getStatus(),
                        order.getAssignedMaster() != null ? String.valueOf(order.getAssignedMaster().getId()) : null,
                        order.getAssignedSpot() != null ? String.valueOf(order.getAssignedSpot().getId()) : null
                );
                writer.write(line);
                writer.write(NEW_LINE);
            }
        }
    }

    // Метод для импорта заказов
    public static List<Order> importOrdersFromCSV(String filePath, List<Master> masters, List<GarageSpot> spots) throws IOException {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            // Пропускаем заголовок
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);

                int id = Integer.parseInt(values[0]);
                LocalDate submissionDate = LocalDate.parse(values[1]);
                LocalDate plannedDate = LocalDate.parse(values[2]);
                LocalDate completionDate = Objects.equals(values[3], "null") ? null : LocalDate.parse(values[3]);
                double price = Double.parseDouble(values[4]);
                String status = values[5];
                int masterId = Objects.equals(values[6], "null") ? -1 : Integer.parseInt(values[6]);
                int spotId = Objects.equals(values[7], "null") ? -1 : Integer.parseInt(values[7]);

                Order order = new Order(id, submissionDate, plannedDate, price);
                order.setStatus(status);
                order.setCompletionDate(completionDate);

                if (masterId != -1) {
                    order.setAssignedMaster(findMasterById(masters, masterId));
                }
                if (spotId != -1) {
                    order.setAssignedSpot(findSpotById(spots, spotId));
                }

                orders.add(order);
            }
        }
        return orders;
    }

    // Методы для мастеров
    public static void exportMastersToCSV(List<Master> masters, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8))) {

            writer.write(HEADER_MASTER);
            writer.write(NEW_LINE);

            for (Master master : masters) {
                String line = String.join(DELIMITER,
                        String.valueOf(master.getId()),
                        master.getName()
                );
                writer.write(line);
                writer.write(NEW_LINE);
            }
        } catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    public static List<Master> importMastersFromCSV(String filePath) throws IOException {
        List<Master> masters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            reader.readLine(); // Пропускаем заголовок

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                masters.add(new Master(
                        Integer.parseInt(values[0]),
                        values[1]
                ));
            }
        }
        return masters;
    }

    // Методы для гаражных мест
    public static void exportSpotsToCSV(List<GarageSpot> spots, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8))) {

            writer.write(HEADER_SPOT);
            writer.write(NEW_LINE);

            for (GarageSpot spot : spots) {
                writer.write(String.valueOf(spot.getId()));
                writer.write(NEW_LINE);
            }
        }
    }

    public static List<GarageSpot> importSpotsFromCSV(String filePath) throws IOException {
        List<GarageSpot> spots = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            reader.readLine(); // Пропускаем заголовок

            String line;
            while ((line = reader.readLine()) != null) {
                spots.add(new GarageSpot(Integer.parseInt(line)));
            }
        }
        return spots;
    }

    // Методы для рабочих дат (ServiceDate)
    public static void exportServiceDatesToCSV(Map<LocalDate, ServiceDate> serviceDates, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8))) {

            writer.write(HEADER_SERVICE_DATE);
            writer.write(NEW_LINE);

            for (ServiceDate serviceDate : serviceDates.values()) {
                String busyMasters = serviceDate.getBusyMasters().stream()
                        .map(m -> String.valueOf(m.getId()))
                        .collect(Collectors.joining(";"));

                String busySpots = serviceDate.getBusySpots().stream()
                        .map(s -> String.valueOf(s.getId()))
                        .collect(Collectors.joining(";"));

                String line = String.join(DELIMITER,
                        serviceDate.getDate().toString(),
                        busyMasters,
                        busySpots
                );
                writer.write(line);
                writer.write(NEW_LINE);
            }
        }
    }

    public static Map<LocalDate, ServiceDate> importServiceDatesFromCSV(
            String filePath, List<Master> masters, List<GarageSpot> spots) throws IOException {
        Map<LocalDate, ServiceDate> serviceDates = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            reader.readLine(); // Пропускаем заголовок

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                LocalDate date = LocalDate.parse(values[0]);
                ServiceDate serviceDate = new ServiceDate(date);

                // Добавляем занятых мастеров
                if (!values[1].isEmpty()) {
                    for (String masterId : values[1].split(";")) {
                        Master master = findMasterById(masters, Integer.parseInt(masterId));
                        if (master != null) {
                            serviceDate.reserveMaster(master);
                        }
                    }
                }

                // Добавляем занятые места
                if (!values[2].isEmpty()) {
                    for (String spotId : values[2].split(";")) {
                        GarageSpot spot = findSpotById(spots, Integer.parseInt(spotId));
                        if (spot != null) {
                            serviceDate.reserveSpot(spot);
                        }
                    }
                }

                serviceDates.put(date, serviceDate);
            }
        }
        return serviceDates;
    }

    // Вспомогательные методы
    private static Master findMasterById(List<Master> masters, int id) {
        return masters.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private static GarageSpot findSpotById(List<GarageSpot> spots, int id) {
        return spots.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}