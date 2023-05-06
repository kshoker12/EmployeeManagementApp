package Persistence;

import Model.Availability;
import Model.Employee;
import Model.Store;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StoreReader {
    private String source;

    public StoreReader(String source) {
        this.source = source;
    }

    public Store read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStore(jsonObject);
    }

    private Store parseStore(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int open = jsonObject.getInt("open");
        int close = jsonObject.getInt("close");
        Store store = new Store(name, open, close);
        int currDay = jsonObject.getInt("currDay");
        int currHour = jsonObject.getInt("currHour");
        int weeklyEarnings = jsonObject.getInt("weeklyEarnings");
        int totalEarnings = jsonObject.getInt("totalEarnings");
        store.setCurrHour(currHour);
        store.setCurrDay(currDay);
        store.setWeeklyEarnings(weeklyEarnings);
        store.setTotalEarnings(totalEarnings);
        addEmployees(store, jsonObject);
        addActiveEmployees(store, jsonObject);
        return store;
    }

    private void addActiveEmployees(Store store, JSONObject jsonObject) {
        JSONArray active = jsonObject.getJSONArray("activeEmployees");
        for (Object o: active) {
            JSONObject obj = (JSONObject) o;
            String name = obj.getString("name");
            for (Employee e: store.getEmployeeList()) {
                if (e.getName() == name) {
                    store.addEmployeeToCurrShift(e);
                }
            }
        }
    }

    private void addEmployees(Store store, JSONObject jsonObject) {
        JSONArray employees = jsonObject.getJSONArray("employeeList");
        for (Object e: employees) {
            JSONObject je = (JSONObject) e;
            Employee employee = addEmployee(je);
            store.hireEmployee(employee);
        }
    }

    private Employee addEmployee(JSONObject je) {
        String name = je.getString("name");
        int hourlySalary = je.getInt("hourlySalary");
        int genderBiological = je.getInt("genderBiological");
        Employee employee = new Employee(name, hourlySalary, genderBiological);
        int hours = je.getInt("hours");
        int totalSalary = je.getInt("totalSalary");
        int weeklySalary = je.getInt("weeklySalary");
        employee.setHours(hours);
        employee.setWeeklySalary(weeklySalary);
        employee.setTotalSalary(totalSalary);
        addAvailabilities(employee, je);
        return employee;
    }

    private void addAvailabilities(Employee e, JSONObject je) {
        JSONArray avails = je.getJSONArray("availability");
        for (Object o: avails) {
            JSONObject avail = (JSONObject) o;
            Availability availability = addAvailability(avail);
            e.addAvailability(availability);
        }
    }

    private Availability addAvailability(JSONObject avail) {
        int day = avail.getInt("day");
        int hours = avail.getInt("hours");
        int start = avail.getInt("start");
        Availability availability = new Availability(day, hours, start);
        return availability;
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
