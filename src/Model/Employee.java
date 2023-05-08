package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Employee implements Writable, Observer {
    private String name;
    private int genderBiological;
    private int hourlySalary;
    private int hours;
    private List<Availability> availability;
    private int totalSalary;
    private int weeklySalary;

    public Employee(String name, int hourlySalary, int genderBiological) {
        this.name = name;
        this.hourlySalary = hourlySalary;
        this.hours = 0;
        this.availability = new ArrayList<>();
        this.weeklySalary = 0;
        this.totalSalary = 0;
        this.genderBiological = genderBiological;
    }

    public void incHour() {
        this.hours++;
    }

    public void updateSalary() {
        this.weeklySalary = hours * hourlySalary;
    }

    public boolean shiftEnd(int time, int day) {
        for (Availability avail: availability) {
            if (avail.getDay() == day && avail.getStart() + avail.getHours() == time) {
                return true;
            }
        }
        return false;
    }

    public void addAvailability(Availability availability) {
        this.availability.add(availability);
    }

    public void removeAvailability(Availability availability) {
        this.availability.remove(availability);
    }

    public void weeklyReset() {
        this.hours = 0;
        this.totalSalary += weeklySalary;
        this.weeklySalary = 0;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(int weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public int getGenderBiological() {
        return genderBiological;
    }

    public void setGenderBiological(int genderBiological) {
        this.genderBiological = genderBiological;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return hourlySalary == employee.hourlySalary && hours == employee.hours && totalSalary == employee.totalSalary && weeklySalary == employee.weeklySalary && Objects.equals(name, employee.name) && Objects.equals(availability, employee.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hourlySalary, hours, availability, totalSalary, weeklySalary);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hourlySalary", hourlySalary);
        json.put("hours", hours);
        json.put("weeklySalary", weeklySalary);
        json.put("totalSalary", totalSalary);
        json.put("availability", availabilityToJson());
        json.put("genderBiological", genderBiological);
        return json;
    }

    private JSONArray availabilityToJson() {
        JSONArray avails = new JSONArray();
        for (Availability a: this.availability) {
            avails.put(a.toJson());
        }
        return avails;
    }

    @Override
    public void update(Observable o, Object arg) {
        int x = (int) arg;
        Store s = (Store) o;
        switch(x) {
            case 1:
                weeklyReset();
                break;
            case 2:
                updateSalary();
                break;
        }
    }
}
