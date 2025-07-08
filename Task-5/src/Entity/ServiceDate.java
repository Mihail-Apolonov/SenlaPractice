package Entity;

import Entity.GarageSpot;
import Entity.Master;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceDate {
    private LocalDate date;
    private List<Master> busyMasters;
    private List<GarageSpot> busySpots;

    public ServiceDate(LocalDate date) {
        this.date = date;
        this.busyMasters = new ArrayList<>();
        this.busySpots = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isMasterAvailable(Master master) {
        return !busyMasters.contains(master);
    }

    public boolean isSpotAvailable(GarageSpot spot) {
        return !busySpots.contains(spot);
    }

    public void reserveMaster(Master master) {
        if (!busyMasters.contains(master)) {
            busyMasters.add(master);
        }
    }
    public void releaseMaster(Master master) {
        busyMasters.remove(master);
    }

    public void reserveSpot(GarageSpot spot) {
        if (!busySpots.contains(spot)) {
            busySpots.add(spot);
        }
    }
    public void releaseSpot(GarageSpot spot) {
        busySpots.remove(spot);
    }

    public List<Master> getAvailableMasters(List<Master> allMasters) {
        List<Master> available = new ArrayList<>(allMasters);
        available.removeAll(busyMasters);
        return available;
    }

    public List<GarageSpot> getAvailableSpots(List<GarageSpot> allSpots) {
        List<GarageSpot> available = new ArrayList<>(allSpots);
        available.removeAll(busySpots);
        return available;
    }

    public int countAvailableMasters(List<Master> allMasters) {
        return allMasters.size() - busyMasters.size();
    }

    public int countAvailableSpots(List<GarageSpot> allSpots) {
        return allSpots.size() - busySpots.size();
    }

    public List<Master> getBusyMasters() {
        return new ArrayList<>(busyMasters);
    }

    public List<GarageSpot> getBusySpots() {
        return new ArrayList<>(busySpots);
    }
}
